package lista3_colecoes_arquitetura.missao4;

import java.util.Objects;

/**
 * Entidade Produto com contrato de ordenação natural alfabética (Comparable).
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

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    /**
     * [ORDENAÇÃO NATURAL]: Critério alfabético por nome.
     * Utiliza desempate pelo código para consistência rigorosa com equals.
     */
    @Override
    public int compareTo(Produto outro) {
        if (outro == null) return 1;
        int compNome = this.nome.compareToIgnoreCase(outro.getNome());
        if (compNome != 0) {
            return compNome;
        }
        return this.codigo.compareToIgnoreCase(outro.getCodigo());
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
        return String.format("Produto [Código: %-6s | Nome: %-25s | Preço: R$ %8.2f]", codigo, nome, preco);
    }
}
