package lista4_topicos_avancados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UtilitariosCollections {

    public static void demonstrarOperacoes(List<Produto> lista) {
        System.out.println("Lista inicial:");
        for (Produto p : lista) {
            System.out.println(p);
        }

        // min e max
        Produto min = Collections.min(lista);
        Produto max = Collections.max(lista);
        System.out.println("\nMais barato (min): " + min);
        System.out.println("Mais caro (max): " + max);

        // shuffle (embaralhar)
        System.out.println("\nEmbaralhando lista (shuffle):");
        Collections.shuffle(lista);
        for (Produto p : lista) {
            System.out.println(p);
        }

        // reverse (inverter)
        System.out.println("\nInvertendo ordem (reverse):");
        Collections.reverse(lista);
        for (Produto p : lista) {
            System.out.println(p);
        }
    }

    public static void demonstrarListaSincronizada() {
        System.out.println("\nTestando Collections.synchronizedList:");
        List<String> listaComum = new ArrayList<>();
        List<String> listaSincronizada = Collections.synchronizedList(listaComum);

        listaSincronizada.add("Log 1");
        listaSincronizada.add("Log 2");
        listaSincronizada.add("Log 3");

        // Para iterar com seguranca em thread, deve-se sincronizar explicitamente
        synchronized (listaSincronizada) {
            for (String s : listaSincronizada) {
                System.out.println("- " + s);
            }
        }
    }
}
