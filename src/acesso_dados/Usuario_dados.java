package acesso_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidade.Usuario_Login;
import interfacea.IUsuario_dados;
import repositorios.Usuario_repositorio;

public class Usuario_dados implements IUsuario_dados {

	private Connection conexao;

	public Usuario_dados() {
		this.conexao = Conexao_Banco_dados.conexao_Banco();
	}

	// VERIFICAR SE O EMAIL EXISTE NO BANCO DE DADOS
	public Usuario_Login Verificacao_Login(String email, String senha) {

		PreparedStatement preparar_dados = null;
		ResultSet execultar = null;
		Integer id = null;
		String nome = null;
		try {

			preparar_dados = this.conexao.prepareStatement("SELECT * FROM test.usuario WHERE email = ? AND senha = ?");

			preparar_dados.setString(1, email);
			preparar_dados.setString(2, senha);

			execultar = preparar_dados.executeQuery();
			if (execultar.next()) {
				id = execultar.getInt("idUsuario");
				email = execultar.getString("email");
				senha = execultar.getString("senha");
				nome = execultar.getString("nome");
			}
			Usuario_Login usuario = new Usuario_Login(senha, email, nome);
			usuario.setId(id);
			return usuario;

		} catch (SQLException erro) {
			throw new Excecao_Dados(erro.getMessage());
		} finally {
			Conexao_Banco_dados.fecharStatement(preparar_dados);

			Conexao_Banco_dados.fecharResultSet(execultar);

			Conexao_Banco_dados.fecharConexao();

		}
	}

	// ADICIONAR USUÁRIO
	public void Cadastro_Usuario(Usuario_Login adicionar_usuario) {
		PreparedStatement adicionar_dados = null;
		ResultSet pegarIdCriado = null;
		int id;
		try {
			String sql = "insert into test.usuario (nome,email,senha) values (?,?,?)";

			adicionar_dados = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			adicionar_dados.setString(1, adicionar_usuario.getNome());
			adicionar_dados.setString(2, adicionar_usuario.getEmail());
			adicionar_dados.setString(3, adicionar_usuario.getSenha());

			int linhasAfetadas = adicionar_dados.executeUpdate();
			if (linhasAfetadas > 0) {
				pegarIdCriado = adicionar_dados.getGeneratedKeys();
				if (pegarIdCriado.next()) {
					id = pegarIdCriado.getInt(1);
					adicionar_usuario.setId(id);
				}
				Conexao_Banco_dados.fecharResultSet(pegarIdCriado);

			} else {
				throw new Excecao_Dados("Erro inesperado!! Nenhuma linha do banco de dados foi alterada");
			}

		} catch (SQLException erro) {
			throw new Excecao_Dados(erro.getMessage());
		} finally {
			Conexao_Banco_dados.fecharStatement(adicionar_dados);
			Conexao_Banco_dados.fecharConexao();
		}

	}

	// LISTAR USUÁRIOS
	public void listar_usuario() {
	    PreparedStatement pegaUsuarios = null;
	    ResultSet resultado = null;
	 

	    try {
	        pegaUsuarios = this.conexao.prepareStatement("SELECT * FROM test.usuario ORDER BY Name");
	        resultado = pegaUsuarios.executeQuery();
	        Usuario_Login usuario = new Usuario_Login();
	        while (resultado.next()) {
	        	 
	        	  
	            usuario.setId(resultado.getInt("idUsuario"));
	            usuario.setNome(resultado.getString("nome"));
	            usuario.setEmail(resultado.getString("email"));
	            
	            new Usuario_repositorio(usuario);
	            
	        }

	        
	     

	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        
	        Conexao_Banco_dados.fecharStatement(pegaUsuarios);
	        Conexao_Banco_dados.fecharResultSet(resultado);
	        Conexao_Banco_dados.fecharConexao();
	    }
	}


}
