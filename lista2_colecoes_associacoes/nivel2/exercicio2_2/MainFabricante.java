package lista2_colecoes_associacoes.nivel2.exercicio2_2;

public class MainFabricante {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("  [EXERCÍCIO 2.2] ASSOCIAÇÃO BIDIRECIONAL CONSISTENTE");
        System.out.println("==========================================================");

        Fabricante fabA = new Fabricante("11.111.111/0001-11", "Fabricante A (Dell)");
        Fabricante fabB = new Fabricante("22.222.222/0001-22", "Fabricante B (Lenovo)");

        Produto prod = new Produto("PRD01", "Notebook Corporativo", 4500.00);

        System.out.println("\n--- 1. Associando o produto ao Fabricante A ---");
        prod.setFabricante(fabA);

        System.out.println("Estado do Produto: " + prod);
        fabA.listarProdutos();
        fabB.listarProdutos();

        System.out.println("\n--- 2. Mudando o fabricante do produto para Fabricante B ---");
        System.out.println("Executando: prod.setFabricante(fabB)...");
        prod.setFabricante(fabB);

        System.out.println("\nEstado do Produto: " + prod);
        System.out.println("\n--- Verificação de Integridade Referencial Bidirecional ---");
        fabA.listarProdutos();
        fabB.listarProdutos();

        System.out.println("\n--- 3. Desvinculando fabricante com prod.setFabricante(null) ---");
        prod.setFabricante(null);
        System.out.println("Estado do Produto: " + prod);
        fabA.listarProdutos();
        fabB.listarProdutos();
    }
}
