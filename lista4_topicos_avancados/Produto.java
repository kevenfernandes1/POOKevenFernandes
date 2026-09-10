package lista4_topicos_avancados;

import java.util.Objects;

/**
 * Entidade Produto com ordenação natural primária por PREÇO (e secundária por código)
 * para viabilizar consultas de faixas, tetos e pisos nas árvores binárias do SortedSet/TreeSet.
 */
public class Produto implements Comparable<Produto> {
    private String codigo;
    private String nome;
    private double preco;

    public Produto(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * [ORDENAÇÃO NATURAL POR PREÇO]:
     * 1. Compara primariamente o valor do preço (crescente).
     * 2. Em caso de empate de preço, utiliza o código como desempate para garantir
     *    consistência estrita com equals() e evitar que o TreeSet descarte produtos de mesmo valor.
     */
    @Override
    public int compareTo(Produto outro) {
        if (outro == null) return 1;
        int compPreco = Double.compare(this.preco, outro.preco);
        if (compPreco != 0) {
            return compPreco;
        }
        return this.codigo.compareToIgnoreCase(outro.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format("[Código: %-6s | Nome: %-25s | Preço: R$ %8.2f]", codigo, nome, preco);
    }
}
