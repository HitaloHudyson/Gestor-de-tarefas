public class Projeto {
    int id;
    String nome_projeto;
    String descricao;

    public Projeto(int id, String nome_projeto, String descricao) { //
        this.id = id;
        this.nome_projeto = nome_projeto;
        this.descricao = descricao;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNome_projeto() {
        return nome_projeto;
    }

    public String getDescricao() {
        return descricao;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNome_projeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}