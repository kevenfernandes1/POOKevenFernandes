package lista3_colecoes_arquitetura.missao5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControleEstoquePDV {
    private Map<String, Produto> mapaEstoque;

    public ControleEstoquePDV() {
        this.mapaEstoque = new HashMap<>();
    }

    public void cadastrarProduto(Produto produto) {
        if (produto != null && produto.getCodigo() != null) {
            mapaEstoque.put(produto.getCodigo(), produto);
        }
    }

    // Consulta direta O(1) pelo codigo usando get
    public Produto consultarPorCodigo(String codigo) {
        if (codigo == null) return null;
        return mapaEstoque.get(codigo);
    }

    public Set<String> obterTodasChaves() {
        return mapaEstoque.keySet();
    }

    public Collection<Produto> obterTodosProdutos() {
        return mapaEstoque.values();
    }

    public Map<String, Produto> getMapaEstoque() {
        return mapaEstoque;
    }
}
