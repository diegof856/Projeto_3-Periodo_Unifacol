package entidade;

import java.util.List;

import entidade.enums.GeneroEnum;

public class Filme {
	private String nomeFilme;
	private String nomeOriginal;
	private Integer ano;
	private String duracao;

	private List<GeneroEnum> generos;
	private Diretor direção;
	
	public Filme(String nomeFilme, String nomeOriginal, Integer ano, List<GeneroEnum> generos, String duracao, Diretor direção) {

		this.nomeFilme = nomeFilme;
		this.nomeOriginal = nomeOriginal;
		this.ano = ano;
		this.generos = generos;
		this.duracao = duracao;
		this.direção = direção;
	}

	public Diretor getDireção() {
		return direção;
	}

	public void setDireção(Diretor direção) {
		this.direção = direção;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<GeneroEnum> getGenero() {
		return generos;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

}
