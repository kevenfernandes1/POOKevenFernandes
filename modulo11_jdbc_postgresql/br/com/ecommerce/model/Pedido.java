package br.com.ecommerce.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entidade Pedido mapeada para a tabela relacional 'pedido'.
 */
public class Pedido {
    private String idPedido;
    private String codigoProduto;
    private int quantidadeComprada;
    private LocalDateTime dataPedido;

    public Pedido() {
    }

    public Pedido(String idPedido, String codigoProduto, int quantidadeComprada, LocalDateTime dataPedido) {
        this.idPedido = idPedido;
        this.codigoProduto = codigoProduto;
        this.quantidadeComprada = quantidadeComprada;
        this.dataPedido = dataPedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Override
    public String toString() {
        String dataFormatada = (dataPedido != null) 
                ? dataPedido.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) 
                : "N/A";
        return String.format("[Pedido: %-10s | Produto: %-7s | Qtd: %2d | Data: %s]",
                idPedido, codigoProduto, quantidadeComprada, dataFormatada);
    }
}
