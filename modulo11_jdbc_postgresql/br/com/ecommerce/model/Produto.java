package br.com.ecommerce.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidade de Domínio Produto mapeada para a tabela relacional 'produto'.
 * Utiliza BigDecimal para precisão monetária exata conforme o tipo NUMERIC(10,2) do SQL.
 */
public class Produto {
    private String codigo;
    private String nome;
    private BigDecimal preco;
    private int quantidadeEstoque;

    public Produto() {
    }

    public Produto(String codigo, String nome, BigDecimal preco, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Produto(String codigo, String nome, double precoDouble, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = BigDecimal.valueOf(precoDouble);
        this.quantidadeEstoque = quantidadeEstoque;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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
        return String.format("[Código: %-7s | Nome: %-30s | Preço: R$ %9.2f | Estoque: %3d unid.]",
                codigo, nome, preco, quantidadeEstoque);
    }
}
