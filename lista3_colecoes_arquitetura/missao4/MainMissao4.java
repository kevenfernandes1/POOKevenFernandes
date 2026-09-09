package lista3_colecoes_arquitetura.missao4;

import java.util.TreeSet;

public class MainMissao4 {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println(" MISSÃO 4: CLASSIFICAÇÃO AUTOMÁTICA COM TREESET (COMPARABLE/COMPARATOR)");
        System.out.println("======================================================================");

        // Criando produtos fora de ordem
        Produto p1 = new Produto("PRD04", "Zenfone Asus", 2999.00);
        Produto p2 = new Produto("PRD01", "iPhone 15 Pro", 7200.00);
        Produto p3 = new Produto("PRD05", "Galaxy S24 Ultra", 6500.00);
        Produto p4 = new Produto("PRD02", "Xiaomi Redmi Note", 1400.00);
        Produto p5 = new Produto("PRD03", "Motorola Edge 50", 2200.00);

        // --- PARTE 1: TreeSet com Ordenação Natural (Comparable - Nome Alfabético) ---
        // O TreeSet mantém os elementos internamente balanceados em uma Árvore Rubro-Negra (Red-Black Tree),
        // garantindo inserção, remoção e busca com complexidade O(log n).
        System.out.println("\n>>> 1. CATÁLOGO COM ORDENAÇÃO NATURAL (TreeSet + Comparable<Produto>) <<<");
        TreeSet<Produto> catalogoNatural = new TreeSet<>();
        catalogoNatural.add(p1);
        catalogoNatural.add(p2);
        catalogoNatural.add(p3);
        catalogoNatural.add(p4);
        catalogoNatural.add(p5);

        System.out.println("Catálogo Auto-Ordenado por Nome (Ordem Alfabética):");
        for (Produto p : catalogoNatural) {
            System.out.println("  " + p);
        }

        // --- PARTE 2: TreeSet com Ordenação Customizada (Comparator - Preço Crescente) ---
        System.out.println("\n>>> 2. CATÁLOGO COM ORDENAÇÃO CUSTOMIZADA (TreeSet + ComparatorPorPreco) <<<");
        TreeSet<Produto> catalogoPreco = new TreeSet<>(new ComparadorPorPreco());
        catalogoPreco.add(p1);
        catalogoPreco.add(p2);
        catalogoPreco.add(p3);
        catalogoPreco.add(p4);
        catalogoPreco.add(p5);

        System.out.println("Catálogo Auto-Ordenado por Preço (Crescente):");
        for (Produto p : catalogoPreco) {
            System.out.println("  " + p);
        }

        System.out.println("\n======================================================================");
    }
}
