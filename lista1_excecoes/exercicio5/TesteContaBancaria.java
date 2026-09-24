package lista1_excecoes.exercicio5;

public class TesteContaBancaria {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(500.0);
        System.out.printf("Saldo inicial: R$ %.2f\n\n", conta.getSaldo());

        // Teste de saque valido
        System.out.println("Saque de R$ 200,00:");
        try {
            conta.sacar(200.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Teste de saque estourando o saldo
        System.out.println("\nSaque de R$ 400,00:");
        try {
            conta.sacar(400.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.printf("\nSaldo final da conta: R$ %.2f\n", conta.getSaldo());
    }
}
