package servicos;

import entidade.Usuario;
import entidade.Usuario_Premium;
import excecoes.EmailInvalido;
import excecoes.Excecao_Dados;
import excecoes.NomeInvalido;
import excecoes.SenhaInvalida;
import menus.Usuario_Premium_Menu;
import repositorios.Geral_Dados_Repositorios;
import repositorios.Usuario_Premium_Dados_Repositorios;
import java.sql.SQLException;
import java.util.Scanner;

import static servicos.Metodos_Gerais_Servicos.validarCpf;


public class Usuario_Premium_Servicos {


    // MENU DE LOGIN Premium
    public static Usuario login_premium(Scanner scannerLogin) throws Excecao_Dados {
        try {
            String email, senha;

            System.out.print("\nEMAIL: ");
            email = scannerLogin.nextLine().trim();

            System.out.print("SENHA: ");
            senha = scannerLogin.nextLine().trim();

            Usuario_Premium_Dados_Repositorios acesso_usuario = new Usuario_Premium_Dados_Repositorios();
            Usuario usuarioLogin = acesso_usuario.Verificacao_Login_Premium(email, senha);

            if (usuarioLogin != null) {

                new Usuario_Premium_Menu().menu(scannerLogin, usuarioLogin);

            } else {
                System.out.println("Usuário ou senha inválidos.");
                login_premium(scannerLogin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CADASTRO USUÁRIO PREMIUM

    public static void cadastro_premium(Scanner scannerLogin) {
        System.out.println("\nCADASTRO DE USUÁRIO\n");

        String nome, email, senha, confirmacaoDeSenha, tipo, cpf;

        try {
            while (true) {
                System.out.println("Digite o CPF:");
                cpf = scannerLogin.next();


                if (validarCpf(cpf)) {
                    if (!new Geral_Dados_Repositorios().verificarCpfExistente(cpf)) {
                        break;
                    } else {
                        System.out.println("CPF já cadastrado. Digite novamente.");
                    }
                } else {
                    System.out.println("CPF inválido. Digite novamente.");
                }
            }

            tipo = "premium";
            scannerLogin.nextLine();

            while (true) {
                System.out.println("NOME: ");
                nome = scannerLogin.nextLine();
                if (!validarNome_Premium(nome)) {
                    break;
                } else {
                    System.out.println("Nome inválido. Digite novamente.");
                }
            }

            while (true) {
                System.out.println("EMAIL: ");
                email = validarEmail_Premium(scannerLogin.nextLine().trim());
                if (!new Usuario_Premium_Dados_Repositorios().verificarEmailExistente(email)) {
                    break;
                } else {
                    System.out.println("E-mail já cadastrado");
                }
            }


            while (true) {
                System.out.println("SENHA: ");
                senha = validarSenha_Premium(scannerLogin.nextLine().trim());
                System.out.println("CONFIRME SUA SENHA: ");
                confirmacaoDeSenha = validarSenha_Premium(scannerLogin.nextLine().trim());

                if (senha.equals(confirmacaoDeSenha)) {
                    break; // Sai do loop se as senhas coincidirem
                } else {
                    System.out.println("As senhas não correspondem. Digite novamente.");
                }
            }

            Usuario_Premium_Dados_Repositorios cadastroRepositorio = new Usuario_Premium_Dados_Repositorios();
            cadastroRepositorio.Cadastro_Usuario(nome, email, senha, tipo, cpf);

            Usuario_Premium usuarioPremium = new Usuario_Premium(nome, email, senha, tipo, cpf);

            // Chame o menu passando o objeto Usuario_Premium
            new Usuario_Premium_Menu().menu(scannerLogin, usuarioPremium);

        } catch (SenhaInvalida | EmailInvalido | NomeInvalido e) {
            System.out.println(e.getMessage());
            cadastro_premium(scannerLogin); // Recomeça o cadastro em caso de erro
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
    }


    //    REMOVER USUÁRIO
        public static void remover_Usuario (Scanner scanner, Usuario usuarioo){
            try {
                System.out.println("Digite o Email do usuário a ser removido: ");
                String emailUsuario = scanner.nextLine().trim();

                if (emailUsuario != null && !emailUsuario.isEmpty()) {
                    Usuario_Premium_Dados_Repositorios.removerUsuario(emailUsuario);
                } else {
                    System.out.println("Nome de usuário inválido.");
                    remover_Usuario(scanner, usuarioo); // Chama o método novamente se o nome de usuário for inválido
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        public static void alterar_Senha(Scanner scanner, Usuario usuario) throws SQLException {
        String senha,email,nova_senha;

            System.out.println("Digite a senha atual: ");
            senha = scanner.nextLine().trim();
            email = usuario.getEmail();

            try {
                if (senha!= null && !senha.isEmpty()) {
                    System.out.println("Digite a Nova Senha: ");
                    nova_senha = scanner.nextLine().trim();

                    new Usuario_Premium_Dados_Repositorios().senha_Alterar(nova_senha,email,senha);
                    Usuario_Premium_Menu.informacao_menu(scanner,usuario);
                } else {
                    System.out.println("Nome de usuário inválido.");
                    alterar_Senha(scanner,usuario);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

        // VALIDAR EMAIL,SENHA E NOME

    public static String validarEmail_Premium(String email) throws EmailInvalido {
        if (email.isBlank() || email.isEmpty()) {
            throw new EmailInvalido();
        } else if (email.contains("@") && email.contains(".") && email.contains("com")) {
            return email;
        } else {
            throw new EmailInvalido();
        }
    }
    public static String validarSenha_Premium(String senha) throws SenhaInvalida {
        if (senha.isBlank() || senha.isEmpty()) {
            throw new SenhaInvalida("A senha não pode estar vazia.");
        } else if (!senha.matches(".*[a-zA-Z].*")) {
            throw new SenhaInvalida("A senha deve conter pelo menos uma letra.");
        } else if (!senha.matches(".*\\d.*")) {
            throw new SenhaInvalida("A senha deve conter pelo menos um número.");
        } else {
            return senha;
        }
    }
    public static boolean validarNome_Premium(String nome) throws NomeInvalido {
        if (nome.isBlank() || nome.isEmpty()) {
            throw new NomeInvalido("O nome não pode ser vazio ou em branco");
        } else if (nome.matches(".*\\d.*")) {
            throw new NomeInvalido("O nome não pode ter numeros");
        } else {
            return Boolean.parseBoolean(nome);
        }
    }

    public static boolean validarOpcao(String opcao) throws NomeInvalido {
        if (opcao.isBlank() || opcao.isEmpty()) {
            throw new NomeInvalido("O nome não pode ser vazio ou em branco");
        } else if (!opcao.matches("\\d+")) {
                throw new NomeInvalido("A opção deve conter apenas números");
            } else {
            return Boolean.parseBoolean(opcao);
        }
    }

    // MOSTRAR USUÁRIOS CADASTRADOS

    public static void mostrar_Usuarios(Scanner scanner, Usuario usuario) throws SQLException {

        Usuario_Premium_Dados_Repositorios nomes_usuarios = new Usuario_Premium_Dados_Repositorios();
        Usuario_Premium_Dados_Repositorios.listar_Usuarios();
    }



}



