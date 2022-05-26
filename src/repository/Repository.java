package repository;

import java.util.List;

public interface Repository<I, T> {

	public List<T> buscarTodos();
	
	public T buscaPorId(I id);
	
	public void salvar(I chave, T objeto);
	
	public void excluir(I id);
}
