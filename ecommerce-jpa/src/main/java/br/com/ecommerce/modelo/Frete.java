package br.com.ecommerce.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "FRETE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Frete implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_rastreio", nullable = false, length = 30)
    private String codigoRastreio;

    @Column(name = "valor_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorBase;

    public Frete() {
    }

    public Frete(String codigoRastreio, BigDecimal valorBase) {
        this.codigoRastreio = codigoRastreio;
        this.valorBase = valorBase;
    }

    // Metodo abstrato implementado polimorficamente pelas subclasses
    public abstract BigDecimal calcularCustoTotal();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }

    @Override
    public String toString() {
        return "Frete [id=" + id + ", rastreio=" + codigoRastreio + ", base=" + valorBase + "]";
    }
}
