package UnitTests.FoodTests;

import Exceptions.ValorNegativoException;
import Food.Food;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotSame;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Food
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class FoodTests {
    /**
     * Testa se o construtor da classe Food configura corretamente os valores iniciais
     */
    @Test
    public void testConstructorWithValidArgsFood() throws ValorNegativoException {
        Food food = new Food(5, 5, 10);
        assertNotNull("Location should not be null", food.getLocation()); // Verifica se a localização não é nula
        assertEquals("Size should be set to 10", 10, food.getSize()); // Verifica se o tamanho está correto
    }
    /**
     * Testa a reação ao tentar definir a localização da comida como nula
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetLocationWithNull() throws ValorNegativoException {
        Food food = new Food(5, 5, 10);
        food.setLocation(null);
    }
    /**
     * Testa a reação ao definir um tamanho negativo para a comida
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeSize() throws ValorNegativoException {
        Food food = new Food(5, 5, 10);
        food.setSize(-1);
    }
    /**
     * Testa a igualdade entre duas instâncias de Food com mesmos valores
     */
    @Test
    public void testFoodEquality() throws ValorNegativoException {
        Food food1 = new Food(5, 5, 10);
        Food food2 = new Food(5, 5, 10);
        assertEquals("Food objects with same location and size should be equal", food1, food2);
    }
    /**
     * Testa a desigualdade entre duas instâncias de Food com tamanhos diferentes
     */
    @Test
    public void testFoodInequalityDifferentSize() throws ValorNegativoException {
        Food food1 = new Food(5, 5, 10);
        Food food2 = new Food(5, 5, 5);
        assertNotEquals("Food objects with different sizes should not be equal", food1, food2);
    }
    /**
     * Testa a clonagem de objetos Food
     */
    @Test
    public void testFoodClone() throws ValorNegativoException {
        Food food = new Food(5, 5, 10);
        Food foodClone = food.clone();
        assertEquals("Cloned food should be equal to the original", food, foodClone);
        assertNotSame("Cloned food should not be the same object as original", food, foodClone);
    }
    /**
     * Testa a representação textual de Food
     */
    @Test
    public void testFoodToString() throws ValorNegativoException {
        Food food = new Food(5, 5, 10);
        String expectedString = "(5,5) and size:10";
        assertEquals("ToString should return the correct representation", expectedString, food.toString());
    }
}
