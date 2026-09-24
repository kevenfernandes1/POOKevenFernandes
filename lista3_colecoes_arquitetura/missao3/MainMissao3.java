package lista3_colecoes_arquitetura.missao3;

import java.util.HashSet;
import java.util.Set;

public class MainMissao3 {
    public static void main(String[] args) {
        Set<CupomDesconto> cupons = new HashSet<>();

        CupomDesconto c1 = new CupomDesconto("PRIMEIRACOMPRA", 10.0);
        CupomDesconto c2 = new CupomDesconto("BLACKFRIDAY", 25.0);
        CupomDesconto c3 = new CupomDesconto("FRETEGRATIS", 0.0);
        CupomDesconto c4 = new CupomDesconto("DESCONTOZERO", 0.0);
        CupomDesconto c5Repetido = new CupomDesconto("PRIMEIRACOMPRA", 30.0); // mesmo codigo

        // Testando retorno do add()
        System.out.println("Inserindo cupons:");
        System.out.println("c1: " + cupons.add(c1));
        System.out.println("c2: " + cupons.add(c2));
        System.out.println("c3: " + cupons.add(c3));
        System.out.println("c4: " + cupons.add(c4));
        System.out.println("c5 (duplicado): " + cupons.add(c5Repetido)); // deve retornar false

        System.out.println("\nCupons cadastrados no Set (" + cupons.size() + "):");
        for (CupomDesconto c : cupons) {
            System.out.println(c);
        }

        // Limpeza dos cupons com desconto 0 usando Iterator
        System.out.println("\nRemovendo cupons zerados:");
        GerenciadorCupons.removerCuponsComDescontoZero(cupons);

        System.out.println("\nCupons ativos restantes (" + cupons.size() + "):");
        for (CupomDesconto c : cupons) {
            System.out.println(c);
        }
    }
}
