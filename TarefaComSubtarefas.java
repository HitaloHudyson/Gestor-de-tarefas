import java.util.ArrayList;
import java.util.List;

public class TarefaComSubtarefas extends Tarefa {
    private List<Tarefa> subtarefas;

    public TarefaComSubtarefas(int idTarefa, int idProjeto, int idUsuario, String nome, String status) {
        // Chama o construtor da classe pai (Tarefa)
        super(idTarefa, idProjeto, idUsuario, nome, status);
        // Inicializa a lista de subtarefas
        this.subtarefas = new ArrayList<>();
    }

    // Métodos para gerenciar subtarefas
    public void adicionarSubtarefa(Tarefa subtarefa) {
        this.subtarefas.add(subtarefa);
    }

    public void removerSubtarefa(Tarefa subtarefa) {
        this.subtarefas.remove(subtarefa);
    }

    public List<Tarefa> getSubtarefas() {
        return subtarefas;
    }
}