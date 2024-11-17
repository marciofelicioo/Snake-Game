package OOPS;
/**
 * Classe Cell: Representa uma célula na grelha do jogo.
 * Uma célula pode conter diferentes tipos de conteúdos, como partes da Snake, comida, obstaculos ou estar vazia.
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 * @inv content != null, "O conteúdo de uma célula nunca deve ser nulo."
 */

public class Cell {
    /**
     * Definição da estrutura do objeto Cell que no caso é o conteúdo de uma célula
     */
    private CellContent content;

    /**
     * Construtor por omissão que inicializa a célula como vazia.
     */
    public Cell() {
        this.content = CellContent.EMPTY;
    }
    /**
     * Definição do construtor de cópia da classe Cell
     */
    public Cell(Cell content) {
        this.content = content.getContent();
    }

    /**
     * Define o conteúdo desta célula.
     * @param content O conteúdo a ser definido na célula, não deve ser nulo.
     * @throws NullPointerException se content for nulo.
     */
    public void setContent(CellContent content) {
        if (content == null) {
            throw new NullPointerException("Content cannot be null.");
        }
        this.content = content;
    }

    /**
     * Retorna o conteúdo atual desta célula.
     * @return O conteúdo desta célula.
     */
    public CellContent getContent() {
        return this.content;
    }
    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promove-se assim o Principio do encapsulamento
     */
    @Override
    public Cell clone(){
        return new Cell(this);
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
        Cell cell = (Cell) obj;
        return this.content == cell.content;
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Cell, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        return "Cell{" +
                "content=" + this.content +
                '}';
    }

}
