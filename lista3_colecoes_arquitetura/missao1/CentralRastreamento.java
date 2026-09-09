package lista3_colecoes_arquitetura.missao1;

/**
 * Central de monitoramento com validação polimórfica de tipos em tempo de execução.
 */
public class CentralRastreamento {

    /**
     * Inspeciona um objeto genérico (Object), validando se ele realiza a interface Rastreavel.
     * Caso realize, efetua o downcasting explícito para acessar o método getStatusRastreio().
     * Caso contrário, emite uma mensagem informando que o item não é rastreável.
     * 
     * @param item Objeto genérico a ser inspecionado.
     */
    public static void inspecionarItem(Object item) {
        if (item instanceof Rastreavel) {
            // Casting explícito com segurança de tipos após checagem do operador instanceof
            Rastreavel itemRastreavel = (Rastreavel) item;
            System.out.println("-> Item Rastreável Detectado:");
            System.out.println("   " + itemRastreavel.getStatusRastreio());
        } else {
            String tipoObjeto = (item != null) ? item.getClass().getSimpleName() : "null";
            System.out.println("-> Atenção: O item informado (" + tipoObjeto + ") NÃO é passível de rastreamento no sistema logístico!");
        }
    }
}
