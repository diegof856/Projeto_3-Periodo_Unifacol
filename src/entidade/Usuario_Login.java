package entidade;

public class Usuario_Login extends EntidadeId {
    private String senha;
    private String email;
    private String nome;
    

    //RECEBER TODAS AS INFORMAÇÕES DO USUARIO
  

    public String getSenha() {
        return senha;
    }
    public Usuario_Login(String senha, String email, String nome) {
		this.senha = senha;
		this.email = email;
		this.nome = nome;
	}
    public Usuario_Login(String email, String nome) {
		
		this.email = email;
		this.nome = nome;
	}
    public Usuario_Login() {
    	
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
	
	
    
}
