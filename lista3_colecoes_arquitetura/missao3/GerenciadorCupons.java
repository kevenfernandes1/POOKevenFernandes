package lista3_colecoes_arquitetura.missao3;

import java.util.Iterator;
import java.util.Set;

/**
 * Serviços de gerenciamento e higienização de cupons promocionais.
 */
public class GerenciadorCupons {

    /**
     * Remove todos os cupons com porcentagem igual a zero da coleção utilizando o padrão Iterator.
     * 
     * [POR QUE UTILIZAR Iterator.remove() AO INVÉS DE for-each/Set.remove()]:
     * As coleções do Java mantêm internamente um contador de modificações estruturais chamado 'modCount'.
     * Ao iterar uma coleção com um loop for-each tradicional (ou for com índice) e chamar diretamente
     * cupons.remove(obj), o 'modCount' da coleção é incrementado enquanto o cursor do iterador
     * não tem conhecimento dessa alteração, resultando no disparo imediato de 'ConcurrentModificationException'
     * (mecanismo conhecido como Fail-Fast).
     * 
     * Ao utilizar 'Iterator it = colecao.iterator()' e a chamada 'it.remove()', o próprio iterador gerencia
     * a sincronização do contador interno 'expectedModCount' com o 'modCount' da coleção, permitindo
     * a remoção segura de elementos durante a travessia sem lançar exceções.
     * 
     * @param conjuntoCupons Coleção Set de cupons a ser higienizada.
     */
    public static void removerCuponsComDescontoZero(Set<CupomDesconto> conjuntoCupons) {
        if (conjuntoCupons == null) return;

        System.out.println("[HIGIENIZAÇÃO] Iniciando varredura com Iterator para remoção de cupons zerados...");
        Iterator<CupomDesconto> it = conjuntoCupons.iterator();
        int removidos = 0;

        while (it.hasNext()) {
            CupomDesconto cupom = it.next();
            if (cupom.getPorcentagem() <= 0.0) {
                System.out.println("  -> Removendo cupom inválido com desconto zero: " + cupom.getCodigo());
                it.remove(); // Remoção segura que atualiza o estado interno do iterador
                removidos++;
            }
        }
        System.out.println("[HIGIENIZAÇÃO] Varredura concluída com sucesso! Total de cupons removidos: " + removidos);
    }
}
