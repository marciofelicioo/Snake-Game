package OOPS_UI;
import Exceptions.*;
import Food.Circulo;
import OOPS.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import static java.lang.System.out;

/**
 * Classe responsável pela interface gráfica do jogo Snake.
 * Gerencia todos os aspectos visuais e interações do usuário para o jogo Snake,
 * utilizando uma instância da classe Arena para manter o estado do jogo.
 *
 * @version 1.0 22/04/2024
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * <p>
 * (Invariantes)Esta classe garante que:
 * 1. A instância da Arena sempre permaneça válida e não nula após a inicialização.
 * 2. Todos os componentes de interface são inicializados antes do uso.
 * 3. O timer para atualização do jogo está sempre ativo durante o jogo.
 * 4. O grid visual está sempre sincronizado com a Arena.
 * 5. Alterações no modo de jogo são controladas e refletidas na interface.
 * 6. Ações do usuário são validadas de acordo com o estado do jogo.
 * 7. A pontuação exibida é sempre não negativa.
 * 8. A visibilidade da grade é controlável e reflete o estado selecionado.
 * 9. A representação textual do estado do jogo é sempre acessível.
 *
 */
public class Game extends JFrame implements ActionListener {
    /**
     * Definição da estrutura da classe Game
     */
    private Arena arena;
    private JPanel[][] cellPanels;
    private Timer timer;
    private JLabel scoreLabel;
    private boolean showGrid = false;
    private JTextArea textualDisplay;
    private JPanel gridPanel;
    private JScrollPane scrollPane;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JPanel scorePanel;
    private JButton toggleModeButton;

    /**
     * Constrói a interface do jogo, configurando componentes e layout.
     *
     * @param arena A arena do jogo que contém todos os elementos e lógica do jogo.
     * @throws ValorNegativoException Se parâmetros inválidos forem passados para a arena.
     * @throws TrianguloException Se ocorrer um erro ao criar formas triangulares no jogo.
     * @throws SegmentoException Se ocorrer um erro ao criar segmentos no jogo.
     * @throws RetanguloException Se ocorrer um erro ao criar retângulos no jogo.
     * @throws ColinearityIntersectionNumberPointsException Se ocorrer um erro de colinearidade ou interseção.
     * @throws QuadradoException Se ocorrer um erro ao criar quadrados no jogo.
     * @throws RetaException Se ocorrer um erro ao criar retas no jogo.
     */

    public Game(Arena arena) {
        this.arena = arena;
        setTitle("Snake Game Version X - Universidade do Algarve April,2024");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        leftLabel = new JLabel("Press key B to show textual representation in the terminal");
        scoreLabel = new JLabel("Score: " + arena.getScore(), JLabel.CENTER);
        rightLabel = new JLabel("Press key Z to show/hide grid");

        scorePanel.add(leftLabel);
        scorePanel.add(scoreLabel);
        scorePanel.add(rightLabel);

        toggleModeButton = new JButton("Auto mode");
        toggleModeButton.addActionListener(e -> toggleMode());
        scorePanel.add(toggleModeButton);

        add(scorePanel, BorderLayout.NORTH);

        gridPanel = new JPanel(new GridLayout(arena.getHeight(), arena.getWidth()));
        cellPanels = new CellPanel[arena.getHeight()][arena.getWidth()];
        for (int i = 0; i < arena.getHeight(); i++) {
            for (int j = 0; j < arena.getWidth(); j++) {
                CellPanel cellPanel = new CellPanel();
                cellPanel.setColor(getCellColor(arena.getCell(i, j).getContent()));
                if (arena.getCell(i, j).getContent() == CellContent.FOOD && arena.getFood() instanceof Circulo) {
                    cellPanel.setCircular(true);
                }
                gridPanel.add(cellPanel);
                cellPanels[i][j] = cellPanel;
            }
        }

        scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        textualDisplay = new JTextArea();
        textualDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textualDisplay.setEditable(false);

        timer = new Timer(200, this);
        timer.start();

        setupKeyBindings();
    }

    /**
     * Atualiza a pontuação no JLabel dedicado.
     */
    public void updateScore() {
        scoreLabel.setText("Score: " + arena.getScore());
    }
    /**
     * Alterna entre o modo automático e manual, atualizando o texto do botão.
     */
    private void toggleMode() {
        try {
            arena.toggleAutoMode();
            if (arena.getAutoMode()) {
                toggleModeButton.setText("Manual Mode");
            } else {
                toggleModeButton.setText("Auto Mode");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error toggling mode: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Configura os bindings de teclas para controle do jogo.
     */
    private void setupKeyBindings() {
        bindKey("UP", KeyEvent.VK_UP, Direction.UP);
        bindKey("DOWN", KeyEvent.VK_DOWN, Direction.DOWN);
        bindKey("LEFT", KeyEvent.VK_LEFT, Direction.LEFT);
        bindKey("RIGHT", KeyEvent.VK_RIGHT, Direction.RIGHT);


        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "TOGGLE_GRID");
        getRootPane().getActionMap().put("TOGGLE_GRID", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGrid = !showGrid;
                updateVisualGrid();
            }
        });


        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "PRINT_TEXTUAL_REPRESENTATION");
        getRootPane().getActionMap().put("PRINT_TEXTUAL_REPRESENTATION", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTextualRepresentation();
            }
        });


        toggleModeButton.setFocusable(false);
    }


    private void bindKey(String name, int keyCode, Direction direction) {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0), name);
        getRootPane().getActionMap().put(name, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arena.getSnake().setDirection(direction);
            }
        });
    }
    /**
     * Responde aos eventos do timer, controlando o fluxo principal do jogo.
     *
     * @param e O evento de ação gerado pelo timer.
     */
    public void actionPerformed(ActionEvent e) {
        try {
            boolean movedSuccessfully;
            if (arena.getAutoMode()) {
                movedSuccessfully = arena.autoMove();
            } else {
                movedSuccessfully = arena.moveSnake(null);
            }
            if (!movedSuccessfully) {
                gameOver(false);
            } else {
                updateVisualGrid();
                updateScore();
                if (arena.getScore() == Integer.MAX_VALUE) {
                    gameOver(true);
                }
            }
        } catch (ValorNegativoException | ColinearityIntersectionNumberPointsException | SegmentoException
                 | RetaException | RetanguloException | QuadradoException | TrianguloException | MovementException i) {
            out.println(i.getMessage());
            try {
                restartGame();
            } catch (ValorNegativoException | ColinearityIntersectionNumberPointsException | SegmentoException
                     | RetaException | RetanguloException | QuadradoException | TrianguloException | MovementException u) {
                out.println(i.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }
    }
    /**
     * Exibe a mensagem de fim de jogo e oferece opção para reiniciar.
     *
     * @param gameWon Indica se o jogo foi ganho ou não.
     * @throws Exception Se ocorrer um erro ao reiniciar o jogo.
     */
    public void gameOver(boolean gameWon) throws Exception {
        String message;
        if (gameWon) {
            message = "Congratulations! You've won the game with the maximum score! \nWould you like to restart?";
        } else {
            message = "Game Over! Your score: " + arena.getScore() + "\nWould you like to restart?";
        }

        int response = JOptionPane.showConfirmDialog(this, message, gameWon ? "Game Won" : "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }
    /**
     * Atualiza a grade visual com base no estado atual da arena.
     */
    private void updateVisualGrid() {
        for (int i = 0; i < arena.getHeight(); i++) {
            for (int j = 0; j < arena.getWidth(); j++) {
                CellPanel cellPanel = (CellPanel) cellPanels[i][j];
                CellContent content = arena.getCell(i, j).getContent();
                Color color = getCellColor(content);
                cellPanel.setColor(color);


                if (content == CellContent.SNAKE_HEAD || content == CellContent.SNAKE_BODY || content == CellContent.OBSTACLE) {
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else {
                    cellPanel.setBorder(BorderFactory.createEmptyBorder());
                }


                if (content == CellContent.FOOD && arena.getFood() instanceof Circulo) {
                    cellPanel.setBackground(new Color(0,0,0));
                    cellPanel.setCircular(true);
                } else {
                    cellPanel.setCircular(false);
                }
                if (showGrid) {
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            }
        }
        repaint();
    }
    /**
     * Obtém a cor correspondente ao conteúdo de uma célula.
     *
     * @param content O tipo de conteúdo da célula.
     * @return A cor correspondente ao tipo de conteúdo.
     */
    private Color getCellColor(CellContent content) {
        switch (content) {
            case SNAKE_HEAD:
                return Color.RED;
            case SNAKE_BODY:
                return new Color(144, 238, 144);
            case FOOD:
                return Color.ORANGE;
            case OBSTACLE:
                return Color.MAGENTA;
            case EMPTY:
                return Color.BLACK;
        }
        return Color.BLACK;
    }
    /**
     * Reinicia o jogo após o fim de uma partida.
     *
     * @throws Exception Se ocorrer um erro ao reiniciar o jogo.
     */
    public void restartGame() throws Exception {

        arena = new Arena(30, 30);


        if (arena.getAutoMode()) {
            arena.prepareAutoMove();
        }



        getContentPane().removeAll();


        scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        scoreLabel = new JLabel("Score: " + arena.getScore(), JLabel.CENTER);
        scorePanel.add(leftLabel);
        scorePanel.add(scoreLabel);
        scorePanel.add(rightLabel);


        toggleModeButton = new JButton(arena.getAutoMode() ? "Manual Mode" : "Auto Mode");
        toggleModeButton.addActionListener(e -> toggleMode());
        scorePanel.add(toggleModeButton);


        getContentPane().add(scorePanel, BorderLayout.NORTH);


        gridPanel = new JPanel(new GridLayout(arena.getHeight(), arena.getWidth()));
        cellPanels = new CellPanel[arena.getHeight()][arena.getWidth()];
        for (int i = 0; i < arena.getHeight(); i++) {
            for (int j = 0; j < arena.getWidth(); j++) {
                CellPanel cellPanel = new CellPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellPanel.setColor(getCellColor(arena.getCell(i, j).getContent()));
                gridPanel.add(cellPanel);
                cellPanels[i][j] = cellPanel;
            }
        }


        scrollPane = new JScrollPane(gridPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);


        validate();
        repaint();
        timer.restart();
    }
    /**
     * Imprime a representação textual do estado atual da arena.
     */
    private void printTextualRepresentation() {
        if (arena != null) {
            System.out.println(arena);
        }
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
    /**
     * @param obj que serve para verificar se o objeto passado como parametro é ou não igual ao objeto recetor
     * @return true ou false caso seja ou não seja igual
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Game game = (Game) obj;
        return (this.arena != null ? this.arena.equals(game.arena) : game.arena == null);
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Game, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        return "Game{" +
                "arenaScore=" + (this.arena != null ? this.arena.getScore() : "No arena") +
                ", autoMode=" + (this.arena != null ? this.arena.getAutoMode() : false) +
                '}';
    }

}