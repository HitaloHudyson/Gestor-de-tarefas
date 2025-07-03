import java.io.BufferedReader;   //LEITURA EFICIENTE DE UM ARQUIVO (BLOCO DE TEXTO)
import java.io.FileReader;       //LER O CONTEUDO DO ARQUIVO (PALAVRA POR PALAVRA)
import java.io.IOException;      //MOSTRAR EXCEÇÃO
import java.util.Scanner;        //ENTRADA DE DADOS

public class Main {
    //CORES PARA O TEXTO
    public static final String ciano = "\u001B[36m";
    public static final String amarelo = "\u001B[33m";
    public static final String padrao = "\u001B[0m";

    //CORES PARA O FUNDO DO TEXTO (BACKGROUND)
    public static final String azul_fundo = "\u001B[44m";
    public static final String vermelho_fundo = "\u001B[41m";
    public static final String verde_fundo = "\033[42m";
    public static final String BOLD = "\033[1m";    // Negrito

    //CAMINHO PARA ARQUIVOS
    public final String caminho_usuarios = "arquivos/usuario.csv";                    //CAMINHO PARA O ARQUIVO usuario.csv
    public static final String caminho_menu = "arquivos/menu.txt";                     //CAMINHO PARA O ARQUIVO menu.txt
    public static final String caminho_menu_usuario = "arquivos/menu_usuario.txt";     //CAMINHO PARA O ARQUIVO menu_usuario.txt

    public static void main(String[] args) {

        int opcao;
        int opcao_auxiliar;
        int id_usuario = 0;
        String nome_usuario = "admin";
        boolean stop = false;                                    //VARIAVEL PARA PARAR O LOOP DO MENU

        Scanner scanner = new Scanner(System.in);                //INICIALIZA O SCANNER PARA LER A ENTRADA DO USUÁRIO
        Usuario usuario = new Usuario(id_usuario, nome_usuario); //INICIALIZA O OBJETO USUÁRIO

        do {
            usuario.titulo(id_usuario, nome_usuario); //MOSTRA O USUÁRIO ATIVO
            ler_arquivo_txt(caminho_menu);            //LÊ O ARQUIVO MENU.TXT
            opcao = opcao(scanner);                   //LÊ A OPÇÃO DIGITADA PELO USUÁRIO
            espaco();                                 //CRIA ESPAÇO ENTRE AS LINHAS

            if(opcao >= 0 && opcao <= 3) {
                switch (opcao) {
                    case 1:
                        usuario.titulo(id_usuario, nome_usuario);     //MOSTRA O USUÁRIO ATIVO
                        ler_arquivo_txt(caminho_menu_usuario);        //LÊ O ARQUIVO MENU_USUARIO.TXT
                        opcao_auxiliar = opcao(scanner);              //LÊ A OPÇÃO DIGITADA PELO USUÁRIO NO MENU DE USUÁRIOS
                        scanner.nextLine();                           // LIMPA O BUFFER DO SCANNER PARA EVITAR PROBLEMAS COM A LEITURA DE LINHAS!!!!!!
                        espaco();                                     //CRIA ESPAÇO ENTRE AS LINHAS
                        gerenciarUsuario(id_usuario, nome_usuario,opcao_auxiliar, usuario, scanner);    //CHAMA OS MÉTODO PARA CRIAR OU LISTAR USUÁRIOS
                        break;
                }
            }   

        } while(stop == false);
    }




    //MÉTODO QUE LER ARQUIVOS
    public static void ler_arquivo_txt(String caminho) {

        //TENTAR RODAR O CÓDIGO, CASO TENHA ALGUM ERRO, ELE MOSTRA A MENSAGEM DE EXCEÇÃO
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            
            String linha;  //VARIAVEL RESPONSAVEL POR ARMAZENAR LINHA LIDA, CASO SEJA DIFERENTE DE NULL
            while ((linha = leitor.readLine()) != null) {
                System.out.println(BOLD + verde_fundo + amarelo + linha + padrao);
            }

        } catch (IOException e) {
            System.err.println("\nERRO: Falha ao ler o arquivo '" + caminho + ".");
        }
    }

    public static int opcao(Scanner scanner) {
        int op = -1; //VARIAVEL PARA ARMAZENAR A OPÇÃO DIGITADA PELO USUÁRIO
        
        do {
            op = scanner.nextInt();
            if (op < 0 || op > 3) {
                System.out.println(vermelho_fundo + ciano + "OPÇÃO INVÁLIDA! DIGITE UMA OPÇÃO VÁLIDA (0-3):" + padrao);
                System.out.print("\007");
            }
        } while (op < 0 || op > 3);  
        return op;
 
    }

    public static void gerenciarUsuario(int id_usuario, String nome_usuario, int opcao_auxiliar, Usuario usuario, Scanner scanner) {
        switch (opcao_auxiliar) {
            case 1:
                System.out.println(BOLD + amarelo + azul_fundo + "CRIAR USUÁRIO:" + padrao);
                nome_usuario = scanner.nextLine();
                usuario.criarUsuario(id_usuario, nome_usuario); id_usuario++; espaco();
                
                break;
            
                    
            case 2:
                System.out.println(BOLD + amarelo + azul_fundo + "LISTAR USUÁRIOS:" + padrao);
                Usuario.listarUsuarios("arquivos/usuario.csv"); //CHAMA O MÉTODO PARA LISTAR USUÁRIOS
                scanner.nextLine();
                espaco();
                break;
        }
        
    }

    public static void espaco() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

