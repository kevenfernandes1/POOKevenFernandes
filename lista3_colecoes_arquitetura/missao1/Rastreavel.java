package lista3_colecoes_arquitetura.missao1;

/**
 * Interface que define o contrato de rastreamento para qualquer modalidade logística.
 */
public interface Rastreavel {
    /**
     * Retorna o status atualizado do rastreamento do item.
     * @return String com a situação e detalhes do rastreio.
     */
    String getStatusRastreio();
}
