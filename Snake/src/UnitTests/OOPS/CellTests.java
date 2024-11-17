package UnitTests.OOPS;

import OOPS.Cell;
import OOPS.CellContent;
import OOPS.CellPanel;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Cell
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class CellTests {
    /**
     * Testa a criação de um CellPanel com o construtor padrão.
     */
    @Test
    public void testDefaultConstructorCellPanel() {
        CellPanel panel = new CellPanel();
        assertFalse("isCircular should be false by default", panel.getIsCircular());
        assertEquals("Default color should be white", Color.WHITE, panel.getColor());
        assertNotNull("Border should not be null", panel.getBorder());
    }

    /**
     * Testa o ajuste da cor do CellPanel para uma cor válida.
     */
    @Test
    public void testSetColorValidCellPanel() {
        CellPanel panel = new CellPanel();
        panel.setColor(Color.RED);
        assertEquals("Color should be set to red", Color.RED, panel.getColor());
    }

    /**
     * Testa o ajuste da cor para null, o que deve lançar uma exceção.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetColorNullCellPanel() {
        CellPanel panel = new CellPanel();
        panel.setColor(null);  // Deve lançar IllegalArgumentException
    }

    /**
     * Testa a configuração do atributo isCircular.
     */
    @Test
    public void testSetCircularCellPanel() {
        CellPanel panel = new CellPanel();
        panel.setCircular(true);
        assertTrue("isCircular should be true after setting to true", panel.getIsCircular());
    }

    /**
     * Testa a clonagem de um CellPanel.
     */
    @Test
    public void testCloneCellPanel() {
        CellPanel original = new CellPanel();
        original.setCircular(true);
        original.setColor(Color.BLUE);

        CellPanel clone = original.clone();
        assertEquals("Cloned CellPanel should have the same color", original.getColor(), clone.getColor());
        assertEquals("Cloned CellPanel should have the same isCircular value", original.getIsCircular(), clone.getIsCircular());
        assertNotSame("Cloned CellPanel should not be the same instance as original", original, clone);
    }

    /**
     * Testa a representação textual do CellPanel.
     */
    @Test
    public void testToStringCellPanel() {
        CellPanel panel = new CellPanel();
        panel.setColor(new Color(255, 100, 100));
        panel.setCircular(true);

        String expected = "CellPanel{color=java.awt.Color[r=255,g=100,b=100], isCircular=true}";
        assertEquals("ToString should return the correct representation", expected, panel.toString());
    }
    /**
     * Testa a criação de uma Cell usando o construtor padrão.
     */
    @Test
    public void testCellDefaultConstructor() {
        Cell cell = new Cell();
        assertNotNull("Content should not be null on initialization", cell.getContent());
        assertEquals("Default content should be EMPTY", CellContent.EMPTY, cell.getContent());
    }
    /**
     * Testa a definição do conteúdo da célula para um valor não nulo.
     */
    @Test
    public void testCellSetContentValid() {
        Cell cell = new Cell();
        cell.setContent(CellContent.FOOD);
        assertEquals("Content should be set to FOOD", CellContent.FOOD, cell.getContent());
    }
    /**
     * Testa a definição do conteúdo da célula para null, o que deve lançar uma exceção.
     */
    @Test(expected = NullPointerException.class)
    public void testCellSetContentNull() {
        Cell cell = new Cell();
        cell.setContent(null);  // Deve lançar NullPointerException
    }
    /**
     * Testa a clonagem de uma Cell.
     */
    @Test
    public void testCellClone() {
        Cell original = new Cell();
        original.setContent(CellContent.OBSTACLE);

        Cell clone = original.clone();
        assertEquals("Cloned Cell should have the same content as original", original.getContent(), clone.getContent());
        assertNotSame("Cloned Cell should not be the same instance as original", original, clone);
    }
    /**
     * Testa a igualdade e desigualdade entre duas Cells.
     */
    @Test
    public void testCellEquality() {
        Cell cell1 = new Cell();
        cell1.setContent(CellContent.SNAKE_BODY);

        Cell cell2 = new Cell();
        cell2.setContent(CellContent.SNAKE_BODY);

        Cell cell3 = new Cell();
        cell3.setContent(CellContent.EMPTY);

        assertEquals("Cells with the same content should be equal", cell1, cell2);
        assertNotEquals("Cells with different content should not be equal", cell1, cell3);
    }
    /**
     * Testa a representação textual de uma Cell.
     */
    @Test
    public void testCellToString() {
        Cell cell = new Cell();
        cell.setContent(CellContent.SNAKE_HEAD);

        String expected = "Cell{content=SNAKE_HEAD}";
        assertEquals("ToString should return the correct representation", expected, cell.toString());
    }
}
