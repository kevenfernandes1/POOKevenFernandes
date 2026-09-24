package lista3_colecoes_arquitetura.missao4;

import java.util.TreeSet;

public class MainMissao4 {
    public static void main(String[] args) {
        Produto p1 = new Produto("PRD04", "Zenfone Asus", 2999.00);
        Produto p2 = new Produto("PRD01", "iPhone 15 Pro", 7200.00);
        Produto p3 = new Produto("PRD05", "Galaxy S24 Ultra", 6500.00);
        Produto p4 = new Produto("PRD02", "Xiaomi Redmi Note", 1400.00);
        Produto p5 = new Produto("PRD03", "Motorola Edge 50", 2200.00);

        // 1. TreeSet com ordem natural (Comparable por nome)
        TreeSet<Produto> catalogoNatural = new TreeSet<>();
        catalogoNatural.add(p1);
        catalogoNatural.add(p2);
        catalogoNatural.add(p3);
        catalogoNatural.add(p4);
        catalogoNatural.add(p5);

        System.out.println("Catalogo ordenado por nome (Comparable):");
        for (Produto p : catalogoNatural) {
            System.out.println(p);
        }

        // 2. TreeSet com comparador customizado (por preco crescente)
        TreeSet<Produto> catalogoPreco = new TreeSet<>(new ComparadorPorPreco());
        catalogoPreco.add(p1);
        catalogoPreco.add(p2);
        catalogoPreco.add(p3);
        catalogoPreco.add(p4);
        catalogoPreco.add(p5);

        System.out.println("\nCatalogo ordenado por preco (Comparator):");
        for (Produto p : catalogoPreco) {
            System.out.println(p);
        }
    }
}
