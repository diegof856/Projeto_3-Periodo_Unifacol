package acesso_dados;

public class Excecao_Dados extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public Excecao_Dados(String mensagem) {
		super(mensagem);
	}
}
