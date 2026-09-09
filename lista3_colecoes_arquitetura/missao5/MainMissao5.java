package lista3_colecoes_arquitetura.missao5;

import java.util.Collection;
import java.util.Set;

public class MainMissao5 {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println(" MISSÃO 5: ACESSO DIRETO O(1) E INDEXAÇÃO POR CHAVE (HASHMAP)");
        System.out.println("======================================================================");

        ControleEstoquePDV pdv = new ControleEstoquePDV();

        // 1. Cadastrando produtos via put(chave, valor)
        System.out.println("\n>>> 1. CADASTRO E INDEXAÇÃO VIA PUT(CHAVE, VALOR) <<<");
        pdv.cadastrarProduto(new Produto("78910001", "Smartphone Samsung Galaxy", 3299.90, 15));
        pdv.cadastrarProduto(new Produto("78910002", "Monitor LG UltraWide 29'", 1499.00, 8));
        pdv.cadastrarProduto(new Produto("78910003", "Mouse Gamer HyperX", 220.00, 40));
        pdv.cadastrarProduto(new Produto("78910004", "Teclado Mecânico Redragon", 280.00, 25));

        // 2. Consulta instantânea O(1) via get(chave) sem laços de repetição
        System.out.println("\n>>> 2. CONSULTA INSTANTÂNEA VIA GET(CHAVE) COM COMPLEXIDADE O(1) <<<");
        String codigoBuscado = "78910002";
        System.out.println("Bipando código de barras: " + codigoBuscado);
        Produto encontrado = pdv.consultarPorCodigo(codigoBuscado);

        if (encontrado != null) {
            System.out.println("-> Produto Localizado Instantaneamente:");
            System.out.println("   " + encontrado);
        } else {
            System.out.println("-> Produto não cadastrado.");
        }

        // Teste de consulta de código inexistente
        String codigoInexistente = "99999999";
        System.out.println("\nBipando código de barras inexistente: " + codigoInexistente);
        Produto naoEncontrado = pdv.consultarPorCodigo(codigoInexistente);
        if (naoEncontrado == null) {
            System.out.println("-> Retorno O(1) imediato: null (Produto não cadastrado)");
        }

        // 3. Exibindo todas as chaves registradas utilizando mapaEstoque.keySet() (Tipo Set<String>)
        System.out.println("\n>>> 3. CHAVES REGISTRADAS VIA KEYSET() [TIPO SET<STRING>] <<<");
        Set<String> chaves = pdv.obterTodasChaves();
        System.out.println("Conjunto de chaves de indexação: " + chaves);

        // 4. Exibindo todos os produtos utilizando mapaEstoque.values() (Tipo Collection<Produto>)
        System.out.println("\n>>> 4. PRODUTOS REGISTRADOS VIA VALUES() [TIPO COLLECTION<PRODUTO>] <<<");
        Collection<Produto> produtos = pdv.obterTodosProdutos();
        for (Produto p : produtos) {
            System.out.println("  -> " + p);
        }

        System.out.println("\n======================================================================");
    }
}
