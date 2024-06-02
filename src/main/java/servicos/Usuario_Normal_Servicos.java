package servicos;

import java.util.Scanner;
import entidade.Usuario;
import excecoes.EmailInvalido;
import excecoes.Excecao_Dados;
import excecoes.NomeInvalido;
import excecoes.SenhaInvalida;
import menus.Usuario_Premium_Menu;
import repositorios.Usuario_Premium_Dados_Repositorios;


public class Usuario_Normal_Servicos {



// Tipo de Usuário Cadastro

    // MENU DE LOGIN_NORMAL

    public static void login_normal(Scanner scannerLogin) throws Excecao_Dados {

        try {
            String email, senha,tipo;

            tipo = "normal";
            System.out.print("\nEMAIL: ");
            email = scannerLogin.nextLine().trim();

            System.out.print("SENHA: ");
            senha = scannerLogin.nextLine().trim();


            Usuario_Premium_Dados_Repositorios acesso_usuario = new Usuario_Premium_Dados_Repositorios();

            Usuario usuarioLogin = (Usuario) acesso_usuario.Verificacao_Login(email,senha,tipo);

            if (usuarioLogin != null) {

            } else {
                System.out.println("Usuário ou senha inválidos.");
                login_normal(scannerLogin);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // CADASTRO USUÁRIO NORMAL

        public static void cadastro_normal(Scanner scannerLogin) {

            System.out.println("\nCADASTRO DE USUÁRIO\n");

            String nome, email, senha, confirmacaoDeSenha, tipo;

            try {
                tipo = "normal";
                System.out.println("NOME: ");
                nome = validarNome(scannerLogin.nextLine().trim());
                System.out.println("EMAIL: ");
                email = validarEmail(scannerLogin.nextLine().trim());
                System.out.println("SENHA: ");
                senha = validarSenha(scannerLogin.nextLine().trim());
                System.out.println("CONFIRME SUA SENHA: ");
                confirmacaoDeSenha = validarSenha(scannerLogin.nextLine().trim());
                if (!senha.equals(confirmacaoDeSenha)) {
                    throw new SenhaInvalida("As senhas não correspondem.");
                }

//                Usuario adicionar_usuario = new Usuario(nome, email, senha, tipo);
//                new Usuario_Premium_Dados_Repositorios().Cadastro_Usuario(adicionar_usuario);

//                new Usuario_Premium_Menu().menu(scannerLogin,adicionar_usuario);
                ;

            } catch (SenhaInvalida erro) {
                System.out.println(erro.getMessage());
                cadastro_normal(scannerLogin);
            } catch (EmailInvalido erro) {
                System.out.println(erro.getMessage());
                cadastro_normal(scannerLogin);
            } catch (NomeInvalido e) {
                System.out.println(e.getMessage());
                cadastro_normal(scannerLogin);
            }

        }

        // VALIDAR EMAIL,SENHA E NOME

        public static String validarEmail(String email) throws EmailInvalido {

            if (email.isBlank() || email.isEmpty()) {
                throw new EmailInvalido();
            } else if (email.contains("@") && email.contains(".") && email.contains("com")) {
                return email;
            } else {
                throw new EmailInvalido();
            }

        }

        public static String validarSenha(String senha) throws SenhaInvalida {
            if (senha.isBlank() || senha.isEmpty()) {
                throw new SenhaInvalida("A senha não pode estar vazia.");
            } else if (!senha.matches(".*[a-zA-Z].*")) {
            }
            return senha;
        }

        public static String validarNome(String nome) throws NomeInvalido {
            if (nome.isBlank() || nome.isEmpty()) {
                throw new NomeInvalido("O nome não pode ser vazio ou em branco");
            } else if (nome.matches(".*\\d.*")) {
                throw new NomeInvalido("O nome não pode ter numeros");
            } else {
                return nome;
            }
        }

        public String confirmacaoSenha(String senha, String confirmacaoSenha) throws SenhaInvalida {
            if (confirmacaoSenha.equals(senha)) {
                return confirmacaoSenha;
            }
            throw new SenhaInvalida("As senhas são diferentes");
        }

}


