package lista2_colecoes_associacoes.nivel4;

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

    // [Exercício 4.1] Ordenação natural alfabética pelo nome (case insensitive)
    @Override
    public int compareTo(Produto outro) {
        return this.nome.compareToIgnoreCase(outro.getNome());
    }

    @Override
    public String toString() {
        return String.format("[Código: %-6s | Nome: %-22s | Preço: R$ %8.2f]", codigo, nome, preco);
    }
}
