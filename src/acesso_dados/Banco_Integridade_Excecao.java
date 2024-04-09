package acesso_dados;

public class Banco_Integridade_Excecao extends RuntimeException {


	private static final long serialVersionUID = 1L;
	public Banco_Integridade_Excecao(String mensagem) {
		super(mensagem);
	}

}
