package repositorios;

import entidade.Filme;

import java.sql.*;



public class Filme_Dados_Repositorio {

    static Connection conexao;

    public Filme cadastro_Filme(Filme filme) throws SQLException {
        int id_user = 0;
        conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();

        try {
            String sql = "insert into popcorn_geral.filme (titulo,ano,tempo,sipnose,id_pais) values (?,?,?,?,?)";

            PreparedStatement adicionar_Filme = conexao.prepareStatement(sql);
            adicionar_Filme.setString(1,filme.getNomeFilme());  // Define o nome do usuário
            adicionar_Filme.setString(2, filme.getAno()); // Define o email do usuário
            adicionar_Filme.setString(3, filme.getDuracao()); // Define a senha do usuário
            adicionar_Filme.setString(4,filme.getSipnose());
            adicionar_Filme.setString(5,filme.getPais());
            adicionar_Filme.executeUpdate();

            // Consulta para obter o ID do usuário recém-inserido
            String sql1 = "SELECT id_filme FROM popcorn_geral.filme WHERE titulo = ?";
            PreparedStatement adicionar_premium_id = conexao.prepareStatement(sql1);
            adicionar_premium_id.setString(1,filme.getNomeFilme());
            ResultSet resultado = adicionar_premium_id.executeQuery();

            // Verifica se há resultados e recupera o ID do usuário
            if (resultado.next()) {
               Integer id_filme = resultado.getInt("id_filme");

                // Insere os dados na tabela usuario_premium
                String addPremium = "INSERT INTO popcorn_geral.genero_filme (id_genero, id_filme) VALUES (?,?)";
                PreparedStatement adicionar_premium_adicionais = conexao.prepareStatement(addPremium);
                adicionar_premium_adicionais.setString(1,filme.getGenero());
                adicionar_premium_adicionais.setInt(2, id_filme);
                adicionar_premium_adicionais.executeUpdate();

                System.out.println("Usuário cadastrado com sucesso!");
            }


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
        return filme;
    }


//    public Filme cadastro_Filme(Filme filme) {
//        conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
//
//        try {
//            String sql = "insert into popcorn_geral.filme (titulo,ano,tempo,sipnose,id_pais) values (?,?,?,?,?)";
//
//            PreparedStatement adicionar_Filme = conexao.prepareStatement(sql);
//            adicionar_Filme.setString(1,filme.getNomeFilme());  // Define o nome do usuário
//            adicionar_Filme.setString(2, filme.getAno()); // Define o email do usuário
//            adicionar_Filme.setString(3, filme.getDuracao()); // Define a senha do usuário
//            adicionar_Filme.setString(4,filme.getSipnose());
//            adicionar_Filme.setString(5,filme.getPais());
//
//
//            adicionar_Filme.executeUpdate();
//            adicionar_Filme.close();
//
//
//        } catch (SQLException erro) {
//            erro.printStackTrace();
//        } finally {
//            // É uma boa prática fechar a conexão após o uso
//            try {
//                if (conexao != null) {
//                    conexao.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public static void removerFilme(String filme) {

        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "DELETE FROM popcorn_geral.filme WHERE titulo = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, filme);

            // Executar o comando SQL
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Filme removido com sucesso!");
            } else {
                System.out.println("Nenhum Filme encontrado com o nome especificado.");
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

    public static void listar_Filmes() throws SQLException {

        conexao = Conexao_Banco_dados_Repositorios.conexao_Banco();
        Statement todos_Filmes = conexao.createStatement();

        try {
            String sql = "SELECT * FROM popcorn_geral.filme;";
            ResultSet resultado = todos_Filmes.executeQuery(sql);

            while (resultado.next()){
                String filmes = resultado.getString("titulo");
                System.out.println(filmes);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void listar_Generos() throws SQLException {

        conexao = Conexao_Banco_dados_Repositorios.conexao_Banco();
        Statement todos_Filmes = conexao.createStatement();

        try {
            String sql = "SELECT * FROM popcorn_geral.genero;";
            ResultSet resultado = todos_Filmes.executeQuery(sql);

            while (resultado.next()){
                String genero = resultado.getString("genero");
                System.out.println(genero);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Integer Chamar_id_pais(String nomePais) throws SQLException {
        Connection conexao = null;
        Integer id_pais = null; // Inicializa o ID do país como nulo

        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "SELECT id_pais FROM popcorn_geral.pais WHERE pais = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nomePais); // Define o nome do país como parâmetro da consulta
            ResultSet resultado = stmt.executeQuery();

            // Verifica se o país existe no banco de dados
            if (resultado.next()) {
                // Se existe, retorna o ID correspondente
                id_pais = resultado.getInt("id_pais");
            } else {
                // Se não existe, imprime uma mensagem informando que o país não foi encontrado
                System.out.println("O país '" + nomePais + "' não foi encontrado no banco de dados.");
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return id_pais;
    }

    public static Integer Chamar_id_genero(String genero) throws SQLException {
        Connection conexao = null;
        Integer id_genero = null; // Inicializa o ID do país como nulo

        try {
            conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
            String sql = "SELECT id_genero FROM popcorn_geral.genero WHERE genero = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, genero); // Define o nome do país como parâmetro da consulta
            ResultSet resultado = stmt.executeQuery();

            // Verifica se o país existe no banco de dados
            if (resultado.next()) {
                // Se existe, retorna o ID correspondente
                id_genero  = resultado.getInt("id_genero");
            } else {
                // Se não existe, imprime uma mensagem informando que o país não foi encontrado
                System.out.println("O país '" + id_genero  + "' não foi encontrado no banco de dados.");
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return id_genero;
    }


        public static Integer Chamar_id_Filme(String filme) throws SQLException {
            Connection conexao = null;
            Integer id_filme = null; // Inicializa o ID do filme como nulo

            try {
                conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
                String sql = "SELECT id_filme FROM popcorn_geral.filme WHERE titulo = ?";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, filme); // Define o título do filme como parâmetro da consulta
                ResultSet resultado = stmt.executeQuery();

                // Verifica se o filme existe no banco de dados
                if (resultado.next()) {
                    // Se existe, retorna o ID correspondente
                    id_filme = resultado.getInt("id_filme");
                } else {
                    // Se não existe, imprime uma mensagem informando que o filme não foi encontrado
                    System.out.println("O filme '" + filme + "' não foi encontrado no banco de dados.");
                }
            } catch (SQLException erro) {
                erro.printStackTrace();
            } finally {
                if (conexao != null) {
                    try {
                        conexao.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return id_filme;
        }

        public static Integer Cadastrar_Filme_Genero(String genero) throws SQLException {
            Connection conexao = null;

            Integer id_filme_Genero = null;
            Integer id_filme_cadastrado = Chamar_id_Filme(genero); // Chamada correta ao método Chamar_id_Filme

            try {
                conexao = new Conexao_Banco_dados_Repositorios().conexao_Banco();
                String sql = "INSERT INTO popcorn_geral.filme (id_genero, id_filme) VALUES (?, ?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, genero);
                stmt.setInt(2, id_filme_cadastrado); // Inserindo o ID do filme retornado pelo método Chamar_id_Filme
                stmt.executeUpdate(); // Execute a inserção

                // Se chegou até aqui, a inserção foi bem-sucedida
                System.out.println("Filme com o gênero '" + genero + "' cadastrado com sucesso!");

                // Como estamos inserindo, não é necessário recuperar o ID gerado
                // Se você precisar do ID gerado, pode fazê-lo com o método getGeneratedKeys()

            } catch (SQLException erro) {
                erro.printStackTrace();
            } finally {
                if (conexao != null) {
                    try {
                        conexao.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return id_filme_Genero;
        }
    }



























//
//    //CHAMAR A TABELA FILMES
//    public static void tabelafilme(Scanner scannerFilme) {
//        System.out.println("\nCATÁLOGO DE FILMES DISPONÍVEIS\n");
//
//        try {
//            Connection connection = DriverManager.getConnection(url, user, senha);
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM filmes";
//
//            ResultSet resultado = statement.executeQuery(query);
//            while (resultado.next()) {
//
//                int idFilme = resultado.getInt("idFilmes");
//                String nomeFilme = resultado.getString("nomeFilme");
//
//                System.out.print(idFilme + "-");
//                System.out.println(nomeFilme);
//            }
//            resultado.close();
//            statement.close();
//            connection.close();
//
//            Filme_servico.menulista(scannerFilme);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    // REMOVER O FILME DO BANCO DE DADO
//    public static void removerFilme(Scanner scannerFilmes) {
//        int removerFilme;
//        Scanner scannerRemover = new Scanner(System.in);
//        System.out.println("DIGITE O ID DO FILME QUE SERÁ REMOVIDO: ");
//        removerFilme = Integer.parseInt(scannerRemover.nextLine());
//
//        String query = "DELETE FROM filmes WHERE idFilmes = ?";
//
//        try (Connection connection = DriverManager.getConnection(url, user, senha);
//             PreparedStatement ps = connection.prepareStatement(query)) {
//
//            ps.setInt(1, removerFilme);
//
//            int removidos = ps.executeUpdate();
//S
//            if (removidos > 0) {
//                System.out.println("FILME REMOVIDO COM SUCESSO");
//                Filme_servico.menuFilme(scannerFilmes);
//            } else {
//                System.out.println("NENHUM FILME COM ESSE ID ENCONTRADO");
//                Filme_servico.menulista(scannerFilmes);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
//
