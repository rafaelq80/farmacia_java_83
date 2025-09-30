package farmacia.model;

public class Cosmetico extends Produto{

	private String fragrancia;

	public Cosmetico(int id, String nome, int categoria, float preco, String fragrancia) {
		super(id, nome, categoria, preco);
		this.fragrancia = fragrancia;
	}

	public String getFragrancia() {
		return fragrancia;
	}

	public void setFragrancia(String fragrancia) {
		this.fragrancia = fragrancia;
	}
	
	public void visualizar() {
		super.visualizar();
		System.out.printf("Fragrância: %s\n", this.fragrancia);
	}
}
