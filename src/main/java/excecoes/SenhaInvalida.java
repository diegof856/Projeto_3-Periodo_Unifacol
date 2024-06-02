package excecoes;

public class SenhaInvalida extends TimeException {
    private static final long serialVersionUID = 1L;

    public SenhaInvalida(String mensagem){
        super(mensagem);
    }
}
