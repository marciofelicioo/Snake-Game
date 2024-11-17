package OOPS;

import Exceptions.ValorNegativoException;
import Figuras_Geometricas.Ponto;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Representa a Cobra do jogo OOPS, composta por uma lista de pontos que representam as partes do corpo,
 * uma direção de movimento e o tamanho de cada segmento do corpo.
 *
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 *
 * @inv Ao definir a Snake o tamanho dos pontos deve ser maior que zero
 */
public class Snake {
    /**
     * Definição da estrutura do objeto Snake
     */
    private ArrayList<Ponto> pontos;
    private Direction direction;
    private final int pontosize;

    /**
     * Definição do construtor por omissão da classe Snake
     */
    public Snake() throws ValorNegativoException {
        setPontos(null);
        this.direction = null;
        this.pontosize = 0;
    }
    /**
     * Definição do construtor de inicialização da classe Snake
     */
    public Snake(int startX, int startY, int pontosize) throws ValorNegativoException {
        this.pontosize = pontosize;
        this.pontos = new ArrayList<>();
        this.pontos.add(new Ponto(startX, startY));
        this.direction = Direction.RIGHT;
    }
    /**
     * Definição do construtor de cópia da classe Snake
     */
    public Snake(Snake snake) throws ValorNegativoException {
        this.pontosize = snake.getPontosSize();
        setPontos(snake.getPontos());
        this.direction = snake.getDirection();
    }

    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto Snake
     */
    public ArrayList<Ponto> getPontos() {
        return this.pontos;
    }

    public int getPontosSize()
    {
        return this.pontosize;
    }
    public Direction getDirection()
    {
        return this.direction;
    }
    public Ponto getHeadPosition() {
        return this.pontos.getFirst();
    }
    public int getHeadDirectionAngle() {
        switch (direction) {
            case UP: return 90;
            case DOWN: return 270;
            case LEFT: return 180;
            case RIGHT: return 0;
            default: return -1;
        }
    }
    /**
     * Estes métodos têm o principal objetivo de modificar a estrutura do objeto Snake
     */
    public void setDirection(Direction newDirection) {
        if ((newDirection == Direction.UP && direction != Direction.DOWN) ||
                (newDirection == Direction.DOWN && direction != Direction.UP) ||
                (newDirection == Direction.LEFT && direction != Direction.RIGHT) ||
                (newDirection == Direction.RIGHT && direction != Direction.LEFT)) {
            this.direction = newDirection;
        }
    }

    public void setPontos(ArrayList<Ponto> pontos) throws ValorNegativoException {
        if (pontosize <= 0) {
            throw new ValorNegativoException("O tamanho dos pontos deve ser maior que zero.");
        }
        if (pontos != null) {
            this.pontos.addAll(pontos);
        }
    }

    /**
     * Move a cobra na direção atual, adicionando uma nova cabeça na frente e removendo a cauda.
     */
    public void move() throws ValorNegativoException {
        Ponto newHead = new Ponto(this.pontos.getFirst().getX(), this.pontos.getFirst().getY());

        switch (direction) {
            case UP:
                newHead.setY(this.pontos.getFirst().getY() - this.pontosize);
                break;
            case DOWN:
                newHead.setY(this.pontos.getFirst().getY() + this.pontosize);                  break;
            case LEFT:
                newHead.setX(pontos.getFirst().getX() - this.pontosize);
                break;
            case RIGHT:
                newHead.setX(this.pontos.getFirst().getX() + this.pontosize);
                break;
        }

        this.pontos.addFirst(newHead);
        this.pontos.removeLast();
    }
    /**
     * Aumenta o tamanho da cobra adicionando um segmento no final, replicando a última posição.
     */
    public void grow() {
        pontos.addLast(this.pontos.getLast());
        System.gc();
    }

    /**
     * Verifica se um ponto específico é a cabeça da cobra.
     * @param x Posição X a verificar.
     * @param y Posição Y a verificar.
     * @return true se o ponto (x, y) for a cabeça da cobra.
     */
    public boolean isHead(int x, int y) {
        return this.pontos.getFirst().getX() == x && this.pontos.getFirst().getY() == y;
    }

    /**
     * Verifica se um ponto específico é parte do corpo da cobra, excluindo a cabeça.
     * @param x Posição X a verificar.
     * @param y Posição Y a verificar.
     * @return true se o ponto (x, y) for parte do corpo da cobra.
     */
    public boolean isBody(int x, int y) {
        for (int i = 1; i < this.pontos.size(); i++) {
            if (this.pontos.get(i).getX() == x && this.pontos.get(i).getY() == y) {
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
    public Snake clone(){
        try {
            return new Snake(this);
        } catch (ValorNegativoException e) {
            throw new RuntimeException(e);
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
        Snake other = (Snake) obj;
        return pontosize == other.pontosize &&
                direction == other.direction &&
                Objects.equals(pontos, other.pontos);
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Snake, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Snake{");
        sb.append("pontos=[");
        for (Ponto ponto : pontos) {
            sb.append(ponto.toString());
            sb.append(", ");
        }
        if (!pontos.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("], direction=").append(direction);
        sb.append(", pontosize=").append(pontosize);
        sb.append('}');
        return sb.toString();
    }
}