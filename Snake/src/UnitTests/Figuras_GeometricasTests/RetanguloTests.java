package UnitTests.Figuras_GeometricasTests;

import Exceptions.*;
import Figuras_Geometricas.Ponto;
import Figuras_Geometricas.Retangulo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Retangulo
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class RetanguloTests {
    /**
     * Este teste tem o principal objetivo de testar se o construtor do Retangulo forma realmente
     * o objeto Retangulo através do método ToString() herdado de poligono com a asserção assertEquals().
     */
    @Test
    public void testconstructorRetangulo1() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
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

        assertEquals("Retangulo: [(0,0), (1,0), (1,1), (0,1)]\n",new Retangulo(p1).toString());
        assertEquals("Retangulo: [(1,0), (1,1), (2,1), (2,0)]\n",new Retangulo(p2).toString());
    }

    @Test
    public void testconstructorRetangulo2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[4];
        Ponto[] p2 = new Ponto[4];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(4,0);
        p1[2] = new Ponto(4,3);
        p1[3] = new Ponto(0,3);

        p2[0] = new Ponto(3,0);
        p2[1] = new Ponto(3,4);
        p2[2] = new Ponto(6,4);
        p2[3] = new Ponto(6,0);

        assertEquals("Retangulo: [(0,0), (4,0), (4,3), (0,3)]\n", new Retangulo(p1).toString());
        assertEquals("Retangulo: [(3,0), (3,4), (6,4), (6,0)]\n", new Retangulo(p2).toString());
    }

    @Test
    public void testconstructorRetangulo3() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[4];
        Ponto[] p2 = new Ponto[4];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(5,0);
        p1[2] = new Ponto(5,4);
        p1[3] = new Ponto(0,4);

        p2[0] = new Ponto(5,0);
        p2[1] = new Ponto(5,4);
        p2[2] = new Ponto(9,4);
        p2[3] = new Ponto(9,0);

        assertEquals("Retangulo: [(0,0), (5,0), (5,4), (0,4)]\n", new Retangulo(p1).toString());
        assertEquals("Retangulo: [(5,0), (5,4), (9,4), (9,0)]\n", new Retangulo(p2).toString());
    }
    @Test
    public void testconstructorRetangulo4() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
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

        assertEquals("Retangulo: [(0,0), (4,0), (4,4), (0,4)]\n", new Retangulo(p1).toString());
        assertEquals("Retangulo: [(1,1), (5,1), (5,5), (1,5)]\n", new Retangulo(p2).toString());
    }
    @Test
    public void testconstructorRetangulo5() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
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

        assertEquals("Retangulo: [(0,0), (2,0), (2,2), (0,2)]\n", new Retangulo(p1).toString());
        assertEquals("Retangulo: [(1,1), (3,1), (3,3), (1,3)]\n", new Retangulo(p2).toString());
    }
    @Test
    public void testconstructorRetangulo6() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
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

        assertEquals("Retangulo: [(0,0), (1,0), (1,1), (0,1)]\n",new Retangulo(p1).toString());
        assertEquals("Retangulo: [(1,0), (1,1), (2,1), (2,0)]\n",new Retangulo(p2).toString());
    }
}
