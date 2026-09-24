package lista3_colecoes_arquitetura.missao4;

import java.util.Comparator;

public class ComparadorPorPreco implements Comparator<Produto> {

    // Regras de retorno do compare:
    // Negativo (< 0): p1 vem antes de p2 (preco menor)
    // Zero (== 0): precos iguais
    // Positivo (> 0): p1 vem depois de p2 (preco maior)
    @Override
    public int compare(Produto p1, Produto p2) {
        if (p1 == null || p2 == null) return 0;

        int difPreco = Double.compare(p1.getPreco(), p2.getPreco());
        if (difPreco != 0) {
            return difPreco;
        }
        // Desempate por codigo para o TreeSet nao descartar produtos com preco igual
        return p1.getCodigo().compareToIgnoreCase(p2.getCodigo());
    }
}
