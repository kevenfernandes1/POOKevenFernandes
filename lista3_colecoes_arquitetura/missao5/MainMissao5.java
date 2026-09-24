package lista3_colecoes_arquitetura.missao5;

public class MainMissao5 {
    public static void main(String[] args) {
        ControleEstoquePDV pdv = new ControleEstoquePDV();

        pdv.cadastrarProduto(new Produto("78910001", "Smartphone Samsung Galaxy", 3299.90, 15));
        pdv.cadastrarProduto(new Produto("78910002", "Monitor LG UltraWide 29", 1499.00, 8));
        pdv.cadastrarProduto(new Produto("78910003", "Mouse Gamer HyperX", 220.00, 40));
        pdv.cadastrarProduto(new Produto("78910004", "Teclado Mecanico Redragon", 280.00, 25));

        // Consulta direta O(1)
        System.out.println("Consultando codigo 78910002:");
        Produto p = pdv.consultarPorCodigo("78910002");
        System.out.println("Resultado: " + p);

        System.out.println("\nConsultando codigo inexistente (99999999):");
        Produto naoExiste = pdv.consultarPorCodigo("99999999");
        System.out.println("Resultado: " + naoExiste);

        // Listando chaves (keySet)
        System.out.println("\nChaves cadastradas (keySet):");
        System.out.println(pdv.obterTodasChaves());

        // Listando valores (values)
        System.out.println("\nProdutos cadastrados (values):");
        for (Produto prod : pdv.obterTodosProdutos()) {
            System.out.println(prod);
        }
    }
}
