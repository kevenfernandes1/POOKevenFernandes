package lista2_colecoes_associacoes.nivel3;

public class MainNotaFiscal {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("  [EXERCÍCIO 3.1] RELACIONAMENTO N:M (NOTA FISCAL & ITEM)");
        System.out.println("==========================================================");

        // 1. Instanciando produtos do catálogo
        Produto p1 = new Produto("PRD100", "Monitor Gamer 24'", 850.00);
        Produto p2 = new Produto("PRD200", "Cabo HDMI 2.1 Ultra", 45.00);
        Produto p3 = new Produto("PRD300", "Headset Surround 7.1", 250.00);

        // 2. Instanciando a Nota Fiscal
        NotaFiscal nf = new NotaFiscal(1042, "02/09/2026");

        // 3. Instanciando Itens (3 itens associados à nota e aos produtos com quantidades e preços distintos)
        new Item(nf, p1, 2, 800.00); // 2 unidades a R$ 800,00 cada
        new Item(nf, p2, 3, 40.00);  // 3 unidades a R$ 40,00 cada
        new Item(nf, p3, 1, 250.00); // 1 unidade a R$ 250,00

        // 4. Exibindo a nota detalhada e o valor total calculado
        nf.imprimirNota();
    }
}
