package lista1_excecoes.exercicio5;

public class ContaBancaria {
    private double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado. Saldo atual: R$ %.2f%n", valor, this.saldo);
        }
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException(
                String.format("Tentativa de saque de R$ %.2f não autorizada. Saldo disponível: R$ %.2f", valor, this.saldo)
            );
        }
        this.saldo -= valor;
        System.out.printf("Saque de R$ %.2f realizado com sucesso! Saldo restante: R$ %.2f%n", valor, this.saldo);
    }
}
