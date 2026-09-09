package lista3_colecoes_arquitetura.missao5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Módulo de Ponto de Venda (PDV) e Estoque com indexação O(1) via Tabela Hash.
 */
public class ControleEstoquePDV {
    // 1. Mapa de estoque associando a chave alfanumérica (código de barras) à entidade Produto
    private Map<String, Produto> mapaEstoque;

    public ControleEstoquePDV() {
        this.mapaEstoque = new HashMap<>();
    }

    /**
     * Cadastra ou atualiza um produto no mapa indexado.
     */
    public void cadastrarProduto(Produto produto) {
        if (produto != null && produto.getCodigo() != null) {
            mapaEstoque.put(produto.getCodigo(), produto);
            System.out.println("[PDV - CADASTRO] Produto indexado com sucesso no HashMap: " + produto.getCodigo());
        }
    }

    /**
     * Realiza a consulta instantânea do produto pela chave (Código de Barras).
     * 
     * [COMPLEXIDADE COMPUTACIONAL]:
     * Recuperação direta via Tabela Hash com complexidade média O(1) [Constante].
     * Não utiliza nenhum laço de repetição (for/while) para busca sequencial linear O(n).
     * 
     * @param codigoChave Código do produto.
     * @return Objeto Produto associado à chave ou null caso não encontrado.
     */
    public Produto consultarPorCodigo(String codigoChave) {
        if (codigoChave == null) return null;
        return mapaEstoque.get(codigoChave); // Acesso direto em tempo constante O(1)
    }

    /**
     * Retorna o conjunto com todas as chaves (códigos) registradas.
     * @return Set contendo as chaves do Map.
     */
    public Set<String> obterTodasChaves() {
        return mapaEstoque.keySet();
    }

    /**
     * Retorna a coleção com todos os produtos armazenados no valor do mapa.
     * @return Collection contendo os valores do Map.
     */
    public Collection<Produto> obterTodosProdutos() {
        return mapaEstoque.values();
    }

    public Map<String, Produto> getMapaEstoque() {
        return mapaEstoque;
    }
}
