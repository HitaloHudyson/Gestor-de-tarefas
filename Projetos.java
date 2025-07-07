public class Projetos {

    private int id_projeto;
    private int id_usuario;
    private String nome_projeto;
    private String descricao;


    //MÉTODO CONTRUTOR DA CLASSE PROJETOS
    public Projetos(int id_projeto, int id_usuario, String nome_projeto, String descricao) {
        this.id_projeto = id_projeto;
        this.nome_projeto = nome_projeto;
        this.descricao = descricao;
        this.id_usuario = id_usuario;
    }

    //SETTERS E GETTERS
    public int getId_projeto() {
        return id_projeto;
    }

    public String getNome_projeto() {
        return nome_projeto;
    }

    public void setNome_projeto(String nome) {
        this.nome_projeto = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_usuario(){
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}