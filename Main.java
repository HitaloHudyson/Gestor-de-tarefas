import java.util.InputMismatchException;  //EXCEÇÃO PARA ENTRADA INVÁLIDA
import java.io.IOException;               //MOSTRAR EXCEÇÃO CASO O ARQUIVO NÃO SEJA ENCONTRADO
import java.io.BufferedReader;            //LEITURA EFICIENTE DE UM ARQUIVO (BLOCO DE TEXTO)
import java.io.FileReader;                //LER O CONTEUDO DO ARQUIVO (PALAVRA POR PALAVRA)
import java.util.Scanner;                 //ENTRADA DE DADOS
import java.io.BufferedWriter; 		      //ESCREVER NO ARQUIVO (BLOCO DE TEXTO)
import java.io.FileWriter; 			      //ESCREVER O CONTEUDO DO ARQUIVO (PALAVRA POR PALAVRA)
import java.util.Map;                     //INTERFACE
import java.util.HashMap;                 //
import java.io.FileNotFoundException;

public class Main {
	
	public static final String preto = "\u001B[30m";         //texto cor preta
	public static final String fundo_branco = "\u001B[47m";   //fundo cor branca
	public static final String padrao = "\u001B[0m";	     //tira as cores
	public static final String vermelho_fundo = "\u001B[41m";//vermelho de fundo

	public static final String caminho_menu = "arquivos/TXT/menu_principal.txt";         //CAMINHO DO ARQUIVO DO MENU PRINCIPAL
	public static final String caminho_menu_usuario = "arquivos/TXT/menu_usuario.txt";   //CAMINHO DO ARQUIVO DO MENU DE USUÁRIO
	public static final String caminho_usuarios = "arquivos/usuario.csv";                //CAMINHO DO ARQUIVO DE USUÁRIOS
	public static final String caminho_menu_projetos = "arquivos/TXT/menu_projetos.txt"; //CAMINHO DO ARQUIVO DE PROJETOS TXT
	public static  final String caminho_projetos = "arquivos/projeto.csv";               //CAMINHO DO ARQUIVO DE PROJETOS CSV



	static String nome_usuario = "admin";
	protected static boolean stop = false;              //VARIAVEL RESPONSAVEL POR PARAR O PROGRAMA
	protected static int id_usuario = 0;                //ID DO USUÁRIO

	protected static String descricao;
	private  static  int id_projeto;                    //ID DO PROJETO
	protected static String nome_projeto;               //NOME DO PROJETO

	public static void main(String[] args) {
				
		int escolher;

		Scanner scanner = new Scanner(System.in);       		    //INICIALIZA O SCANNER PARA LER A ENTRADA DO USUÁRIO
		Usuarios usuario = new Usuarios(id_usuario, nome_usuario);  //CRIA O USUÁRIO COM ID E NOME
		Projetos projeto = new Projetos(id_projeto, id_usuario, nome_projeto, descricao);  //OBJETO PROJETO

		do {
			usuario.mostrar_titulo(id_usuario, nome_usuario);				 //ID e NOME
		    ler_arquivo_TXT(caminho_menu);	         						 //MOSTRA O MENU PRINCIPAL
			escolher = escolha_menu(scanner);		                         //ESCOLHA DO MENU PRINCIPAL
			espaco();			                                             //MÉTODO QUE AFASTA O ULTIMO OUTPUT

			switch(escolher) {
				case 0:
					stop = true;                                             //variável stop do tipo booleana recebe true para parar todo o programa;
					System.out.println(vermelho_fundo + "Fim de programa." + padrao);
					break;

					//USUARIO --> CADASTRAR --> LISTAR --> ESCOLHER USUARIO
				case 1:
					usuario.mostrar_titulo(id_usuario, nome_usuario);        //MOSTRA O ID E NOME DO USUÁRIO
					ler_arquivo_TXT(caminho_menu_usuario);                    //MOSTRA O MENU DE USUÁRIO
					escolher = escolha_menu(scanner);  			              //ESCOLHA DO MENU DE USUÁRIO
					espaco();			                                      //MÉTODO QUE AFASTA O ULTIMO OUTPUT

					if (escolher == 1) {
						id_usuario = obterProximoId(caminho_usuarios);
						usuario = criarUsuario(id_usuario, caminho_usuarios, scanner);      //CHAMA O MÉTODO PARA CRIAR USUÁRIO
						break;

					} else if(escolher == 2) {                                              //LISTA DE USUÁRIOS CADASTRADOS
						System.out.println(fundo_branco + preto + "Lista de usuários cadastrados:" + padrao);
						listar(caminho_usuarios, scanner);
						System.out.print(fundo_branco + preto + "Digite ENTER para retornar:" + padrao);
						scanner.nextLine();
						espaco();
						break;

					} else if(escolher == 3) {                                              //TROCA O USUSARIO
						usuario = trocarUsuario(scanner, caminho_usuarios);
						break;

					} else if (escolher == 0) {
						break;
					}

					//PROJETOS -- > CRIAR NOVO PROJETO --> LISTAR OS  PROJETOS
				case 2:
					usuario.mostrar_titulo(id_usuario, nome_usuario);
					ler_arquivo_TXT(caminho_menu_projetos);
					escolher = escolha_menu(scanner);
					espaco();

					if(escolher == 1) {
						id_projeto = obterProximoId(caminho_projetos);
						criarProjeto(projeto, usuario, caminho_projetos, scanner);                //CHAMA O MÉTODO PARA CRIAR USUÁRIO
						break;

					} else if (escolher == 2) {
						System.out.println(fundo_branco + preto + "Lista de projetos:" + padrao);
						listar(caminho_projetos, scanner);
						System.out.print(fundo_branco + preto + "Digite ENTER para retornar:" + padrao);
						scanner.nextLine();
						espaco();
						break;
					}


			}
		} while(!stop);
	}


	protected static int escolha_menu(Scanner scanner) {
		int escolha_do_usuario;

		while(true) {
			try {
				//RECEBE A ALTERNATIVA DO USUÁRIO
				System.out.print("Digite sua opção: ");
				escolha_do_usuario = scanner.nextInt();		// Lê a opção do usuário
				scanner.nextLine(); 						// Limpa o buffer

				//TERMINA O WHILE CASO O USUARIO ESCOLHA UMA ALTERNATIVA VÁLIDA
				if(escolha_do_usuario >= 0 && escolha_do_usuario <= 3) {
					return escolha_do_usuario;
				} else {
					System.out.println(vermelho_fundo + preto + "OPÇÃO INVALIDA! DIGITE UM NUMERO DE 0 A 3" + "\007" + padrao);
				}

			//CATCH MOSTRA UMA MENSAGEM DE ERRO, CASO O USUÁRIO DIGITE UMA STRING
			} catch(InputMismatchException e) { 
				System.out.println(vermelho_fundo + "ERRO! Por favor, digite apenas números. \007" + padrao);
				scanner.nextLine(); //LIMPA O BUFFER DO SCANNER CASO A ULTIMA LEITURA TENHA SIDO UM INT
			}
		}
	}

	protected static void ler_arquivo_TXT(String caminho) {
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(caminho));
			String linha;

			//LE O ARQUIVO LINHA POR LINHA E MOSTRA NO CONSOLE
			while((linha = leitor.readLine()) != null) {
				System.out.println(fundo_branco + preto + linha + padrao);
			}
			leitor.close();
		
		//MOSTRA UMA MENSAGEM DE ERRO CASO O ARQUIVO NÃO SEJA ENCONTRADO
		} catch(IOException e) {
			System.out.println("ERRO AO LER O ARQUIVO: " + e.getMessage());
		}
	}

	private static int obterProximoId(String caminho_usuarios) {
		int maxId = -1; // COMEÇA COM -1 PARA FICAR 0 CASO NÃO HAJA NENHUM USUARIO CADASTRADO
		try (BufferedReader reader = new BufferedReader(new FileReader(caminho_usuarios))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				try {
					// SPLIT QUE IGNORA AS VIRGULAS DENTRO DAS ASPAS, MAS CORTAS AS FORA
					String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					int idAtual = Integer.parseInt(partes[0].trim());      //TENTA CONVERTER STRING PARA INTEIRO
					if (idAtual > maxId) {  maxId = idAtual;  }            //PEGA O O ID DE MAIOR VALOR
				} catch (NumberFormatException e) {
					// Ignora linhas que não começam com um número válido (cabeçalhos, etc.)
					System.err.println(vermelho_fundo + "Aviso: Ignorando linha mal formatada no CSV: " + linha + padrao);
				}
			}
		} catch (FileNotFoundException e) {
			//SE O ARQUIVO NÃO EXISTIR O ID SERÁ 0
			return 0;
		} catch (IOException e) {
			System.out.println(vermelho_fundo +  "Erro ao ler o arquivo de usuários: " + padrao + e.getMessage());
			// FALHA NA LEITURA ARQUIVO
			return -1;
		}
		return maxId + 1;
	}

	//MÉTODO PARA CRIAR USUÁRIO
	protected static Usuarios criarUsuario(int id_usuario, String caminho, Scanner scanner) {
        String nome;         // Variável para armazenar o nome do usuário que será digitado.
        
        // ETAPA 1: Obter e validar a entrada do usuário ---
        // Este loop garante que só sairemos daqui com um nome válido.
        while (true) {
            System.out.print(fundo_branco + preto + "Digite o nome do usuário: " + padrao);
            nome = scanner.nextLine(); // Lê a linha inteira digitada pelo usuário.

            if (nome.trim().isEmpty()) { // .trim() remove espaços em branco antes e depois.
                System.out.println(vermelho_fundo + "O nome do usuário não pode ser vazio. Tente novamente." + padrao);
                continue; // Volta para o início do loop para pedir o nome de novo.
            } else {
                break; // Se o nome não for vazio, sai do loop para continuar.
            }
        }

		Usuarios novoUsuario = new Usuarios(id_usuario, nome);

        // ETAPA 2: Preparar os dados e salvar no arquivo
        // Esta parte do código só é executada APÓS o loop ter sido encerrado com um nome válido.
        try (FileWriter fw = new FileWriter(caminho_usuarios, true);
             BufferedWriter writer = new BufferedWriter(fw)) {

            // Escreve a linha no formato "id,"nome"" para proteger nomes com vírgula.
            writer.write(id_usuario + "," + "\"" + nome + "\"");
            writer.newLine(); // Adiciona uma nova linha para o próximo registro.

            System.out.println(fundo_branco + "Usuário '" + nome + "' cadastrado com sucesso!" + padrao);

        } catch (IOException e) {
            System.err.println( vermelho_fundo + "Erro ao escrever no arquivo CSV: " + e.getMessage());
            e.printStackTrace(); // Útil para depuração.
        }
		return novoUsuario;
    }

	public static void listar(String caminho, Scanner scanner) {
		//TENTAR RODAR O CÓDIGO, CASO TENHA ALGUM ERRO, ELE MOSTRA A MENSAGEM DE EXCEÇÃO
		try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {

			String linha;  //VARIÁVEL RESPONSÁVEL POR ARMAZENAR LINHA LIDA, CASO SEJA DIFERENTE DE NULL
			while ((linha = leitor.readLine()) != null) {
				System.out.println("- " + linha);
			}

		} catch (IOException e) {  //EXCEÇÃO
			System.err.println("\nERRO: Falha ao ler o arquivo '" + caminho_usuarios + ".");
		}
	}

	public static Usuarios trocarUsuario(Scanner scanner, String caminho_usuarios) {
		Map<Integer, Usuarios> usuariosDisponiveis = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(caminho_usuarios))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				if (linha.trim().isEmpty()) continue; // Ignora linhas em branco

				// Divide a linha no primeiro ",", para o caso de o nome ter vírgulas.
				String[] partes = linha.split(",", 2);
				if (partes.length == 2) {
					try {
						int id = Integer.parseInt(partes[0].trim());
						// Remove as aspas do início e do fim do nome
						String nome = partes[1].trim();
						if (nome.startsWith("\"") && nome.endsWith("\"")) {
							nome = nome.substring(1, nome.length() - 1);
						}
						usuariosDisponiveis.put(id, new Usuarios(id, nome));
					} catch (NumberFormatException e) {
						System.out.println("Aviso: Linha mal formatada no CSV será ignorada: " + linha);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo de usuários: " + e.getMessage());
			return null; // Retorna nulo se não conseguir ler o arquivo
		}

		if (usuariosDisponiveis.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado para trocar.");
			return null;
		}

		// ETAPA 2: Apresentar os usuários e pedir uma escolha.
		System.out.println("\n--- Trocar de Usuário ---");
		System.out.println(fundo_branco + preto + "Selecione um dos usuários abaixo:" + padrao);

		// Mostra todos os usuários disponíveis
		for (Usuarios u : usuariosDisponiveis.values()) {
			System.out.println(u.getID() + " - " + u.getNOME());
		}

		// ETAPA 3: Obter e validar a entrada do usuário.
		Usuarios usuarioSelecionado = null;
		while (true) {
			System.out.print("Digite o ID do usuário desejado: ");
			String entrada = scanner.nextLine();
			try {
				int idEscolhido = Integer.parseInt(entrada);
				if (usuariosDisponiveis.containsKey(idEscolhido)) {
					usuarioSelecionado = usuariosDisponiveis.get(idEscolhido);
					break; // Sai do loop pois encontrou um usuário válido.
				} else {
					System.out.println(vermelho_fundo + "ID inválido. Por favor, escolha um ID da lista." + padrao);
				}
			} catch (NumberFormatException e) {
				System.out.println(vermelho_fundo + "Entrada inválida. Por favor, digite apenas o número do ID." + padrao);
			}
		}

		// ETAPA 4: Retornar o usuário que foi escolhido.
		System.out.println(fundo_branco + preto + "Usuário alterado com sucesso para: " + usuarioSelecionado.getNOME() + padrao);
		return usuarioSelecionado;
	}

	private static int idProjeto(int id) {
		return id + 100;
	}

	private static int obterProximoIdProjeto(String caminho_projetos) {
		// Define um ID inicial. Se o arquivo estiver vazio, o primeiro ID será 101.
		int maxId = 100;

		try (BufferedReader br = new BufferedReader(new FileReader(caminho_projetos))) {
			String linha;
			// Pula o cabeçalho, se houver. Se não houver, comente ou remova a linha abaixo.
			br.readLine();

			while ((linha = br.readLine()) != null) {
				if (linha.trim().isEmpty()) {
					continue; // Pula linhas em branco
				}

				// Quebra a linha pela vírgula para pegar a primeira coluna (o ID)
				String[] partes = linha.split(",");

				if (partes.length > 0) {
					try {
						int idAtual = Integer.parseInt(partes[0].trim());
						if (idAtual > maxId) {
							maxId = idAtual; // Atualiza o maior ID encontrado
						}
					} catch (NumberFormatException e) {
						// Ignora linhas que não começam com um número válido
						System.err.println("Aviso: linha mal formatada no CSV será ignorada: " + linha);
					}
				}
			}
		} catch (IOException e) {
			// Se o arquivo não existir ainda, não é um erro. Ele será criado.
			// O maxId continuará como 100, e o primeiro ID será 101.
		}

		return maxId + 1; // Retorna o próximo ID disponível
	}


	private static void criarProjeto(Projetos projeto, Usuarios usuario, String caminho_projetos, Scanner scanner) {
		String nome_projeto;
		String descricao;

		//PRIMEIRA ETAPA --> RECEBER O NOME E DESCRIÇÃO DO PROJETO
		while(true) {
			System.out.print(fundo_branco + preto + "Digite o nome do projeto: " + padrao);
			nome_projeto = scanner.nextLine();
			System.out.print(fundo_branco + preto + "Descrição:" + padrao);
			descricao = scanner.nextLine();

			if(nome_projeto.trim().isEmpty() || descricao.trim().isEmpty()) {
				System.out.println(vermelho_fundo + "ERRO! O projeto ou descrição não podem ficar sem os nomes. \007" + padrao);
			} else {
				break;
			}
		}

		//ID DO USUÁRIO LOGADO
		int id_usuario_atual = usuario.getID();

		// CHAMAR O NOVO ID DO PROJETO (ID INCREMENTADO)
		int novo_id_projeto = obterProximoIdProjeto(caminho_projetos);

		//SEGUNDA ETAPA --> ESCREVER OS DADOS FORNECIDOS NO PROJETO.CSV
		try (FileWriter fw = new FileWriter(caminho_projetos, true); BufferedWriter writer = new BufferedWriter(fw)) {
			// Use as variáveis com os IDs corretos!
			writer.write(novo_id_projeto + ","  + id_usuario_atual + "," + "\"" + nome_projeto + "\"," + "\"" + descricao + "\"");
			writer.newLine();

			System.out.println(fundo_branco + preto + "Projeto ID " + novo_id_projeto + " criado com sucesso. Pressione ENTER para prosseguir." + padrao);
			scanner.nextLine();

		} catch (IOException e) {
			System.err.println( vermelho_fundo + "Erro ao escrever no arquivo CSV: " + e.getMessage());
			e.printStackTrace();
		}
	}

	//METODO PARA AFASTAR O ULTIMO OUTPUT
	public static void espaco() { System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); }

}