package lista3_colecoes_arquitetura.missao3;

import java.util.Objects;

/**
 * Entidade de Cupom Promocional com integridade de unicidade baseada exclusivamente no código.
 */
public class CupomDesconto {
    private String codigo;
    private double porcentagem;

    public CupomDesconto(String codigo, double porcentagem) {
        this.codigo = codigo;
        this.porcentagem = porcentagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    /**
     * Contrato de igualdade baseado EXCLUSIVAMENTE no atributo codigo.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CupomDesconto that = (CupomDesconto) o;
        return Objects.equals(codigo != null ? codigo.toUpperCase() : null,
                              that.codigo != null ? that.codigo.toUpperCase() : null);
    }

    /**
     * Contrato de hashCode baseado EXCLUSIVAMENTE no atributo codigo.
     * Fundamental para a correta distribuição nos buckets da tabela Hash do HashSet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo != null ? codigo.toUpperCase() : null);
    }

    @Override
    public String toString() {
        return String.format("Cupom [Código: %-12s | Desconto: %5.1f%%]", codigo, porcentagem);
    }
}
