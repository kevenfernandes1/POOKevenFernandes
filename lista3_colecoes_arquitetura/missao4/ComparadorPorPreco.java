package lista3_colecoes_arquitetura.missao4;

import java.util.Comparator;

/**
 * Critério de ordenação customizado por preço para catálogo do E-Commerce.
 * 
 * [REGRAS DE RETORNO DO CONTRATO Comparator.compare(p1, p2)]:
 * 1. Retorno NEGATIVO (< 0):
 *    Indica que o primeiro objeto (p1) é "menor" que o segundo (p2) e deve precedê-lo na ordenação.
 * 2. Retorno ZERO (== 0):
 *    Indica que ambos os objetos são equivalentes segundo este critério.
 *    (OBS: No TreeSet, se o retorno for 0, o TreeSet considera o elemento duplicado e descarta a inserção.
 *     Por isso, implementamos um desempate secundário por código/nome para permitir produtos com mesmo preço).
 * 3. Retorno POSITIVO (> 0):
 *    Indica que o primeiro objeto (p1) é "maior" que o segundo (p2) e deve sucedê-lo na ordenação.
 */
public class ComparadorPorPreco implements Comparator<Produto> {

    @Override
    public int compare(Produto p1, Produto p2) {
        if (p1 == null && p2 == null) return 0;
        if (p1 == null) return -1;
        if (p2 == null) return 1;

        // Comparação primária pelo preço
        int resultadoPreco = Double.compare(p1.getPreco(), p2.getPreco());
        if (resultadoPreco != 0) {
            return resultadoPreco; // < 0 se p1 for mais barato, > 0 se p1 for mais caro
        }

        // Critério de desempate secundário (por nome/código) para evitar descarte indevido no TreeSet
        return p1.getCodigo().compareToIgnoreCase(p2.getCodigo());
    }
}
