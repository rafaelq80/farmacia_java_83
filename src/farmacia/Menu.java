package farmacia;

import java.util.InputMismatchException;
import java.util.Scanner;

import farmacia.util.Cores;

public class Menu {

	// Objeto para ler dados digitados pelo usuário
	private static final Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {

		int opcao;

		// Laço de repetição que mantém o menu rodando até a opção sair ser acionada
		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Produto                        ");
			System.out.println("            2 - Listar todos os Produtos             ");
			System.out.println("            3 - Buscar Produto por ID                ");
			System.out.println("            4 - Atualizar Dados do Produto           ");
			System.out.println("            5 - Apagar Produto                       ");
			System.out.println("            0 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			// Tenta capturar a opção digitada
			// Caso o usuário digite algo inválido, mostra mensagem de erro
			try {
				opcao = leia.nextInt();
				leia.nextLine();
			} catch (InputMismatchException e) {
				opcao = -1;
				System.out.println("\nDigite um número inteiro entre 0 e 8");
				leia.nextLine();
			}

			// Se a opção for 0, o sistema será finalizado (System.exit(0))
			if (opcao == 0) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nFarmácia Bem-Estar - Medicamentos Barato é aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			// Verifica qual opção do menu o usuário escolheu e executa a ação
			// correspondente
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Produto\n\n");

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todos os Produtos\n\n");

				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados do Produto - por ID\n\n");

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados do Produto\n\n");

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar o Produto\n\n");

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				keyPress();
				break;
			}
		}

	}

	// Exibe informações sobre o projeto e a pessoa desenvolvedora
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Generation Brasil - generation@generation.org");
		System.out.println("github.com/conteudoGeneration");
		System.out.println("*********************************************************");
	}

	// Método responsável por aguardar o usuário pressionar a tecla Enter para
	// continuar
	public static void keyPress() {
		System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
		leia.nextLine();
	}

}
