package interfacea;

import java.util.List;

public interface IBaseRepositorio<T> {
	void adicionarLista(T entidade);
	List<T> verLista();
}
