package Figuras_Geometricas;
import Exceptions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Triangulo: Esta classe sendo ela subclasse de Poligono contém um construtor que cria um objeto Triangulo
 * e o seu principal objetivo é verificar se o mesmo é bem formado através da respetiva invariante
 * @author Márcio Felício
 * @version 1.0
 * @inv Não pode haver um Triangulo cuja soma dos comprimentos de dois lados seja inferior ao comprimento do seu terceiro lado
 */
public class Triangulo extends Poligono {
    /**
     * Este método é um construtor por omissão de objetos da classe Triangulo
     */
    public Triangulo(){super();}
    /**
     * Este método é um construtor de inicialização de objetos da classe Triangulo
     * @param p representa um array de objetos do tipo Ponto
     */
    public Triangulo(Ponto[] p) throws ColinearityIntersectionNumberPointsException, SegmentoException, RetaException, RetanguloException, QuadradoException, TrianguloException{
        super(p);
        verify();
    }
    /**
     * Este método é um construtor de cópia de objetos da classe Triangulo
     * @param tr representa o objeto Triangulo cuja estrutura interna pretende-se copiar
     */
    public Triangulo(Triangulo tr){
        this.setSegmentos(tr.getSegmentos());
    }
    /**
     * Este método protected é uma pré condição para verificar se conseguimos obter um objeto Triangulo
     * A verificação é feita através do método getSegmentos() herdado e o valor retornado tem de ser três
     */

    @Override
    protected void verify() throws TrianguloException{
        if(this.getSegmentos().length != 3)
        {
            throw new TrianguloException("Triangulo:vi");
        }
    }

    /**
     * Este método translada um Triangulo consoante as coordenadas de um novo centroide para o Triangulo
     * @param novoX representa a coordenada x do novo centroide
     * @param novoY representa a coordenada y do novo centroide
     * @return new Triangulo(pontosTransladados.toArray(new Ponto[0]));
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

        return new Triangulo(pontosTransladados.toArray(new Ponto[0]));
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Poligono, redefino desta
     * maneira o toString da SuperClasse Poligono.
     */
    @Override
    public String toString()
    {
        StringBuilder s1 = new StringBuilder("Triangulo: [");
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
