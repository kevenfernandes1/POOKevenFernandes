package lista2_colecoes_associacoes.nivel4;

import java.util.ArrayList;
import java.util.Collections;

public class MainOrdenacao {

    public static void exibirLista(String titulo, ArrayList<Produto> lista) {
        System.out.println("\n--- " + titulo + " ---");
        for (Produto p : lista) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("  [NÍVEL 4] ORDENAÇÃO COM COMPARABLE E COMPARATOR");
        System.out.println("==========================================================");

        ArrayList<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto("COD04", "Zenfone Asus", 2999.00));
        listaProdutos.add(new Produto("COD01", "iPhone 15 Pro", 7200.00));
        listaProdutos.add(new Produto("COD05", "Galaxy S24 Ultra", 6500.00));
        listaProdutos.add(new Produto("COD02", "Xiaomi Redmi Note", 1400.00));
        listaProdutos.add(new Produto("COD03", "Motorola Edge 50", 2200.00));

        exibirLista("Lista Original (Desordenada)", listaProdutos);

        // [Exercício 4.1] Ordenação Natural com Comparable (Ordem Alfabética de Nome)
        Collections.sort(listaProdutos);
        exibirLista("[Exercício 4.1] Ordenação Natural por Nome (Comparable)", listaProdutos);

        // [Exercício 4.2 a] Ordenação por Preço Crescente com Comparator
        Collections.sort(listaProdutos, new ComparadorPorPreco());
        exibirLista("[Exercício 4.2 a] Ordenação por Preço Crescente (Comparator)", listaProdutos);

        // [Exercício 4.2 b] Ordenação por Preço Decrescente com reverseOrder
        Collections.sort(listaProdutos, Collections.reverseOrder(new ComparadorPorPreco()));
        exibirLista("[Exercício 4.2 b] Ordenação por Preço Decrescente (reverseOrder)", listaProdutos);

        // [Exercício 4.2 c] Ordenação por Código com Comparator
        Collections.sort(listaProdutos, new ComparadorPorCodigo());
        exibirLista("[Exercício 4.2 c] Ordenação por Código (Comparator)", listaProdutos);
    }
}
