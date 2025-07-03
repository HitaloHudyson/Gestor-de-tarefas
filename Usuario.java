import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Usuario extends Main {               //usuario herda a classe Main
    private int id;                        //ID DO USUÁRIO
    private String nome;                   //NOME DO USUÁRIO

    public Usuario(int id, String nome) {  // CONSTRUTOR DA CLASSE USUÁRIO
        this.id = id;
        this.nome = nome;
    }

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //MÉTODO PARA MOSTRAR O USUÁRIO ATIVO
    public void titulo(int id, String nome) {
        System.out.println(BOLD + azul_fundo + amarelo + "ID: " + id + "  NOME: " + nome + padrao);
    }

    //MÉTODO PARA CRIAR USUÁRIO
    protected void criarUsuario(int id_usuario, String nome_usuario) {
        try (FileWriter fw = new FileWriter(caminho_usuarios, true);
             BufferedWriter writer = new BufferedWriter(fw)) {

            writer.write(id_usuario + "," + nome_usuario);   // Escreve a linha no formato "id,nome"
            writer.newLine();                                // Adiciona uma nova linha para o próximo registro

            System.out.println(BOLD + verde_fundo + amarelo +"Usuário '" + nome_usuario + "' salvo com sucesso!" + padrao);

        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void listarUsuarios(String caminho) { 

        //TENTAR RODAR O CÓDIGO, CASO TENHA ALGUM ERRO, ELE MOSTRA A MENSAGEM DE EXCEÇÃO
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            
            String linha;  //VARIAVEL RESPONSAVEL POR ARMAZENAR LINHA LIDA, CASO SEJA DIFERENTE DE NULL
            while ((linha = leitor.readLine()) != null) {
                System.out.println(BOLD + amarelo + "- " + linha + padrao);
            }

        } catch (IOException e) {
            System.err.println("\nERRO: Falha ao ler o arquivo '" + caminho + ".");
        }
    }
    
}