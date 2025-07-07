public class Usuarios {
	private int id;
	private String nome;
	
	//MÉTODO CONSTRUTOR
	public Usuarios(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	//GETTERS E SETTERS
	public int getID() { 
		return id; 
	}
	public void setID(int id) { 
		this.id = id; 
	}
	
	public String getNOME() { 
		return nome; 
	}
	public void setNOME(String nome) { 
		this.nome = nome; 
	}
	
	//MOSTRA O ID E O NOME DO USUÁRIO
	protected void mostrar_titulo(int id, String nome) {
		System.out.println("ID: " + this.id + " NOME: " + this.nome);
	}
}