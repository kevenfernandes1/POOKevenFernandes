package lista4_topicos_avancados;

import java.util.SortedSet;
import java.util.TreeSet;

public class GerenciadorSubconjuntos {
    private SortedSet<Produto> catalogo;

    public GerenciadorSubconjuntos() {
        this.catalogo = new TreeSet<>();
    }

    public void adicionarProduto(Produto p) {
        catalogo.add(p);
    }

    // Faixa de preco [min, max[ usando subSet
    public SortedSet<Produto> filtrarPorFaixaPreco(double minPreco, double maxPreco) {
        Produto limiteMin = new Produto("MIN", "", minPreco);
        Produto limiteMax = new Produto("MAX", "", maxPreco);
        return catalogo.subSet(limiteMin, limiteMax);
    }

    // Produtos abaixo do teto usando headSet
    public SortedSet<Produto> filtrarAbaixoDeTeto(double tetoPreco) {
        Produto teto = new Produto("TETO", "", tetoPreco);
        return catalogo.headSet(teto);
    }

    // Produtos a partir do piso usando tailSet
    public SortedSet<Produto> filtrarAcimaDePiso(double pisoPreco) {
        Produto piso = new Produto("PISO", "", pisoPreco);
        return catalogo.tailSet(piso);
    }

    public void exibirCatalogo() {
        System.out.println("Catalogo ordenado por preco (" + catalogo.size() + "):");
        for (Produto p : catalogo) {
            System.out.println(p);
        }
    }

    public SortedSet<Produto> getCatalogo() {
        return catalogo;
    }
}
