package lista4_topicos_avancados;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Módulo 1: Visões de Subconjuntos em Árvores Binárias (SortedSet / TreeSet).
 * 
 * [CONCEITO DE SUBCONJUNTOS DO SORTEDSET]:
 * O SortedSet fornece "visões" (views) dinâmicas sobre sub-faixas da árvore balanceada (Red-Black Tree):
 * 1. subSet(fromElement, toElement): Retorna elementos no intervalo semi-aberto [fromElement, toElement[,
 *    ou seja, inclui o 'fromElement' e EXCLUI o 'toElement'.
 * 2. headSet(toElement): Retorna elementos estritamente menores que o teto ]-inf, toElement[.
 * 3. tailSet(fromElement): Retorna elementos maiores ou iguais ao piso [fromElement, +inf[.
 * 
 * Todas as operações operam em tempo O(log n) para localização dos limites e retornam visões
 * conectadas à coleção principal (alterações na visão refletem na coleção original e vice-versa).
 */
public class GerenciadorSubconjuntos {
    private SortedSet<Produto> catalogo;

    public GerenciadorSubconjuntos() {
        this.catalogo = new TreeSet<>();
    }

    public void adicionarProduto(Produto p) {
        catalogo.add(p);
    }

    /**
     * Consulta produtos dentro de uma faixa de preço específica [minPreco, maxPreco[.
     */
    public SortedSet<Produto> filtrarPorFaixaPreco(double minPreco, double maxPreco) {
        // Criamos instâncias de contorno com os preços limites
        Produto limiteInferior = new Produto("MIN", "Limite Min", minPreco);
        Produto limiteSuperior = new Produto("MAX", "Limite Max", maxPreco);
        return catalogo.subSet(limiteInferior, limiteSuperior);
    }

    /**
     * Consulta produtos abaixo de um preço teto (headSet: menor estrito que o teto).
     */
    public SortedSet<Produto> filtrarAbaixoDeTeto(double tetoPreco) {
        Produto teto = new Produto("TETO", "Teto Preco", tetoPreco);
        return catalogo.headSet(teto);
    }

    /**
     * Consulta produtos a partir de um preço piso (tailSet: maior ou igual ao piso).
     */
    public SortedSet<Produto> filtrarAcimaDePiso(double pisoPreco) {
        Produto piso = new Produto("PISO", "Piso Preco", pisoPreco);
        return catalogo.tailSet(piso);
    }

    public void exibirCatalogoCompleto() {
        System.out.println("\n--- Catálogo Geral Ordenado por Preço (" + catalogo.size() + " itens) ---");
        for (Produto p : catalogo) {
            System.out.println("  " + p);
        }
    }

    public SortedSet<Produto> getCatalogo() {
        return catalogo;
    }
}
