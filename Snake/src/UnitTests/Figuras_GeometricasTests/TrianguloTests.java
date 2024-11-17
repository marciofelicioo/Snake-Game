package UnitTests.Figuras_GeometricasTests;

import Exceptions.*;
import Figuras_Geometricas.Ponto;
import Figuras_Geometricas.Triangulo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Triangulo
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class TrianguloTests {
    /**
     * Este teste tem o principal objetivo de testar se o construtor do Triangulo forma realmente
     * o objeto Triangulo através do método ToString() herdado de poligono com a asserção assertEquals().
     */
    @Test
    public void testconstructorTriangulo() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[3];
        Ponto[] p2 = new Ponto[3];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(1,0);
        p1[2] = new Ponto(1,1);
        p2[0] = new Ponto(2,0);
        p2[1] = new Ponto(2,2);
        p2[2] = new Ponto(3,0);
        assertEquals("Triangulo: [(0,0), (1,0), (1,1)]\n",new Triangulo(p1).toString());
        assertEquals("Triangulo: [(2,0), (2,2), (3,0)]\n",new Triangulo(p2).toString());
    }
    @Test
    public void testconstructorTriangulo2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[3];
        Ponto[] p2 = new Ponto[3];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(2,0);
        p1[2] = new Ponto(1,1);
        p2[0] = new Ponto(3,0);
        p2[1] = new Ponto(5,0);
        p2[2] = new Ponto(4,2);

        assertEquals("Triangulo: [(0,0), (2,0), (1,1)]\n", new Triangulo(p1).toString());
        assertEquals("Triangulo: [(3,0), (5,0), (4,2)]\n", new Triangulo(p2).toString());
    }

    @Test
    public void testconstructorTriangulo3() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[3];
        Ponto[] p2 = new Ponto[3];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(3,0);
        p1[2] = new Ponto(2,2);
        p2[0] = new Ponto(4,0);
        p2[1] = new Ponto(7,0);
        p2[2] = new Ponto(5,3);

        assertEquals("Triangulo: [(0,0), (3,0), (2,2)]\n", new Triangulo(p1).toString());
        assertEquals("Triangulo: [(4,0), (7,0), (5,3)]\n", new Triangulo(p2).toString());
    }

    @Test
    public void testconstructorTriangulo4() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[3];
        Ponto[] p2 = new Ponto[3];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(4,0);
        p1[2] = new Ponto(3,3);
        p2[0] = new Ponto(5,0);
        p2[1] = new Ponto(9,0);
        p2[2] = new Ponto(7,4);

        assertEquals("Triangulo: [(0,0), (4,0), (3,3)]\n", new Triangulo(p1).toString());
        assertEquals("Triangulo: [(5,0), (9,0), (7,4)]\n", new Triangulo(p2).toString());
    }

}
