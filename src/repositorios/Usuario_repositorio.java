package repositorios;
//import java.sql.*;

import java.util.List;

//
/*
//CRIAR A CONEXÃO COM O BANCO DE DADOS
public class Usuario_repositorio {
    private static final String url = "jdbc:mysql://localhost:3306/dados_usuario";
   private static final String user = "Andinho";    
private static final String password = "Waud3r1@1@";

    private static Connection conn;
  
//VERIFICAÇÃO DE CONEXÃO
    public static Connection getUsuario_Repositorio() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    // VERIFICAÇÃO DO EMAIL E SENHA NO BANCO DE DADOS
    public static boolean VerificacaoLogin(String email,String senha) {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "SELECT COUNT(*) FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement statemant = conn.prepareStatement(sql);
            statemant.setString(1,email);
            statemant.setString(2,senha);

            ResultSet resultSet = statemant.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return false;
    }
}*/

import java.util.Scanner;

import entidade.Usuario_Login;
import excecoes.EntradaInvalida;
import interfacea.IUsuario_Repositorio;
import servicos.Menu_Servico;

public class Usuario_repositorio extends Repositorio_Base<Usuario_Login> implements IUsuario_Repositorio {
	private Usuario_Login[] elementos;
	private int capacidade;
	private int inicio;
	private int fim;
	private  int tamanho;
	

	public Usuario_repositorio(Usuario_Login usuarios) {
		adicionarLista(usuarios);
		adicionarFila(usuarios);
	}

	public Usuario_repositorio() {
		this.capacidade = verLista().size();
		elementos = new Usuario_Login[capacidade];
		inicio = 0;
		fim = -1;
		tamanho = 0;
	}
	@Override
	public void adicionarFila(Usuario_Login elemento) {
		if (estaCheia()) {
			throw new IllegalStateException("A fila esta cheia");
		}
		fim = (fim + 1) % capacidade;
		elementos[fim] = elemento;
		tamanho++;
	}
	@Override
	public Usuario_Login remover() {
		if (estaVazia()) {
			throw new IllegalStateException("A fila está vazia");
		}

		Usuario_Login elementoRemovido = elementos[inicio];
		inicio = (inicio + 1) % capacidade;
		tamanho--;

		return elementoRemovido;
	}
	@Override
	public Usuario_Login inicio() {
		if (estaVazia()) {
			throw new IllegalStateException("A fila está vazia");
		}

		return elementos[inicio];
	}
	@Override
	public boolean estaVazia() {
		return tamanho == 0;
	}
	@Override
	public boolean estaCheia() {
		return tamanho == capacidade;
	}

	public void mostrarTamanhoDaFila(Scanner sc, Usuario_Login usuarioVindoDoMenu) throws EntradaInvalida {

	        
	        System.out.println(tamanho);

	        System.out.print("Deseja ver a lista de todos os usuarios(s/n): ");
	        char resposta = sc.next().charAt(0);
	        if(resposta == 's') {
	        	System.out.println(remover());
	        } else {
	            new Menu_Servico().menu_usuario(sc, usuarioVindoDoMenu);
	        }

	}


}