package lista4_topicos_avancados;

import java.util.ArrayList;
import java.util.List;

public class MainAvancado {
    public static void main(String[] args) {
        // 1. SortedSet: subSet, headSet, tailSet
        System.out.println("--- Testes com SortedSet (TreeSet) ---");
        GerenciadorSubconjuntos catalogo = new GerenciadorSubconjuntos();
        catalogo.adicionarProduto(new Produto("P01", "Cabo USB", 35.00));
        catalogo.adicionarProduto(new Produto("P02", "Mouse", 85.00));
        catalogo.adicionarProduto(new Produto("P03", "Teclado", 190.00));
        catalogo.adicionarProduto(new Produto("P04", "Headset", 350.00));
        catalogo.adicionarProduto(new Produto("P05", "Monitor", 850.00));
        catalogo.adicionarProduto(new Produto("P06", "Placa de Video", 2200.00));

        catalogo.exibirCatalogo();

        System.out.println("\nFaixa entre 50 e 500 (subSet):");
        for (Produto p : catalogo.filtrarPorFaixaPreco(50.00, 500.00)) {
            System.out.println(p);
        }

        System.out.println("\nAbaixo de 200 (headSet):");
        for (Produto p : catalogo.filtrarAbaixoDeTeto(200.00)) {
            System.out.println(p);
        }

        System.out.println("\nA partir de 800 (tailSet):");
        for (Produto p : catalogo.filtrarAcimaDePiso(800.00)) {
            System.out.println(p);
        }

        // 2. Queue com LinkedList (offer, peek, poll)
        System.out.println("\n--- Testes com Queue ---");
        FilaPedidosQueue fila = new FilaPedidosQueue();
        fila.enfileirarPedido("Pedido 01");
        fila.enfileirarPedido("Pedido 02");
        fila.enfileirarPedido("Pedido 03");

        fila.espiarProximo();
        fila.atenderPedido();
        fila.exibirFila();

        // 3. Utilitarios de Collections
        System.out.println("\n--- Testes com Collections (shuffle, reverse, min, max, sync) ---");
        List<Produto> lista = new ArrayList<>();
        lista.add(new Produto("A1", "Camera", 3400.00));
        lista.add(new Produto("A2", "Lente", 650.00));
        lista.add(new Produto("A3", "Tripe", 180.00));

        UtilitariosCollections.demonstrarOperacoes(lista);
        UtilitariosCollections.demonstrarListaSincronizada();

        // 4. Hashtable e Enumeration
        System.out.println("\n--- Testes com Hashtable e Enumeration ---");
        SessoesHashtable sessoes = new SessoesHashtable();
        sessoes.adicionarSessao("tok_01", "keven.fernandes");
        sessoes.adicionarSessao("tok_02", "mariana.silva");

        sessoes.listarChavesComEnumeration();
        sessoes.listarValoresComEnumeration();
    }
}
