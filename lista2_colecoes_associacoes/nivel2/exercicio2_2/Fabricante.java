package lista2_colecoes_associacoes.nivel2.exercicio2_2;

import java.util.ArrayList;

public class Fabricante {
    private String cnpj;
    private String nome;
    private ArrayList<Produto> listaProdutos;

    public Fabricante(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.listaProdutos = new ArrayList<>();
    }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public ArrayList<Produto> getListaProdutos() { return listaProdutos; }

    public void addProduto(Produto novo) {
        if (novo == null || this.listaProdutos.contains(novo)) {
            return;
        }
        this.listaProdutos.add(novo);
        novo.setFabricante(this);
    }

    public void removeProduto(Produto antigo) {
        if (antigo == null || !this.listaProdutos.contains(antigo)) {
            return;
        }
        this.listaProdutos.remove(antigo);
        antigo.setFabricante(null);
    }

    public void listarProdutos() {
        System.out.println("Fabricante: " + nome + " (CNPJ: " + cnpj + ") - Total: " + listaProdutos.size() + " produto(s)");
        if (listaProdutos.isEmpty()) {
            System.out.println("  (Lista vazia)");
        } else {
            for (Produto p : listaProdutos) {
                System.out.println("  -> " + p.getNome() + " (Código: " + p.getCodigo() + ", Preço: R$ " + p.getPreco() + ")");
            }
        }
    }
}
