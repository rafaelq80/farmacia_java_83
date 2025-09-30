package farmacia;

import java.util.InputMismatchException;
import java.util.Scanner;

import farmacia.controller.ProdutoController;
import farmacia.model.Cosmetico;
import farmacia.model.Medicamento;
import farmacia.model.Produto;
import farmacia.util.Cores;

public class Menu {

	// Objeto para ler dados digitados pelo usuário
	private static final Scanner leia = new Scanner(System.in);

	private static final ProdutoController produtoController = new ProdutoController();

	public static void main(String[] args) {

		int opcao;
		
		criarProdutosTeste();

		Medicamento m1 = new Medicamento(1, "Paracetamol 750mg", 1, 20.00f, "Paracetamol");
		m1.visualizar();

		Cosmetico c1 = new Cosmetico(1, "Sabonete Lux", 2, 4.00f, "Flores do Campo");
		c1.visualizar();

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

	// Método responsável por criar algumas produtos de teste automaticamente ao
	// iniciar o programa
	private static void criarProdutosTeste() {
		produtoController
				.cadastrar(new Medicamento(produtoController.gerarId(), "Paracetamol 500mg", 1, 15.00f, "Paracetamol"));
		produtoController
				.cadastrar(new Cosmetico(produtoController.gerarId(), "Creme Dental Colgate", 2, 10.00f, "Menta"));
	}

	// Método responsável por listar todas as produtos
	private static void listarProdutos() {
		produtoController.listarTodas();
	}

	// Método responsável por criar uma nova produto com os dados digitados pelo
	// usuário
	private static void cadastrarProduto() {

		System.out.print("Digite o nome do Produto: ");
		leia.skip("\\R");
		String nome = leia.nextLine();

		System.out.print("Digite o Categoria da produto (1 - CC | 2 - CP): ");
		int categoria = leia.nextInt();

		System.out.print("Digite o Preco inicial: ");
		float preco = leia.nextFloat();

		switch (categoria) {
		case 1 -> {
			System.out.print("Digite o Generico inicial: ");
			String generico = leia.nextLine();
			leia.nextLine();

			// Automatiza o id da produto através do método gerarId()
			produtoController.cadastrar(new Medicamento(produtoController.gerarId(), nome, categoria, preco, generico));
		}
		case 2 -> {
			System.out.print("Digite o dia do aniversário da produto: ");
			String fragrancia = leia.nextLine();
			leia.nextLine();
			produtoController.cadastrar(new Cosmetico(produtoController.gerarId(), nome, categoria, preco, fragrancia));
		}
		default -> System.out.println(Cores.TEXT_RED + "Categoria de produto inválido!" + Cores.TEXT_RESET);
		}
	}

	// Método responsável por procurar uma produto já cadastrada pelo número
	private static void procurarProdutoPorId() {

		System.out.print("Digite o número da produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		produtoController.procurarPorId(id);
	}

	// Método responsável por excluir uma produto existente pelo id
	private static void deletarProduto() {

		System.out.print("Digite o número da produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		// Busca a produto pelo número
		Produto produto = produtoController.buscarNaCollection(id);

		// Verifica se a produto existe
		if (produto != null) {

			// Confirmação da exclusão
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

	// Método responsável por atualizar os dados de uma produto existentepelo id
	private static void atualizarProduto() {

		System.out.print("Digite o número da produto: ");
		int id = leia.nextInt();
		leia.nextLine();

		// Busca a produto pelo número
		Produto produto = produtoController.buscarNaCollection(id);

		// Se a produto existir
		if (produto != null) {

			// Obtém os dados atuais
			String nome = produto.getNome();
			int categoria = produto.getCategoria();
			float preco = produto.getPreco();

			// Atualiza o nome do nome (ou mantém valor atual se apertar Enter)
			System.out.printf(
					"Nome atual: %s\nDigite o novo nome do Nome (Pressione ENTER para manter o valor atual): ", nome);
			String entrada = leia.nextLine();
			nome = entrada.isEmpty() ? nome : entrada;

			// Atualiza preco (ou mantém valor atual se apertar Enter)
			System.out.printf("Preco atual: %.2f\nDigite o novo Preco (Pressione ENTER para manter o valor atual): ",
					preco);
			entrada = leia.nextLine();

			/**
			 * Se o usuário não digitou nada (entrada vazia), mantém o valor atual de
			 * 'agencia'. Caso contrário, converte o valor digitado (String) para número
			 * Real (float), substitui a , pelo . (método replace) e atribui à variável
			 * 'preco'.
			 */
			preco = entrada.isEmpty() ? preco : Float.parseFloat(entrada.replace(",", "."));

			// Se a produto for do categoria Produto Corrente
			switch (categoria) {
			case 1 -> {

				/**
				 * Como o objeto 'produto' é do categoria genérico Produto, precisamos
				 * convertê-la (casting) para Medicamento. Isso é necessário porque apenas a
				 * classe Medicamento possui o atributo 'generico'. Após o casting, conseguimos
				 * acessar o método getGenerico() para obter o generico da produto.
				 */
				String generico = ((Medicamento) produto).getGenerico();

				// Atualiza o generico da produto (ou mantém valor atual se apertar Enter)
				System.out.printf(
						"Generico atual é: %.2f\nDigite o novo Generico (Pressione ENTER para manter o valor atual): ",
						generico);
				entrada = leia.nextLine();
				generico = entrada.isEmpty() ? generico : entrada;

				/**
				 * Na atualização não utilizamos o método gerarId() no atributo 'id'. Isso
				 * porque o número da produto já existe e identifica unicamente essa produto.
				 * 
				 * Se chamarmos 'gerarId()', um novo número seria criado e substituiria o
				 * antigo, o que impediria a atualização dos dados.
				 */
				produtoController.atualizar(new Medicamento(id, nome, categoria, preco, generico));

			}
			// Se a produto for do categoria Produto Poupança
			case 2 -> {

				/**
				 * Como o objeto 'produto' é do categoria genérico Produto, precisamos
				 * convertê-la (casting) para Cosmetico. Isso é necessário porque apenas a
				 * classe Cosmetico possui o atributo 'fragrancia'. Após o casting, conseguimos
				 * acessar o método getFragrancia() para obter o dia do aniversário da produto.
				 */
				String fragrancia = ((Cosmetico) produto).getFragrancia();

				// Atualiza o dia do aniversário (ou mantém valor atual se apertar Enter)
				System.out.printf(
						"Aniversário atual é: %d\nDigite o novo dia do Aniversário (Pressione ENTER para manter o valor atual): ",
						fragrancia);
				entrada = leia.nextLine();
				fragrancia = entrada.isEmpty() ? fragrancia : entrada;
				produtoController.atualizar(new Cosmetico(id, nome, categoria, preco, fragrancia));
			}
			// Se o categoria da produto for inválido
			default -> System.out.println(Cores.TEXT_RED + "Categoria de produto inválido!" + Cores.TEXT_RESET);
			}

		} else {
			// Caso a produto não exista
			System.out.printf("\nA produto número %d não foi encontrada!", id);
		}
	}

}
