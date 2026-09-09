package lista3_colecoes_arquitetura.missao1;

public class PacoteCorreios implements Rastreavel {
    private String codigoEtiqueta;
    private String cepDestino;
    private String statusAtual;

    public PacoteCorreios(String codigoEtiqueta, String cepDestino, String statusAtual) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.cepDestino = cepDestino;
        this.statusAtual = statusAtual;
    }

    public String getCodigoEtiqueta() { return codigoEtiqueta; }
    public String getCepDestino() { return cepDestino; }
    public String getStatusAtual() { return statusAtual; }
    public void setStatusAtual(String statusAtual) { this.statusAtual = statusAtual; }

    @Override
    public String getStatusRastreio() {
        return String.format("[CORREIOS] Etiqueta: %s | CEP Destino: %s | Status: %s",
                codigoEtiqueta, cepDestino, statusAtual);
    }
}
