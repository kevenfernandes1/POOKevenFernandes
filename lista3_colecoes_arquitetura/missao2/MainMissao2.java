package lista3_colecoes_arquitetura.missao2;

public class MainMissao2 {
    public static void main(String[] args) {
        // 1. Testando a LinkedList para gerenciamento de fila
        System.out.println("Testes da fila de pedidos (LinkedList):");
        GerenciadorFilas fila = new GerenciadorFilas();

        fila.enfileirarPedidoNormal("Pedido 101 - Monitor");
        fila.enfileirarPedidoNormal("Pedido 102 - Fone");
        fila.enfileirarPedidoPrioritario("Pedido 999 - Remédio Urgente");

        fila.exibirFila();

        fila.atenderProximoPedido();
        fila.exibirFila();

        // 2. Testando o Vector para registros concorrentes
        System.out.println("\nTestes de log com Vector:");
        RegistradorLogsVector auditoria = new RegistradorLogsVector();
        auditoria.registrarLog("Login de usuario admin");
        auditoria.registrarLog("Pedido 999 despachado");
        auditoria.registrarLog("Alteracao de estoque");

        auditoria.exibirLogs();
    }
}
