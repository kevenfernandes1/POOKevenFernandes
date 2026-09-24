package lista3_colecoes_arquitetura.missao1;

public class CentralRastreamento {

    public static void inspecionarItem(Object item) {
        // Valida se o objeto implementa a interface Rastreavel usando instanceof
        if (item instanceof Rastreavel) {
            Rastreavel rastreavel = (Rastreavel) item; // casting explicito
            System.out.println("Item rastreavel: " + rastreavel.getStatusRastreio());
        } else {
            System.out.println("O item informado nao pode ser rastreado.");
        }
    }
}
