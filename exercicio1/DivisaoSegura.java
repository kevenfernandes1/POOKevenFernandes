package exercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisaoSegura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite o primeiro número inteiro (numerador): ");
            int num1 = scanner.nextInt();

            System.out.print("Digite o segundo número inteiro (denominador): ");
            int num2 = scanner.nextInt();

            int resultado = num1 / num2;
            System.out.println("Resultado da divisão: " + resultado);

        } catch (ArithmeticException e) {
            System.err.println("Erro: Não é possível realizar divisão por zero!");
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Por favor, digite apenas números inteiros!");
        } finally {
            System.out.println("Fim da operação.");
            scanner.close();
        }
    }
}
