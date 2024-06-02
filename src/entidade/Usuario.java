package entidade;

public class Usuario {
    public static Usuario getEmail;
    protected String nome;
    protected String email;
    protected String senha;
    protected String tipo;
    protected String novasenha;
    private Usuario_Endereco endereco;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNovasenha() {
        return novasenha;
    }

    public void setNovasenha(String novasenha) {
        this.novasenha = novasenha;
    }

    public Usuario_Endereco getEndereco() {
        return this.endereco;
    }


}


