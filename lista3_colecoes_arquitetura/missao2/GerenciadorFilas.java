package lista3_colecoes_arquitetura.missao2;

import java.util.LinkedList;
import java.util.List;

public class GerenciadorFilas {
    private List<String> filaPedidos;

    public GerenciadorFilas() {
        this.filaPedidos = new LinkedList<>();
    }

    // A LinkedList e mais rapida que o ArrayList para inserir no inicio (O(1) vs O(n))
    // porque na LinkedList basta atualizar os ponteiros do primeiro no, enquanto no
    // ArrayList e necessario deslocar todos os elementos seguintes na memoria (shift).
    public void enfileirarPedidoNormal(String pedido) {
        filaPedidos.add(pedido);
        System.out.println("Pedido normal adicionado: " + pedido);
    }

    public void enfileirarPedidoPrioritario(String pedido) {
        filaPedidos.add(0, pedido);
        System.out.println("Pedido prioritario adicionado no inicio: " + pedido);
    }

    public String atenderProximoPedido() {
        if (filaPedidos.isEmpty()) {
            System.out.println("Fila vazia.");
            return null;
        }
        String proximo = filaPedidos.remove(0);
        System.out.println("Pedido atendido: " + proximo);
        return proximo;
    }

    public void exibirFila() {
        System.out.println("\nFila de pedidos (" + filaPedidos.size() + "):");
        for (int i = 0; i < filaPedidos.size(); i++) {
            System.out.println((i + 1) + ". " + filaPedidos.get(i));
        }
    }

    public List<String> getFilaPedidos() {
        return filaPedidos;
    }
}
