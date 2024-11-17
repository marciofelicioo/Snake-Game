package UnitTests.FoodTests;

import Exceptions.*;
import Figuras_Geometricas.Poligono;
import Figuras_Geometricas.Ponto;
import Food.Circulo;
import Obstaculos.Obstaculo;
import Obstaculos.ObstaculoEstatico;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Circulo
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class CirculoTests {
    /**
     * Testa o construtor de Circulo com argumentos válidos
     */
    @Test
    public void testConstructorWithValidArgsCirculo() throws ValorNegativoException {
        Circulo circulo = new Circulo(5, 5, 10);
        assertNotNull("Location should not be null", circulo.getLocation());
        assertEquals("Radius should be 5.0", 5.0, circulo.getRadius(), 0.01);
        assertEquals("Size should be set to 10", 10, circulo.getSize());
    }
    /**
     * Testa a definição de um raio negativo para o círculo
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeRadius() {
        Circulo circulo = new Circulo();
        circulo.setRadius(-1.0);
    }
    /**
     * Testa a igualdade entre dois objetos Circulo com os mesmos valores
     */
    @Test
    public void testCirculoEquality() throws ValorNegativoException {
        Circulo circulo1 = new Circulo(5, 5, 10);
        Circulo circulo2 = new Circulo(5, 5, 10);
        assertEquals("Circulo objects with same location and radius should be equal", circulo1, circulo2);
    }
    /**
     * Testa a desigualdade entre dois objetos Circulo com raios diferentes
     */
    @Test
    public void testCirculoInequalityDifferentRadius() throws ValorNegativoException {
        Circulo circulo1 = new Circulo(5, 5, 10);
        Circulo circulo2 = new Circulo(5, 5, 20);
        assertNotEquals("Circulo objects with different radius should not be equal", circulo1, circulo2);
    }
    /**
     * Testa a clonagem de objetos Circulo
     */
    @Test
    public void testCirculoClone() throws ValorNegativoException {
        Circulo circulo = new Circulo(5, 5, 10);
        Circulo circuloClone = circulo.clone();
        assertEquals("Cloned Circulo should be equal to the original", circulo, circuloClone);
        assertNotSame("Cloned Circulo should not be the same object as original", circulo, circuloClone);
    }
    /**
     * Testa a representação textual de Círculo
     */
    @Test
    public void testCirculoToString() throws ValorNegativoException {
        Circulo circulo = new Circulo(5, 5, 10);
        String expectedString = "Circulo{centro=(5,5), raio=5.0}";
        assertEquals("ToString should return the correct representation", expectedString, circulo.toString());
    }
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
}
