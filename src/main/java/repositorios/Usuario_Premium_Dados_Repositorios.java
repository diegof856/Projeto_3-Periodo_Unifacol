package repositorios;
import java.sql.*;

import entidade.Usuario;
import entidade.Usuario_Endereco;

import static repositorios.Geral_Dados_Repositorios.Chamar_id;

public class Usuario_Premium_Dados_Repositorios {

    static Connection conexao;


    // VERIFICAR O LOGIN NORMAL
    public Usuario Verificacao_Login(String email, String senha, String tipo) {
        conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
        Usuario usuarioLogin = null;
        try {
            String sql = "SELECT * FROM popcorn_geral.usuario WHERE email = ? AND senha = ? AND tipo ='normal' ";
            PreparedStatement preparar_dados = conexao.prepareStatement(sql);

            preparar_dados.setString(1, email);
            preparar_dados.setString(2, senha);

            ResultSet resultado = preparar_dados.executeQuery();

            // Verifica se encontrou um usuário com o email e senha fornecidos
            if (resultado.next()) {
                // Extrai os dados do ResultSet e cria um objeto usuario
                usuarioLogin = new Usuario();
                usuarioLogin.setEmail(resultado.getString("email"));
                usuarioLogin.setSenha(resultado.getString("senha"));// Adicione outras informações do usuário conforme necessário
            }

            // Fecha o ResultSet
            resultado.close();

            // Retorna o usuário encontrado (ou null se não foi encontrado)
            return usuarioLogin;
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            // Fecha a conexão
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    // Método Verificacao_Login_Premium

    public Usuario Verificacao_Login_Premium(String email, String senha) {
        conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
        Usuario usuarioLogin = null;

        try {
            String sql = "SELECT * FROM popcorn_geral.usuario WHERE email = ? AND senha = ?";
            PreparedStatement preparar_dados = conexao.prepareStatement(sql);
            preparar_dados.setString(1, email);
            preparar_dados.setString(2, senha);
            ResultSet resultSet = preparar_dados.executeQuery();

            // Verifica se existe um usuário com o e-mail e senha fornecidos
            if (resultSet.next()) {

                // Se existir, cria um objeto usuario com as informações do ResultSet
                usuarioLogin = new Usuario();
                usuarioLogin.setNome(resultSet.getString("nome"));
                usuarioLogin.setEmail(resultSet.getString("email"));
                usuarioLogin.setSenha(resultSet.getString("senha"));
                usuarioLogin.setTipo(resultSet.getString("tipo"));

                return usuarioLogin;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar o login do usuário.", e);
        } finally {
            // Feche a conexão com o banco de dados
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Retorna null se nenhum usuário for encontrado
        return usuarioLogin;
    }

    //ADICIONAR USUÁRIO

    public void Cadastro_Usuario(String nome, String email, String senha, String tipo, String cpf) throws SQLException {
        int id_user = 0;
        conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();

        try {
            String sql = "INSERT INTO popcorn_geral.usuario (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";
            PreparedStatement adicionar_premium = conexao.prepareStatement(sql);

            adicionar_premium.setString(1, nome);
            adicionar_premium.setString(2, email);
            adicionar_premium.setString(3, senha);
            adicionar_premium.setString(4, tipo);
            adicionar_premium.executeUpdate();

            // Consulta para obter o ID do usuário recém-inserido
            String sql1 = "SELECT id_usuario FROM popcorn_geral.usuario WHERE Email = ?";
            PreparedStatement adicionar_premium_id = conexao.prepareStatement(sql1);
            adicionar_premium_id.setString(1, email);
            ResultSet resultado = adicionar_premium_id.executeQuery();

            // Verifica se há resultados e recupera o ID do usuário
            if (resultado.next()) {
                id_user = resultado.getInt("id_usuario");
            }

            // Insere os dados na tabela usuario_premium
            String addPremium = "INSERT INTO popcorn_geral.usuario_premium (cpf, id_usuario) VALUES (?, ?)";
            PreparedStatement adicionar_premium_adicionais = conexao.prepareStatement(addPremium);
            adicionar_premium_adicionais.setString(1, cpf);
            adicionar_premium_adicionais.setInt(2, id_user);
            adicionar_premium_adicionais.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            // Fecha a conexão com o banco de dados
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    //Alterar SENHA ATUAL

    public boolean senha_Alterar(String senha_nova, String email, String senhaAtual) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "UPDATE popcorn_geral.usuario SET senha = ? WHERE email = ? AND senha = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, senha_nova);
            stmt.setString(2, email);
            stmt.setString(3, senhaAtual);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Senha alterada com sucesso!");
                return true;
            } else {
                System.out.println("Usuário não encontrado ou senha incorreta.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar senha: " + e.getMessage());
            return false;
        } finally {
            // Fechar conexão e statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar statement: " + e.getMessage());
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }


//   REMOVER USUÁRIO DO BANCO

        public static void removerUsuario(String email) {
            try {

                conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
                String sql = "DELETE FROM popcorn_geral.usuario WHERE email = ?";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, email);

                // Executar o comando SQL
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Usuário removido com sucesso!");
                } else {
                    System.out.println("Nenhum usuário encontrado com o nome especificado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Fechar a conexão com o banco de dados
                try {
                    if (conexao != null) {
                        conexao.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


//        VERIFICAR SE EMAIL EXISTE

    public static boolean verificarEmailExistente(String email) throws SQLException {
        Connection conexao = null;
        boolean existeCadastro = false;
        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "SELECT COUNT(*) AS total FROM popcorn_geral.usuario WHERE email = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                int total = resultado.getInt("total");
                existeCadastro = total > 0;
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
        return existeCadastro; // Retornar o valor da variável existeCadastro
    }



//   LISTAR TODOS OS USUÁRIOS

   public static void listar_Usuarios () throws SQLException {
       conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
       Statement todos_usuarios = conexao.createStatement();

       try {
           String sql = "SELECT * FROM popcorn_geral.usuario;";
           ResultSet resultado = todos_usuarios.executeQuery(sql);

           while (resultado.next()){
               String nomes = resultado.getString("nome");
               System.out.println(nomes);
           }

   }catch (Exception e){
           e.printStackTrace();
       }
   }






}
