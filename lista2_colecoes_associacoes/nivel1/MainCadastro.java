package lista2_colecoes_associacoes.nivel1;

import java.util.ArrayList;

public class MainCadastro {

    // [Exercício 1.2] Busca por código
    public static Produto buscarPorCodigo(ArrayList<Produto> lista, String codigo) {
        if (lista == null || codigo == null) return null;
        for (Produto p : lista) {
            if (codigo.equalsIgnoreCase(p.getCodigo())) {
                return p;
            }
        }
        return null;
    }

    // [Exercício 1.2] Reajuste percentual geral em todos os produtos
    public static void aplicarReajusteGeral(ArrayList<Produto> lista, double percentual) {
        if (lista == null) return;
        for (Produto p : lista) {
            double novoPreco = p.getPreco() * (1 + (percentual / 100.0));
            p.setPreco(novoPreco);
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  [EXERCÍCIO 1.1] CADASTRO BÁSICO DE PRODUTOS");
        System.out.println("==================================================");

        // a) Instanciando a lista
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        // b) Adicionando pelo menos 4 produtos distintos
        Produto p1 = new Produto("P001", "Notebook Dell G15", 4800.00);
        Produto p2 = new Produto("P002", "Mouse Sem Fio Logitech", 150.00);
        Produto p3 = new Produto("P003", "Teclado Mecânico RGB", 320.00);
        Produto p4 = new Produto("P004", "Monitor 27' 144Hz", 1250.00);

        listaProdutos.add(p1);
        listaProdutos.add(p2);
        listaProdutos.add(p3);
        listaProdutos.add(p4);

        // c) Percorrendo e exibindo
        System.out.println("\n--- Lista Inicial de Produtos Cadastrados ---");
        for (Produto p : listaProdutos) {
            System.out.println(p);
        }
        System.out.println("Total de produtos na lista: " + listaProdutos.size());

        // d) Removendo produtos (por índice e por objeto)
        System.out.println("\n--- Removendo Produtos ---");
        System.out.println("1. Removendo produto no índice 1 (Mouse Logitech)...");
        listaProdutos.remove(1); // remoção por índice

        System.out.println("2. Removendo produto por objeto (Teclado Mecânico)...");
        listaProdutos.remove(p3); // remoção por objeto

        System.out.println("\nQuantidade atual de produtos após remoções: " + listaProdutos.size());
        for (Produto p : listaProdutos) {
            System.out.println(p);
        }

        System.out.println("\n==================================================");
        System.out.println("  [EXERCÍCIO 1.2] BUSCA E ATUALIZAÇÃO");
        System.out.println("==================================================");

        // a) Busca por código existente
        System.out.println("\n--- a) Busca por código existente ('P001') ---");
        Produto encontrado = buscarPorCodigo(listaProdutos, "P001");
        if (encontrado != null) {
            System.out.println("Produto encontrado com sucesso:\n" + encontrado);
        } else {
            System.out.println("Produto não encontrado.");
        }

        // b) Busca por código inexistente
        System.out.println("\n--- b) Busca por código inexistente ('P999') ---");
        Produto naoEncontrado = buscarPorCodigo(listaProdutos, "P999");
        if (naoEncontrado != null) {
            System.out.println("Produto encontrado:\n" + naoEncontrado);
        } else {
            System.out.println("Aviso: Produto não encontrado para o código 'P999'.");
        }

        // c) Reajuste geral de 10%
        System.out.println("\n--- c) Aplicando reajuste geral de 10% nos preços ---");
        aplicarReajusteGeral(listaProdutos, 10.0);

        System.out.println("Lista de produtos com preços reajustados:");
        for (Produto p : listaProdutos) {
            System.out.println(p);
        }
    }
}
