package entidade;

public class Usuario_Normal extends Usuario {

    public Usuario_Normal(String nome, String email, String senha, String tipo) {
        super(nome, email, senha, tipo);
    }

    public Usuario_Normal() {
        super(); // Chama o construtor vazio da classe pai Usuario
    }
}
