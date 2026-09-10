package lista4_topicos_avancados;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class MainAvancado {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   EXERCÍCIO COMPLEMENTAR AVANÇADO: COLEÇÕES, CONTRATOS E ESTRUTURAS LEGADAS    ");
        System.out.println("================================================================================");

        // =========================================================================
        // MÓDULO 1: SUBCONJUNTOS DO SORTEDSET (subSet, headSet, tailSet)
        // =========================================================================
        System.out.println("\n################################################################################");
        System.out.println(">>> 1. SUBCONJUNTOS EM ÁRVORE BINÁRIA BALANCEADA (SORTEDSET / TREESET) <<<");
        System.out.println("################################################################################");

        GerenciadorSubconjuntos moduloSubconjuntos = new GerenciadorSubconjuntos();
        moduloSubconjuntos.adicionarProduto(new Produto("P01", "Cabo USB-C 1m", 35.00));
        moduloSubconjuntos.adicionarProduto(new Produto("P02", "Mouse Óptico USB", 85.00));
        moduloSubconjuntos.adicionarProduto(new Produto("P03", "Teclado Sem Fio", 190.00));
        moduloSubconjuntos.adicionarProduto(new Produto("P04", "Headset Gamer 7.1", 350.00));
        moduloSubconjuntos.adicionarProduto(new Produto("P05", "Monitor IPS 24'", 850.00));
        moduloSubconjuntos.adicionarProduto(new Produto("P06", "Placa de Vídeo RTX 4060", 2200.00));
        moduloSubconjuntos.adicionarProduto(new Produto("P07", "Notebook Gamer Core i7", 5800.00));
        moduloSubconjuntos.adicionarProduto(new Produto("P08", "MacBook Pro M3 Max", 14500.00));

        moduloSubconjuntos.exibirCatalogoCompleto();

        // 1.1 subSet(min, max): Faixa intermediária [R$ 150.00 até R$ 2500.00[
        System.out.println("\n--- 1.1 subSet(R$ 150.00, R$ 2500.00): Produtos na faixa intermediária ---");
        SortedSet<Produto> faixaIntermediaria = moduloSubconjuntos.filtrarPorFaixaPreco(150.00, 2500.00);
        for (Produto p : faixaIntermediaria) {
            System.out.println("  " + p);
        }

        // 1.2 headSet(teto): Produtos populares com teto de R$ 200.00 (estritamente menores)
        System.out.println("\n--- 1.2 headSet(R$ 200.00): Produtos com preço abaixo do teto de R$ 200.00 ---");
        SortedSet<Produto> produtosAbaixoTeto = moduloSubconjuntos.filtrarAbaixoDeTeto(200.00);
        for (Produto p : produtosAbaixoTeto) {
            System.out.println("  " + p);
        }

        // 1.3 tailSet(piso): Produtos premium/alto padrão a partir do piso de R$ 2000.00
        System.out.println("\n--- 1.3 tailSet(R$ 2000.00): Produtos premium a partir do piso de R$ 2000.00 ---");
        SortedSet<Produto> produtosAcimaPiso = moduloSubconjuntos.filtrarAcimaDePiso(2000.00);
        for (Produto p : produtosAcimaPiso) {
            System.out.println("  " + p);
        }

        // =========================================================================
        // MÓDULO 2: CONTRATO FORMAL DA INTERFACE QUEUE COM LINKEDLIST
        // =========================================================================
        System.out.println("\n################################################################################");
        System.out.println(">>> 2. CONTRATO FORMAL DE FILA: INTERFACE QUEUE (offer, peek, poll) <<<");
        System.out.println("################################################################################");

        FilaPedidosQueue filaModulo = new FilaPedidosQueue();

        // Inserção com offer()
        System.out.println("\n--- Inserindo pedidos via offer() ---");
        filaModulo.enfileirarPedido("Pedido #5001 - Smartphone 128GB");
        filaModulo.enfileirarPedido("Pedido #5002 - Smart TV 4K 55'");
        filaModulo.enfileirarPedido("Pedido #5003 - Caixa de Som Bluetooth");

        filaModulo.exibirFila();

        // Consulta com peek() (sem remover)
        System.out.println("\n--- Consultando cabeçalho com peek() ---");
        filaModulo.espiarProximo();

        // Atendimento com poll()
        System.out.println("\n--- Processando pedidos com poll() ---");
        filaModulo.atenderPedido();
        filaModulo.atenderPedido();

        filaModulo.exibirFila();

        // Atendendo último pedido e testando retorno null em fila vazia
        filaModulo.atenderPedido();
        filaModulo.espiarProximo();
        filaModulo.atenderPedido();

        // =========================================================================
        // MÓDULO 3: MÉTODOS UTILITÁRIOS DA CLASSE COLLECTIONS
        // =========================================================================
        System.out.println("\n################################################################################");
        System.out.println(">>> 3. MÉTODOS UTILITÁRIOS (Collections.shuffle, reverse, min, max, sync) <<<");
        System.out.println("################################################################################");

        List<Produto> listaTrabalho = new ArrayList<>();
        listaTrabalho.add(new Produto("A01", "Câmera DSLR Canon", 3400.00));
        listaTrabalho.add(new Produto("A02", "Lente 50mm f/1.8", 650.00));
        listaTrabalho.add(new Produto("A03", "Tripé de Alumínio", 180.00));
        listaTrabalho.add(new Produto("A04", "Microfone Lapela", 95.00));
        listaTrabalho.add(new Produto("A05", "Iluminador Ring Light", 130.00));

        UtilitariosCollections.demonstrarOperacoesLista(listaTrabalho);
        UtilitariosCollections.demonstrarListaSincronizada();

        // =========================================================================
        // MÓDULO 4: ESTRUTURAS LEGADAS (HASHTABLE E ENUMERATION)
        // =========================================================================
        System.out.println("\n################################################################################");
        System.out.println(">>> 4. ESTRUTURA LEGADA SINCRONIZADA: HASHTABLE & ENUMERATION <<<");
        System.out.println("################################################################################");

        SessoesHashtable moduloHashtable = new SessoesHashtable();
        moduloHashtable.registrarSessao("sess_tok_991", "keven.fernandes | IP: 192.168.1.105 | Role: ADMIN");
        moduloHashtable.registrarSessao("sess_tok_992", "mariana.silva   | IP: 192.168.1.112 | Role: OPERADOR");
        moduloHashtable.registrarSessao("sess_tok_993", "carlos.eduardo  | IP: 192.168.1.140 | Role: CLIENTE");

        moduloHashtable.listarChavesComEnumeration();
        moduloHashtable.listarValoresComEnumeration();
        moduloHashtable.exibirRelatorioSessoes();

        System.out.println("\n================================================================================");
        System.out.println("           TODOS OS REQUISITOS TÉCNICOS EXECUTADOS COM SUCESSO!                 ");
        System.out.println("================================================================================");
    }
}
