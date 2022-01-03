package sistemafuncionarios.model;

import sistemafuncionarios.utils.Cpf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Funcionario {

    private String nome;
    private String cpf;
    private double salario;
    private boolean isAtivo;
    public static List<Funcionario> funcionarios = new ArrayList<>();

    public Funcionario() {
        Scanner sc = new Scanner(System.in);
        String cpf;
        double salario = 0;

        try {
            System.out.print("Nome: ");
            this.nome = sc.nextLine();

            do {
                System.out.print("CPF: ");
                cpf = sc.nextLine();
                if (!Cpf.validaCpf(cpf)) {
                    System.err.println("CPF inválido!");
                }
            } while (!Cpf.validaCpf(cpf));

            this.cpf = cpf;

            do {
                System.out.print("Salário: R$");
                salario = Double.parseDouble(sc.nextLine());
            } while (salario == 0);

            this.salario = salario;

            this.isAtivo = true;

            getFuncionarios().add(this);
//            funcionarios.add(this);

        } catch (Exception e) {
            System.err.println("Erro ao adicionar funcionário!");
        }

    }

    public Funcionario(String nome, String cpf, double salario, boolean isAtivo) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.isAtivo = isAtivo;
    }

    public static List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public static List<Funcionario> getFuncionariosAtivos() {

        List<Funcionario> funcionariosAtivos = new ArrayList<>();

        if (!getFuncionarios().isEmpty()) {
            for (Funcionario funcionario: getFuncionarios()) {
                if (funcionario.isAtivo()) {
                    funcionariosAtivos.add(funcionario);
                }
            }
        }
        return funcionariosAtivos;
    }

    public static List<Funcionario> getFuncionariosDemitidos() {

        List<Funcionario> funcionariosDemitidos = new ArrayList<>();

        if (!getFuncionarios().isEmpty()) {
            for (Funcionario funcionario: getFuncionarios()) {
                if (!funcionario.isAtivo()) {
                    funcionariosDemitidos.add(funcionario);
                }
            }
        }
        return funcionariosDemitidos;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                "\nCargo: " + this.getClass().getSimpleName() +
                String.format("\nStatus do funcionário: %s\n", isAtivo ? "Trabalhando" : "Demitido");
    }

    public void toStringCompleto() {
        System.out.println("-------------------------------------");
        System.out.println("Nome: "+this.nome);
        System.out.println("Cargo: "+this.getClass().getSimpleName());
        System.out.println("CPF: "+this.cpf);
        System.out.printf("Salário: R$%.2f", salario);
        System.out.printf("\nStatus do funcionário: %s\n", isAtivo ? "Trabalhando" : "Demitido");
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
