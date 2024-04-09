package excecoes;

public class TimeException extends Exception {
   
	private static final long serialVersionUID = 1L;

	public TimeException(String mensagem){
        super(mensagem);
    }

    public void addSuppressed() {
        System.out.println("ERRO, TENTE NOVAMENTE");
    }
}
