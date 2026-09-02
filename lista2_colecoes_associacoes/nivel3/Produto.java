package lista2_colecoes_associacoes.nivel3;

import java.util.ArrayList;

public class Produto {
    private String codigo;
    private String nome;
    private double precoBase;
    private ArrayList<Item> listaItens;

    public Produto(String codigo, String nome, double precoBase) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.listaItens = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    public ArrayList<Item> getListaItens() { return listaItens; }

    public void addItem(Item item) {
        if (item != null && !this.listaItens.contains(item)) {
            this.listaItens.add(item);
        }
    }

    public void removeItem(Item item) {
        if (item != null) {
            this.listaItens.remove(item);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Preço Base: R$ %.2f)", codigo, nome, precoBase);
    }
}
