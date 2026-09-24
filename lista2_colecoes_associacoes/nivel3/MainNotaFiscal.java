package lista2_colecoes_associacoes.nivel3;

public class MainNotaFiscal {
    public static void main(String[] args) {
        Produto p1 = new Produto("PRD100", "Monitor Gamer 24", 850.00);
        Produto p2 = new Produto("PRD200", "Cabo HDMI 2.1", 45.00);
        Produto p3 = new Produto("PRD300", "Headset 7.1", 250.00);

        NotaFiscal nf = new NotaFiscal(1042, "02/09/2026");

        // Cria os itens associando nota e produto
        new Item(nf, p1, 2, 800.00);
        new Item(nf, p2, 3, 40.00);
        new Item(nf, p3, 1, 250.00);

        // Exibe a nota fiscal calculada
        nf.imprimirNota();
    }
}
