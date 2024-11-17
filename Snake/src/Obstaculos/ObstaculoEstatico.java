package Obstaculos;
import Exceptions.ValorNegativoException;
import Figuras_Geometricas.Poligono;

/**
 * Classe que representa um obstáculo estático no ambiente de jogo Snake.
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 * @version 1.0 21/04/2024
 * @inv A forma do ObstaculoEstatico não pode ser alterada após sua inicialização.
 */
public class ObstaculoEstatico extends Obstaculo {

    /**
     * Definição do construtor por omissão da classe ObstaculoEstatico
     */
    public ObstaculoEstatico() {
        super();
    }

    /**
     * Definição do construtor de inicialização da classe ObstaculoEstatico
     */
    public ObstaculoEstatico(Poligono forma) throws ValorNegativoException {
        super(forma);
    }

    /**
     * Definição do construtor de cópia da classe ObstaculoEstatico
     */
    public ObstaculoEstatico(ObstaculoEstatico obstaculoEstatico) throws ValorNegativoException {
        super(obstaculoEstatico);
    }

    /**
     * Impede a alteração da forma do obstáculo após sua inicialização. Substitui o método setForma
     * da superclasse para lançar uma exceção se uma tentativa de modificação for feita.
     * @param novaForma A nova forma proposta para o obstáculo.
     * @throws UnsupportedOperationException se tentar alterar a forma.
     */
    @Override
    public void setForma(Poligono novaForma) {
        throw new UnsupportedOperationException("Não é possível alterar a forma de um ObstaculoEstatico.");
    }

    /**
     * Método sobreescrito da superclasse Obstaculo que é mantido vazio para refletir
     * a natureza estática do obstáculo. Este método não realiza nenhuma ação.
     */
    @Override
    public void atualizar() {
        // Método vazio, conforme obstáculos estáticos não requerem atualização.
    }


    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promove-se assim o Principio do encapsulamento
     */
    @Override
    public ObstaculoEstatico clone() {
        try {
            return new ObstaculoEstatico(this);
        } catch (ValorNegativoException e) {
            throw new RuntimeException("Erro ao clonar ObstaculoEstatico: " + e.getMessage(), e);
        }
    }
}
