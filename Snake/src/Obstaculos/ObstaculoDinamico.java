package Obstaculos;
import Exceptions.*;
import Figuras_Geometricas.Poligono;
import Figuras_Geometricas.Ponto;
/**
 * Classe que representa um obstáculo dinâmico no ambiente de jogo Snake.
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 * @inv centroRotacao != null, "O centro de rotação não pode ser nulo, garantindo que o obstáculo possa ser rotacionado corretamente."
 */
public class ObstaculoDinamico extends Obstaculo {
    /**
     * Definição da estrutura do objeto Obstaculo que no caso é um angulo de rotação e um Ponto que indica o centro de rotação
     */
    private double anguloRotacao;
    private Ponto centroRotacao;
    /**
     * Definição do construtor por omissão da classe ObstaculoDinamico
     */
    public ObstaculoDinamico() {
        super();
        this.anguloRotacao = 0;
        this.centroRotacao = null;
    }
    /**
     * Este método é um construtor de inicialização de objetos da classe Obstaculo
     * @param forma representa um Poligono
     * @param anguloRotacao representa o angulo de rotação que será aplicado à forma
     * @param centroRotacao representa o ponto sobre a qual a forma irá rotacionar
     */
    public ObstaculoDinamico(Poligono forma, double anguloRotacao, Ponto centroRotacao) throws ValorNegativoException {
        super(forma);
        this.anguloRotacao = anguloRotacao;
        this.centroRotacao = centroRotacao;
    }
    /**
     * Este método é um construtor de cópia de objetos da classe Obstaculo
     * @param obstaculoDinamico representa o objeto ObstaculoDinamico cuja estrutura interna pretende-se copiar
     */
    public ObstaculoDinamico(ObstaculoDinamico obstaculoDinamico) {
        super(obstaculoDinamico);
        setAnguloRotacao(obstaculoDinamico.getAnguloRotacao());
        setCentroRotacao(obstaculoDinamico.getCentroRotacao());
    }


    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto ObstaculoDinamico
     */
    public double getAnguloRotacao()
    {
        return this.anguloRotacao;
    }

    public Ponto getCentroRotacao()
    {
        return this.centroRotacao;
    }

    /**
     * Estes métodos têm o principal objetivo de modificar a estrutura do objeto ObstaculoDinamico
     */
    public void setAnguloRotacao(double anguloRotacao) {
        this.anguloRotacao = anguloRotacao;
    }

    public void setCentroRotacao(Ponto centroRotacao)
    {
        if (centroRotacao == null) throw new NullPointerException("O centro de rotação não pode ser nulo");
        this.centroRotacao = centroRotacao;
    }

    /**
     * Método que atualiza o estado do obstáculo rotacionando sua forma.
     * @throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException,
     * ColinearityIntersectionNumberPointsException, QuadradoException, RetaException Exceções que podem ocorrer durante a rotação.
     */
    @Override
    public void atualizar() throws TrianguloException, ValorNegativoException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        setForma(getForma().poligonoRotacao(anguloRotacao, centroRotacao));
    }

    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promove-se assim o Principio do encapsulamento
     */

    @Override
    public Obstaculo clone() {
        return new ObstaculoDinamico(this);
    }
}