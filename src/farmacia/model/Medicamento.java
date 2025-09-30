package farmacia.model;

public class Medicamento extends Produto {

	private String generico;
	
	public Medicamento(int id, String nome, int categoria, float preco, String generico) {
		super(id, nome, categoria, preco);
		this.generico = generico;
	}

	public String getGenerico() {
		return generico;
	}

	public void setGenerico(String generico) {
		this.generico = generico;
	}

	public void visualizar() {
		super.visualizar();
		System.out.printf("Nome genérico: %s\n", this.generico);
	}
	
	
}
