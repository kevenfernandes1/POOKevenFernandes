package lista3_colecoes_arquitetura.missao1;

public class MainMissao1 {
    public static void main(String[] args) {
        PacoteCorreios correios = new PacoteCorreios("BR123456789XP", "01001-000", "Objeto em transito");
        CargaTransportadora transportadora = new CargaTransportadora("CTE-998877", "ABC-1D23", 1450.75, "Rodovia Pres. Dutra");
        EntregaExpressa expressa = new EntregaExpressa("PED-5042", "Carlos", 18, "Saiu para entrega");

        // Objetos que nao implementam Rastreavel
        String documento = "NF-e 45892";
        Integer codigo = 40028922;

        System.out.println("Inspecionando pacotes:");
        CentralRastreamento.inspecionarItem(correios);
        CentralRastreamento.inspecionarItem(transportadora);
        CentralRastreamento.inspecionarItem(expressa);

        System.out.println("\nTestando itens invalidos:");
        CentralRastreamento.inspecionarItem(documento);
        CentralRastreamento.inspecionarItem(codigo);
    }
}
