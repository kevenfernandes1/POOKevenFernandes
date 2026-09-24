package lista2_colecoes_associacoes.nivel1;

import java.util.ArrayList;

public class MainCadastro {

    public static Produto buscarPorCodigo(ArrayList<Produto> lista, String codigo) {
        if (lista == null || codigo == null) return null;
        for (Produto p : lista) {
            if (codigo.equalsIgnoreCase(p.getCodigo())) {
                return p;
            }
        }
        return null;
    }

    public static void aplicarReajusteGeral(ArrayList<Produto> lista, double percentual) {
        if (lista == null) return;
        for (Produto p : lista) {
            double novoPreco = p.getPreco() * (1 + (percentual / 100.0));
            p.setPreco(novoPreco);
        }
    }

    public static void main(String[] args) {
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        Produto p1 = new Produto("P001", "Notebook Dell G15", 4800.00);
        Produto p2 = new Produto("P002", "Mouse Sem Fio Logitech", 150.00);
        Produto p3 = new Produto("P003", "Teclado Mecanico RGB", 320.00);
        Produto p4 = new Produto("P004", "Monitor 27 144Hz", 1250.00);

        listaProdutos.add(p1);
        listaProdutos.add(p2);
        listaProdutos.add(p3);
        listaProdutos.add(p4);

        System.out.println("Lista de produtos cadastrados:");
        for (Produto p : listaProdutos) {
            System.out.println(p);
        }
        System.out.println("Total: " + listaProdutos.size());

        // Removendo por indice e por objeto
        System.out.println("\nRemovendo produto no indice 1 e o p3...");
        listaProdutos.remove(1);
        listaProdutos.remove(p3);

        System.out.println("Quantidade atual: " + listaProdutos.size());
        for (Produto p : listaProdutos) {
            System.out.println(p);
        }

        // Testes de busca
        System.out.println("\nBuscando P001:");
        Produto encontrado = buscarPorCodigo(listaProdutos, "P001");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("Produto nao encontrado.");
        }

        System.out.println("\nBuscando P999:");
        Produto naoEncontrado = buscarPorCodigo(listaProdutos, "P999");
        if (naoEncontrado != null) {
            System.out.println("Encontrado: " + naoEncontrado);
        } else {
            System.out.println("Produto nao encontrado.");
        }

        // Reajuste de 10%
        System.out.println("\nAplicando reajuste de 10%:");
        aplicarReajusteGeral(listaProdutos, 10.0);
        for (Produto p : listaProdutos) {
            System.out.println(p);
        }
    }
}
