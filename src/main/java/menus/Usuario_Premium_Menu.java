package menus;
import entidade.Usuario;
import entidade.Usuario_Endereco;
import entidade.Usuario_Premium;
import excecoes.EmailInvalido;
import excecoes.Excecao_Dados;
import excecoes.SenhaInvalida;
import excecoes.TimeException;
import repositorios.Geral_Dados_Repositorios;
import repositorios.Usuario_Premium_Dados_Repositorios;
import servicos.Metodos_Gerais_Servicos;
import servicos.Opcoes_Servicos;
import servicos.Usuario_Premium_Servicos;

import java.sql.SQLException;
import java.util.Scanner;

import static repositorios.Geral_Dados_Repositorios.Chamar_id;


public class Usuario_Premium_Menu {


    //    MINHAS INFORMAÇÕES

    public static void infomacoes_usuario(Scanner scanner, Usuario usuario) throws SQLException {
        System.out.println("MINHAS INFORMAÇÕES");
        System.out.println("\nNome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        if (usuario.getSenha() == null) {
            return;
        } else {
            System.out.println("Senha: ********");
        }

        System.out.println("Tipo de Usuário: " + usuario.getTipo());
        System.out.println("CPF: ***********");

        System.out.println("\nINFORMAÇOES ADICIONAIS\n");

        Geral_Dados_Repositorios repositorios = new Geral_Dados_Repositorios();
        Usuario_Endereco endereco = repositorios.obterEnderecoDoBancoDeDados(usuario);

        if (endereco == null) {
            System.out.println("Endereço não encontrado");
        } else {
            System.out.println("Estado: " + endereco.getEstado());
            System.out.println("Cidade: " + endereco.getCidade());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Complemento: " + endereco.getComplemento());
        }
    }




    // MENU PRINCIPAL PREMIUM
    public static void menu(Scanner scannerMenuGeral, Usuario usuarioLogin ) throws EmailInvalido, Excecao_Dados, SenhaInvalida  {
        int opcao;

        System.out.println("""
        POPCORN
                            
        1.Meu Perfil
        2.Minhas Listas
        3.Catálogo de Filmes
        """);

        if (scannerMenuGeral.hasNextInt()) {
            opcao = scannerMenuGeral.nextInt();
            scannerMenuGeral.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        menu_perfil(scannerMenuGeral, usuarioLogin);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    menu_listas(scannerMenuGeral, usuarioLogin);
                    break;

                case 3:
                    try {
                        Filme_Menu.menu_catalogo(scannerMenuGeral, usuarioLogin);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    System.out.println("\nOPÇÃO INVÁLIDA, TENTE NOVAMENTE");
                    menu(scannerMenuGeral, usuarioLogin); // Chamada recursiva para lidar com entrada inválida
            }
        } else {
            System.out.println("\nOPÇÃO INVÁLIDA, TENTE NOVAMENTE");
            scannerMenuGeral.nextLine();
            menu(scannerMenuGeral,usuarioLogin);
        }

        }


    //MENU DAS INFORMAÇÕES DO USUÁRIO
    public static void menu_perfil(Scanner scannerPerfil, Usuario informacao_usuario) throws SQLException {

int opcao;

        System.out.println("""
                                    
                1.Minhas Informações
                2.Listar Usuários
                3.Minhas Listas
                4.Voltar para o Inicio
                                    
                OPÇÃO: """);

        if (scannerPerfil.hasNextInt()) {
             opcao = scannerPerfil.nextInt();
            scannerPerfil.nextLine();

        switch (opcao) {
            case 1:
                try {
                    informacao_menu(scannerPerfil,informacao_usuario);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 2:

                Usuario_Premium_Servicos.mostrar_Usuarios(scannerPerfil,informacao_usuario);
                System.out.println("""
                                   
                1.Remover Usuário
                2.Voltar para ao perfil
                                    
                OPÇÃO: """);

                if (scannerPerfil.hasNextLine()) {
                    String input = scannerPerfil.nextLine().trim();

                    // Verifica se a entrada é um número
                    if (input.matches("\\d+")) {
                        opcao = Integer.parseInt(input);
                    } else {
                        System.out.println("ERRO: Insira apenas números.");
                        menu_perfil(scannerPerfil,informacao_usuario);
                        return;
                    }
                } else {
                    System.out.println("ERRO: Nenhuma entrada fornecida.");
                    menu_perfil(scannerPerfil,informacao_usuario);
                    return;
                }

                switch(opcao){

                    case 1:
                        Usuario_Premium_Servicos.remover_Usuario(scannerPerfil,informacao_usuario);

                    case 2:
                        Usuario_Premium_Menu.menu_perfil(scannerPerfil,informacao_usuario);
                        break;

                    default:
                        throw new IllegalStateException("Opção invalida: " + opcao);

                }
                break;

            case 3:

//                IMPLEMENTAÇÃO DE LISTAS

            case 4:


                try {
                    Opcoes_Servicos.retornar_menu(scannerPerfil,informacao_usuario);
                } catch (EmailInvalido e) {
                    throw new RuntimeException(e);
                } catch (SenhaInvalida e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                System.out.println("\nOPÇÃO INVÁLIDA, TENTE NOVAMENTE");
                menu_perfil(scannerPerfil, informacao_usuario); // Chamada recursiva para lidar com entrada inválida
        }
        } else {
            System.out.println("\nOPÇÃO INVÁLIDA, TENTE NOVAMENTE");
            scannerPerfil.nextLine();
            menu_perfil(scannerPerfil,informacao_usuario);
        }


    }


// MENU DE INFORMAÇÕES
public static void informacao_menu(Scanner scanner, Usuario usuario) throws SQLException {
    infomacoes_usuario(scanner, usuario);

    System.out.println("""
        
        1.Cadastrar Informações Adicionais
        2.Mudar Nome do Usuário
        3.Mudar Senha
        4.Retornar Para Perfil
                                
        Opção: """);

    int opcao;

    // Verifica se há uma próxima linha no scanner
    if (scanner.hasNextLine()) {
        String input = scanner.nextLine().trim();

        // Verifica se a entrada é um número
        if (input.matches("\\d+")) {
            opcao = Integer.parseInt(input);

            switch (opcao) {
                case 1:
                    Geral_Dados_Repositorios verificar = new Geral_Dados_Repositorios();
                    boolean idUsuario = verificar.verificarCadastroExistente(usuario.getEmail());
                    if (idUsuario) {
                        try {
                            Metodos_Gerais_Servicos.cadastro_adicional(scanner, usuario);
                        } catch (TimeException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    // Lógica para mudar nome do usuário
                    break;
                case 3:
                    Usuario_Premium_Servicos.alterar_Senha(scanner,usuario);
                    break;
                case 4:
                    menu_perfil(scanner, usuario);
                    break;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA, TENTE NOVAMENTE");
                    informacao_menu(scanner, usuario);
                    return;
            }
        } else {
            System.out.println("ERRO: Insira apenas números.");
            informacao_menu(scanner, usuario);
            return;
        }
    } else {
        System.out.println("ERRO: Nenhuma entrada fornecida.");
        informacao_menu(scanner, usuario);
        return;
    }
}





    // MENU LISTAS
    public static void menu_listas(Scanner scannerLista, Usuario Verificar) throws EmailInvalido, SenhaInvalida {
        System.out.println("MINHAS LISTAS");
        int opcao;

        System.out.println("""
                          
                1.Adicionar
                2.Remover
                3.Criar nova Lista
                4.Voltar para o Perfil
                                
                """);
        if (scannerLista.hasNextLine()) {
            String input = scannerLista.nextLine().trim();

            // Verifica se a entrada é um número
            if (input.matches("\\d+")) {
                opcao = Integer.parseInt(input);
            } else {
                System.out.println("ERRO: Insira apenas números.");
                menu_listas(scannerLista,Verificar);
                return;
            }
        } else {
            System.out.println("ERRO: Nenhuma entrada fornecida.");
            menu_listas(scannerLista,Verificar);
            return;
        }
        switch (opcao) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                // Implementar Criar nova Lista
                break;

            case 4:
                try {
                    menu_perfil(scannerLista,Verificar);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                System.out.println("\nOPÇÃO INVALIDA,TENTE NOVAMENTE");
                menu_listas(scannerLista,Verificar);
        }
    }

    // MENU AMIGOS
    public void menu_amigos(Scanner scannerAmigos, Usuario usuarioLogin) {
        System.out.println("MEUS AMIGOS");

        System.out.println("""
                          
                1.Adicionar
                2.Remover
                3.Voltar para o Perfil
                                
                """);
        int opcao;
        if (scannerAmigos.hasNextLine()) {
            String input = scannerAmigos.nextLine().trim();

            // Verifica se a entrada é um número
            if (input.matches("\\d+")) {
                opcao = Integer.parseInt(input);
            } else {
                System.out.println("ERRO: Insira apenas números.");
                menu_amigos(scannerAmigos,usuarioLogin);
                return;
            }
        } else {
            System.out.println("ERRO: Nenhuma entrada fornecida.");
            menu_amigos(scannerAmigos,usuarioLogin);
            return;
        }

        switch (opcao) {
            case 1:
                // Implementar Adicionar amigo
                break;

            case 2:
                // Implementar Remover amigo
                break;

            case 3:
//                try {
////                    menu_perfil(scannerAmigos,usuarioLogin);
//                }catch (){
//
//                }
//                break;

            default:
                System.out.println("\nOPÇÃO INVALIDA,TENTE NOVAMENTE");
                menu_amigos(scannerAmigos, usuarioLogin);
        }
    }


}
