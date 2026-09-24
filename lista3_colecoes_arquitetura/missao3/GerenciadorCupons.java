package lista3_colecoes_arquitetura.missao3;

import java.util.Iterator;
import java.util.Set;

public class GerenciadorCupons {

    // Usamos it.remove() com Iterator para remover durante a iteracao.
    // Se usassemos um for-each chamando set.remove(), o Java dispararia
    // a excecao ConcurrentModificationException.
    public static void removerCuponsComDescontoZero(Set<CupomDesconto> cupons) {
        if (cupons == null) return;

        Iterator<CupomDesconto> it = cupons.iterator();
        while (it.hasNext()) {
            CupomDesconto cupom = it.next();
            if (cupom.getPorcentagem() <= 0.0) {
                System.out.println("Removendo cupom zerado: " + cupom.getCodigo());
                it.remove();
            }
        }
    }
}
