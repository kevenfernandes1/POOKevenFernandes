package lista3_colecoes_arquitetura.missao2;

import java.util.LinkedList;
import java.util.List;

/**
 * Módulo de Gerenciamento de Filas de Processamento de Pedidos.
 * 
 * [ANÁLISE DE DESEMPENHO COMPUTACIONAL: LinkedList vs ArrayList]:
 * A classe LinkedList implementa uma lista duplamente encadeada (Doubly-Linked List).
 * Cada elemento é envolvido em um nó (Node) contendo referências para o nó anterior e para o próximo nó.
 * 
 * 1. Inserção/Remoção no Início (add(0, elem) / addFirst):
 *    - LinkedList: Custo O(1) [Constante]. Basta instanciar o novo nó e atualizar os ponteiros 'head' e 'next'.
 *      Nenhum outro elemento precisa ser movido na memória.
 *    - ArrayList: Custo O(n) [Linear]. Como o ArrayList é baseado em um array contíguo interno, a inserção
 *      no índice 0 exige o deslocamento (shift à direita) de TODOS os 'n' elementos existentes na memória
 *      (via System.arraycopy).
 * 
 * 2. Acesso Aleatório por Índice (get(i)):
 *    - ArrayList: Custo O(1) direto pela aritmética de ponteiros na memória contígua.
 *    - LinkedList: Custo O(n), pois necessita percorrer os nós a partir do início ou do fim até o índice desejado.
 * 
 * Conclusão: Para filas de checkout onde há frequentes inserções no início (pedidos prioritários/VIP)
 * ou remoções no início (processamento FIFO), a LinkedList é a estrutura ideal.
 */
public class GerenciadorFilas {
    private List<String> filaPedidos;

    public GerenciadorFilas() {
        // Modelagem utilizando a interface List associada à instância de LinkedList
        this.filaPedidos = new LinkedList<>();
    }

    /**
     * Insere um pedido regular no final da fila (Padrão FIFO).
     */
    public void enfileirarPedidoNormal(String pedido) {
        filaPedidos.add(pedido); // Inserção no fim O(1)
        System.out.println("[FILA] Pedido normal enfileirado no final: " + pedido);
    }

    /**
     * Insere um pedido urgente/VIP no início da fila com prioridade máxima.
     */
    public void enfileirarPedidoPrioritario(String pedido) {
        filaPedidos.add(0, pedido); // Inserção no início: O(1) na LinkedList
        System.out.println("[FILA - VIP] Pedido prioritário inserido no INÍCIO da fila: " + pedido);
    }

    /**
     * Processa e remove o próximo pedido da fila.
     */
    public String atenderProximoPedido() {
        if (filaPedidos.isEmpty()) {
            System.out.println("[FILA] Nenhum pedido pendente para processamento.");
            return null;
        }
        String proximo = filaPedidos.remove(0); // Remoção do início: O(1) na LinkedList
        System.out.println("[PROCESSAMENTO] Pedido atendido e removido da fila: " + proximo);
        return proximo;
    }

    public void exibirFila() {
        System.out.println("\n--- Estado Atual da Fila de Pedidos (" + filaPedidos.size() + " pendentes) ---");
        if (filaPedidos.isEmpty()) {
            System.out.println("  (Fila vazia)");
        } else {
            int posicao = 1;
            for (String pedido : filaPedidos) {
                System.out.printf("  %dº da fila: %s%n", posicao++, pedido);
            }
        }
    }

    public List<String> getFilaPedidos() {
        return filaPedidos;
    }
}
