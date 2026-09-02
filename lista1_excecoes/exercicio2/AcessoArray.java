package lista1_excecoes.exercicio2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AcessoArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cidades = {"São Paulo", "Rio de Janeiro", "Curitiba", "Salvador", "Belo Horizonte"};

        try {
            System.out.println("--- Lista de Cidades Disponíveis (índices 0 a 4) ---");
            System.out.print("Digite um número de 0 a 4 para escolher uma cidade: ");
            int indice = scanner.nextInt();

            String cidadeEscolhida = cidades[indice];
            System.out.println("Cidade selecionada: " + cidadeEscolhida);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Erro: Índice inexistente! Por favor, escolha um número válido entre 0 e 4.");
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Por favor, digite um número inteiro!");
        } finally {
            scanner.close();
        }
    }
}
