package lista2_colecoes_associacoes.nivel2.exercicio2_1;

public class MainCategoria {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  [EXERCÍCIO 2.1] ASSOCIAÇÃO UNIDIRECIONAL (1:N)");
        System.out.println("==================================================");

        Categoria catInformatica = new Categoria(1, "Informática");
        Categoria catAlimentos = new Categoria(2, "Alimentos");

        Produto p1 = new Produto("INF01", "SSD NVMe 1TB", 380.00);
        Produto p2 = new Produto("INF02", "Placa de Vídeo RTX 4060", 2100.00);
        Produto p3 = new Produto("ALI01", "Arroz Integral 5kg", 28.50);
        Produto p4 = new Produto("ALI02", "Azeite de Oliva 500ml", 42.00);

        catInformatica.adicionarProduto(p1);
        catInformatica.adicionarProduto(p2);

        catAlimentos.adicionarProduto(p3);
        catAlimentos.adicionarProduto(p4);

        System.out.println("\n--- Listagem de Produtos por Categoria ---");
        catInformatica.listarProdutos();
        System.out.println();
        catAlimentos.listarProdutos();

        System.out.println("\n--- Removendo SSD da categoria Informática ---");
        catInformatica.removerProduto(p1);
        catInformatica.listarProdutos();
    }
}
