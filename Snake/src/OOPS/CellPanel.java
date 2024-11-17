package OOPS;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
/**
 * Classe CellPanel estende JPanel e é usada para representar uma célula individual em uma interface gráfica,
 *
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 *
 * @inv A cor nunca é nula.
 * @inv O valor de 'isCircular' determina a forma da célula desenhada, true para circular e false para retangular.
 */
public class CellPanel extends JPanel {
    /**
     * Definição da estrutura do objeto CellPanel
     */
    private boolean isCircular = false;
    private Color color = Color.WHITE;

    /**
     * Construtor por omissão para CellPanel.
     * Este construtor cria uma instância padrão do CellPanel com bordas pretas.
     */
    public CellPanel() {
        super();  // Chama o construtor da superclasse JPanel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Define uma borda preta ao redor do painel
    }
    /**
     * Definição do construtor de cópia da classe CellPanel
     */
    public CellPanel(CellPanel cellPanel)
    {
        setCircular(cellPanel.getIsCircular());
        setColor(cellPanel.getColor());
    }
    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto CellPanel
     */
    public boolean getIsCircular()
    {
        return this.isCircular;
    }
    public Color getColor()
    {
        return this.color;
    }
    /**
     * Estes métodos têm o principal objetivo de modificar a estrutura do objeto CellPanel
     */
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null.");
        }
        this.color = color;
        this.isCircular = false;
        repaint();
    }

    public void setCircular(boolean circular) {
        this.isCircular = circular;
        repaint();
    }

    /**
     * Método sobrescrito de JPanel utilizado para desenhar o componente CellPanel.
     * @param g o contexto gráfico que fornece métodos de desenho.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        if (isCircular) {
            int diameter = Math.min(getWidth(), getHeight());
            g.fillOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
        } else {
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promovo assim o Principio do encapsulamento
     */
    @Override
    public CellPanel clone() {
        return new CellPanel(this);
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
        CellPanel that = (CellPanel) obj;
        return this.isCircular == that.isCircular && Objects.equals(color, that.color);
    }

    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto CellPanel, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        return String.format("CellPanel{color=%s, isCircular=%s}", color, isCircular);
    }
}
