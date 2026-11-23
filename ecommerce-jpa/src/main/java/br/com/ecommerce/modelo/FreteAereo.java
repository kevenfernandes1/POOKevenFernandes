package br.com.ecommerce.modelo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "FRETE_AEREO")
@PrimaryKeyJoinColumn(name = "ID_FRETE")
public class FreteAereo extends Frete {

    private static final long serialVersionUID = 1L;

    @Column(name = "prefixo_voo", length = 20)
    private String prefixoVoo;

    @Column(name = "taxa_aeroportuaria", precision = 10, scale = 2)
    private BigDecimal taxaAeroportuaria;

    public FreteAereo() {
    }

    public FreteAereo(String codigoRastreio, BigDecimal valorBase, String prefixoVoo, BigDecimal taxaAeroportuaria) {
        super(codigoRastreio, valorBase);
        this.prefixoVoo = prefixoVoo;
        this.taxaAeroportuaria = taxaAeroportuaria;
    }

    @Override
    public BigDecimal calcularCustoTotal() {
        BigDecimal base = getValorBase() != null ? getValorBase() : BigDecimal.ZERO;
        BigDecimal taxa = this.taxaAeroportuaria != null ? this.taxaAeroportuaria : BigDecimal.ZERO;
        return base.add(taxa);
    }

    public String getPrefixoVoo() {
        return prefixoVoo;
    }

    public void setPrefixoVoo(String prefixoVoo) {
        this.prefixoVoo = prefixoVoo;
    }

    public BigDecimal getTaxaAeroportuaria() {
        return taxaAeroportuaria;
    }

    public void setTaxaAeroportuaria(BigDecimal taxaAeroportuaria) {
        this.taxaAeroportuaria = taxaAeroportuaria;
    }

    @Override
    public String toString() {
        return "FreteAereo [rastreio=" + getCodigoRastreio() + ", voo=" + prefixoVoo + 
               ", total=R$ " + calcularCustoTotal() + "]";
    }
}
