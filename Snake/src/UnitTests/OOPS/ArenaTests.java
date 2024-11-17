package UnitTests.OOPS;

import Exceptions.DimensionException;
import OOPS.Arena;
import org.junit.Test;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Arena
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class ArenaTests {
    /**
     * Testa a exceção de dimensões inválidas na criação da Arena.
     */
    @Test(expected = DimensionException.class)
    public void testConstructorWithInvalidDimensionsArena1() throws Exception {
        new Arena(-1, 20);
    }
    @Test(expected = DimensionException.class)
    public void testConstructorWithInvalidDimensionsArena2() throws Exception {
        new Arena(20, -1);
    }
    @Test(expected = DimensionException.class)
    public void testConstructorWithInvalidDimensionsArena3() throws Exception {
        new Arena(-1, -1);
    }
}
