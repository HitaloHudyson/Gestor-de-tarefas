import java.io.BufferedReader; //LEITURA EFICIENTE DE UM ARQUIVO (BLOCO DE TEXTO)
import java.io.FileReader; //LER O CONTEUDO DO ARQUIVO (PALAVRA POR PALAVRA)
import java.io.IOException;  //MOSTRAR EXCEÇÃO
import java.util.Scanner; //ENTRADA DE DADOS

public class Main {
    //CORES PARA O TEXTO
    public static final String ciano = "\u001B[36m";
    public static final String amarelo = "\u001B[33m";
    public static final String padrao = "\u001B[0m";

    //CORES PARA O FUNDO DO TEXTO (BACKGROUND)
    public static final String azul_fundo = "\u001B[44m";
    public static final String vermelho_fundo = "\u001B[41m";

    //CAMINHO PARA ARQUIVOS
    public static final String caminho_menu = "arquivos/menu.txt";

    /**
     * @param args
     */
    public static void main(String[] args) {

        int opcao;
        boolean stop = false;

        Scanner scanner = new Scanner(System.in);

        do {
            ler(caminho_menu);
            opcao = Scanner.nextInt();

        } while(stop == false);
    }




    //MÉTODO QUE LER ARQUIVOS
    public static void ler(String caminho) {

        //TENTAR RODAR O CÓDIGO, CASO TENHA ALGUM ERRO, ELE MOSTRA A MENSAGEM DE EXCEÇÃO
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            
            String linha; //VARIAVEL RESPONSAVEL POR ARMAZENAR LINHA LIDA, CASO SEJA DIFERENTE DE NULL
            while ((linha = leitor.readLine()) != null) {
                System.out.println(azul_fundo + amarelo + linha);
            }

        } catch (IOException e) {
            System.err.println("\nERRO: Falha ao ler o arquivo '" + caminho + "'.");
        }
    }
}