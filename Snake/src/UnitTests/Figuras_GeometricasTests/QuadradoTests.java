package UnitTests.Figuras_GeometricasTests;

import Exceptions.*;
import Figuras_Geometricas.Ponto;
import Figuras_Geometricas.Quadrado;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Quadrado
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class QuadradoTests {
    /**
     * Este teste tem o principal objetivo de testar se o construtor do Quadrado forma realmente
     * o objeto Quadrado através do método ToString() herdado de poligono com a asserção assertEquals().
     */
    @Test
    public void testconstructorQuadrado1() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[4];
        Ponto[] p2 = new Ponto[4];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(1,0);
        p1[2] = new Ponto(1,1);
        p1[3] = new Ponto(0,1);
        p2[0] = new Ponto(1,0);
        p2[1] = new Ponto(1,1);
        p2[2] = new Ponto(2,1);
        p2[3] = new Ponto(2,0);

        assertEquals("Quadrado: [(0,0), (1,0), (1,1), (0,1)]\n",new Quadrado(p1).toString());
        assertEquals("Quadrado: [(1,0), (1,1), (2,1), (2,0)]\n",new Quadrado(p2).toString());
    }
    @Test
    public void testconstructorQuadrado2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[4];
        Ponto[] p2 = new Ponto[4];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(2,0);
        p1[2] = new Ponto(2,2);
        p1[3] = new Ponto(0,2);
        p2[0] = new Ponto(1,1);
        p2[1] = new Ponto(3,1);
        p2[2] = new Ponto(3,3);
        p2[3] = new Ponto(1,3);

        assertEquals("Quadrado: [(0,0), (2,0), (2,2), (0,2)]\n", new Quadrado(p1).toString());
        assertEquals("Quadrado: [(1,1), (3,1), (3,3), (1,3)]\n", new Quadrado(p2).toString());
    }


    @Test
    public void testconstructorQuadrado3() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[4];
        Ponto[] p2 = new Ponto[4];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(4,0);
        p1[2] = new Ponto(4,4);
        p1[3] = new Ponto(0,4);
        p2[0] = new Ponto(1,1);
        p2[1] = new Ponto(5,1);
        p2[2] = new Ponto(5,5);
        p2[3] = new Ponto(1,5);

        assertEquals("Quadrado: [(0,0), (4,0), (4,4), (0,4)]\n", new Quadrado(p1).toString());
        assertEquals("Quadrado: [(1,1), (5,1), (5,5), (1,5)]\n", new Quadrado(p2).toString());
    }

}
