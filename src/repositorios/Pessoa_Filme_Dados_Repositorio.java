package repositorios;

import entidade.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pessoa_Filme_Dados_Repositorio {

    private Connection conexao;

    public void Cadastro_Usuario(Usuario adicionar_usuario) {
        conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();

        try {
            String sql = "insert into popcorn_geral.usuario (nome,email,senha,tipo) values (?,?,?,?)";

            PreparedStatement adicionar_dados = conexao.prepareStatement(sql);
            adicionar_dados.setString(1, adicionar_usuario.getNome());  // Define o nome do usuário
            adicionar_dados.setString(2, adicionar_usuario.getEmail()); // Define o email do usuário
            adicionar_dados.setString(3, adicionar_usuario.getSenha()); // Define a senha do usuário
            adicionar_dados.setString(4,adicionar_usuario.getTipo());

            adicionar_dados.executeUpdate();
            adicionar_dados.close();

        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            // É uma boa prática fechar a conexão após o uso
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
