package lista4_topicos_avancados;

import java.util.Objects;

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

    // Ordena por preco (e desempata por codigo para nao sumir no TreeSet)
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
        return "Produto [codigo=" + codigo + ", nome=" + nome + ", preco=" + preco + "]";
    }
}
