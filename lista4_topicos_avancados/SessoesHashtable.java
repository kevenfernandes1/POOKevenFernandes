package lista4_topicos_avancados;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Módulo 4: Estruturas Legadas da Plataforma Java (Hashtable e Enumeration).
 * 
 * [CARACTERÍSTICAS DA CLASSE LEGADA HASHTABLE E INTERFACE ENUMERATION]:
 * 
 * 1. Origem Histórica:
 *    - Presentes no Java desde o JDK 1.0 (anteriores ao framework de coleções do Java 2 / JDK 1.2).
 *    - Posteriormente foram adaptadas (retroffited) para implementar a interface Map.
 * 
 * 2. Propriedades Técnicas da Hashtable:
 *    - Sincronizada por Padrão: Todos os métodos de leitura e escrita são 'synchronized' (thread-safe nativo).
 *    - Rejeição Rigorosa a Nulos: NÃO permite nem chaves nulas nem valores nulos (dispara NullPointerException imediata).
 *      (Diferente do HashMap, que aceita uma chave null e múltiplos valores null).
 * 
 * 3. Interface Enumeration (Precursora do Iterator):
 *    - Fornece dois métodos fundamentais:
 *      * hasMoreElements(): equivalente ao hasNext() do Iterator.
 *      * nextElement(): equivalente ao next() do Iterator.
 *    - Diferença crucial para o Iterator: A interface Enumeration é de "apenas leitura" e NÃO possui método de remoção
 *      (não possui equivalente ao 'it.remove()').
 */
public class SessoesHashtable {
    private Hashtable<String, String> tabelaSessoes;

    public SessoesHashtable() {
        this.tabelaSessoes = new Hashtable<>();
    }

    /**
     * Registra uma sessão de usuário ativo.
     * @param tokenSession Token de autenticação da sessão (Chave).
     * @param dadosUsuario Informações do usuário e IP (Valor).
     */
    public void registrarSessao(String tokenSession, String dadosUsuario) {
        if (tokenSession == null || dadosUsuario == null) {
            throw new NullPointerException("Hashtable não permite chaves ou valores nulos!");
        }
        tabelaSessoes.put(tokenSession, dadosUsuario);
        System.out.println("[HASHTABLE] Sessão registrada: Token=" + tokenSession + " => " + dadosUsuario);
    }

    /**
     * Percorre e exibe todas as chaves utilizando o padrão Enumeration via método keys().
     */
    public void listarChavesComEnumeration() {
        System.out.println("\n--- Listagem de Chaves (Tokens) via Enumeration.keys() ---");
        Enumeration<String> chaves = tabelaSessoes.keys();

        int contador = 1;
        while (chaves.hasMoreElements()) {
            String token = chaves.nextElement();
            System.out.printf("  %d. Chave (Token): %s%n", contador++, token);
        }
    }

    /**
     * Percorre e exibe todos os valores utilizando o padrão Enumeration via método elements().
     */
    public void listarValoresComEnumeration() {
        System.out.println("\n--- Listagem de Valores (Usuários) via Enumeration.elements() ---");
        Enumeration<String> valores = tabelaSessoes.elements();

        int contador = 1;
        while (valores.hasMoreElements()) {
            String dados = valores.nextElement();
            System.out.printf("  %d. Valor (Dados): %s%n", contador++, dados);
        }
    }

    /**
     * Percorre a tabela correlacionando chave e valor com Enumeration.
     */
    public void exibirRelatorioSessoes() {
        System.out.println("\n--- Relatório Completo de Sessões Ativas (Hashtable Thread-Safe) ---");
        Enumeration<String> chaves = tabelaSessoes.keys();

        while (chaves.hasMoreElements()) {
            String chave = chaves.nextElement();
            String valor = tabelaSessoes.get(chave);
            System.out.printf("  [SESSÃO ATIVA] Token: %-15s | Usuário/IP: %s%n", chave, valor);
        }
    }

    public Hashtable<String, String> getTabelaSessoes() {
        return tabelaSessoes;
    }
}
