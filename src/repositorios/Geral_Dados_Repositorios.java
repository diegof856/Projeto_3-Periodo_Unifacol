package repositorios;
import entidade.Usuario;
import entidade.Usuario_Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Geral_Dados_Repositorios {

    private Connection conexao;

    public void Cadastro_Usuario(Usuario_Endereco endereco) {
        conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();

        try {
            String sql = "insert into popcorn_geral.endereco (estado,cidade,bairro,complemento,id_usuario) values (?,?,?,?,?)";

            PreparedStatement adicionar_dados = conexao.prepareStatement(sql);
            adicionar_dados.setString(1, endereco.getEstado()); // Define o email do usuário
            adicionar_dados.setString(2, endereco.getCidade()); // Define a senha do usuário
            adicionar_dados.setString(3,endereco.getBairro());
            adicionar_dados.setString(4,endereco.getComplemento());
            adicionar_dados.setString(5,endereco.getId_usuario());

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
    public Usuario_Endereco obterEnderecoDoBancoDeDados(Usuario usuario) throws SQLException {

        Usuario_Endereco endereco = null;
        Connection conexao = null;

        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String idUsuario = Chamar_id(usuario);

            String sql = "SELECT estado, cidade, bairro, complemento FROM endereco WHERE id_usuario = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, idUsuario);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                String estado = resultado.getString("estado");
                String cidade = resultado.getString("cidade");
                String bairro = resultado.getString("bairro");
                String complemento = resultado.getString("complemento");
                endereco = new Usuario_Endereco(estado, cidade, bairro, complemento, idUsuario);
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return endereco;
    }


    public static String Chamar_id(Usuario email) throws SQLException {
        Connection conexao = null;

        String id_user = null; // Inicializa o ID do usuário com zero

        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "SELECT id_usuario FROM popcorn_geral.usuario WHERE Email = ?";
            PreparedStatement resultado_id = conexao.prepareStatement(sql);
            resultado_id.setString(1, email.getEmail());  // Define o e-mail do usuário
            ResultSet resultado = resultado_id.executeQuery();

            if (resultado.next()) {
                id_user = resultado.getString("id_usuario");
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return id_user;

    }


    public static boolean verificarCadastroExistente(String email) throws SQLException {
        String idUsuario = null; // Inicializa com null para o caso de não encontrar o usuário

        Connection conexao = null;
        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "SELECT id_usuario FROM popcorn_geral.usuario WHERE Email = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                idUsuario = resultado.getString("id_usuario");
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
        // Retorna true se idUsuario não for nulo, indicando que o usuário já está cadastrado
        return idUsuario != null;
    }



    public static boolean verificarCpfExistente(String cpf) throws SQLException {
        Connection conexao = null;
        boolean existeCpf = false;
        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "SELECT COUNT(*) AS total FROM popcorn_geral.usuario_premium WHERE cpf = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                int total = resultado.getInt("total");
                existeCpf = total > 0;
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
        return existeCpf;
    }




}
