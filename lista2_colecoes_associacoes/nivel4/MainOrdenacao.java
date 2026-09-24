package lista2_colecoes_associacoes.nivel4;

import java.util.ArrayList;
import java.util.Collections;

public class MainOrdenacao {

    public static void exibirLista(String titulo, ArrayList<Produto> lista) {
        System.out.println("\n" + titulo + ":");
        for (Produto p : lista) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto("COD04", "Zenfone Asus", 2999.00));
        listaProdutos.add(new Produto("COD01", "iPhone 15 Pro", 7200.00));
        listaProdutos.add(new Produto("COD05", "Galaxy S24 Ultra", 6500.00));
        listaProdutos.add(new Produto("COD02", "Xiaomi Redmi Note", 1400.00));
        listaProdutos.add(new Produto("COD03", "Motorola Edge 50", 2200.00));

        exibirLista("Lista Original", listaProdutos);

        // 1. Ordem natural por nome (Comparable)
        Collections.sort(listaProdutos);
        exibirLista("Ordenado por Nome (Comparable)", listaProdutos);

        // 2. Ordem por preco crescente (Comparator)
        Collections.sort(listaProdutos, new ComparadorPorPreco());
        exibirLista("Ordenado por Preco Crescente", listaProdutos);

        // 3. Ordem por preco decrescente
        Collections.sort(listaProdutos, Collections.reverseOrder(new ComparadorPorPreco()));
        exibirLista("Ordenado por Preco Decrescente", listaProdutos);

        // 4. Ordem por codigo
        Collections.sort(listaProdutos, new ComparadorPorCodigo());
        exibirLista("Ordenado por Codigo", listaProdutos);
    }
}
