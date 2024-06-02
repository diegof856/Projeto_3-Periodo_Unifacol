package menus;
import entidade.Usuario;
import entidade.Usuario_Premium;
import excecoes.EmailInvalido;
import excecoes.Informacao_Excecoes;
import excecoes.NomeInvalido;
import excecoes.SenhaInvalida;
import servicos.Filme_Servicos;
import servicos.Opcoes_Servicos;

import java.sql.SQLException;
import java.util.Scanner;

public class Filme_Menu {


    // MENU DE OPÇÕES DA LISTA DE FILMESe
    public static void menu_catalogo(Scanner scannerFilmes, Usuario usuario) throws SQLException {

        System.out.println("\nCatálogo de Filmes:");
        Filme_Servicos.mostar_Filme();

        System.out.print("""

                1.Selecionar Filme
                2.Cadastrar Filme
                3.Remover Filme
                4.Cadastrar Ator
                5.Cadastrar Diretor
                6.Voltar ao Menu

                OPÇÃO:""");

        int opcao;
        if (scannerFilmes.hasNextLine()) {
            String input = scannerFilmes.nextLine().trim();

            // Verifica se a entrada é um número
            if (input.matches("\\d+")) {
                opcao = Integer.parseInt(input);
            } else {
                System.out.println("ERRO: Insira apenas números.");
                menu_catalogo(scannerFilmes,usuario);
                return;
            }
        } else {
            System.out.println("ERRO: Nenhuma entrada fornecida.");
            menu_catalogo(scannerFilmes,usuario);
            return;
        }

        switch (opcao) {
            case 1:

                break;

            case 2:
                try {
                    try {
                        try {
                            Filme_Servicos.addFilme(scannerFilmes,usuario);
                        } catch (EmailInvalido e) {
                            throw new RuntimeException(e);
                        } catch (SenhaInvalida e) {
                            throw new RuntimeException(e);
                        }
                    } catch (Informacao_Excecoes e) {
                        throw new RuntimeException(e);
                    }
                } catch (NomeInvalido e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:

Filme_Servicos.remover_Filme(scannerFilmes,usuario);
            case 4:

                break;

            case 5:

                break;
            case 6:
                try {
                    Opcoes_Servicos.retornar_menu(scannerFilmes,usuario);
                } catch (EmailInvalido e) {
                    throw new RuntimeException(e);
                } catch (SenhaInvalida e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("\nERRO, TENTE NOVAMENTE\n");
                menu_catalogo(scannerFilmes,usuario);
                break;

        }


    }


}


