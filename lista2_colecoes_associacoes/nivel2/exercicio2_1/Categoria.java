package lista2_colecoes_associacoes.nivel2.exercicio2_1;

import java.util.ArrayList;

public class Categoria {
    private int id;
    private String descricao;
    private ArrayList<Produto> listaProdutos;

    public Categoria(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.listaProdutos = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public ArrayList<Produto> getListaProdutos() { return listaProdutos; }

    public void adicionarProduto(Produto p) {
        if (p != null && !listaProdutos.contains(p)) {
            listaProdutos.add(p);
        }
    }

    public void removerProduto(Produto p) {
        if (p != null) {
            listaProdutos.remove(p);
        }
    }

    public void listarProdutos() {
        System.out.println("Categoria: [" + id + "] " + descricao + " (Total: " + listaProdutos.size() + " produto(s))");
        if (listaProdutos.isEmpty()) {
            System.out.println("  (Nenhum produto vinculado)");
        } else {
            for (Produto p : listaProdutos) {
                System.out.println("  -> " + p);
            }
        }
    }
}
