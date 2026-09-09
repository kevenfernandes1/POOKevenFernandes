package lista3_colecoes_arquitetura.missao1;

public class MainMissao1 {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println(" MISSÃO 1: CONTRATOS DE SERVIÇO E VALIDAÇÃO DE TIPOS (INSTANCEOF)");
        System.out.println("======================================================================");

        // 1. Instanciando diferentes modalidades de entrega (realizações de Rastreavel)
        PacoteCorreios correios = new PacoteCorreios("BR123456789XP", "01001-000", "Objeto postado em trânsito para o CD");
        CargaTransportadora transportadora = new CargaTransportadora("CTE-998877", "ABC-1D23", 1450.75, "Rodovia Presidente Dutra, km 180");
        EntregaExpressa motoboy = new EntregaExpressa("PED-5042", "Carlos Eduardo", 18, "Saiu para entrega ao destinatário");

        // 2. Criando objetos não rastreáveis para validação do instanceof
        String documentoFiscal = "NF-e nº 45892 (Documento em PDF)";
        Integer codigoAvulso = 40028922;

        // 3. Inspecionando todos os itens através da CentralRastreamento
        System.out.println("\n--- Inspeção de Pacote dos Correios ---");
        CentralRastreamento.inspecionarItem(correios);

        System.out.println("\n--- Inspeção de Carga de Transportadora ---");
        CentralRastreamento.inspecionarItem(transportadora);

        System.out.println("\n--- Inspeção de Entrega Expressa ---");
        CentralRastreamento.inspecionarItem(motoboy);

        System.out.println("\n--- Inspeção de Documento Não Rastreável (String) ---");
        CentralRastreamento.inspecionarItem(documentoFiscal);

        System.out.println("\n--- Inspeção de Objeto Numérico (Integer) ---");
        CentralRastreamento.inspecionarItem(codigoAvulso);

        System.out.println("\n======================================================================");
    }
}
