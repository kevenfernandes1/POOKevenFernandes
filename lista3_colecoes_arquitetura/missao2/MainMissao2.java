package lista3_colecoes_arquitetura.missao2;

public class MainMissao2 {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println(" MISSÃO 2: FILAS DE PROCESSAMENTO (LINKEDLIST) E CONCORRÊNCIA (VECTOR)");
        System.out.println("======================================================================");

        // --- PARTE 1: Processamento de Filas com LinkedList ---
        System.out.println("\n>>> 1. OPERAÇÕES DE FILA COM LINKEDLIST <<<");
        GerenciadorFilas gerenciador = new GerenciadorFilas();

        gerenciador.enfileirarPedidoNormal("Pedido #101 - Smart TV 55'");
        gerenciador.enfileirarPedidoNormal("Pedido #102 - Fone Bluetooth");
        gerenciador.enfileirarPedidoNormal("Pedido #103 - Cadeira Gamer");

        gerenciador.exibirFila();

        // Inserção no início (Pedido Prioritário VIP)
        System.out.println("\n--- Chegada de Pedido VIP (Inserção no Início da Fila) ---");
        gerenciador.enfileirarPedidoPrioritario("Pedido #999 [VIP] - Medicamentos Urgentes");

        gerenciador.exibirFila();

        // Atendimento ordenado
        System.out.println("\n--- Processamento dos Pedidos da Fila ---");
        gerenciador.atenderProximoPedido();
        gerenciador.atenderProximoPedido();

        gerenciador.exibirFila();

        // --- PARTE 2: Registrador de Auditoria com Vector ---
        System.out.println("\n>>> 2. REGISTRADOR DE AUDITORIA CONCORRENTE COM VECTOR <<<");
        RegistradorLogsVector auditoria = new RegistradorLogsVector();

        auditoria.registrarLog("Usuário 'admin_logistica' autenticado com sucesso.");
        auditoria.registrarLog("Pedido #999 processado e despachado para entrega.");
        auditoria.registrarLog("Atualização de estoque realizada no armazém central.");

        auditoria.exibirLogs();

        System.out.println("\n======================================================================");
    }
}
