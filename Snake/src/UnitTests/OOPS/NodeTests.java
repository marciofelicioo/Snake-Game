package UnitTests.OOPS;

import OOPS.Node;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe PoligonoTest: Esta classe tem como principal objetivo a realização de testes da classe Node
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 23/04/2024
 */
public class NodeTests {
    /**
     * Testa a definição de coordenadas x e y negativas.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNodeSetNegativeX() {
        new Node(-1, 5, null, 0.0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNodeSetNegativeY() {
        new Node(5, -1, null, 0.0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNodeSetNegativeXY() {
        new Node(-1, -1, null, 0.0, 0.0);
    }

    /**
     * Testa a definição de um parent como a própria instância.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNodeSetSelfAsParent() {
        Node node = new Node(5, 5, null, 0.0, 0.0);
        node.setParent(node); // Should throw IllegalArgumentException
    }

//    /**
//     * Testa a funcionalidade de clonagem do Node.
//     */
//    @Test
//    public void testNodeClone() {
//        Node original = new Node(10, 15, null, 5.0, 10.0);
//        Node clone = original.clone();
//        assertEquals("Cloned Node should be equal to the original", original, clone);
//        assertNotSame("Cloned Node should not be the same object as original", original, clone);
//    }

    /**
     * Testa a igualdade e desigualdade de dois Nodes.
     */
    @Test
    public void testNodeEquality() {
        Node node1 = new Node(10, 15, null, 5.0, 10.0);
        Node node2 = new Node(10, 15, null, 5.0, 10.0);
        Node node3 = new Node(5, 15, null, 5.0, 10.0);

        assertEquals("Nodes with the same state should be equal", node1, node2);
        assertNotEquals("Nodes with different states should not be equal", node1, node3);
        assertNotEquals("Nodes with different states should not be equal", node2, node3);
    }
//**************************************************************************************************************************************************************************************************************************

}
