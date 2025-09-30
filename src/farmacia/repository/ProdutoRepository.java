package farmacia.repository;

import farmacia.model.Produto;

public interface ProdutoRepository {
	
	// Métodos do CRUD
	
	public void listarTodas();
	public void cadastrar(Produto produto);
	public void atualizar(Produto produto);
	public void procurarPorNumero(int id);
	public void deletar(int id);
	
}
