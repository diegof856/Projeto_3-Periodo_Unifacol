/*package acesso_dados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidade.Filme;
import repositorios.Filme_repositorio;

// ARMAZENAR OS FILMES NO BANCO DE DADOS

public class Filmes_dados{
    public void cadastroFilmesadd(Filme Filmes) {

        String sql = "INSERT INTO FILMES(nomeFilme,nomeoriginal,ano,genero,duracao)VALUES(?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = Filme_repositorio.getFilme_repostorio().prepareStatement(sql);
            ps.setString(1, Filmes.getNomeFilme());
            ps.setString(2, Filmes.getNomeOriginal());
            ps.setString(3, String.valueOf(Filmes.getAno()));
           ps.setString(4, Filmes.getDuracao());
            ps.setString(5, Filmes.getGenero());
            ps.executeUpdate();
           ps.close();


       } catch (Exception e) {
           e.printStackTrace();
        }
}


    }*/

