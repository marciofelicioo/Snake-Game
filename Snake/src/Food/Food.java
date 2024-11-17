package Food;

import Exceptions.ValorNegativoException;
import Figuras_Geometricas.Ponto;

/**
 * Classe Food: Representa um item de comida no jogo.
 * A comida é usada para alimentar a Snake.
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 * @inv location != null, "A localização da comida nunca deve ser null."
 * @inv size > 0, "O tamanho da comida deve ser sempre positivo."
 */
public class Food {
    /**
     * Definição da estrutura do objeto Food que no caso é um Ponto
     */
    private Ponto location;
    int size;
    /**
     * Definição do construtor por omissão da classe Food
     */
    public Food()
    {
        this.location = null;
        this.size = 0;
    }
    /**
     * Definição do construtor de inicialização da classe Food
     */
    public Food(int x, int y, int size) throws ValorNegativoException {
        setLocation(new Ponto(x, y));
        setSize(size);
    }
    /**
     * Definição do construtor de cópia da classe Food
     */
    public Food(Food food)
    {
        this.location = food.getLocation();
        this.size = food.getSize();
    }


    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto Food
     */
    public Ponto getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }
    /**
     * Estes métodos têm o principal objetivo de modificar a estrutura interna do objeto Food
     */
    public void setLocation(Ponto location){
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        this.location = location;
    }

    public void setSize(int size){
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive.");
        }
        this.size = size;
    }

    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promove-se assim o Principio do encapsulamento
     */
    @Override
    public Food clone()
    {
        return new Food(this);
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
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Food other = (Food) obj;
        return this.location.equals(other.getLocation()) && this.size == other.getSize();
    }

    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Food, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString()
    {
        return "(" + Math.round(this.getLocation().getX()) + "," + Math.round(this.getLocation().getY()) + ")"
                + " and size:" + this.getSize();
    }

}
