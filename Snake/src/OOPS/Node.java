package OOPS;

import java.util.Objects;

/**
 * Classe que representa um nó em um algoritmo de caminho.
 * Cada nó armazena sua posição no grid (x, y), o custo para alcançá-lo a partir do ponto de partida (gCost),
 * uma estimativa do custo para chegar ao ponto final (hCost) e o custo total (fCost).
 *
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 *
 * @inv O fCost seja sempre a soma do gCost com o hCost.
 * @inv  As coordenadas x e y sejam não negativas.
 * @inv O parent não seja uma auto-referência.
 */

public class Node {
    /**
     * Definição da estrutura do objeto Node
     */
    int x, y;
    Node parent;
    double gCost, hCost, fCost;
    /**
     * Definição do construtor por omissão da classe Node
     */
    public Node()
    {
        setX(0);
        setY(0);
        setParent(null);
        setgCost(0);
        setfCost(0);
        sethCost(0);
    }
    /**
     * Definição do construtor de inicialização da classe Node
     */
    public Node(int x, int y, Node parent, double gCost, double hCost) {
        setX(x);
        setY(y);
        setParent(parent);
        setgCost(gCost);
        sethCost(hCost);
        setfCost(gCost + hCost);
    }
    /**
     * Definição do construtor de cópia da classe Node
     */
    public Node(Node node){
        setX(node.getX());
        setY(node.getY());
        setParent(node.getParent());
        setfCost(node.getfCost());
        sethCost(node.gethCost());
        setgCost(node.getgCost());
    }
    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto Node
     */
    public Node getParent()
    {
        return this.parent;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public double getgCost()
    {
        return this.gCost;
    }
    public double gethCost()
    {
        return this.hCost;
    }
    public double getfCost()
    {
        return this.hCost + this.gCost;
    }
    /**
     * Estes métodos têm o principal objetivo de modificar a estrutura do objeto Node
     */

    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Coordenada x não pode ser negativa.");
        }
        this.x = x;
    }
    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException("Coordenada y não pode ser negativa.");
        }
        this.y = y;
    }
    public void setParent(Node Parent)
    {
        if (Parent == this) {
            throw new IllegalArgumentException("O nó parent não pode ser o próprio nó.");
        }
        if(Parent != null){
            this.parent = Parent;
        }
        else {
            this.parent = Parent;
        }
    }
    public void setgCost(double gCost)
    {
        this.gCost = gCost;
    }
    public void sethCost(double hCost)
    {
        this.hCost = hCost;
    }
    public void setfCost(double fCost)
    {
        this.fCost = fCost;
    }
    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promove-se assim o Principio do encapsulamento
     */
    @Override
    public Node clone() {
        return new Node(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    /**
     * @param obj que serve para verificar se o objeto passado como parametro é ou não igual ao objeto recetor
     * @return true ou false caso seja ou não seja igual
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return this.x == node.getX() && this.y == node.getY();
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Node, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        return String.format("Node(%d, %d) gCost: %.2f, hCost: %.2f, fCost: %.2f", x, y, gCost, hCost, fCost);
    }
}
