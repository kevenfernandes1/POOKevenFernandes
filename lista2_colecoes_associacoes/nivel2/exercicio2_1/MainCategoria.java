package lista2_colecoes_associacoes.nivel2.exercicio2_1;

public class MainCategoria {
    public static void main(String[] args) {
        Categoria catInformatica = new Categoria(1, "Informatica");
        Categoria catAlimentos = new Categoria(2, "Alimentos");

        Produto p1 = new Produto("INF01", "SSD NVMe 1TB", 380.00);
        Produto p2 = new Produto("INF02", "Placa de Video RTX 4060", 2100.00);
        Produto p3 = new Produto("ALI01", "Arroz Integral 5kg", 28.50);
        Produto p4 = new Produto("ALI02", "Azeite de Oliva 500ml", 42.00);

        catInformatica.adicionarProduto(p1);
        catInformatica.adicionarProduto(p2);

        catAlimentos.adicionarProduto(p3);
        catAlimentos.adicionarProduto(p4);

        System.out.println("Produtos cadastrados por categoria:");
        catInformatica.listarProdutos();
        System.out.println();
        catAlimentos.listarProdutos();

        System.out.println("\nRemovendo produto da categoria informatica:");
        catInformatica.removerProduto(p1);
        catInformatica.listarProdutos();
    }
}
