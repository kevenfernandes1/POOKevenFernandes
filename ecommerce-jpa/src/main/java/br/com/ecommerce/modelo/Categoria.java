package br.com.ecommerce.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(length = 10)
    private String sigla;

    // Lista ordenada alfabeticamente pelo nome do produto
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @OrderBy("nome ASC")
    private List<ProdutoJpa> produtos = new ArrayList<>();

    // Mapa indexado pelo atributo 'codigo' do produto
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @MapKey(name = "codigo")
    private Map<String, ProdutoJpa> produtosPorCodigo = new HashMap<>();

    public Categoria() {
    }

    public Categoria(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public void adicionarProduto(ProdutoJpa produto) {
        this.produtos.add(produto);
        this.produtosPorCodigo.put(produto.getCodigo(), produto);
        produto.setCategoria(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<ProdutoJpa> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoJpa> produtos) {
        this.produtos = produtos;
    }

    public Map<String, ProdutoJpa> getProdutosPorCodigo() {
        return produtosPorCodigo;
    }

    public void setProdutosPorCodigo(Map<String, ProdutoJpa> produtosPorCodigo) {
        this.produtosPorCodigo = produtosPorCodigo;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nome=" + nome + ", sigla=" + sigla + "]";
    }
}
