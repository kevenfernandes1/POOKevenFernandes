package lista1_excecoes.exercicio4;

import java.util.Scanner;

public class ConversorNumerico {

    public static int converterParaInteiro(String texto) throws NumberFormatException {
        return Integer.parseInt(texto);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um valor textual para converter em número inteiro: ");
        String texto = scanner.nextLine();

        try {
            int numero = converterParaInteiro(texto);
            System.out.println("Conversão realizada com sucesso! Número inteiro: " + numero);
            System.out.println("O resultado de " + numero + " multiplicado por 2 é: " + (numero * 2));

        } catch (NumberFormatException e) {
            System.err.println("Erro: Não foi possível converter \"" + texto + "\" para um número inteiro.");
            System.err.println("Detalhe da exceção: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
