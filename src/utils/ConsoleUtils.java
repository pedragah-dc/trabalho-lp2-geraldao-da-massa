package utils;

import java.util.Scanner;

public class ConsoleUtils {

    public static String lerStringValida(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = sc.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("ERRO: o campo não pode ficar vazio.");
        }
    }

    public static int lerInteiroValido(Scanner sc, String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            String input = sc.nextLine();
            try {
                int valor = Integer.parseInt(input);
                // Correção: se o valor estiver entre min E max, ele é válido e retorna
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("O número deve estar entre " + min + " e " + max);
            } catch (NumberFormatException e) {
                System.out.println("ERRO: por favor, insira um número inteiro válido.");
            }
        }
    }
}