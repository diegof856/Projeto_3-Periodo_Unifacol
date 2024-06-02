package servicos;
import entidade.Filme;
import entidade.Usuario;
import excecoes.EmailInvalido;
import excecoes.Informacao_Excecoes;
import excecoes.NomeInvalido;
import excecoes.SenhaInvalida;
import repositorios.Filme_Dados_Repositorio;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Filme_Servicos {

    // CADASTRO DE FILMES - RECEBER AS INFORMAÇÕES DO FILME QUE SERÁ ADICIONADO
    public static void addFilme(Scanner scannerFilmes, Usuario usuario) throws NomeInvalido, Informacao_Excecoes, SQLException, EmailInvalido, SenhaInvalida {
        System.out.println("\nCADASTRO DE FILMES\n");

        String nomeFilme, ano, duracao, sinopse, pais = null, idGenero = null;

        while (true) {
            System.out.print("Nome do Filme: ");
            nomeFilme = scannerFilmes.nextLine();

            if (validarNome(nomeFilme)) {
                break;
            } else {
                System.out.println("Nome inválido. Digite novamente.");
            }
        }

        while (true) {
            System.out.print("Ano de Lançamento: ");
            ano = scannerFilmes.nextLine();
            if (validarValores(ano)) {
                break;
            } else {
                System.out.println("Ano inválido. Digite novamente.");
            }
        }

        while (true) {
            System.out.print("Duração do Filme em minutos: ");
            duracao = scannerFilmes.nextLine();
            if (validarValores(duracao)) {
                break;
            } else {
                System.out.println("Valor Inválido. Digite novamente.");
            }
        }

        while (true) {
            System.out.print("Sinopse: ");
            sinopse = scannerFilmes.nextLine();
            if (!sinopse.isBlank()) {
                break;
            } else {
                System.out.println("Sinopse inválida. Digite novamente.");
            }
        }

        try {
            while (true) {
                System.out.print("País: ");
                String nomePais = scannerFilmes.nextLine(); // Recebe o nome do país como uma String

                Filme_Dados_Repositorio repositorioFilme = new Filme_Dados_Repositorio();
                Integer idPais = repositorioFilme.Chamar_id_pais(nomePais); // Obtém o ID do país do banco de dados

                if (idPais == null) {
                    System.out.println("O país '" + nomePais + "' não existe no banco de dados ou está incorreto. Digite novamente.");
                } else {
                    pais = String.valueOf(idPais); // Atribui o ID do país à variável 'pais'
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }

        while (true) {
            System.out.println("\nTodos os Gênero: ");
            Filme_Servicos.mostar_Genero();

            System.out.print("Selecione um Gênero: ");
            String genero = scannerFilmes.nextLine();

            Filme_Dados_Repositorio repositorioFilme = new Filme_Dados_Repositorio();
            Integer idGenero_consulta = repositorioFilme.Chamar_id_genero(genero); // Obtém o ID do país do banco de dados

            if (idGenero_consulta == null) {
                System.out.println("O país '" + genero + "' não existe no banco de dados ou está incorreto. Digite novamente.");
            } else {
                idGenero = String.valueOf(idGenero_consulta); // Atribui o ID do país à variável 'pais'
                break;
            }
        }

        // Loop para garantir que o filme seja cadastrado com sucesso antes de prosseguir
        while (true) {
            Filme cadastrar = new Filme(nomeFilme, ano, duracao, sinopse, pais, idGenero);

            Filme_Dados_Repositorio add_Filme = new Filme_Dados_Repositorio();
            Filme filme_cadastro = add_Filme.cadastro_Filme(cadastrar);

            if (filme_cadastro != null) {
                System.out.println("""
                    FILME CADASTRADO
                                
                    NOME DO FILME: %s
                    ANO DE LANÇAMENTO: %s
                    DURAÇÃO DO FILME EM MINUTOS: %s
                    SINOPSE: %s
                  
                    """.formatted(nomeFilme, ano, duracao, sinopse));
                System.out.println("\nFILME CADASTRADO COM SUCESSO\n");

                Opcoes_Servicos.retornar_menu(scannerFilmes, usuario);
                break;
            } else {
                System.out.println("Erro ao cadastrar o filme. Tente novamente.");
            }
        }
    }


    public static void mostar_Filme() throws SQLException {

        Filme_Dados_Repositorio todos_filmes = new Filme_Dados_Repositorio();
        Filme_Dados_Repositorio.listar_Filmes();

    }

    public static void mostar_Genero() throws SQLException {

        Filme_Dados_Repositorio todos_generos = new Filme_Dados_Repositorio();
        Filme_Dados_Repositorio.listar_Generos();

    }

    public static void remover_Filme (Scanner scanner, Usuario usuarioo){
        try {
            System.out.println("Digite o Filme a ser removido: ");
            String FilmeUsuario = scanner.nextLine();

            if (FilmeUsuario != null && !FilmeUsuario.isEmpty()) {
                Filme_Dados_Repositorio.removerFilme(FilmeUsuario);
            } else {
                System.out.println("Nome de usuário inválido.");
                remover_Filme(scanner, usuarioo); // Chama o método novamente se o nome de usuário for inválido
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validarNome(String nome) {
        return !nome.isBlank() && !nome.isEmpty(); // Retorna true se o nome não estiver vazio ou em branco
    }

    public static boolean validarValores(String opcao) {
        return !opcao.isBlank() && opcao.matches("[0-9]+"); // Retorna true se a opção não estiver vazia e contiver apenas dígitos
    }


    public static boolean validarPais(Integer numero) {
        return numero != null; // Retorna true se o número não for nulo
    }

}

















