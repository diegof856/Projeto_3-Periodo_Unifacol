package interfacea;

import entidade.Usuario_Login;

public interface IUsuario_Repositorio {
	boolean estaCheia();
	boolean estaVazia();
	Usuario_Login remover();
	 void adicionarFila(Usuario_Login elemento);
	 Usuario_Login inicio();
}
