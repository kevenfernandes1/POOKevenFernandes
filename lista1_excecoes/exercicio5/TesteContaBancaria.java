package lista1_excecoes.exercicio5;

public class TesteContaBancaria {
    public static void main(String[] args) {
        // Criando uma conta com saldo inicial de R$ 500,00
        ContaBancaria conta = new ContaBancaria(500.00);

        System.out.println("=========================================");
        System.out.println("       SISTEMA DE CONTA BANCÁRIA        ");
        System.out.println("=========================================");
        System.out.printf("Saldo inicial: R$ %.2f%n%n", conta.getSaldo());

        // Teste 1: Operação de saque válido
        System.out.println("--- Teste 1: Realizando saque válido de R$ 200,00 ---");
        try {
            conta.sacar(200.00);
        } catch (SaldoInsuficienteException e) {
            System.err.println("Erro: " + e.getMessage());
        }

        System.out.println();

        // Teste 2: Operação de saque que estoura o saldo
        System.out.println("--- Teste 2: Realizando saque acima do saldo (R$ 400,00) ---");
        try {
            conta.sacar(400.00);
        } catch (SaldoInsuficienteException e) {
            System.err.println("Exceção capturada com sucesso: " + e.getMessage());
        }

        System.out.println();
        System.out.printf("Saldo final da conta: R$ %.2f%n", conta.getSaldo());
        System.out.println("=========================================");
    }
}
