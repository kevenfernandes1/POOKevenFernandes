package lista4_topicos_avancados;

import java.util.Enumeration;
import java.util.Hashtable;

public class SessoesHashtable {
    // Hashtable e sincronizada por padrao e nao aceita chave ou valor null
    private Hashtable<String, String> sessoes;

    public SessoesHashtable() {
        this.sessoes = new Hashtable<>();
    }

    public void adicionarSessao(String token, String usuario) {
        if (token != null && usuario != null) {
            sessoes.put(token, usuario);
        }
    }

    // Percorrendo chaves com Enumeration
    public void listarChavesComEnumeration() {
        System.out.println("Tokens cadastrados (Enumeration.keys):");
        Enumeration<String> chaves = sessoes.keys();
        while (chaves.hasMoreElements()) {
            System.out.println("- " + chaves.nextElement());
        }
    }

    // Percorrendo valores com Enumeration
    public void listarValoresComEnumeration() {
        System.out.println("\nUsuarios cadastrados (Enumeration.elements):");
        Enumeration<String> valores = sessoes.elements();
        while (valores.hasMoreElements()) {
            System.out.println("- " + valores.nextElement());
        }
    }

    public Hashtable<String, String> getSessoes() {
        return sessoes;
    }
}
