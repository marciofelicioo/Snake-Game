package Food;

import Exceptions.ValorNegativoException;
import Figuras_Geometricas.Ponto;
/**
 * Classe que estende Food para representar uma comida em forma de círculo no jogo.
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 * @inv radius > 0, "O raio deve ser sempre positivo."
 * @inv size == 2 * radius, "O tamanho deve ser sempre o dobro do raio."
 */
public class Circulo extends Food {
    /**
     * Definição da estrutura do objeto Circulo que no caso é um raio
     */
    private double radius;
    /**
     * Definição do construtor por omissão da classe Circulo
     */
    public Circulo()
    {
        super();
    }
    /**
     * Definição do construtor de inicialização da classe Circulo
     */
    public Circulo(int x, int y, int diameter) throws ValorNegativoException {
        super(x, y, diameter);
        setRadius(diameter/2.0);
    }
    /**
     * Definição do construtor de cópia da classe Circulo
     */
    public Circulo(Circulo circulo)
    {
        super(circulo);
        setRadius(circulo.getRadius());
    }

    /**
     * Este método tem o principal objetivo de retornar a estrutura do objeto Circulo
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Este método tem o principal objetivo de modificar a estrutura interna do objeto Circulo
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        this.radius = radius;
        this.size = (int) (2 * radius); // Atualiza o diâmetro conforme o raio
    }
    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promove-se assim o Principio do encapsulamento
     */
    @Override
    public Circulo clone()
    {
        return new Circulo(this);
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
        Circulo circulo = (Circulo) obj;
        return this.radius == circulo.getRadius();
    }

    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Circulo, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        return "Circulo{" +
                "centro=" + getLocation() +
                ", raio=" + radius +
                '}';
    }
}
