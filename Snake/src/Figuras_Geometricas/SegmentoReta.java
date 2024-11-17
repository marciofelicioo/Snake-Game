package Figuras_Geometricas;

import Exceptions.RetaException;
import Exceptions.SegmentoException;

/**
 * Classe SegmentoReta: Esta classe contém um construtor que cria um objeto que é um Segmento de uma Reta
 * e o seu objetivo é calcular o declive de um segmento de reta e verificar se dois segmentos de reta
 * intercetam se ou não através do método declive e cruzamento respetivamente
 * @author Márcio Felício
 * @version 1.0
 * @inv Não pode haver um Segmento de reta cujo comprimento seja zero ou inferior a zero
 */
public class SegmentoReta {
    /**
     * Definição da estrutura do objeto Reta que no caso são dois pontos
     */
    private Ponto p1;
    private Ponto p2;
    /**
     * Definição do construtor por omissão da classe SegmentoReta
     */
    public SegmentoReta()
    {
        this.p1 = null;
        this.p2 = null;
    }
    /**
     * Definição do construtor de inicialização da classe SegmentoReta
     */
    public SegmentoReta(Ponto p1, Ponto p2) throws SegmentoException, RetaException {
        Reta.verify(p1,p2);
        verify(p1,p2);
        setInicioFim(p1,p2);
    }
    /**
     * Definição do construtor de cópia da classe SegmentoReta
     */
    public SegmentoReta(SegmentoReta s1)
    {
        this.p1 = s1.getInicio();
        this.p2 = s1.getFim();
    }

    /**
     * @return o ponto inicial do objeto Segmento de reta recetor
     */
    public Ponto getInicio()
    {
        return this.p1;
    }

    /**
     * @return o ponto final do objeto Segmento de reta recetor
     */
    public Ponto getFim()
    {
        return this.p2;
    }

    /**
     * Este método tem o principal objetivo de modificar a estrutura do objeto recetor Segmento de reta
     * @param p1 representa o primeiro ponto do segmento de reta
     * @param p2 representa o ultimo ponto do segmento de reta
     */
    public void setInicioFim(Ponto p1, Ponto p2) throws SegmentoException {
        verify(p1,p2);
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Este método estático verifica se cumpre os requesitos para ser um segmento de reta
     * @param p1 representa o ponto inicial do segmento de reta
     * @param p2 representa o ponto final do segmento de reta
     */
    private static void verify(Ponto p1, Ponto p2) throws SegmentoException
    {
        if(p1.comprimentoSeg(p2) == 0 || p1.comprimentoSeg(p2) < 0)
        {
            throw new SegmentoException("Segmento:vi");
        }
    }


    /**
     * Este método tem como principal objetivo verificar se dois segmentos de reta se cruzam
     * @param s representa o segmento de reta
     * @return true ou false caso se cruzem ou não através de ((d1 * d2 < 0) && (d3 * d4 < 0))
     */
    public boolean cruzamento(SegmentoReta s) {
        Ponto p1 = new Ponto(this.getInicio());
        Ponto p2 = new Ponto(this.getFim());
        Ponto p3 = new Ponto(s.getInicio());
        Ponto p4 = new Ponto(s.getFim());
        double d1 = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
        double d2 = (p2.getX() - p1.getX()) * (p4.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p4.getX() - p1.getX());
        double d3 = (p4.getX() - p3.getX()) * (p1.getY() - p3.getY()) - (p4.getY() - p3.getY()) * (p1.getX() - p3.getX());
        double d4 = (p4.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p4.getY() - p3.getY()) * (p2.getX() - p3.getX());

        return (d1 * d2 < 0) && (d3 * d4 < 0);
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
        SegmentoReta s1 = (SegmentoReta) obj;
        return (this.getInicio().equals(s1.getInicio()) && this.getFim().equals(s1.getFim())) ||
                (this.getInicio().equals(s1.getFim()) && this.getFim().equals(s1.getInicio()));
    }
    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promovo assim o Principio do encapsulamento
     */
    @Override
    public SegmentoReta clone()
    {
        return new SegmentoReta(this);
    }

    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto SegmentoReta, redefino desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString()
    {
        StringBuilder s1 = new StringBuilder();
        s1.append("ponto inicial do segmento-");s1.append("(");s1.append(this.getInicio().toString());s1.append(")");
        s1.append("\n");
        s1.append("ponto final do segmento-");s1.append("(");s1.append(this.getFim().toString());s1.append(")");
        s1.append("\n");
        return s1.toString();
    }
}
