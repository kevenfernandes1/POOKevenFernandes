package exercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidacaoIdade {

    public static void validarIdade(int idade) {
        if (idade < 0 || idade > 150) {
            throw new IdadeInvalidaException("A idade deve estar entre 0 e 150 anos. Valor informado: " + idade);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite a idade da pessoa: ");
            int idade = scanner.nextInt();

            validarIdade(idade);
            System.out.println("Idade válida cadastrada com sucesso: " + idade + " anos.");

        } catch (IdadeInvalidaException e) {
            System.err.println("Erro de Validação: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Por favor, digite um número inteiro para a idade!");
        } finally {
            scanner.close();
        }
    }
}
