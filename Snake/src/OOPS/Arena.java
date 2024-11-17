package OOPS;

import Exceptions.*;
import Figuras_Geometricas.*;
import Food.*;
import OOPS.Automatic.Automatic;
import Obstaculos.Obstaculo;
import Obstaculos.ObstaculoDinamico;
import Obstaculos.ObstaculoEstatico;
import java.util.*;

/**
 * Classe Arena: Representa a área de jogo onde a cobra se movimenta.
 * Gerencia todos os aspectos do ambiente de jogo, como a cobra, alimentos, obstáculos e o campo de jogo.
 * Fornece métodos para iniciar e controlar o movimento da cobra, colocação de alimentos e obstáculos, e verificação de colisões.
 *
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 * <p>
 * Invariantes de Classe:
 * 1. As dimensões da arena (width e height) devem sempre ser positivas após a inicialização.
 * 2. A grid deve sempre ser inicializado e corresponder às dimensões de width e height da arena.
 * 3. O objeto snake deve ser inicializado antes do uso e não deve ser nulo após a configuração inicial.
 * 4. Em autoMode, deve haver um caminho definido se houver alimentos disponíveis na arena.
 */
public class Arena implements Automatic {
    /**
     * Definição da estrutura do objeto Arena
     */
    private List<Food> foods;
    private final int width;
    private final int height;
    private Cell[][] grid;
    private Snake snake;
    private int score;
    private List<Obstaculo> obstaculos;
    private boolean isAutoMode;
    private List<Direction> autoMovePath;
    private Iterator<Direction> pathIterator;

    /**
     * Definição do construtor por omissão da classe Arena
     */
    public Arena()
    {
        this.foods = null;
        this.width = 0;
        this.height = 0;
        this.grid = null;
        this.snake = null;
        this.score = 0;
        this.obstaculos = null;
    }
    /**
     * Definição do construtor de inicialização da classe Arena
     */
    public Arena(int width, int height) throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException, DimensionException {
        if (width <= 0 || height <= 0) {
            throw new DimensionException("Width and height must be greater than zero.");
        }
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        this.foods = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        initializeGrid();
        initializeSnake(width / 2, height / 2, 1);
        initializeObstacles();
        placeNewFood();
    }
    /**
     * Definição do construtor de cópia da classe Arena
     */
    public Arena(Arena arena)
    {
        this.foods = arena.getFoods();
        this.width = arena.getWidth();
        this.height = arena.getHeight();
        this.grid = arena.getGrid();
        this.snake = arena.getSnake();
        this.score = arena.getScore();
        this.obstaculos = arena.getObstaculos();
    }
    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto Arena
     */

    private Direction getDirectionFromNodes(Node from, Node to) {
        if (from.getX() == to.getX() && from.getY() == to.getY() + 1) return Direction.UP;
        if (from.getX() == to.getX() && from.getY() == to.getY() - 1) return Direction.DOWN;
        if (from.getX() == to.getX() + 1 && from.getY() == to.getY()) return Direction.LEFT;
        if (from.getX() == to.getX() - 1 && from.getY() == to.getY()) return Direction.RIGHT;
        return null;
    }
    public boolean getAutoMode()
    {
        return this.isAutoMode;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }
    public int getScore() {
        return this.score;
    }
    public Snake getSnake()
    {
        return this.snake;
    }
    public Food getFood() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (isCellOccupiedByFood(x, y, this.foods)) {
                    for (Food food : this.foods) {
                        if ((int)food.getLocation().getX() == x && (int)food.getLocation().getY() == y) {
                            return food;
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<Food> getFoods()
    {
        return this.foods;
    }

    public Cell[][] getGrid() {
        int height = this.grid.length;
        int width = this.grid[0].length;

        Cell[][] gridCopy = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gridCopy[i][j] = new Cell(this.grid[i][j]);
            }
        }
        return gridCopy;
    }

    public List<Obstaculo> getObstaculos()
    {
        return this.obstaculos;
    }
    /**
     * Atualiza toda o grid de jogo, responsável por sincronizar o estado atual da arena.
     *
     * @throws TrianguloException Se ocorrer um erro ao manipular triângulos.
     * @throws ValorNegativoException Se ocorrer um erro com valores negativos inesperados.
     * @throws SegmentoException Se ocorrer um erro ao manipular segmentos.
     * @throws RetanguloException Se ocorrer um erro ao manipular retângulos.
     * @throws ColinearityIntersectionNumberPointsException Se ocorrer um erro de colinearidade ou interseção.
     * @throws QuadradoException Se ocorrer um erro ao manipular quadrados.
     * @throws RetaException Se ocorrer um erro ao manipular retas.
     * */
    public void updateGrid() throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        cleanGrid();
        updateSnakeOnGrid();
        updateFoodOnGrid();
        updateObstaculosOnGrid();
    }
    /**
     * Método para atualizar a posição dos obstaculos na grid após movimento.
     */
    private void updateObstaculosOnGrid() throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        for (Obstaculo obstaculo : this.obstaculos) {
            obstaculo.atualizar(); // Atualiza se for um obstáculo dinâmico
            for (Ponto p : obstaculo.getForma().getPoligonoPontos()) {
                double x =  p.getX();
                double y =  p.getY();
                if (isWithinBounds(x, y)) {
                    this.grid[(int)y][(int)x].setContent(CellContent.OBSTACLE);
                }
            }
        }
    }
    /**
     * Limpa o grid removendo qualquer conteúdo anterior, definindo todas as células como vazias.
     * Este método é essencial antes de qualquer atualização do grid para garantir que não existam sobreposições ou dados residuais.
     *
     * @throws TrianguloException Se ocorrer um erro ao manipular triângulos.
     * @throws ValorNegativoException Se ocorrer um erro com valores negativos inesperados.
     * @throws SegmentoException Se ocorrer um erro ao manipular segmentos.
     * @throws RetanguloException Se ocorrer um erro ao manipular retângulos.
     * @throws ColinearityIntersectionNumberPointsException Se ocorrer um erro de colinearidade ou interseção.
     * @throws QuadradoException Se ocorrer um erro ao manipular quadrados.
     * @throws RetaException Se ocorrer um erro ao manipular retas.
     */
    private void cleanGrid() throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j].setContent(CellContent.EMPTY);
            }
        }
    }
    /**
     * Método para atualizar a posição da cobra na grid após movimento.
     */
    private void updateSnakeOnGrid() throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        List<Ponto> pontosSnake = this.snake.getPontos(); // Obter todos os pontos que compõem a snake
        for (int i = 0; i < pontosSnake.size(); i++) {
            Ponto pt = pontosSnake.get(i);
            for (int dx = 0; dx < this.snake.getPontosSize(); dx++) {
                for (int dy = 0; dy < this.snake.getPontosSize(); dy++) {
                    double x = pt.getX() + dx;
                    double y = pt.getY() + dy;
                    if (isWithinBounds(x, y)) {
                        if (i == 0) {
                            this.grid[(int)y][(int)x].setContent(CellContent.SNAKE_HEAD); // Cabeça da snake
                        } else {
                            this.grid[(int)y][(int)x].setContent(CellContent.SNAKE_BODY); // Corpo da snake
                        }
                    }
                }
            }
        }
    }
    /**
     * Método para atualizar a posição da comida na grid após movimento.
     */
    private void updateFoodOnGrid() throws ValorNegativoException {
        for (Food food : this.foods) {
            int foodSize = food.getSize();
            int startX = (int) food.getLocation().getX();
            int startY = (int) food.getLocation().getY();

            for (int dx = 0; dx < foodSize; dx++) {
                for (int dy = 0; dy < foodSize; dy++) {
                    int x = startX + dx;
                    int y = startY + dy;
                    if (isWithinBounds(x, y)) {
                        this.grid[y][x].setContent(CellContent.FOOD);
                    }
                }
            }
        }
    }

    /**
     * Método para inicializar o grid com células vazias.
     */
    private void initializeGrid() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j] = new Cell();
            }
        }
    }

    /**
     * Método para inicializar a cobra na posição central da arena.
     * @param startX Posição inicial X da cobra.
     * @param startY Posição inicial Y da cobra.
     */
    private void initializeSnake(int startX, int startY, int snakePontosize) throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        this.snake = new Snake(startX, startY, snakePontosize);
        updateGrid();
    }

    /**
     * Método para inicializar obstáculos aleatórios na arena.
     */
    public void initializeObstacles() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Random rand = new Random();
        List<Poligono> templates = new ArrayList<>();
        templates.add(new Quadrado(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1), new Ponto(0, 1)})); // Quadrado
        templates.add(new Triangulo(new Ponto[]{
                new Ponto(0, 1),
                new Ponto(1, 1),
                new Ponto(0.5, 0)
        }));
        templates.add(new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(2, 0), new Ponto(2, 1), new Ponto(0, 1)})); // Retângulo
        templates.add(new Retangulo(new Ponto[]{new Ponto(0, 0), new Ponto(2, 0), new Ponto(2, 1), new Ponto(0, 1)})); // Retângulo

        int minDistance = 5;

        for (int i = 0; i < 6; i++) {
            boolean placed = false;
            while (!placed) {
                Poligono template = templates.get(rand.nextInt(templates.size()));
                int x = rand.nextInt(this.width - 5) == 0 ? 1 : rand.nextInt(this.width - 5);
                int y = rand.nextInt(this.height - 5) == 0 ? 1 : rand.nextInt(this.height - 5);
                Poligono translated = template.transladaPoligonoCentroide(x, y);


                if (checkDistanceFromSnake(translated, minDistance)) {
                    double angle = rand.nextDouble() * 360;
                    Obstaculo novoObstaculo = rand.nextBoolean() ? new ObstaculoDinamico(translated, angle, translated.calculaCentroide()) : new ObstaculoEstatico(translated);
                    this.obstaculos.add(novoObstaculo);
                    placed = true;
                }
            }
        }
    }
    /**
     * Verifica se qualquer ponto do obstáculo está a uma distância segura de qualquer parte da cobra.
     * @param obstacle O polígono representando o obstáculo.
     * @param minDistance A distância mínima permitida entre a cobra e qualquer parte do obstáculo.
     * @return boolean Retorna verdadeiro se o obstáculo estiver suficientemente distante da cobra, caso contrário, falso.
     */
    private boolean checkDistanceFromSnake(Poligono obstacle, int minDistance) {

        for (Ponto obstaclePoint : obstacle.getPoligonoPontos()) {
            for (int dx = -minDistance; dx <= minDistance; dx++) {
                for (int dy = -minDistance; dy <= minDistance; dy++) {
                    int checkX = (int) obstaclePoint.getX() + dx;
                    int checkY = (int) obstaclePoint.getY() + dy;
                    if (isCellOccupiedBySnake(checkX, checkY, this.snake)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Verifica se as coordenadas especificadas estão dentro dos limites do campo de jogo.
     * @param x A coordenada x a ser verificada.
     * @param y A coordenada y a ser verificada.
     * @return boolean Retorna verdadeiro se ambas as coordenadas estiverem dentro dos limites do campo, caso contrário, falso.
     */
    boolean isWithinBounds(double x, double y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }
    /**
     * Método para alternar o modo automático de movimentação da cobra.
     */
    public void toggleAutoMode() throws Exception {
        this.isAutoMode = !this.isAutoMode;
        if (this.isAutoMode) {
            prepareAutoMove();
        } else {
            this.autoMovePath = null;
            this.pathIterator = null;
        }
    }

    /**
     * Documentação em interface Automatic
     */
    public void prepareAutoMove() throws Exception {
        Food targetFood = getFood();
        if (targetFood != null) {
            this.autoMovePath = findPathToFood(targetFood);
            if (this.autoMovePath.isEmpty()) {
                throw new PathNotFoundException("No path available to the next food.");
            } else {
                this.pathIterator = this.autoMovePath.iterator();
            }
        } else {
            throw new FoodNotFoundException("No food available to target.");
        }
    }
    /**
     * Documentação em interface Automatic
     */
    public boolean autoMove() throws MovementException {
        while (true) {
            if (this.pathIterator != null && this.pathIterator.hasNext()) {
                Direction nextMove = this.pathIterator.next();
                try {
                    return moveSnake(nextMove);
                } catch (Exception e) {
                    throw new MovementException("Failed to move snake as intended.");
                }
            }

            try {
                prepareAutoMove();
                if (this.pathIterator == null || !this.pathIterator.hasNext()) {
                    throw new MovementException("Failed to find a new path or path is empty.");
                }
            } catch (Exception e) {
                throw new MovementException("Error in preparing auto move: " + e.getMessage());
            }
        }
    }

    /**
     * Documentação em interface Automatic
     */
    public List<Direction> findPathToFood(Food targetFood) throws ValorNegativoException {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(Node::getfCost));
        Map<Ponto, Node> allNodes = new HashMap<>();

        Node start = new Node((int)this.snake.getHeadPosition().getX(), (int)this.snake.getHeadPosition().getY(), null, 0,
                calculateHCost((int)this.snake.getHeadPosition().getX(), (int)this.snake.getHeadPosition().getY(), (int)targetFood.getLocation().getX(), (int)targetFood.getLocation().getY()));
        openSet.add(start);
        allNodes.put(new Ponto(start.getX(), start.getY()), start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.getX() == targetFood.getLocation().getX() && current.getY() == targetFood.getLocation().getY()) {
                return reconstructPath(current);
            }

            for (Direction direction : Direction.values()) {
                int newX = current.getX() + direction.dx;
                int newY = current.getY() + direction.dy;
                if (isWithinBounds(newX, newY) && !isCellOccupiedBySnake(newX, newY, this.snake) && !isCellOccupiedByObstacle(newX, newY, this.obstaculos)) {
                    Node neighbor = new Node(newX, newY, current, current.getgCost() + 1,
                            calculateHCost(newX, newY, (int)targetFood.getLocation().getX(), (int)targetFood.getLocation().getY()));
                    Node existingNode = allNodes.get(new Ponto(newX, newY));
                    if (existingNode == null || existingNode.getfCost() > neighbor.getfCost()) {
                        allNodes.put(new Ponto(newX, newY), neighbor);
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return new ArrayList<>();
    }
    /**
     * Documentação em interface Automatic
     */
    public List<Direction> reconstructPath(Node target) {
        List<Direction> path = new ArrayList<>();
        Node current = target;
        while (current.getParent() != null) {
            path.add(0, getDirectionFromNodes(current.getParent(), current));
            current = current.getParent();
        }
        return path;
    }

    /**
     * Método para mover a cobra na direção especificada.
     * @param direction Direção para mover a cobra.
     * @return true se a movimentação foi bem-sucedida.
     */
    public boolean moveSnake(Direction direction) throws Exception {
        this.snake.setDirection(direction);
        this.snake.move();
        if (checkCollision()) {
            return false;
        }
        checkFoodConsumption();
        updateGrid();
        return true;
    }

    /**
     * Método para colocar um novo alimento na arena de forma aleatória.
     * @return true se o alimento foi colocado com sucesso.
     */
    public boolean placeNewFood() throws ValorNegativoException {
        Random rand = new Random();
        int size = 1;

        Ponto chosenPosition = null;
        int count = 0;

        for (int x = 2; x <= this.width - size - 2; x++) {
            for (int y = 2; y <= this.height - size - 2; y++) {
                if (isCellFree(x, y)) {
                    count++;
                    if (rand.nextInt(count) == 0) {
                        chosenPosition = new Ponto(x, y);
                    }
                }
            }
        }

        if (chosenPosition != null) {
            if (rand.nextBoolean()) {
                this.foods.add(new Circulo((int)chosenPosition.getX(), (int)chosenPosition.getY(), size));
            } else {
                this.foods.add(new Food((int)chosenPosition.getX(), (int)chosenPosition.getY(), size));
            }
            return true;
        }
        return false;
    }

    /**
     * Verifica se uma célula específica do grid está livre para ser ocupada.
     * @param x Coordenada x da célula a ser verificada.
     * @param y Coordenada y da célula a ser verificada.
     * @return true se a célula estiver livre; false caso contrário.
     */
    private boolean isCellFree(int x, int y)
    {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
            return false;
        }
        if (isCellOccupiedByObstacle(x, y, this.obstaculos) || isCellOccupiedBySnake(x, y, this.snake) || isCellOccupiedByFood(x, y, this.foods)) {
            return false;
        }

        return true;
    }
    /**
     * Verifica se uma comida específica foi consumida pela cobra. A comida é considerada consumida
     * se todos os pontos que ela ocupa estão cobertos pela cabeça da cobra.
     * @param food A comida a ser verificada se foi consumida.
     * @return true se toda a área ocupada pela comida está coberta pela cobra, false caso contrário.
     */
    private boolean isFoodConsumed(Food food) {
        int x = (int)food.getLocation().getX();
        int y = (int)food.getLocation().getY();
        int size = food.getSize();


        for (int dx = 0; dx < size; dx++) {
            for (int dy = 0; dy < size; dy++) {
                if (!snakeCovers(x + dx, y + dy)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Método para verificar se a cobra consumiu algum alimento e serve inclusive para
     * atribuir o score.
     * Caso a snake consuma uma comida em forma de circulo o score
     * atribuido equivale a 50 pontos caso contrário o score atribuido equivale a 100 pontos
     */
    public void checkFoodConsumption() throws Exception {
        List<Food> toRemove = new ArrayList<>();
        for (Food food : this.foods) {
            if (isFoodConsumed(food)) {
                System.gc();
                if(this.foods.getLast() instanceof Circulo) {
                    this.score += 50;
                    this.snake.grow();
                }
                else {
                    this.score += 100;
                    this.snake.grow();
                }
                toRemove.add(food);
            }
        }
        this.foods.removeAll(toRemove);
        if (!toRemove.isEmpty()) {
            placeNewFood();
            prepareAutoMove();
        } else if (this.foods.isEmpty()) {
            this.score = Integer.MAX_VALUE;
        }
    }

    /**
     * Este método é utilizado para determinar se uma posição específica (x, y) está ocupada pela cobra.
     * @param x A coordenada x da posição a ser verificada.
     * @param y A coordenada y da posição a ser verificada.
     * @return true se a posição (x, y) está ocupada por qualquer parte da cobra, incluindo a cabeça e o corpo.
     */
    private boolean snakeCovers(int x, int y) {
        for (Ponto ponto : this.snake.getPontos()) {
            if (ponto.getX() <= x && x < ponto.getX() + this.snake.getPontosSize() &&
                    ponto.getY() <= y && y < ponto.getY() + this.snake.getPontosSize()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Método para verificar colisões da cobra com obstáculos ou bordas da arena.
     * @return true se houver colisão.
     */
    private boolean checkCollision() {
        List<Ponto> pontosCobra = this.snake.getPontos();


        for (Ponto pontoCobra : pontosCobra) {

            if (isCellOccupiedByObstacle((int) pontoCobra.getX(), (int) pontoCobra.getY(), this.obstaculos)) {
                return true;
            }


            if (pontoCobra.getX() < 0 || pontoCobra.getX() >= this.width || pontoCobra.getY() < 0 || pontoCobra.getY() >= this.height) {
                return true;
            }
        }
        Ponto head = pontosCobra.getFirst();
        for (int i = 1; i < pontosCobra.size(); i++) {
            if (head.getX() == pontosCobra.get(i).getX() && head.getY() == pontosCobra.get(i).getY()) {
                return true;
            }
        }
        for (int i = 1; i < this.snake.getPontos().size() - 1; i++) {
            if (head.getX() == this.snake.getPontos().get(i).getX() &&
                    head.getY() == this.snake.getPontos().get(i).getY()) {
                return true;
            }
        }
        return false;

    }

    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promove-se assim o Principio do encapsulamento
     */
    @Override
    public Arena clone()
    {
        return new Arena(this);
    }

    /**
     * @param obj que serve para verificar se o objeto passado como parametro é ou não igual ao objeto recetor
     * @return true ou false caso seja ou não seja igual
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Arena other = (Arena) obj;

        if (this.width != other.width || this.height != other.height) {
            return false;
        }

        if (this.isAutoMode != other.isAutoMode) {
            return false;
        }

        if (this.score != other.score) {
            return false;
        }

        if (!Objects.equals(this.snake, other.snake)) {
            return false;
        }

        if (!Objects.equals(this.foods, other.foods)) {
            return false;
        }

        if (!Objects.equals(this.obstaculos, other.obstaculos)) {
            return false;
        }

        return areGridsEqual(this.grid, other.grid);
    }
    /**
     * Este método é usado para comparar dois grids célula por célula, considerando tanto a igualdade de tamanho
     * dos arrays quanto a igualdade de conteúdo de cada célula.
     * @param grid1 O primeiro grid de células para comparação.
     * @param grid2 O segundo grid de células para comparação.
     * @return true se ambos os grids têm o mesmo tamanho e conteúdo idêntico em todas as células correspondentes;
     *         false caso contrário.
     */
    private boolean areGridsEqual(Cell[][] grid1, Cell[][] grid2) {
        if (grid1 == null && grid2 == null) return true;
        if (grid1 == null || grid2 == null) return false;
        if (grid1.length != grid2.length) return false;

        for (int i = 0; i < grid1.length; i++) {
            if (grid1[i].length != grid2[i].length) return false;
            for (int j = 0; j < grid1[i].length; j++) {
                if (!Objects.equals(grid1[i][j], grid2[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Arena, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.snake.isHead(x, y)) {
                    builder.append("H");
                } else if (this.snake.isBody(x, y)) {
                    builder.append("T");
                } else if (isCellOccupiedByFood(x, y, this.foods)) {
                    builder.append("F");
                } else if (isCellOccupiedByObstacle(x, y, this.obstaculos)) {
                    builder.append("O");
                } else {
                    builder.append(".");
                }
            }
            builder.append("\n");
        }

        builder.append("Dir: ").append(this.snake.getHeadDirectionAngle()).append(" Pontos: ").append(this.score).append("\n");

        return builder.toString();
    }
}

