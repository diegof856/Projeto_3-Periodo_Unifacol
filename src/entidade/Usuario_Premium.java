package entidade;

public class Usuario_Premium extends Usuario {
    private static String cpf; // Adicionando o campo CPF

    public Usuario_Premium( String nome, String email, String senha, String tipo,String cpf) {
        super(nome, email, senha, tipo);
        this.cpf = cpf;
    }

    // Adicione os métodos getters e setters para o campo CPF, se necessário

    public static String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}






