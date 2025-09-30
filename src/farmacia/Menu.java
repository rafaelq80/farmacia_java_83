package farmacia;

import java.util.InputMismatchException;
import java.util.Scanner;

import farmacia.controller.ProdutoController;
import farmacia.model.Cosmetico;
import farmacia.model.Medicamento;
import farmacia.model.Produto;
import farmacia.util.Cores;

public class Menu {

	private static final Scanner leia = new Scanner(System.in);

	private static final ProdutoController produtoController = new ProdutoController();

	public static void main(String[] args) {

		int opcao;
		
		criarProdutosTeste();

		/*Medicamento m1 = new Medicamento(1, "Paracetamol 750mg", 1, 20.00f, "Paracetamol");
		m1.visualizar();

		Cosmetico c1 = new Cosmetico(1, "Sabonete Lux", 2, 4.00f, "Flores do Campo");
		c1.visualizar();*/

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

			try {
				opcao = leia.nextInt();
				leia.nextLine();
			} catch (InputMismatchException e) {
				opcao = -1;
				System.out.println("\nDigite um número inteiro entre 0 e 8");
				leia.nextLine();
			}

			if (opcao == 0) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nFarmácia Bem-Estar - Medicamentos Barato é aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Produto\n\n");

				cadastrarProduto();

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todos os Produtos\n\n");

				listarProdutos();

				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados do Produto - por ID\n\n");

				procurarProdutoPorId();

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados do Produto\n\n");

				atualizarProduto();
				
				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar o Produto\n\n");
				deletarProduto();
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				keyPress();
				break;
			}
		}

	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Generation Brasil - generation@generation.org");
		System.out.println("github.com/conteudoGeneration");
		System.out.println("*********************************************************");
	}

	public static void keyPress() {
		System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
		leia.nextLine();
	}

	private static void criarProdutosTeste() {
		produtoController
				.cadastrar(new Medicamento(produtoController.gerarId(), "Paracetamol 500mg", 1, 15.00f, "Paracetamol"));
		produtoController
				.cadastrar(new Cosmetico(produtoController.gerarId(), "Creme Dental Colgate", 2, 10.00f, "Menta"));
	}

	private static void listarProdutos() {
		produtoController.listarTodas();
	}

	private static void cadastrarProduto() {

		System.out.print("Digite o nome do produto: ");
		String nome = leia.nextLine();

		System.out.print("Digite o Categoria do produto (1 - Medicamento | 2 - Cosmético): ");
		int categoria = leia.nextInt();

		System.out.print("Digite o Preco do produto: ");
		float preco = leia.nextFloat();

		switch (categoria) {
		case 1 -> {
			System.out.print("Digite o mome genérico: ");
			leia.skip("\\R");
			String generico = leia.nextLine();
			
			produtoController.cadastrar(new Medicamento(produtoController.gerarId(), nome, categoria, preco, generico));
		}
		case 2 -> {
			System.out.print("Digite a fragrância: ");
			leia.skip("\\R");
			String fragrancia = leia.nextLine();
			
			produtoController.cadastrar(new Cosmetico(produtoController.gerarId(), nome, categoria, preco, fragrancia));
		}
		default -> System.out.println(Cores.TEXT_RED + "Categoria de produto inválido!" + Cores.TEXT_RESET);
		}
	}

	private static void procurarProdutoPorId() {

		System.out.print("Digite o Id do produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		produtoController.procurarPorId(id);
	}

	private static void deletarProduto() {

		System.out.print("Digite o Id do produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		Produto produto = produtoController.buscarNaCollection(id);

		if (produto != null) {

			System.out.print("\nTem certeza que deseja excluir esta produto? (S/N): ");
			String confirmacao = leia.nextLine();

			if (confirmacao.equalsIgnoreCase("S")) {
				produtoController.deletar(id);
			} else {
				System.out.println("\nOperação cancelada!");
			}

		} else {
			System.out.printf("\nA produto número %d não foi encontrada!", id);
		}
	}

	private static void atualizarProduto() {

		System.out.print("Digite o Id do produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		Produto produto = produtoController.buscarNaCollection(id);

		if (produto != null) {

			String nome = produto.getNome();
			int categoria = produto.getCategoria();
			float preco = produto.getPreco();

			System.out.printf(
					"Nome atual: %s\nDigite o novo nome do Produto (Pressione ENTER para manter o valor atual): ", nome);
			String entrada = leia.nextLine();
			nome = entrada.isEmpty() ? nome : entrada;

			System.out.printf("Preco atual: %.2f\nDigite o novo Preco (Pressione ENTER para manter o valor atual): ",
					preco);
			entrada = leia.nextLine();
			preco = entrada.isEmpty() ? preco : Float.parseFloat(entrada.replace(",", "."));

			switch (categoria) {
			case 1 -> {
				String generico = ((Medicamento) produto).getGenerico();
				
				System.out.printf(
						"Generico atual é: %s\nDigite o novo Nome Genérico (Pressione ENTER para manter o valor atual): ",
						generico);
				entrada = leia.nextLine();
				generico = entrada.isEmpty() ? generico : entrada;
				produtoController.atualizar(new Medicamento(id, nome, categoria, preco, generico));

			}

			case 2 -> {
				String fragrancia = ((Cosmetico) produto).getFragrancia();

				System.out.printf(
						"Fragrância atual é: %s\nDigite a nova fragrância (Pressione ENTER para manter o valor atual): ",
						fragrancia);
				entrada = leia.nextLine();
				fragrancia = entrada.isEmpty() ? fragrancia : entrada;
				produtoController.atualizar(new Cosmetico(id, nome, categoria, preco, fragrancia));
			}
			default -> System.out.println(Cores.TEXT_RED + "Categoria de produto inválido!" + Cores.TEXT_RESET);
			}

		} else {
			System.out.printf("\nA produto número %d não foi encontrada!", id);
		}
	}

}
