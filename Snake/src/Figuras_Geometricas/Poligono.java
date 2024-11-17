package Figuras_Geometricas;

import Exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Classe Poligono: Esta classe contém um construtor que cria um objeto poligono
 * e o seu principal objetivo é retornar o perimetro do mesmo
 * @author Márcio Felício
 * @version 1.0
 * @inv Não pode haver um poligono com menos de três pontos, três pontos consecutivos colineares e
 * dois segmentos de reta que se cruzam
 */
public class Poligono {

    /**
     * Definição da estrutura do objeto Poligono que no caso é um array de segmentos
     */
    private final SegmentoReta[] segmentos;

    /**
     * Este método é um construtor por omissão de objetos da classe Poligono
     */
    public Poligono()
    {
        this.segmentos = null;
    }
    /**
     * Este método é um construtor de inicialização de objetos da classe Poligono
     * @param p1 representa um array de objetos do tipo Ponto
     */
    public Poligono(Ponto[] p1) throws ColinearityIntersectionNumberPointsException,
            SegmentoException, RetaException, RetanguloException, QuadradoException, TrianguloException {
        this.segmentos = new SegmentoReta[p1.length];
        for(int i = 0; i < p1.length - 1; i++)
        {
            this.segmentos[i] = new SegmentoReta(p1[i],p1[i + 1]);
        }
        this.segmentos[p1.length - 1] = new SegmentoReta(p1[p1.length - 1],p1[0]);
        verify();
    }

    /**
     * Este método é um construtor de cópia de objetos da classe Poligono
     * @param pol representa o objeto poligono cuja estrutura interna pretende-se copiar
     */
    public Poligono(Poligono pol)
    {
        this.segmentos = pol.getSegmentos();
    }
    /**
     * Este método privado é uma pré condição para verificar se conseguimos obter um objeto poligono
     * A verificação é feita através da intercessão entre os segmentos de reta do objeto poligono
     * e também através da colinearidade de três pontos
     */
    protected void verify() throws ColinearityIntersectionNumberPointsException, RetaException,
            SegmentoException,RetanguloException, TrianguloException, QuadradoException{
        int numero_Pontos = 0;
        for(int i = 0; i < this.segmentos.length; i++)
        {
            numero_Pontos++;
            Reta R1 = new Reta(this.segmentos[i].getInicio(),this.segmentos[i].getFim());
            Ponto p3 = getPoligonoPontos()[(i + 2) % getPoligonoPontos().length];
            if(R1.colinearity(p3))
            {
                throw new ColinearityIntersectionNumberPointsException("Poligono:vi");
            }
        }
        if(!(numero_Pontos > 2) || checkIntersection()) {
            throw new ColinearityIntersectionNumberPointsException("Poligono:vi");
        }
    }

    /**
     * @return o array de segmentos do objeto poligono recetor da mensagem
     */
    public SegmentoReta[] getSegmentos()
    {
        return this.segmentos;
    }
    /**
     * Este método tem o principal objetivo de inserir pela primeira vez segementos na estrutura poligono
     * Não modifica nada até porque não será possível pois a estrutura é final, é um método de auxílio para
     * o construtor de cópia das subclasses de Poligono e não para alteração de estrutura interna
     * @param segmentos que representa os segmentos que irão ser usados para inicializar e não modificar
     *                 a estrutura interna do poligono em questão
     */
    protected void setSegmentos(SegmentoReta[] segmentos){
        for(int i = 0; i < segmentos.length; i++)
        {
            this.segmentos[i] = segmentos[i];
        }
    }

    /**
     * @return o array de pontos do objeto poligono recetor da mensagem
     */
    public Ponto[] getPoligonoPontos()
    {
        Ponto[] pt = new Ponto[this.getSegmentos().length];
        for(int i = 0; i < this.getSegmentos().length; i++)
        {
            pt[i] = new Ponto(this.getSegmentos()[i].getInicio());
        }
        return pt;
    }

    /**
     * @return true caso os segmentos do poligono se intercetem e false caso contrário
     */
    public boolean checkIntersection() throws RetaException, SegmentoException {
        Ponto[] pontos = this.getPoligonoPontos();
        int n = pontos.length;
        SegmentoReta[] segmentos = new SegmentoReta[n];

        /**
         * Aqui crio todos os segmentos de reta para deixar o código mais otimizado possível
         */
        for (int i = 0; i < n; i++) {
            Ponto p1 = pontos[i];
            Ponto p2 = pontos[(i + 1) % n];
            segmentos[i] = new SegmentoReta(p1, p2);
        }

        /**
         * Aqui verifico a interseção entre os segmentos criados
         */
        for (int i = 0; i < n; i++) {
            SegmentoReta s1 = segmentos[i];
            for (int j = i + 2; j < n; j++) {
                SegmentoReta s2 = segmentos[j];
                if (s1.cruzamento(s2)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Este método calcula o centroide de um poligono
     * @return new Ponto(sumX / poligono.getPoligonoPontos().length, sumY / poligono.getPoligonoPontos().length);
     */
    public Ponto calculaCentroide() throws ValorNegativoException {
        double sumX = 0, sumY = 0;
        for (Ponto p : this.getPoligonoPontos()) {
            sumX += p.getX();
            sumY += p.getY();
        }
        return new Ponto(sumX / this.getPoligonoPontos().length, sumY / this.getPoligonoPontos().length);
    }
    /**
     * Este método translada um poligono consoante as coordenadas de um novo centroide para o poligono
     * @param novoX representa a coordenada x do novo centroide
     * @param novoY representa a coordenada y do novo centroide
     * @return new Poligono(pontosTransladados.toArray(new Ponto[0]));
     */
    public Poligono transladaPoligonoCentroide(double novoX, double novoY) throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto centroideAtual = calculaCentroide();
        double deltaX = novoX - centroideAtual.getX();
        double deltaY = novoY - centroideAtual.getY();

        List<Ponto> pontosTransladados = new ArrayList<>();

        for (Ponto ponto : this.getPoligonoPontos()) {
            double xTransladado = (ponto.getX() + deltaX) > 0 ? (ponto.getX() + deltaX) : 0;
            double yTransladado = (ponto.getY() + deltaY) > 0 ? (ponto.getY() + deltaY) : 0;
            pontosTransladados.add(new Ponto(xTransladado,yTransladado));
        }

        return new Poligono(pontosTransladados.toArray(new Ponto[0]));
    }
    /**
     * Este metodo rotaciona o poligono x graus sobre um ponto do poligono e retorna o valor
     * dos pontos após rotação
     * @param angleDegrees representa os graus de rotação
     * @param pivotPoint representa o ponto sobre a qual o poligono será rotacionado
     * @return Poligono;
     */
    public Poligono poligonoRotacao(double angleDegrees, Ponto pivotPoint) throws TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException, ValorNegativoException {
        Ponto[] pontos = new Ponto[this.getPoligonoPontos().length];
        for(int i = 0; i < this.getPoligonoPontos().length; i++)
        {
            pontos[i] = this.getPoligonoPontos()[i].rotatePoint(pivotPoint, angleDegrees);
        }
        return new Poligono(pontos);
    }

    /**
     * @param pol é um poligono que irá ser usado no método para descobrir se o mesmo é intersetado por outro poligono ou não
     * @return true caso intercete e false caso não
     */
    public boolean intersectaPoligono(Poligono pol) throws RetaException, SegmentoException {
        for (int i = 0; i < this.getPoligonoPontos().length - 1; i++) {
            SegmentoReta segmentoTrajetoria = new SegmentoReta(this.getPoligonoPontos()[i], this.getPoligonoPontos()[i + 1]);
            for (int j = 0; j < pol.getPoligonoPontos().length; j++) {
                Ponto ponto1 = pol.getPoligonoPontos()[j];
                Ponto ponto2 = pol.getPoligonoPontos()[(j + 1) % pol.getPoligonoPontos().length];
                SegmentoReta segmentoPoligono = new SegmentoReta(ponto1, ponto2);
                if (segmentoTrajetoria.cruzamento(segmentoPoligono))
                    return true;
            }
        }
        return false;
    }

    /**
     * @return perimetro do poligono
     */
    public double perimetro()
    {
        double perimetro = 0;
        for(int i = 0; i < this.getSegmentos().length - 1; i++)
        {
            perimetro += this.getSegmentos()[i].getInicio().comprimentoSeg(this.getSegmentos()[i + 1].getInicio());
        }
        perimetro += this.getSegmentos()[getSegmentos().length - 1].getInicio().comprimentoSeg(this.getSegmentos()[0].getInicio());
        return perimetro;
    }
    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    /**
     * @param obj serve para verificar se o objeto passado como parametro é ou não igual ao objeto recetor
     * @return true ou false caso seja ou não seja igual
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Poligono)) return false;

        Poligono p1 = (Poligono) obj;
        if(p1.getPoligonoPontos().length != this.getPoligonoPontos().length) return false;

        Ponto[] Poligono_pontos = new Ponto[p1.getPoligonoPontos().length];
        Ponto[] Poligono_pontos2 = new Ponto[this.getPoligonoPontos().length];


        for(int i = 0; i < getPoligonoPontos().length; i++)
        {
            Poligono_pontos[i] = p1.getPoligonoPontos()[i];
            Poligono_pontos2[i] = this.getPoligonoPontos()[i];
        }

        Comparator<Ponto> comparator = Comparator.comparing(Ponto::getX).thenComparing(Ponto::getY);

        Arrays.sort(Poligono_pontos, comparator);
        Arrays.sort(Poligono_pontos2, comparator);
        return Arrays.toString(Poligono_pontos).equals(Arrays.toString(Poligono_pontos2));
    }
    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promovo assim o Principio do encapsulamento
     */
    @Override
    public Poligono clone()
    {
        return new Poligono(this);
    }
    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Poligono, redefino desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString()
    {
        StringBuilder s1 = new StringBuilder("Poligono de ");
        s1.append(this.getPoligonoPontos().length);
        s1.append(" vertices:");
        s1.append(" [");
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
