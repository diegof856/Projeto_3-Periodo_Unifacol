package entidade;

public class Filme {
    private String nomeFilme;
    private String ano;
    private String duracao;
    private String sipnose;
    private String pais;
    private String genero;



    public Filme(String nomeFilme, String ano, String duracao, String sipnose, String pais,String genero)  {

        this.nomeFilme = nomeFilme;
        this.ano = ano;
        this.duracao = duracao;
        this.sipnose = sipnose;
        this.pais = pais;
        this.genero = genero;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getSipnose() {
        return sipnose;
    }

    public void setSipnose(String sipnose) {
        this.sipnose = sipnose;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
