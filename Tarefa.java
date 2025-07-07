public class Tarefa {
    private int idTarefa;
    private int idProjeto;
    private int idUsuario;
    private String nome;
    private String status; // Ex: "Pendente", "Em Andamento", "Concluída"

    // Construtor
    public Tarefa(int idTarefa, int idProjeto, int idUsuario, String nome, String status) {
        this.idTarefa = idTarefa;
        this.idProjeto = idProjeto;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.status = status;
    }

    // Getters e Setters
    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}