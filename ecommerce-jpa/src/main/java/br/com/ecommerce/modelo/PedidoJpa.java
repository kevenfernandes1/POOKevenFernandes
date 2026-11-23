package br.com.ecommerce.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO_JPA")
public class PedidoJpa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_pedido", nullable = false, unique = true, length = 30)
    private String numeroPedido;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @ManyToMany
    @JoinTable(
        name = "ITEM_PEDIDO_ASSOCIACAO",
        joinColumns = @JoinColumn(name = "ID_PEDIDO"),
        inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<ProdutoJpa> produtos = new ArrayList<>();

    public PedidoJpa() {
        this.dataPedido = LocalDateTime.now();
    }

    public PedidoJpa(String numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.dataPedido = LocalDateTime.now();
    }

    public void adicionarProduto(ProdutoJpa produto) {
        this.produtos.add(produto);
        produto.getPedidos().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ProdutoJpa> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoJpa> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "PedidoJpa [id=" + id + ", numeroPedido=" + numeroPedido + ", data=" + dataPedido + "]";
    }
}
