package UnitTests.ObstaculoTests;

import Exceptions.*;
import Figuras_Geometricas.Poligono;
import Figuras_Geometricas.Ponto;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe ObstaculoDinamico
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class ObstaculoDinamicoTests {
    /**
     * Testa a criação de um ObstaculoDinamico com parâmetros válidos.
     */
    @Test
    public void testConstructorWithValidArguments() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)});
        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico obstaculo = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        assertNotNull("O objeto ObstaculoDinamico não deve ser nulo", obstaculo);
        assertEquals("O ângulo de rotação deve ser 45", 45, obstaculo.getAnguloRotacao(), 0.01);
        assertEquals("O centro de rotação deve ser (0.5, 0.5)", centro, obstaculo.getCentroRotacao());
    }

    /**
     * Testa o ajuste do ângulo de rotação.
     */
    @Test
    public void testSetAnguloRotacao() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)});
        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico obstaculo = new Obstaculos.ObstaculoDinamico(poligono, 0, centro);
        obstaculo.setAnguloRotacao(90);
        assertEquals("O ângulo de rotação deve ser atualizado para 90", 90, obstaculo.getAnguloRotacao(), 0.01);
    }

    /**
     * Testa a exceção para definir um centro de rotação nulo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetCentroRotacaoNull() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)});
        Obstaculos.ObstaculoDinamico obstaculo = new Obstaculos.ObstaculoDinamico(poligono, 0, new Ponto(0.5, 0.5));
        obstaculo.setCentroRotacao(null);
    }

    /**
     * Testa a igualdade entre dois ObstaculoDinamico com a mesma forma, ângulo e centro de rotação.
     */
    @Test
    public void testEqualityObstaculoDinamico() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)});
        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico obstaculo1 = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        Obstaculos.ObstaculoDinamico obstaculo2 = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        assertEquals("Dois ObstaculoDinamico com a mesma forma, ângulo e centro de rotação devem ser iguais", obstaculo1, obstaculo2);
    }

    /**
     * Testa a clonagem de um ObstaculoDinamico.
     */
    @Test
    public void testCloneObstaculoDinamico() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)});
        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico original = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        Obstaculos.ObstaculoDinamico clone = (Obstaculos.ObstaculoDinamico) original.clone();
        assertEquals("O objeto clonado deve ser igual ao original", original, clone);
        assertNotSame("O objeto clonado não deve ser o mesmo que o original", original, clone);
    }
//**************************************************************************************************************************************************************************************************************************
    /**
     * Testa a criação de um ObstaculoDinamico com parâmetros válidos.
     */
    @Test
    public void testConstructorWithValidArguments2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico obstaculo = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        assertNotNull("O objeto ObstaculoDinamico não deve ser nulo", obstaculo);
        assertEquals("O ângulo de rotação deve ser 45", 45, obstaculo.getAnguloRotacao(), 0.01);
        assertEquals("O centro de rotação deve ser (0.5, 0.5)", centro, obstaculo.getCentroRotacao());
    }

    /**
     * Testa o ajuste do ângulo de rotação.
     */
    @Test
    public void testSetAnguloRotacao2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico obstaculo = new Obstaculos.ObstaculoDinamico(poligono, 0, centro);
        obstaculo.setAnguloRotacao(90);
        assertEquals("O ângulo de rotação deve ser atualizado para 90", 90, obstaculo.getAnguloRotacao(), 0.01);
    }

    /**
     * Testa a exceção para definir um centro de rotação nulo.
     */
    @Test(expected = NullPointerException.class)
    public void testSetCentroRotacaoNull2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});        Obstaculos.ObstaculoDinamico obstaculo = new Obstaculos.ObstaculoDinamico(poligono, 0, new Ponto(0.5, 0.5));
        obstaculo.setCentroRotacao(null);
    }

    /**
     * Testa a igualdade entre dois ObstaculoDinamico com a mesma forma, ângulo e centro de rotação.
     */
    @Test
    public void testEqualityObstaculoDinamico2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico obstaculo1 = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        Obstaculos.ObstaculoDinamico obstaculo2 = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        assertEquals("Dois ObstaculoDinamico com a mesma forma, ângulo e centro de rotação devem ser iguais", obstaculo1, obstaculo2);
    }

    /**
     * Testa a clonagem de um ObstaculoDinamico.
     */
    @Test
    public void testCloneObstaculoDinamico2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculos.ObstaculoDinamico original = new Obstaculos.ObstaculoDinamico(poligono, 45, centro);
        Obstaculos.ObstaculoDinamico clone = (Obstaculos.ObstaculoDinamico) original.clone();
        assertEquals("O objeto clonado deve ser igual ao original", original, clone);
        assertNotSame("O objeto clonado não deve ser o mesmo que o original", original, clone);
    }
}
