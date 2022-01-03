package sistemafuncionarios.utils;

public class Cpf {
    public static boolean validaCpf(String cpf) {

        if (cpf.length() != 11) {
            return false;
        }

        int peso, soma, num;
        int digitoVerificador;
        char digitoVerificador1, digitoVerificador2;

        try {
            peso = 10;
            soma = 0;
            for (int i = 0; i < 9; i++) {
                num = cpf.charAt(i) - 48;
                soma += (num * peso);
                peso--;
            }
            digitoVerificador = 11 - (soma % 11);

            if(digitoVerificador == 10) {
                digitoVerificador1 = '0';
            } else {
                digitoVerificador1 = (char) (digitoVerificador + 48);
            }

            peso = 11;
            soma = 0;
            for (int i = 0; i < 10; i++) {
                num = cpf.charAt(i) - 48;
                soma += (num * peso);
                peso--;
            }
            digitoVerificador = 11 - (soma % 11);
            if (digitoVerificador == 10) {
                digitoVerificador2 = '0';
            } else {
                digitoVerificador2 = (char) (digitoVerificador + 48);
            }

            return digitoVerificador1 == cpf.charAt(9) && digitoVerificador2 == cpf.charAt(10);

        } catch (Exception e) {
            System.err.println("CPF inválido!");
            return false;
        }
    }
}