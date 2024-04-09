package entidade;

public class Diretor {
	private String nome;
	private Integer idade;
	private String nacionalidade;
	
	public Diretor(String nome, Integer idade, String nacionalidade1) {

		this.nome = nome;
		this.idade = idade;
		this.nacionalidade = nacionalidade1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

}
