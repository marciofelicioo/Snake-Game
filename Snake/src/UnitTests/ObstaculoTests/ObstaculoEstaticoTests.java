package UnitTests.ObstaculoTests;

import Exceptions.*;
import Figuras_Geometricas.Poligono;
import Figuras_Geometricas.Ponto;
import Obstaculos.Obstaculo;
import Obstaculos.ObstaculoEstatico;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe ObstaculoEstatico
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class ObstaculoEstaticoTests {
    /**
     * Testa o comportamento do construtor com um polígono válido.
     */
    @Test
    public void testConstructorWithValidPolygonObstaculo() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1), new Ponto(0, 1)});
        Obstaculo obstaculo = new ObstaculoEstatico(poligono);
        assertNotNull("A forma do obstáculo não deve ser null após a inicialização", obstaculo.getForma());
        assertEquals("A forma do obstáculo deve corresponder ao polígono fornecido", poligono, obstaculo.getForma());
    }


    /**
     * Testa a igualdade entre dois obstáculos com a mesma forma.
     */
    @Test
    public void testEqualityWithSameFormObstaculo() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1), new Ponto(0, 1)});
        Obstaculo obstaculo1 = new ObstaculoEstatico(poligono);
        Obstaculo obstaculo2 = new ObstaculoEstatico(poligono);
        assertEquals("Obstáculos com a mesma forma devem ser considerados iguais", obstaculo1, obstaculo2);
    }

    /**
     * Testa a desigualdade entre dois obstáculos com formas diferentes.
     */
    @Test
    public void testInequalityWithDifferentFormObstaculo() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono1 = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)});
        Poligono poligono2 = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(2, 0), new Ponto(2, 2)});
        Obstaculo obstaculo1 = new ObstaculoEstatico(poligono1);
        Obstaculo obstaculo2 = new ObstaculoEstatico(poligono2);
        assertNotEquals("Obstáculos com formas diferentes não devem ser considerados iguais", obstaculo1, obstaculo2);
    }

    /**
     * Testa a representação textual de um obstáculo.
     */
    @Test
    public void testObstaculoToString() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1), new Ponto(0, 1)});
        Obstaculo obstaculo = new ObstaculoEstatico(poligono);
        String expectedString = "Obstaculo com forma: " + poligono;
        assertEquals("ToString deve retornar a representação correta do obstáculo", expectedString, obstaculo.toString());
    }
//**************************************************************************************************************************************************************************************************************************
    /**
     * Testa o comportamento do construtor com um polígono válido.
     */
    @Test
    public void testConstructorWithValidPolygonObstaculo2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});
        Obstaculo obstaculo = new ObstaculoEstatico(poligono);
        assertNotNull("A forma do obstáculo não deve ser null após a inicialização", obstaculo.getForma());
        assertEquals("A forma do obstáculo deve corresponder ao polígono fornecido", poligono, obstaculo.getForma());
    }

    /**
     * Testa a igualdade entre dois obstáculos com a mesma forma.
     */
    @Test
    public void testEqualityWithSameFormObstaculo2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});
        Obstaculo obstaculo1 = new ObstaculoEstatico(poligono);
        Obstaculo obstaculo2 = new ObstaculoEstatico(poligono);
        assertEquals("Obstáculos com a mesma forma devem ser considerados iguais", obstaculo1, obstaculo2);
    }

    /**
     * Testa a desigualdade entre dois obstáculos com formas diferentes.
     */
    @Test
    public void testInequalityWithDifferentFormObstaculo2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono1 = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});
        Poligono poligono2 = new Poligono(new Ponto[]{new Ponto(1, 1), new Ponto(3, 1), new Ponto(3, 3)});
        Obstaculo obstaculo1 = new ObstaculoEstatico(poligono1);
        Obstaculo obstaculo2 = new ObstaculoEstatico(poligono2);
        assertNotEquals("Obstáculos com formas diferentes não devem ser considerados iguais", obstaculo1, obstaculo2);
    }

    /**
     * Testa a representação textual de um obstáculo.
     */
    @Test
    public void testObstaculoToString2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Poligono poligono = new Poligono(new Ponto[]{new Ponto(5,0), new Ponto(5,4), new Ponto(9,4), new Ponto(9,0)});
        Obstaculo obstaculo = new ObstaculoEstatico(poligono);
        String expectedString = "Obstaculo com forma: " + poligono;
        assertEquals("ToString deve retornar a representação correta do obstáculo", expectedString, obstaculo.toString());
    }

}
