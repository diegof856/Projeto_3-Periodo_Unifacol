package entidade;

import java.util.Date;

public class Pessoa {
    private String nome;
    private Integer idade;
    private String sexo;
    private Date data_nasc;

    public Pessoa(String nome, Integer idade, String sexo, Date data_nasc) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.data_nasc = data_nasc;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

}

class Diretor extends Pessoa {

    public Diretor(String nome, Integer idade, String sexo, Date data_nasc) {
         super(nome, idade, sexo, data_nasc);

    }

class ator extends Pessoa {
        public ator(String nome, Integer idade, String sexo, Date data_nasc) {
            super(nome, idade, sexo, data_nasc);
        }
    }
}



