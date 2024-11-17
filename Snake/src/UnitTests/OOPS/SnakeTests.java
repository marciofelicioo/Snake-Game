package UnitTests.OOPS;

import Exceptions.ValorNegativoException;
import OOPS.Direction;
import OOPS.Snake;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Snake
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class SnakeTests {
    /**
     * Testa a funcionalidade de crescimento da cobra.
     */
    @Test
    public void testGrowSnake() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        snake.grow();
        assertEquals("Snake should have additional segment", 2, snake.getPontos().size());
    }

    /**
     * Testa a alteração de direção válida (não oposta).
     */
    @Test
    public void testSetValidDirectionSnake() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        snake.setDirection(Direction.LEFT);  // Valid as it is not opposite to initial RIGHT
        assertEquals("Direction should be updated to LEFT", Direction.RIGHT, snake.getDirection());
    }
//**************************************************************************************************************************************************************************************************************************
    /**
     * Testa a funcionalidade de verificação se um ponto é a cabeça da cobra.
     */
    @Test
    public void testIsHeadSnake() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        assertTrue("Should return true for head position", snake.isHead(5, 5));
    }

    /**
     * Testa a funcionalidade de verificação se um ponto é parte do corpo da cobra.
     */
    @Test
    public void testIsBodySnake() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        snake.grow();
        snake.move();
        assertTrue("Should return true for body position", snake.isBody(5, 5));
    }

    /**
     * Testa a igualdade e desigualdade entre duas instâncias de Snake.
     */
    @Test
    public void testEqualitySnake() throws ValorNegativoException {
        Snake snake1 = new Snake(5, 5, 1);
        Snake snake2 = new Snake(5, 5, 1);
        Snake snake3 = new Snake(5, 6, 1);

        assertEquals("Snakes with the same state should be equal", snake1, snake2);
        assertNotEquals("Snakes with different states should not be equal", snake1, snake3);
    }

    /**
     * Testa a funcionalidade de crescimento da cobra com múltiplos crescimentos.
     */
    @Test
    public void testGrowSnakeMultipleTimes() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        snake.grow();
        snake.grow();
        assertEquals("Snake should have additional segments", 3, snake.getPontos().size());
    }

    /**
     * Testa a alteração de direção válida (não oposta) para direita.
     */
    @Test
    public void testSetValidDirectionRightSnake() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        snake.setDirection(Direction.UP);
        snake.setDirection(Direction.RIGHT);
        assertEquals("Direction should be updated to RIGHT", Direction.RIGHT, snake.getDirection());
    }

    /**
     * Testa a funcionalidade de verificação se um ponto não é a cabeça da cobra.
     */
    @Test
    public void testIsNotHeadSnake() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        assertFalse("Should return false for non-head position", snake.isHead(4, 5));
    }

    /**
     * Testa a funcionalidade de verificação se um ponto não é parte do corpo da cobra.
     */
    @Test
    public void testIsNotBodySnake() throws ValorNegativoException {
        Snake snake = new Snake(5, 5, 1);
        snake.grow();
        snake.move();
        assertFalse("Should return false for non-body position", snake.isBody(4, 5));
    }

    /**
     * Testa a igualdade e desigualdade entre duas instâncias de Snake com crescimentos diferentes.
     */
    @Test
    public void testEqualityWithDifferentGrowthSnake() throws ValorNegativoException {
        Snake snake1 = new Snake(5, 5, 1);
        Snake snake2 = new Snake(5, 5, 2);
        Snake snake3 = new Snake(5, 5, 1);

        assertNotEquals("Snakes with different growth should not be equal", snake1, snake2);
        assertEquals("Snakes with the same state should be equal", snake1, snake3);
    }

}
