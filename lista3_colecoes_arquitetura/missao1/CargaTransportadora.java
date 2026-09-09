package lista3_colecoes_arquitetura.missao1;

public class CargaTransportadora implements Rastreavel {
    private String numeroConhecimentoFrete;
    private String placaVeiculo;
    private double pesoKg;
    private String localizacaoAtual;

    public CargaTransportadora(String numeroConhecimentoFrete, String placaVeiculo, double pesoKg, String localizacaoAtual) {
        this.numeroConhecimentoFrete = numeroConhecimentoFrete;
        this.placaVeiculo = placaVeiculo;
        this.pesoKg = pesoKg;
        this.localizacaoAtual = localizacaoAtual;
    }

    public String getNumeroConhecimentoFrete() { return numeroConhecimentoFrete; }
    public String getPlacaVeiculo() { return placaVeiculo; }
    public double getPesoKg() { return pesoKg; }
    public String getLocalizacaoAtual() { return localizacaoAtual; }
    public void setLocalizacaoAtual(String localizacaoAtual) { this.localizacaoAtual = localizacaoAtual; }

    @Override
    public String getStatusRastreio() {
        return String.format("[TRANSPORTADORA] CT-e: %s | Placa: %s | Peso: %.2f kg | Local: %s",
                numeroConhecimentoFrete, placaVeiculo, pesoKg, localizacaoAtual);
    }
}
