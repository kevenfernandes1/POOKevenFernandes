package lista2_colecoes_associacoes.nivel3;

public class Item {
    private int quantidade;
    private double precoVendaUnitario;
    private NotaFiscal notaFiscal;
    private Produto produto;

    public Item(NotaFiscal nf, Produto prod, int quantidade, double precoVendaUnitario) {
        this.notaFiscal = nf;
        this.produto = prod;
        this.quantidade = quantidade;
        this.precoVendaUnitario = precoVendaUnitario;

        // Ao ser instanciado, vincula-se automaticamente à NotaFiscal e ao Produto
        if (nf != null) {
            nf.addItem(this);
        }
        if (prod != null) {
            prod.addItem(this);
        }
    }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPrecoVendaUnitario() { return precoVendaUnitario; }
    public void setPrecoVendaUnitario(double precoVendaUnitario) { this.precoVendaUnitario = precoVendaUnitario; }

    public NotaFiscal getNotaFiscal() { return notaFiscal; }
    public void setNotaFiscal(NotaFiscal notaFiscal) { this.notaFiscal = notaFiscal; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public double calcularSubtotal() {
        return this.quantidade * this.precoVendaUnitario;
    }
}
