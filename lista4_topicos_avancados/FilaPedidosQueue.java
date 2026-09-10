package lista4_topicos_avancados;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Módulo 2: Contrato Formal de Fila (Interface Queue com LinkedList).
 * 
 * [CONTRATOS DE MÉTODOS DA INTERFACE QUEUE]:
 * A interface Queue do Java padroniza dois conjuntos de métodos para manipulação de filas:
 * 
 * 1. Métodos que lançam exceções em falhas:
 *    - Inserção: add(e) -> Lança IllegalStateException se a fila estiver cheia.
 *    - Remoção: remove() -> Lança NoSuchElementException se a fila estiver vazia.
 *    - Consulta: element() -> Lança NoSuchElementException se a fila estiver vazia.
 * 
 * 2. Métodos especiais que retornam valores especiais (true/false ou null) [REQUISITO DA ESPECIFICAÇÃO]:
 *    - Inserção: offer(e) -> Retorna true se inseriu com sucesso, ou false caso contrário.
 *    - Remoção: poll() -> Recupera e remove o cabeçalho da fila, ou retorna NULL se vazia.
 *    - Consulta: peek() -> Recupera SEM remover o cabeçalho da fila, ou retorna NULL se vazia.
 */
public class FilaPedidosQueue {
    private Queue<String> fila;

    public FilaPedidosQueue() {
        // Modelagem com o contrato Queue associado à realização LinkedList
        this.fila = new LinkedList<>();
    }

    /**
     * Enfileira um novo pedido utilizando exclusivamente offer().
     * @param pedido Identificador descritivo do pedido.
     * @return boolean indicando o sucesso da inserção.
     */
    public boolean enfileirarPedido(String pedido) {
        boolean sucesso = fila.offer(pedido); // Método seguro de inserção
        System.out.println("[QUEUE - offer()] Pedido enfileirado: " + pedido + " (Sucesso: " + sucesso + ")");
        return sucesso;
    }

    /**
     * Inspeciona o próximo pedido do cabeçalho da fila sem removê-lo utilizando peek().
     * @return Próximo pedido ou null se a fila estiver vazia.
     */
    public String espiarProximo() {
        String proximo = fila.peek(); // Consulta segura sem remoção
        if (proximo != null) {
            System.out.println("[QUEUE - peek()] Próximo pedido no topo da fila (sem remoção): " + proximo);
        } else {
            System.out.println("[QUEUE - peek()] A fila está vazia (retorno: null).");
        }
        return proximo;
    }

    /**
     * Atende e desenfileira o próximo pedido da fila utilizando poll().
     * @return Pedido atendido ou null se a fila estiver vazia.
     */
    public String atenderPedido() {
        String pedidoAtendido = fila.poll(); // Remoção segura com retorno null caso vazia
        if (pedidoAtendido != null) {
            System.out.println("[QUEUE - poll()] Pedido atendido e desenfileirado: " + pedidoAtendido);
        } else {
            System.out.println("[QUEUE - poll()] Não há pedidos pendentes para atender (retorno: null).");
        }
        return pedidoAtendido;
    }

    public void exibirFila() {
        System.out.println("\n--- Fila de Pedidos Atual (" + fila.size() + " pendentes) ---");
        if (fila.isEmpty()) {
            System.out.println("  (Fila vazia)");
        } else {
            int pos = 1;
            for (String p : fila) {
                System.out.printf("  Posição %d: %s%n", pos++, p);
            }
        }
    }

    public Queue<String> getFila() {
        return fila;
    }
}
