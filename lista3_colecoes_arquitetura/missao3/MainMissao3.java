package lista3_colecoes_arquitetura.missao3;

import java.util.HashSet;
import java.util.Set;

public class MainMissao3 {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println(" MISSÃO 3: PREVENÇÃO DE DUPLICIDADES (HASHSET) E ITERATOR SEGURO");
        System.out.println("======================================================================");

        // 1. Instanciando o conjunto HashSet
        Set<CupomDesconto> conjuntoCupons = new HashSet<>();

        // 2. Criando cupons
        CupomDesconto c1 = new CupomDesconto("PRIMEIRACOMPRA", 10.0);
        CupomDesconto c2 = new CupomDesconto("BLACKFRIDAY", 25.0);
        CupomDesconto c3 = new CupomDesconto("FRETEGRATIS", 0.0); // Cupom zerado (será removido)
        CupomDesconto c4 = new CupomDesconto("DESCONTOZERO", 0.0); // Outro cupom zerado
        CupomDesconto c5Duplicado = new CupomDesconto("PRIMEIRACOMPRA", 30.0); // Mesmo código que c1!

        // 3. Testando inserções e avaliando o retorno booleano de add()
        System.out.println("\n>>> 1. TESTE DE INSERÇÃO E REJEIÇÃO DE DUPLICIDADES COM ADD() <<<");
        boolean inseriuC1 = conjuntoCupons.add(c1);
        System.out.printf("Inserção de '%s' (10%%): %s%n", c1.getCodigo(), inseriuC1 ? "SUCESSO (true)" : "REJEITADO (false)");

        boolean inseriuC2 = conjuntoCupons.add(c2);
        System.out.printf("Inserção de '%s' (25%%): %s%n", c2.getCodigo(), inseriuC2 ? "SUCESSO (true)" : "REJEITADO (false)");

        boolean inseriuC3 = conjuntoCupons.add(c3);
        System.out.printf("Inserção de '%s' (0%%): %s%n", c3.getCodigo(), inseriuC3 ? "SUCESSO (true)" : "REJEITADO (false)");

        boolean inseriuC4 = conjuntoCupons.add(c4);
        System.out.printf("Inserção de '%s' (0%%): %s%n", c4.getCodigo(), inseriuC4 ? "SUCESSO (true)" : "REJEITADO (false)");

        System.out.println("\n--- Tentando inserir cupom com código repetido ('PRIMEIRACOMPRA' com 30%) ---");
        boolean inseriuC5 = conjuntoCupons.add(c5Duplicado);
        System.out.printf("Inserção de '%s' duplicado: %s%n", c5Duplicado.getCodigo(), inseriuC5 ? "SUCESSO (true)" : "REJEITADO (false) [Duplicata bloqueada pelo HashSet!]");

        System.out.println("\n--- Lista de Cupons no Conjunto (" + conjuntoCupons.size() + " cupons únicos) ---");
        for (CupomDesconto cupom : conjuntoCupons) {
            System.out.println("  " + cupom);
        }

        // 4. Executando a limpeza de cupons zerados com Iterator
        System.out.println("\n>>> 2. HIGIENIZAÇÃO SEGURA COM ITERATOR (SEM ConcurrentModificationException) <<<");
        GerenciadorCupons.removerCuponsComDescontoZero(conjuntoCupons);

        System.out.println("\n--- Estado Final dos Cupons no E-Commerce (" + conjuntoCupons.size() + " cupons ativos) ---");
        for (CupomDesconto cupom : conjuntoCupons) {
            System.out.println("  " + cupom);
        }

        System.out.println("\n======================================================================");
    }
}
