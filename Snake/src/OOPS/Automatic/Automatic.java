package OOPS.Automatic;

import Exceptions.*;
import Figuras_Geometricas.*;
import Food.*;
import OOPS.Direction;
import OOPS.Node;
import OOPS.Snake;
import Obstaculos.Obstaculo;

import java.util.*;

public interface Automatic {

    /**
     * Prepara o movimento automático da cobra identificando um caminho até a comida mais próxima.
     * Se nenhuma comida for encontrada, uma exceção é lançada.
     * Se um caminho não for encontrado, uma exceção específica de caminho é lançada.
     * @throws Exception Lança exceções personalizadas se nenhum alimento ou caminho for encontrado.
     */
    void prepareAutoMove() throws Exception;
    /**
     * Executa um movimento automático da cobra seguindo o caminho previamente definido.
     * Caso não haja mais movimentos ou o caminho esteja vazio, tenta preparar um novo caminho.
     * @return boolean Retorna verdadeiro se o movimento for bem-sucedido, falso caso contrário.
     * @throws MovementException Lança uma exceção se o movimento falhar.
     */
    boolean autoMove() throws MovementException;

    /**
     * Encontra um caminho da posição atual da cobra até a comida especificada.
     * Utiliza uma estratégia de busca A* para determinar o caminho mais curto.
     * @param targetFood A comida alvo para a qual encontrar um caminho.
     * @return List<Direction> Lista de direções que compõem o caminho até a comida.
     * @throws ValorNegativoException Se a heurística de custo gerar valores negativos.
     */
    List<Direction> findPathToFood(Food targetFood) throws ValorNegativoException;  // Retorno vazio significa que não há caminho encontrado

    /**
     * Reconstrói o caminho a partir de um nó de destino utilizando a sequência de nós parentes.
     * @param target O nó de destino do qual o caminho será reconstruído.
     * @return List<Direction> Uma lista de direções que precisa ser seguida para ir do início ao nó de destino.
     */
    List<Direction> reconstructPath(Node target);

    /**
     * default method que calcula o custo heurístico (H) para o algoritmo A*.
     * @param x A coordenada x do nó atual.
     * @param y A coordenada y do nó atual.
     * @param targetX A coordenada x do nó de destino.
     * @param targetY A coordenada y do nó de destino.
     * @return double O custo heurístico calculado entre o nó atual e o nó de destino.
     */
    default double calculateHCost(int x, int y, int targetX, int targetY) {
        return Math.abs(targetX - x) + Math.abs(targetY - y);
    }
    /**
     * Verifica se a célula especificada está ocupada por uma parte da cobra.
     * @param x A coordenada x da célula a ser verificada.
     * @param y A coordenada y da célula a ser verificada.
     * @return boolean Verdadeiro se a célula estiver ocupada por uma parte da cobra, falso caso contrário.
     */
    default boolean isCellOccupiedByObstacle(int x, int y, List<Obstaculo> obstaculos) {
        for (Obstaculo obstaculo : obstaculos) {
            for (Ponto p : obstaculo.getForma().getPoligonoPontos()) {
                if ((int)p.getX() == x && (int)p.getY() == y) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Verifica se a célula especificada está ocupada por comida.
     * @param x A coordenada x da célula a ser verificada.
     * @param y A coordenada y da célula a ser verificada.
     * @return boolean Verdadeiro se a célula estiver ocupada por comida, falso caso contrário.
     */
    default boolean isCellOccupiedByFood(int x, int y, List<Food> foods) {
        for (Food food : foods) {
            if ((int)food.getLocation().getX() == x && (int)food.getLocation().getY() == y) {
                return true;
            }
        }
        return false;
    }
    /**
     * Verifica se a célula especificada está ocupada por um obstáculo.
     * @param x A coordenada x da célula a ser verificada.
     * @param y A coordenada y da célula a ser verificada.
     * @return boolean Verdadeiro se a célula estiver ocupada por um obstáculo, falso caso contrário.
     */
    default boolean isCellOccupiedBySnake(int x, int y, Snake snake) {
        for (Ponto ponto : snake.getPontos()) {
            if ((int)ponto.getX() == x && (int)ponto.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
