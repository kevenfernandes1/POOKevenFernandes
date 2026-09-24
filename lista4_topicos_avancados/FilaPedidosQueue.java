package lista4_topicos_avancados;

import java.util.LinkedList;
import java.util.Queue;

public class FilaPedidosQueue {
    private Queue<String> fila;

    public FilaPedidosQueue() {
        this.fila = new LinkedList<>();
    }

    // Insercao segura com offer
    public boolean enfileirarPedido(String pedido) {
        boolean ok = fila.offer(pedido);
        System.out.println("Pedido enfileirado: " + pedido);
        return ok;
    }

    // Consulta sem remover com peek
    public String espiarProximo() {
        String proximo = fila.peek();
        System.out.println("Proximo da fila (peek): " + proximo);
        return proximo;
    }

    // Remove e atende com poll
    public String atenderPedido() {
        String atendido = fila.poll();
        System.out.println("Pedido atendido (poll): " + atendido);
        return atendido;
    }

    public void exibirFila() {
        System.out.println("\nFila de pedidos (" + fila.size() + "):");
        int pos = 1;
        for (String p : fila) {
            System.out.println((pos++) + ". " + p);
        }
    }

    public Queue<String> getFila() {
        return fila;
    }
}
