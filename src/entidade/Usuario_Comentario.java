package entidade;

public class Usuario_Comentario {
    private String avaliacao;
    private String comentario;

    public Usuario_Comentario(String avaliacao, String comentario) {
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
