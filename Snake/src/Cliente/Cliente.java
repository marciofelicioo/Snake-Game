package Cliente;

import Exceptions.*;
import OOPS.Arena;
import OOPS.Sound.Sound;
import OOPS_UI.Game;


/**
 * Classe Cliente, para testar as outras classes e casos possiveis caso um cliente queira usar.
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 */
public class Cliente {
    /**
     * Método principal que executa o jogo.
     * Cria uma nova instância da classe Arena e, em seguida, tenta iniciar o jogo.
     * Se algum erro de inicialização ocorrer (como valores inválidos ou problemas na criação dos elementos do jogo),
     * ele exibe uma mensagem de erro e tenta novamente. O loop continua até que o jogo seja iniciado com sucesso.
     *
     * @param args Argumentos de linha de comando não utilizados neste contexto.
     */
    public static void main(String[] args) {
        while (true)
        {
            try {
                Arena arena = new Arena(30, 30);
                new Game(arena);
                System.out.println("Jogo iniciado com sucesso.");
                Sound y = new Sound();
                y.Sound();
                break;
            } catch (ValorNegativoException | ColinearityIntersectionNumberPointsException | SegmentoException
                     | RetaException | RetanguloException | QuadradoException | TrianguloException | DimensionException u) {
                System.out.println("Erro ao iniciar o jogo: " + u.getMessage());
                System.out.println("Tentando novamente...");
            }
        }
    }
}



