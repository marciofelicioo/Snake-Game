package OOPS;
/**
 * Enumeração que define os tipos possíveis de conteúdo para uma célula no jogo.
 * Este enum é utilizado para determinar o estado atual de uma célula dentro do tabuleiro do jogo,
 * permitindo identificar se ela contém comida, uma parte do corpo da cobra, a cabeça da cobra,
 * um obstáculo, ou se está vazia.
 *
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 *
 * @enum EMPTY Indica que a célula está vazia, não contendo nenhum objeto.
 * @enum FOOD Indica que a célula contém comida, que pode ser consumida pela cobra.
 * @enum OBSTACLE Indica que a célula contém um obstáculo, que não pode ser atravessado pela cobra.
 * @enum SNAKE_BODY Indica que a célula contém uma parte do corpo da cobra.
 * @enum SNAKE_HEAD Indica que a célula contém a cabeça da cobra, que é o principal ponto de movimento e interação com outros elementos do jogo.
 */
public enum CellContent {
    EMPTY,
    FOOD,
    OBSTACLE,
    SNAKE_BODY,
    SNAKE_HEAD
}
