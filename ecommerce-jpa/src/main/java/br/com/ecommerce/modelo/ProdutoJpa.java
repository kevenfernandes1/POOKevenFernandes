package br.com.ecommerce.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO_JPA")
@NamedQueries({
    @NamedQuery(
        name = "ProdutoJpa.listarTodosOrdenados",
        query = "SELECT p FROM ProdutoJpa p ORDER BY p.nome ASC"
    ),
    @NamedQuery(
        name = "ProdutoJpa.buscarPorFaixaPreco",
        query = "SELECT p FROM ProdutoJpa p WHERE p.preco BETWEEN :precoMinimo AND :precoMaximo"
    ),
    @NamedQuery(
        name = "ProdutoJpa.buscarPorCodigoUnico",
        query = "SELECT p FROM ProdutoJpa p WHERE p.codigo = :codigo"
    )
})
public class ProdutoJpa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 10, nullable = false)
    private String codigo;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA")
    private Categoria categoria;

    @ManyToMany(mappedBy = "produtos")
    private List<PedidoJpa> pedidos = new ArrayList<>();

    public ProdutoJpa() {
    }

    public ProdutoJpa(String codigo, String nome, BigDecimal preco, Integer quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public ProdutoJpa(String codigo, String nome, BigDecimal preco, Integer quantidadeEstoque, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<PedidoJpa> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoJpa> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "ProdutoJpa [id=" + id + ", codigo=" + codigo + ", nome=" + nome + 
               ", preco=" + preco + ", estoque=" + quantidadeEstoque + "]";
    }
}
