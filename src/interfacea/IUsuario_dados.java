package interfacea;

import entidade.Usuario_Login;

public interface IUsuario_dados {
	Usuario_Login Verificacao_Login(String email, String senha);
}
