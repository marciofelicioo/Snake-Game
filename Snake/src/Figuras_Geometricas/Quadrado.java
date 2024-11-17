package Figuras_Geometricas;
import Exceptions.*;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.pow;

/**
 * Classe Quadrado: Esta classe sendo ela subclasse de Poligono e Retangulo contém um construtor que cria um objeto Quadrado
 * e o seu principal objetivo é verificar se o mesmo é bem formado através da respetiva invariante
 * @author Márcio Felício
 * @version 1.0
 * @inv Não pode haver um Quadrado cujo número de segmentos seja diferente de quatro e inclusive que os lados todos
 * tenham um comprimento diferente de um do outro
 */
public class Quadrado extends Retangulo {
    /**
     * Este método é um construtor por omissão de objetos da classe Quadrado
     */
    public Quadrado()
    {
        super();
    }

    /**
     * Este método é um construtor de inicialização de objetos da classe Quadrado
     * @param pontos representa um array de objetos do tipo Ponto
     */
    public Quadrado(Ponto[] pontos) throws ColinearityIntersectionNumberPointsException, SegmentoException, RetaException, RetanguloException, QuadradoException, TrianguloException {
        super(pontos);
        verify();
    }
    /**
     * Este método é um construtor de cópia de objetos da classe Quadrado
     * @param quadrado representa o objeto Quadrado cuja estrutura interna pretende-se copiar
     */
    public Quadrado(Quadrado quadrado)
    {
        this.setSegmentos(quadrado.getSegmentos());
    }
    /**
     * Este método protected é uma pré condição para verificar se conseguimos obter um objeto Quadrado
     * A primeira verificação é feita através do método getSegmentos() herdado e o valor retornado tem de ser quatro
     * A penultima verificação que faço é através dos comprimentos dos lados do quadrado cujo valor tem de ser igual
     * A ultima verificação que faço é ver se os angulos internos são de 90 graus
     */
    @Override
    protected void verify() throws QuadradoException
    {
        if(this.getSegmentos().length != 4)
        {
            System.out.println(this.getSegmentos().length);
            throw new QuadradoException("Quadrado:vi");
        }
        double x = Math.ceil(this.getSegmentos()[0].getInicio().comprimentoSeg(this.getSegmentos()[0].getFim()));
        double y = Math.ceil(this.getSegmentos()[1].getInicio().comprimentoSeg(this.getSegmentos()[1].getFim()));
        double z = Math.ceil(this.getSegmentos()[2].getInicio().comprimentoSeg(this.getSegmentos()[2].getFim()));
        double w = Math.ceil(this.getSegmentos()[3].getInicio().comprimentoSeg(this.getSegmentos()[3].getFim()));
        if((x*4)!=(x+y+z+w) || x == 0){
            throw new QuadradoException("Quadrado:vi");
        }
        double diagonal_one = this.getSegmentos()[0].getInicio().comprimentoSeg(this.getSegmentos()[1].getFim());
        double diagonal_two = this.getSegmentos()[3].getInicio().comprimentoSeg(this.getSegmentos()[0].getFim());
        double diagonal_one_square = pow(diagonal_one,2);
        double diagonal_two_square = pow(diagonal_two,2);
        double first_check = (pow(x,2) + pow(y,2));
        double second_check = (pow(z,2) + (pow(w,2)));
        double third_check = (pow(z,2) + (pow(y,2)));
        double forth_check = (pow(x,2) + (pow(w,2)));
        if(((int)diagonal_one_square != Math.round(first_check)) && ((int)diagonal_one_square != Math.round(second_check))
                && ((int)diagonal_two_square != Math.round(third_check)) && ((int)diagonal_two_square != Math.round(forth_check)))
        {
            throw new QuadradoException("Quadrado:vi");
        }
    }
    /**
     * Este método translada um Quadrado consoante as coordenadas de um novo centroide para o Quadrado
     * @param novoX representa a coordenada x do novo centroide
     * @param novoY representa a coordenada y do novo centroide
     * @return new Quadrado(pontosTransladados.toArray(new Ponto[0]));
     */
    @Override
    public Poligono transladaPoligonoCentroide(double novoX, double novoY) throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto centroideAtual = calculaCentroide();
        double deltaX = novoX - centroideAtual.getX();
        double deltaY = novoY - centroideAtual.getY();

        List<Ponto> pontosTransladados = new ArrayList<>();

        for (Ponto ponto : this.getPoligonoPontos()) {
            double xTransladado = (ponto.getX() + deltaX) >= 0 ? (ponto.getX() + deltaX) : 0;
            double yTransladado = (ponto.getY() + deltaY) >= 0 ? (ponto.getY() + deltaY) : 0;
            pontosTransladados.add(new Ponto(xTransladado,yTransladado));
        }

        return new Quadrado(pontosTransladados.toArray(new Ponto[0]));
    }

    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Poligono, redefino desta
     * maneira o toString da SuperClasse Retangulo que é subclasse de Poligono.
     */
    @Override
    public String toString()
    {
        StringBuilder s1 = new StringBuilder("Quadrado: [");
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
