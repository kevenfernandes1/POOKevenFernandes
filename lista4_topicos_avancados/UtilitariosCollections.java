package lista4_topicos_avancados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Módulo 3: Métodos Utilitários da Classe Collections (java.util.Collections).
 * 
 * [RECURSOS UTILITÁRIOS DA CLASSE COLLECTIONS]:
 * 1. Collections.shuffle(List<?> list):
 *    Embaralha aleatoriamente a ordem dos elementos da lista com complexidade O(n)
 *    utilizando o algoritmo de permutação de Fisher-Yates (Knuth Shuffle).
 * 2. Collections.reverse(List<?> list):
 *    Inverte a ordem dos elementos da lista in-place em tempo O(n).
 * 3. Collections.min(Collection<?> coll) / Collections.max(Collection<?> coll):
 *    Determina o menor e o maior elemento da coleção com base na ordem natural (Comparable)
 *    ou através de um Comparator customizado em tempo O(n).
 * 4. Collections.synchronizedList(List<T> list):
 *    Aplica o padrão de projeto Decorator (Wrapper) sobre uma lista não thread-safe (ex.: ArrayList),
 *    envolvendo todas as operações de mutação em blocos sincronizados com o lock do próprio wrapper.
 *    OBS: Ao iterar sobre a lista sincronizada, a documentação oficial da Oracle exige o uso de
 *    um bloco synchronized explícito para evitar condições de corrida (Race Conditions).
 */
public class UtilitariosCollections {

    public static void demonstrarOperacoesLista(List<Produto> lista) {
        System.out.println("\n--- 1. Lista Original de Produtos ---");
        for (Produto p : lista) {
            System.out.println("  " + p);
        }

        // 1. Collections.min() e Collections.max()
        Produto produtoMaisBarato = Collections.min(lista);
        Produto produtoMaisCaro = Collections.max(lista);

        System.out.println("\n--- 2. Produto Menor e Maior (Collections.min / max) ---");
        System.out.println("  Menor valor (min): " + produtoMaisBarato);
        System.out.println("  Maior valor (max): " + produtoMaisCaro);

        // 2. Collections.shuffle()
        System.out.println("\n--- 3. Embaralhamento Aleatório (Collections.shuffle) ---");
        Collections.shuffle(lista);
        for (Produto p : lista) {
            System.out.println("  " + p);
        }

        // 3. Collections.reverse()
        System.out.println("\n--- 4. Inversão da Ordem Atual (Collections.reverse) ---");
        Collections.reverse(lista);
        for (Produto p : lista) {
            System.out.println("  " + p);
        }
    }

    public static void demonstrarListaSincronizada() {
        System.out.println("\n--- 5. Encapsulamento Concorrente com Collections.synchronizedList ---");

        // Criação da lista base e encapsulamento em wrapper sincronizado thread-safe
        List<String> listaNaoSincronizada = new ArrayList<>();
        List<String> listaSegura = Collections.synchronizedList(listaNaoSincronizada);

        // Inserções atômicas thread-safe
        listaSegura.add("Evento #01 - Autenticação no Gateway");
        listaSegura.add("Evento #02 - Verificação Antifraude");
        listaSegura.add("Evento #03 - Faturamento de Cartão");

        System.out.println("Itens inseridos de forma thread-safe na lista sincronizada:");

        // Padrão obrigatório da Oracle para travessia segura de coleções sincronizadas:
        synchronized (listaSegura) {
            for (String evento : listaSegura) {
                System.out.println("  [LOCKED] " + evento);
            }
        }
    }
}
