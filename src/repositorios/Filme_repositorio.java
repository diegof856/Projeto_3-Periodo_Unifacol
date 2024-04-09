package repositorios;

import java.util.ArrayList;
import java.util.List;

import entidade.Diretor;
import entidade.Filme;
import entidade.enums.GeneroEnum;

/*
import servicos.Filme_servico;
import java.sql.Connection;/import java.sql.*;import java.util.Scanner;
public class Filme_repositorio {

    //CRIAR A CONEXÃO COM O BANCO DE DADOS
    private static final String url = "jdbc:mysql://localhost:3306/filmes";
    private static final String user = "Andinho";
    private static final String senha = "Waud3r1@1@";

    private static Connection conn;

    // VERIFICAÇÃO DE CONEXÃO
    public static Connection getFilme_repostorio() {

       try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, senha);
                return conn;
           } else {                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //CHAMAR A TABELA FILMES
    public static void tabelafilme(Scanner scannerFilme) {
        System.out.println("\nCATÁLOGO DE FILMES DISPONÍVEIS\n");

        try {
            Connection connection = DriverManager.getConnection(url, user, senha);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM filmes";

            ResultSet resultado = statement.executeQuery(query);
            while (resultado.next()) {

               int idFilme = resultado.getInt("idFilmes");
                String nomeFilme = resultado.getString("nomeFilme");

                System.out.print(idFilme + "-");
                System.out.println(nomeFilme);
            }
            resultado.close();
            statement.close();
            connection.close();

           Filme_servico.menulista(scannerFilme);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

   // REMOVER O FILME DO BANCO DE DADO
    public static void removerFilme(Scanner scannerFilmes) {
        int removerFilme;
        Scanner scannerRemover = new Scanner(System.in);
        System.out.println("DIGITE O ID DO FILME QUE SERÁ REMOVIDO: ");
        removerFilme = Integer.parseInt(scannerRemover.nextLine());

        String query = "DELETE FROM filmes WHERE idFilmes = ?";

        try (Connection connection = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = connection.prepareStatement(query)) {

           ps.setInt(1, removerFilme);
           int removidos = ps.executeUpdate();

            if (removidos > 0) {
                System.out.println("FILME REMOVIDO COM SUCESSO");
               Filme_servico.menuFilme(scannerFilmes);
            } else {
                System.out.println("NENHUM FILME COM ESSE ID ENCONTRADO");
               Filme_servico.menulista(scannerFilmes);
           }

       } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}*/

public class Filme_repositorio {
	
public List<Filme> colocarFilme() {
	List<GeneroEnum> generos = new ArrayList<GeneroEnum>();
	 generos.add(GeneroEnum.Biopic);
	 generos.add(GeneroEnum.Historico);
	 generos.add(GeneroEnum.Suspense);
	 
		Filme f1 = new Filme("Oppenheimer", "Oppenheimer", 2023, generos, "3h", new Diretor("Christopher Nolan", 53, "Britânico"));
	List<Filme> lista = new ArrayList<Filme>();
	lista.add(f1);
	return lista;
} 
}

