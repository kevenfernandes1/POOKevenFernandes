package lista2_colecoes_associacoes.nivel2.exercicio2_2;

public class MainFabricante {
    public static void main(String[] args) {
        Fabricante fabA = new Fabricante("11.111.111/0001-11", "Dell");
        Fabricante fabB = new Fabricante("22.222.222/0001-22", "Lenovo");

        Produto prod = new Produto("PRD01", "Notebook Corporativo", 4500.00);

        System.out.println("Associando produto ao Fabricante A:");
        prod.setFabricante(fabA);
        System.out.println("Produto: " + prod);
        fabA.listarProdutos();
        fabB.listarProdutos();

        System.out.println("\nTrocando para Fabricante B:");
        prod.setFabricante(fabB);
        System.out.println("Produto: " + prod);
        fabA.listarProdutos();
        fabB.listarProdutos();

        System.out.println("\nDesvinculando fabricante (setFabricante(null)):");
        prod.setFabricante(null);
        System.out.println("Produto: " + prod);
        fabA.listarProdutos();
        fabB.listarProdutos();
    }
}
