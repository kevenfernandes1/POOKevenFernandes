package lista3_colecoes_arquitetura.missao1;

public class EntregaExpressa implements Rastreavel {
    private String idPedido;
    private String nomeEntregador;
    private int minutosEstimadosEntrega;
    private String statusEntrega;

    public EntregaExpressa(String idPedido, String nomeEntregador, int minutosEstimadosEntrega, String statusEntrega) {
        this.idPedido = idPedido;
        this.nomeEntregador = nomeEntregador;
        this.minutosEstimadosEntrega = minutosEstimadosEntrega;
        this.statusEntrega = statusEntrega;
    }

    public String getIdPedido() { return idPedido; }
    public String getNomeEntregador() { return nomeEntregador; }
    public int getMinutosEstimadosEntrega() { return minutosEstimadosEntrega; }
    public String getStatusEntrega() { return statusEntrega; }
    public void setStatusEntrega(String statusEntrega) { this.statusEntrega = statusEntrega; }

    @Override
    public String getStatusRastreio() {
        return String.format("[ENTREGA EXPRESSA] Pedido: %s | Entregador: %s | ETA: %d min | Status: %s",
                idPedido, nomeEntregador, minutosEstimadosEntrega, statusEntrega);
    }
}
