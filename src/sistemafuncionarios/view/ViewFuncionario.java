package sistemafuncionarios.view;

import sistemafuncionarios.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class ViewFuncionario {

    static Scanner sc = new Scanner(System.in);

    public static boolean menuAdicionarFuncionario() {
        Funcionario funcionario = null;
        int opcao = 0;

        do {
            System.out.println("-------------------------------------");
            System.out.println("Cargo do Funcionário");
            System.out.println("[ 1 ] - Gerente Geral");
            System.out.println("[ 2 ] - Gerente de Departamento");
            System.out.println("[ 3 ] - Líder Técnico");
            System.out.println("[ 4 ] - Colaborador");
            System.out.println("[ 9 ] - Voltar");
            try {
                System.out.print("Opção desejada (9 para voltar): ");
                opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    case 1:
                        funcionario = new GerenteGeral();
                        break;
                    case 2:
                        funcionario = new GerenteDepartamento();
                        break;
                    case 3:
                        funcionario = new LiderTecnico();
                        break;
                    case 4:
                        funcionario = new Colaborador();
                        break;
                    case 9:
                        System.out.println("Voltando ao menu inicial...");
                        break;
                    default:
                        System.err.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Opção inválida!");
            }
//            if (funcionario instanceof Colaborador) {
//                System.out.println("Colaborador adicionado com sucesso!");
//            } else if (funcionario instanceof LiderTecnico) {
//                System.out.println("Líder Técnico adicionado com sucesso!");
//            } else if (funcionario instanceof GerenteDepartamento) {
//                System.out.println("Gerente de Departamento adicionado com sucesso!");
//            } else if (funcionario instanceof GerenteGeral) {
//                System.out.println("Gerente Geral adicionado com sucesso!");
//            }
        } while (opcao != 9);

        return funcionario == null;
    }

    public static void listarFuncionarios(boolean ativo) {

        List<Funcionario> funcionarioList = new ArrayList<>();

        if (Funcionario.getFuncionarios().isEmpty()) {
            System.out.println("Ainda não há funcionários cadastrados.");
        } else {
            if (ativo) {
                funcionarioList = Funcionario.getFuncionariosAtivos();
            } else {
                funcionarioList = Funcionario.getFuncionariosDemitidos();
            }
            if (!funcionarioList.isEmpty()) {
                for(Funcionario funcionario: funcionarioList) {
                    System.out.println(funcionario);
                }
            }
        }
    }

    public static boolean listarFuncionarios() {

        if (Funcionario.getFuncionarios().isEmpty()) {
            System.out.println("Ainda não há funcionários cadastrados.");
            return false;
        } else {
            System.out.println("------------------------");
            for (int i = 0; i < Funcionario.getFuncionarios().size(); i++) {
                System.out.println("- Funcionário " + (i+1));
                System.out.println(Funcionario.getFuncionarios().get(i));
            }
            return true;
        }
    }

    public static Funcionario getFuncionario() {

        int indexFuncionario;
        Funcionario funcionario = null;

        if(listarFuncionarios()) {
            try {
                do {
                    System.out.print("Número do funcionário (0 para voltar): ");
                    indexFuncionario = Integer.parseInt(sc.nextLine());
                    if (indexFuncionario == 0) {
                        break;
                    }
                } while (indexFuncionario > Funcionario.getFuncionarios().size());

                if (indexFuncionario != 0) {
                    funcionario = Funcionario.getFuncionarios().get(indexFuncionario - 1);
                }

            } catch(Exception e) {
                e.getMessage();
            }
        }
        return funcionario;
    }

    public static void atualizarFuncionario(Funcionario funcionario) {

        int opcao = 0;
        char confirmar;

        do {
            try {
                funcionario.toStringCompleto();
                System.out.println("-------------------------------------");
                System.out.println("[ 1 ] - Nome");
                System.out.println("[ 2 ] - Salário");
                System.out.println("[ 3 ] - Status do funcionário");
                System.out.print("Opção desejada (9 para voltar): ");
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("Nome atualizado: ");
                        funcionario.setNome(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Novo Salário: R$");
                        double salario = Double.parseDouble(sc.nextLine());
                        funcionario.setSalario(salario);
                        break;
                    case 3:
                        System.out.println("Status: " + (funcionario.isAtivo() ? "Trabalhando" : "Demitido"));
                        if (funcionario.isAtivo()) {
                            do {
                                System.out.printf("Demitir %s %s? [S / N] : ", funcionario.getClass().getSimpleName(), funcionario.getNome());
                                confirmar = sc.nextLine().trim().toLowerCase().charAt(0);
                            } while (confirmar != 's' && confirmar != 'n');

                            if (confirmar == 's') {
                                funcionario.setAtivo(false);
                            }
                        } else {

                            do {
                                System.out.printf("Contratar %s para o cargo de %s? [S / N] : ", funcionario.getNome(), funcionario.getClass().getSimpleName());
                                confirmar = sc.nextLine().trim().toLowerCase().charAt(0);
                            } while (confirmar != 's' && confirmar != 'n');

                            if (confirmar == 's') {
                                funcionario.setAtivo(true);
                            }
                        }
                        break;
                    case 9:
                        break;
                    default:
                        System.err.println("Opção inválida!");
                        break;
                }

            } catch (Exception e) {
                e.getMessage();
            }

        } while (opcao != 9);

    }

    public static void verDetalhesFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            funcionario.toStringCompleto();
        }
    }

    public static void demitirFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            funcionario.setAtivo(false);
        }
    }

}
