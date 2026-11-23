package br.com.ecommerce.modelo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "FRETE_RODOVIARIO")
@PrimaryKeyJoinColumn(name = "ID_FRETE")
public class FreteRodoviario extends Frete {

    private static final long serialVersionUID = 1L;

    @Column(name = "placa_veiculo", length = 10)
    private String placaVeiculo;

    @Column(name = "valor_pedagios", precision = 10, scale = 2)
    private BigDecimal valorPedagios;

    public FreteRodoviario() {
    }

    public FreteRodoviario(String codigoRastreio, BigDecimal valorBase, String placaVeiculo, BigDecimal valorPedagios) {
        super(codigoRastreio, valorBase);
        this.placaVeiculo = placaVeiculo;
        this.valorPedagios = valorPedagios;
    }

    @Override
    public BigDecimal calcularCustoTotal() {
        BigDecimal base = getValorBase() != null ? getValorBase() : BigDecimal.ZERO;
        BigDecimal pedagio = this.valorPedagios != null ? this.valorPedagios : BigDecimal.ZERO;
        return base.add(pedagio);
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public BigDecimal getValorPedagios() {
        return valorPedagios;
    }

    public void setValorPedagios(BigDecimal valorPedagios) {
        this.valorPedagios = valorPedagios;
    }

    @Override
    public String toString() {
        return "FreteRodoviario [rastreio=" + getCodigoRastreio() + ", placa=" + placaVeiculo + 
               ", total=R$ " + calcularCustoTotal() + "]";
    }
}
