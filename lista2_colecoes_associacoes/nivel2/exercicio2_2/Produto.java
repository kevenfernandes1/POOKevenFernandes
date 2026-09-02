package lista2_colecoes_associacoes.nivel2.exercicio2_2;

import java.util.Objects;

public class Produto {
    private String codigo;
    private String nome;
    private double preco;
    private Fabricante fabricante;

    public Produto(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = null;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public Fabricante getFabricante() { return fabricante; }

    public void setFabricante(Fabricante novoFab) {
        // a) Se o fabricante atual for igual ao novoFab, encerra
        if (this.fabricante == novoFab) {
            return;
        }

        // b) Se novoFab for null, desvincula do fabricante antigo
        if (novoFab == null) {
            Fabricante antigo = this.fabricante;
            this.fabricante = null;
            if (antigo != null) {
                antigo.removeProduto(this);
            }
            return;
        }

        // c) Caso contrário, desvincula do anterior, aponta para novoFab e adiciona no novoFab
        Fabricante antigo = this.fabricante;
        this.fabricante = novoFab;
        if (antigo != null) {
            antigo.removeProduto(this);
        }
        novoFab.addProduto(this);
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
        String fabNome = (fabricante != null) ? fabricante.getNome() : "Nenhum";
        return String.format("[%s] %-25s | R$ %8.2f | Fabricante: %s", codigo, nome, preco, fabNome);
    }
}
