package Obstaculos;
import Exceptions.*;
import Figuras_Geometricas.Poligono;
import Figuras_Geometricas.Ponto;
/**
 * Classe abstrata Obstaculo que serve como base para diferentes tipos de obstáculos(dinâmicos ou estáticos).
 * Cada obstáculo é definido por um polígono que descreve sua forma.
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 * @inv A forma do obstáculo não pode ser null após a sua criação, garantindo que cada obstáculo tenha uma representação
 * geométrica definida.
 */
public abstract class Obstaculo {
    /**
     * Definição da estrutura do objeto Obstaculo que no caso é um Poligono
     */
    private Poligono forma;
    /**
     * Definição do construtor por omissão da classe Obstaculo
     */
    public Obstaculo()
    {
        this.forma = null;
    }
    /**
     * Este método é um construtor de inicialização de objetos da classe Obstaculo
     * @param forma representa um Poligono
     */
    public Obstaculo(Poligono forma) {
        this.forma = forma.clone();
    }
    /**
     * Este método é um construtor de cópia de objetos da classe Obstaculo
     * @param obstaculo representa o objeto Obstaculo cuja estrutura interna pretende-se copiar
     */
    public Obstaculo(Obstaculo obstaculo)
    {
        setForma(obstaculo.getForma());
    }
    /**
     * Método abstrato destinado a ser implementado por subclasses para definir como o obstáculo deve ser atualizado,
     * por exemplo, mudar sua posição ou rotação.
     * @throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException,
     * ColinearityIntersectionNumberPointsException, QuadradoException, RetaException Exceções que podem ser lançadas
     * durante a atualização do obstáculo.
     */
    public abstract void atualizar() throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException;
    /**
     * Este método tem o principal objetivo de retornar a estrutura do objeto Obstaculo
     */
    public Poligono getForma() {
        return this.forma.clone();
    }

    /**
     * Define a forma do obstáculo.
     * @param forma O novo polígono que define a forma do obstáculo.
     */
    public void setForma(Poligono forma) {
        if (forma == null) throw new NullPointerException("A forma do obstáculo não pode ser null.");
        this.forma = forma.clone();
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
        Obstaculo obstaculo = (Obstaculo) obj;
        return this.forma.equals(obstaculo.getForma());
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Obstaculo, redefine-se desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString() {
        return "Obstaculo com forma: " + this.forma.toString();
    }

}