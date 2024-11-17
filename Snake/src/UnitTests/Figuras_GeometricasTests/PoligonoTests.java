package UnitTests.Figuras_GeometricasTests;

import Exceptions.*;
import Figuras_Geometricas.Poligono;
import Figuras_Geometricas.Ponto;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Poligono
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class PoligonoTests {
    /**
     * Este teste tem o principal objetivo de testar se o construtor do poligono forma realmente
     * o objeto poligono através do método ToString() com a asserção assertEquals().
     */
    @Test
    public void testconstructorPoligono1() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[3];
        Ponto[] p2 = new Ponto[4];

        p1[0] = new Ponto(0,1);
        p1[1] = new Ponto(2,1);
        p1[2] = new Ponto(2,0);
        p2[0] = new Ponto(0,0);
        p2[1] = new Ponto(1,1);
        p2[2] = new Ponto(3,0);
        p2[3] = new Ponto(3,4);

        assertEquals("Poligono de 3 vertices: [(0,1), (2,1), (2,0)]\n",new Poligono(p1).toString());
        assertEquals("Poligono de 4 vertices: [(0,0), (1,1), (3,0), (3,4)]\n",new Poligono(p2).toString());
    }
    @Test
    public void testconstructorPoligono2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p = new Ponto[6];

        p[0] = new Ponto(0,0);
        p[1] = new Ponto(1,2);
        p[2] = new Ponto(2,1);
        p[3] = new Ponto(3,3);
        p[4] = new Ponto(4,2);
        p[5] = new Ponto(5,0);

        assertEquals("Poligono de 6 vertices: [(0,0), (1,2), (2,1), (3,3), (4,2), (5,0)]\n",new Poligono(p).toString());
    }
    @Test
    public void testconstructorPoligono3() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[5];
        Ponto[] p2 = new Ponto[6];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(1,0);
        p1[2] = new Ponto(1,1);
        p1[3] = new Ponto(0.5,1.5);
        p1[4] = new Ponto(0,1);

        p2[0] = new Ponto(0,0);
        p2[1] = new Ponto(1,1);
        p2[2] = new Ponto(1,2);
        p2[3] = new Ponto(1.5,3);
        p2[4] = new Ponto(0.5,2.5);
        p2[5] = new Ponto(0,2);

        assertEquals("Poligono de 5 vertices: [(0,0), (1,0), (1,1), (1,2), (0,1)]\n", new Poligono(p1).toString());
        assertEquals("Poligono de 6 vertices: [(0,0), (1,1), (1,2), (2,3), (1,3), (0,2)]\n", new Poligono(p2).toString());
    }
    /**
     * Este teste tem o principal objetivo de testar se dois poligonos intercetam se ou não
     * através das asserções assertTrue e assertFalse
     */
    @Test
    public void testPolygonIntersection1() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[3];
        Ponto[] p2 = new Ponto[3];
        Ponto[] p3 = new Ponto[3];

        p1[0] = new Ponto(0,1);
        p1[1] = new Ponto(2,1);
        p1[2] = new Ponto(2,0);
        p2[0] = new Ponto(0,0);
        p2[1] = new Ponto(1,1);
        p2[2] = new Ponto(3,0);
        p3[0] = new Ponto(4,0);
        p3[1] = new Ponto(7,0);
        p3[2] = new Ponto(5.5,1);

        Poligono pol = new Poligono(p1);
        Poligono pol_1 = new Poligono(p2);
        Poligono pol_2 = new Poligono(p3);

        assertTrue(pol.intersectaPoligono(pol_1));
        assertFalse(pol.intersectaPoligono(pol_2));
        assertFalse(pol_1.intersectaPoligono(pol_2));
    }
    @Test
    public void testPolygonIntersection2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[4];
        Ponto[] p2 = new Ponto[4];
        Ponto[] p3 = new Ponto[4];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(2,0);
        p1[2] = new Ponto(2,2);
        p1[3] = new Ponto(0,2);
        p2[0] = new Ponto(1,1);
        p2[1] = new Ponto(3,1);
        p2[2] = new Ponto(3,3);
        p2[3] = new Ponto(1,3);
        p3[0] = new Ponto(4,0);
        p3[1] = new Ponto(6,0);
        p3[2] = new Ponto(6,2);
        p3[3] = new Ponto(4,2);

        Poligono pol = new Poligono(p1);
        Poligono pol_1 = new Poligono(p2);
        Poligono pol_2 = new Poligono(p3);

        assertTrue(pol.intersectaPoligono(pol_1));
        assertFalse(pol.intersectaPoligono(pol_2));
        assertFalse(pol_1.intersectaPoligono(pol_2));
    }

    @Test
    public void testPolygonIntersection3() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[3];
        Ponto[] p2 = new Ponto[3];
        Ponto[] p3 = new Ponto[3];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(2,0);
        p1[2] = new Ponto(1,2);
        p2[0] = new Ponto(1,1);
        p2[1] = new Ponto(3,1);
        p2[2] = new Ponto(2,3);
        p3[0] = new Ponto(4,0);
        p3[1] = new Ponto(6,0);
        p3[2] = new Ponto(5,2);

        Poligono pol = new Poligono(p1);
        Poligono pol_1 = new Poligono(p2);
        Poligono pol_2 = new Poligono(p3);

        assertTrue(pol.intersectaPoligono(pol_1));
        assertFalse(pol.intersectaPoligono(pol_2));
        assertFalse(pol_1.intersectaPoligono(pol_2));
    }

    @Test
    public void testPolygonIntersection4() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] p1 = new Ponto[4];
        Ponto[] p2 = new Ponto[4];
        Ponto[] p3 = new Ponto[4];

        p1[0] = new Ponto(0,0);
        p1[1] = new Ponto(3,0);
        p1[2] = new Ponto(3,3);
        p1[3] = new Ponto(0,3);
        p2[0] = new Ponto(1,1);
        p2[1] = new Ponto(4,1);
        p2[2] = new Ponto(4,4);
        p2[3] = new Ponto(1,4);
        p3[0] = new Ponto(5,0);
        p3[1] = new Ponto(8,0);
        p3[2] = new Ponto(8,3);
        p3[3] = new Ponto(5,3);

        Poligono pol = new Poligono(p1);
        Poligono pol_1 = new Poligono(p2);
        Poligono pol_2 = new Poligono(p3);

        assertTrue(pol.intersectaPoligono(pol_1));
        assertFalse(pol.intersectaPoligono(pol_2));
        assertFalse(pol_1.intersectaPoligono(pol_2));
    }
    /**
     * Este teste tem como principal objetivo verificar se o método CalculaCentroide esta operacional
     * Começámos por criar um array de pontos e logo a seguir um poligono com o array de pontos passados como
     * parametro.
     * A seguir cria-se um ponto com o centroide do poligono esperado e após isso o centroide calculado
     * Após isso verificámos se são iguais através do equals e o asserTrue trata de retornar true caso sejam
     * de facto iguais
     */
    @Test
    public void testCalculaCentroide1() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(4, 0), new Ponto(4, 3), new Ponto(0, 3)};
        Poligono poligono = new Poligono(pontos);

        Ponto centroideEsperado = new Ponto(2, 1.5);

        Ponto centroideCalculado = poligono.calculaCentroide();

        assertEquals(centroideEsperado, centroideCalculado);
    }
    @Test
    public void testCalculaCentroide2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] pontos = {new Ponto(1, 1), new Ponto(5, 1), new Ponto(5, 5), new Ponto(1, 5)};
        Poligono poligono = new Poligono(pontos);

        Ponto centroideEsperado = new Ponto(3, 3);

        Ponto centroideCalculado = poligono.calculaCentroide();

        assertEquals(centroideEsperado, centroideCalculado);
    }
    @Test
    public void testCalculaCentroide3() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] pontos = {new Ponto(0,0), new Ponto(1, 1), new Ponto(1, 2), new Ponto(1.5, 3),
                new Ponto(0.5, 2.5), new Ponto(0, 2)};
        Poligono poligono = new Poligono(pontos);
        Ponto centroideEsperado = new Ponto(((double) 4 /6), 1.75);

        Ponto centroideCalculado = poligono.calculaCentroide();

        assertEquals(centroideEsperado, centroideCalculado);
    }
    @Test
    public void testCalculaCentroide4() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] pontos = {new Ponto(1, 1), new Ponto(5, 1), new Ponto(5, 5)};
        Poligono poligono = new Poligono(pontos);

        Ponto centroideEsperado = new Ponto(((double) 11 /3), ((double) 7 /3));

        Ponto centroideCalculado = poligono.calculaCentroide();

        assertEquals(centroideEsperado, centroideCalculado);
    }
    /**
     * Este teste tem como principal objetivo verificar se o método TransladaPoligonoCentroide está operacional
     * Começámos por criar um array de pontos iniciais e logo a seguir um poligono inicial com o array de pontos
     *  iniciais passado como parametro.
     * A seguir criámos os deslocamentos pelo eixo do x e eixo do y respetivamente.
     * Após isso criámos uma lista de pontos pontosEsperados que representa o resultado esperado da aplicação do método
     * estático transladaPoligonoCentroide e passo a lista como parametro ao construtor de um poligono novo
     * Por fim comparámos através do método equals se o poligonoEsperado é igual ao poligonoTransladado e o assertTrue trata
     * de verificar se o resultado do método equals é true
     */
    @Test
    public void testTransladaPoligonoCentroide1() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] pontosIniciais = {new Ponto(3, 3), new Ponto(5, 3), new Ponto(5, 5), new Ponto(3, 5)};
        Poligono poligonoInicial = new Poligono(pontosIniciais);

        int NovoCentroideX = 2;
        int NovoCentroideY = 3;
        int dx = (int)(NovoCentroideX - poligonoInicial.calculaCentroide().getX());
        int dy = (int)(NovoCentroideY - poligonoInicial.calculaCentroide().getY());

        Ponto[] pontosEsperados = {new Ponto(3 + dx, 3 + dy),
                new Ponto(5 + dx, 3 + dy), new Ponto(5 + dx, 5 + dy), new Ponto(3 + dx, 5 + dy)};

        Poligono poligonoEsperado = new Poligono(pontosEsperados);

        Poligono poligonoTransladado = poligonoInicial.transladaPoligonoCentroide(NovoCentroideX, NovoCentroideY);

        assertEquals(poligonoEsperado, poligonoTransladado);
    }
    @Test
    public void testTransladaPoligonoCentroide2() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] pontosIniciais = {new Ponto(1, 1), new Ponto(3, 1), new Ponto(3, 3), new Ponto(1, 3)};
        Poligono poligonoInicial = new Poligono(pontosIniciais);

        int novoCentroideX = 2;
        int novoCentroideY = 2;
        int dx = novoCentroideX - (int) poligonoInicial.calculaCentroide().getX();
        int dy = novoCentroideY - (int) poligonoInicial.calculaCentroide().getY();

        Ponto[] pontosEsperados = {new Ponto(1 + dx, 1 + dy), new Ponto(3 + dx, 1 + dy),
                new Ponto(3 + dx, 3 + dy), new Ponto(1 + dx, 3 + dy)};
        Poligono poligonoEsperado = new Poligono(pontosEsperados);

        Poligono poligonoTransladado = poligonoInicial.transladaPoligonoCentroide(novoCentroideX, novoCentroideY);

        assertEquals(poligonoEsperado, poligonoTransladado);
    }
    @Test
    public void testTransladaPoligonoCentroide3() throws ValorNegativoException, TrianguloException, SegmentoException, RetanguloException, ColinearityIntersectionNumberPointsException, QuadradoException, RetaException {
        Ponto[] pontosIniciais = {new Ponto(2, 2), new Ponto(4, 2), new Ponto(4, 4), new Ponto(2, 4)};
        Poligono poligonoInicial = new Poligono(pontosIniciais);

        int novoCentroideX = 3;
        int novoCentroideY = 3;
        int dx = novoCentroideX - (int) poligonoInicial.calculaCentroide().getX();
        int dy = novoCentroideY - (int) poligonoInicial.calculaCentroide().getY();

        Ponto[] pontosEsperados = {new Ponto(2 + dx, 2 + dy), new Ponto(4 + dx, 2 + dy),
                new Ponto(4 + dx, 4 + dy), new Ponto(2 + dx, 4 + dy)};
        Poligono poligonoEsperado = new Poligono(pontosEsperados);

        Poligono poligonoTransladado = poligonoInicial.transladaPoligonoCentroide(novoCentroideX, novoCentroideY);

        assertEquals(poligonoEsperado, poligonoTransladado);
    }

}
