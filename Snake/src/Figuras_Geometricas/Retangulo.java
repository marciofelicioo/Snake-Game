package Figuras_Geometricas;
import Exceptions.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

/**
 * Classe Retangulo: Esta classe sendo ela subclasse de Poligono contém um construtor que cria um objeto Retangulo
 * e o seu principal objetivo é verificar se o mesmo é bem formado através da respetiva invariante
 * @author Márcio Felício
 * @version 1.0
 * @inv Não pode haver um Retângulo cujo número de segmentos seja diferente de quatro e inclusive que os lados opostos
 * tenham um comprimento diferente de um do outro
 */
public class Retangulo extends Poligono {

    /**
     * Este método é um construtor por omissão de objetos da classe Retangulo
     */
    public Retangulo()
    {
        super();
    }

    /**
     * Este método é um construtor de inicialização de objetos da classe Retangulo
     * @param pontos representa um array de objetos do tipo Ponto
     */
    public Retangulo(Ponto[] pontos) throws ColinearityIntersectionNumberPointsException, SegmentoException, RetaException, RetanguloException, QuadradoException, TrianguloException {
        super(pontos);
        verify();
    }

    /**
     * Este método é um construtor de cópia de objetos da classe Retangulo
     * @param retangulo representa o objeto Retangulo cuja estrutura interna pretende-se copiar
     */
    public Retangulo(Retangulo retangulo)
    {
        this.setSegmentos(retangulo.getSegmentos());
    }
    /**
     * Este método translada um Retangulo consoante as coordenadas de um novo centroide para o Retangulo
     * @param novoX representa a coordenada x do novo centroide
     * @param novoY representa a coordenada y do novo centroide
     * @return new Retangulo(pontosTransladados.toArray(new Ponto[0]));
     */
    @Override
    public Poligono transladaPoligonoCentroide(double novoX, double novoY) throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto centroideAtual = calculaCentroide();
        double deltaX = novoX - centroideAtual.getX();
        double deltaY = novoY - centroideAtual.getY();

        List<Ponto> pontosTransladados = new ArrayList<>();

        for (Ponto ponto : this.getPoligonoPontos()) {
            double xTransladado = (ponto.getX() + deltaX) > 0 ? (ponto.getX() + deltaX) : 0;
            double yTransladado = (ponto.getY() + deltaY) > 0 ? (ponto.getY() + deltaY) : 0;
            pontosTransladados.add(new Ponto(xTransladado, yTransladado));
        }

        return new Retangulo(pontosTransladados.toArray(new Ponto[0]));
    }
    /**
     * Este método protected é uma pré condição para verificar se conseguimos obter um objeto Retangulo
     * A primeira verificação é feita através do método getSegmentos() herdado e o valor retornado tem de ser quatro
     * A penultima verificação que faço é através dos comprimentos dos lados do quadrado cujo valor tem de ser igual
     * A ultima verificação que faço é ver se os angulos internos são de 90 graus
     */
    @Override
    protected void verify() throws RetanguloException, QuadradoException
    {
        if(this.getSegmentos().length != 4)
        {
            throw new RetanguloException("Retangulo:vi");
        }
        double x = this.getSegmentos()[0].getInicio().comprimentoSeg(this.getSegmentos()[0].getFim());
        double y = this.getSegmentos()[1].getInicio().comprimentoSeg(this.getSegmentos()[1].getFim());
        double z = this.getSegmentos()[2].getInicio().comprimentoSeg(this.getSegmentos()[2].getFim());
        double w = this.getSegmentos()[3].getInicio().comprimentoSeg(this.getSegmentos()[3].getFim());
        if((x != z) || (y != w))
        {
            throw new RetanguloException("Retangulo:vi");
        }
        double diagonal_one = this.getSegmentos()[0].getInicio().comprimentoSeg(this.getSegmentos()[1].getFim());
        double diagonal_two = this.getSegmentos()[3].getInicio().comprimentoSeg(this.getSegmentos()[0].getFim());
        int diagonal_one_square = (int)pow(diagonal_one,2);
        int diagonal_two_square = (int)pow(diagonal_two,2);
        double first_check = (pow(x,2) + pow(y,2));
        double second_check = (pow(z,2) + (pow(w,2)));
        double third_check = (pow(z,2) + (pow(y,2)));
        double forth_check = (pow(x,2) + (pow(w,2)));
        if((diagonal_one_square != Math.round(first_check)) && (diagonal_one_square != Math.round(second_check))
                && (diagonal_two_square != Math.round(third_check)) && (diagonal_two_square != Math.round(forth_check)))
        {
            throw new RetanguloException("Retangulo:vi");
        }
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Poligono, redefino desta
     * maneira o toString da SuperClasse Poligono.
     */
    @Override
    public String toString()
    {
        StringBuilder s1 = new StringBuilder("Retangulo: [");
        for(int i = 0; i < this.getPoligonoPontos().length; i++ )
        {
            s1.append(this.getPoligonoPontos()[i].toString());
            s1.append(", ");
        }
        s1.delete(s1.length() - 2,s1.length());
        s1.append("]\n");
        return s1.toString();
    }
}
