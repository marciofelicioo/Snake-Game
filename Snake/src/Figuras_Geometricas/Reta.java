package Figuras_Geometricas;

import Exceptions.RetaException;

import static java.lang.Math.abs;

/**
 * Classe Reta: Esta classe contém um construtor que cria um objeto que é um Segmento de uma Reta e o seu
 * principal objetivo é verificar se uma reta tem o que é preciso para ser formada
 * @author Márcio Felício
 * @version 1.0
 * @inv A subtração entre o valor absoluto das coordenadas x e a subtração entre o valor absoluto das coordenada y
 * de dois pontos não pode ser inferior a 10^-9
 */
public class Reta {
    /**
     * Definição da estrutura do objeto Reta que no caso são dois pontos
     */
    private Ponto p1;
    private Ponto p2;

    /**
     * Definição do construtor por omissão da classe Reta
     */
    public Reta()
    {
        this.p1 = null;
        this.p2 = null;
    }
    /**
     * Definição do construtor de inicialização da classe Reta
     */
    public Reta(Ponto p1, Ponto p2) throws RetaException {
        verify(p1,p2);
        this.p1 = p1;
        this.p2 = p2;
    }
    /**
     * Definição do construtor de cópia da classe Reta
     */
    public Reta(Reta r1)
    {
        this.p1 = r1.getPonto_1();
        this.p2 = r1.getPonto_2();
    }
    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto Reta
     */
    public Ponto getPonto_1()
    {
        return this.p1;
    }
    public Ponto getPonto_2()
    {
        return this.p2;
    }
    /**
     * Este método estático verifica se cumpre os requesitos para ser uma reta
     * @param p1 representa o ponto inicial do segmento de reta
     * @param p2 representa o ponto final do segmento de reta
     */
    public static void verify(Ponto p1, Ponto p2) throws RetaException
    {
        if((abs(p1.getX() - p2.getX()) <= Math.pow(10,-9)) && (abs(p1.getY() - p2.getY()) <= Math.pow(10,-9)))
        {
            throw new RetaException("Reta:vi");
        }
    }
    /**
     * Este método verifica se tres pontos são colineares
     * @param p representa o ponto inicial do segmento de reta
     */
    public boolean colinearity(Ponto p) {
        double a = p1.getY() - p2.getY();
        double b = p2.getX() - p1.getX();
        double c = p1.getX() * p2.getY() - p2.getX() * p1.getY();
        return a * p.getX() + b * p.getY() + c == 0;
    }
    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promovo assim o Principio do encapsulamento
     */
    @Override
    public Reta clone()
    {
        return new Reta(this);
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
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        Reta R1 = (Reta) obj;
        return (this.getPonto_1().equals(R1.getPonto_2()) && this.getPonto_2().equals(R1.getPonto_2())) ||
                (this.getPonto_1().equals(R1.getPonto_2()) && this.getPonto_2().equals(R1.getPonto_1()));
    }
}
