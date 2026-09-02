package lista2_colecoes_associacoes.nivel3;

import java.util.ArrayList;

public class NotaFiscal {
    private int numero;
    private String data;
    private ArrayList<Item> listaItens;

    public NotaFiscal(int numero, String data) {
        this.numero = numero;
        this.data = data;
        this.listaItens = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public ArrayList<Item> getListaItens() { return listaItens; }

    public void addItem(Item item) {
        if (item != null && !this.listaItens.contains(item)) {
            this.listaItens.add(item);
        }
    }

    public void removeItem(Item item) {
        if (item != null) {
            this.listaItens.remove(item);
        }
    }

    public double calcularTotalNota() {
        double total = 0.0;
        for (Item item : listaItens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void imprimirNota() {
        System.out.println("=================================================================");
        System.out.printf("                    NOTA FISCAL Nº %04d                         %n", numero);
        System.out.printf(" Data de Emissão: %s%n", data);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-10s %-22s %5s %12s %12s%n", "CÓDIGO", "PRODUTO", "QTD", "UNITÁRIO", "SUBTOTAL");
        System.out.println("-----------------------------------------------------------------");
        for (Item item : listaItens) {
            System.out.printf("%-10s %-22s %5d   R$ %9.2f   R$ %9.2f%n",
                    item.getProduto().getCodigo(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getPrecoVendaUnitario(),
                    item.calcularSubtotal());
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(" VALOR TOTAL DA NOTA:                                R$ %9.2f%n", calcularTotalNota());
        System.out.println("=================================================================");
    }
}
