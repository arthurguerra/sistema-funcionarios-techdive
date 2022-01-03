package sistemafuncionarios.view;

import sistemafuncionarios.model.Funcionario;

import javax.swing.text.View;
import java.util.Scanner;

public class SistemaFuncionariosApp {

    public static void main(String[] args) {

        iniciar();

    }

    public static void iniciar() {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        do {
            menuIniciar();
            try {
                opcao = Integer.parseInt(sc.nextLine());

            } catch (Exception e) {
                e.getMessage();
            }

            Funcionario funcionario;

            switch(opcao) {
                case 1:
                    ViewFuncionario.menuAdicionarFuncionario();
                    break;
                case 2:
                    funcionario = ViewFuncionario.getFuncionario();
                    if (funcionario != null) {
                        funcionario.toStringCompleto();
                    }
                    break;
                case 3:
                    funcionario = ViewFuncionario.getFuncionario();
                    if (funcionario != null) {
                        funcionario.setAtivo(false);
                        System.out.printf("%s %s demitido com sucesso.\n", funcionario.getClass().getSimpleName(), funcionario.getNome());
                    }
                    break;
                case 4:
                    funcionario = ViewFuncionario.getFuncionario();
                    if (funcionario != null) {
                        ViewFuncionario.atualizarFuncionario(funcionario);
                    }
                    break;
                case 5:
                    ViewFuncionario.listarFuncionarios();
                    break;
                case 6:
                    ViewFuncionario.listarFuncionarios(true);
                    break;
                case 7:
                    ViewFuncionario.listarFuncionarios(false);
                    break;
                case 9:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.err.println("Opção inválida!");
                    break;
            }
        } while (opcao != 9);

    }

    public static void menuIniciar() {
        System.out.println("-------------------------------------");
        System.out.println("[ 1 ] - Adicionar funcionário");
        System.out.println("[ 2 ] - Ver detalhes de um funcionário");
        System.out.println("[ 3 ] - Demitir funcionário");
        System.out.println("[ 4 ] - Atualizar informações de um funcionário");
        System.out.println("[ 5 ] - Listar todos os funcionários");
        System.out.println("[ 6 ] - Listar somente os funcionários trabalhando");
        System.out.println("[ 7 ] - Listar somente os funcionários demitidos");
        System.out.println("[ 9 ] - Sair");
        System.out.print("Opção desejada (9 para sair): ");
    }
}
