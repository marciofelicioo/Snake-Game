package OOPS;

/**
 * Enumeração que define as direções básicas em um plano bidimensional.
 *
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 *
 * Cada direção é associada a um par de valores (dx, dy) que indicam o deslocamento no eixo X e Y, respectivamente.
 * Por exemplo, UP representa um movimento para cima na grade, o que significa um decremento na coordenada Y.
 */
public enum Direction {
    /**
     * Representa a direção para cima. Não altera a coordenada X, decrementa a coordenada Y.
     */
    UP(0, -1),

    /**
     * Representa a direção para baixo. Não altera a coordenada X, incrementa a coordenada Y.
     */
    DOWN(0, 1),

    /**
     * Representa a direção para esquerda. Decrementa a coordenada X, não altera a coordenada Y.
     */
    LEFT(-1, 0),

    /**
     * Representa a direção para direita. Incrementa a coordenada X, não altera a coordenada Y.
     */
    RIGHT(1, 0);

    /**
     * Estrutura para o construtor privado
     */
    final int dx;

    final int dy;

    /**
     * Construtor privado que inicializa a direção com os deslocamentos especificados para X e Y.
     *
     * @param dx Deslocamento horizontal associado a esta direção.
     * @param dy Deslocamento vertical associado a esta direção.
     */
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
