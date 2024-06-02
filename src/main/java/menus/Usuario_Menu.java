package menus;

import excecoes.EmailInvalido;
import excecoes.Excecao_Dados;
import excecoes.NomeInvalido;
import excecoes.SenhaInvalida;
import interfacea.Iusuario;
import servicos.Usuario_Normal_Servicos;
import servicos.Usuario_Premium_Servicos;

import java.util.Scanner;



public class Usuario_Menu implements Iusuario {

    // MENU DE LOGIN OU REGISTRO:
    @Override
    public void menu(Scanner scanner) throws Excecao_Dados {
        int opcao = 0;

        System.out.print("""
            
            POPCORN

            1.LOGIN
            2.CADASTRO

            OPÇÃO:""");

        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menu_tipo_login(scanner);
                    break;
                case 2:
                    try {
                        menu_tipo_cadastro(scanner);
                    } catch (EmailInvalido e) {
                        throw new RuntimeException(e);
                    } catch (SenhaInvalida e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    System.out.println("\nERRO: Opção inválida. Tente novamente.\n");
                    menu(scanner);
                    break;
            }
        } else {
            System.out.println("\nERRO: Insira apenas números inteiros.\n");
            scanner.nextLine();
            menu(scanner);
        }
    }


    // VERIFICAÇÃO DE TIPO DE LOGIN

    public void menu_tipo_login(Scanner scannerLogin_tipo) throws Excecao_Dados {
        int opcao = 0;

        System.out.print("""
        
        POPCORN

        1.Usuário Normal
        2.Usuário Premium

        OPÇÃO:""");

        if (scannerLogin_tipo.hasNextLine()) {
            String input = scannerLogin_tipo.nextLine().trim();

            // Verifica se a entrada é um número
            if (input.matches("\\d+")) {
                opcao = Integer.parseInt(input);
            } else {
                System.out.println("ERRO: Insira apenas números.");
                menu_tipo_login(scannerLogin_tipo);
                return;
            }
        } else {
            System.out.println("ERRO: Nenhuma entrada fornecida.");
            menu_tipo_login(scannerLogin_tipo);
            return;
        }

        switch (opcao) {
            case 1:
                Usuario_Normal_Servicos.login_normal(scannerLogin_tipo);
                break;
            case 2:
                Usuario_Premium_Servicos.login_premium(scannerLogin_tipo);
                break;
            default:
                System.out.println("ERRO: Opção inválida. Tente novamente.");
                menu_tipo_login(scannerLogin_tipo);
                break;
        }
    }




    // VERIFICAÇÃO DE TIPO DE USUÁRIO

    public void menu_tipo_cadastro(Scanner scannerLogin_tipo) throws Excecao_Dados, EmailInvalido, SenhaInvalida {
        int opcao = 0;

        System.out.print("""
                
                POPCORN

                1.Usuário Normal
                2.Usuário Premium

                OPÇÃO:""");

        // Verifica se há uma próxima linha no scanner
        if (scannerLogin_tipo.hasNextLine()) {
            String input = scannerLogin_tipo.nextLine().trim();

            // Verifica se a entrada é um número
            if (input.matches("\\d+")) {
                opcao = Integer.parseInt(input);
            } else {
                System.out.println("\nERRO: Insira apenas números.\n");
                menu_tipo_login(scannerLogin_tipo);
                return;
            }
        } else {
            System.out.println("\nERRO: Nenhuma entrada fornecida.\n");
            menu_tipo_login(scannerLogin_tipo);
            return;
        }

        switch (opcao) {
            case 1:
                Usuario_Normal_Servicos.cadastro_normal(scannerLogin_tipo);
                break;
            case 2:
                Usuario_Premium_Servicos.cadastro_premium(scannerLogin_tipo);
                break;
            default:
                System.out.println("\nERRO: Opção inválida. Tente novamente.\n");
                menu_tipo_login(scannerLogin_tipo);
                break;
        }

    }


}

