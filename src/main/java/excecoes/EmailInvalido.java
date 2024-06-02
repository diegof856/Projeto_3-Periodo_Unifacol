package excecoes;

public class EmailInvalido extends TimeException{

    private static final long serialVersionUID = 1L;

    public EmailInvalido() {
        super("Email invalido meu patrão(a)");
    }
}
