package servicos;

import java.util.Scanner;

import acesso_dados.Excecao_Dados;
import acesso_dados.Usuario_dados;
import entidade.Usuario_Login;
import excecoes.EmailInvalido;
import excecoes.NomeInvalido;
import excecoes.SenhaInvalida;
import interfacea.Iusuario;

public class Usuario_Servico implements Iusuario {

	// MENU DE LOGIN OU REGISTRO:
	@Override
	public void menu(Scanner scannerLogin) throws Excecao_Dados {
		int opcao = 0;

		System.out.print("""
				POPCORN

				1.LOGIN
				2.CADASTRO

				OPÇÃO:""");

		opcao = scannerLogin.nextInt();
		scannerLogin.nextLine();

		switch (opcao) {
		case 1:
			login(scannerLogin);
			break;
		case 2:
			cadastro(scannerLogin);
			break;
		default:
			System.out.println("ERRO,TENTE NOVAMENTE");
			menu(scannerLogin);
			break;
		}

	}

	// MENU DE LOGIN
	private void login(Scanner scannerLogin) throws Excecao_Dados {

		String email, senha;

		try {
			System.out.print("\nEMAIL: ");
			email = validarEmail(scannerLogin.nextLine().trim());

			System.out.print("SENHA: ");
			senha = validarSenha(scannerLogin.nextLine().trim());

			Usuario_dados usuarioDados = new Usuario_dados();

			Usuario_Login usuarioLogin = usuarioDados.Verificacao_Login(email, senha);

			new Menu_Servico().menu_geral(scannerLogin, usuarioLogin);
			
		} catch (EmailInvalido erroEmail) {
			System.out.println(erroEmail.getMessage());
			login(scannerLogin);
		} catch (SenhaInvalida erroSenha) {
			System.out.println(erroSenha.getMessage());
			login(scannerLogin);
		}

	}

	// CADASTRO USUÁRIO
	public void cadastro(Scanner scanner) {

		System.out.println("\nCADASTRO DE USUÁRIO\n");

		String nome, email, senha, confirmacaoDeSenha;

		try {
			System.out.println("NOME: ");
			nome = validarNome(scanner.nextLine().trim());
			System.out.println("EMAIL: ");
			email = validarEmail(scanner.nextLine().trim());
			System.out.println("SENHA: ");
			senha = validarSenha(scanner.nextLine().trim());
			System.out.println("CONFIRME SUA SENHA: ");
			confirmacaoDeSenha = confirmaçãoSenha(senha, scanner.nextLine().trim());

			Usuario_Login adicionar_usuario = new Usuario_Login(senha, email, nome);
			new Usuario_dados().Cadastro_Usuario(adicionar_usuario);

			new Menu_Servico().menu_geral(scanner, adicionar_usuario);;
			

		} catch (SenhaInvalida erro) {
			System.out.println(erro.getMessage());
			cadastro(scanner);
		} catch (EmailInvalido erro) {
			System.out.println(erro.getMessage());
			cadastro(scanner);
		} catch (NomeInvalido e) {
			System.out.println(e.getMessage());
			cadastro(scanner);
		}

	}
	
	//Perfil
	

	// VALIDAR EMAIL,SENHA E NOME
	public String validarEmail(String email) throws EmailInvalido {

		if (email.isBlank() || email.isEmpty()) {
			throw new EmailInvalido();
		} else if (email.contains("@") && email.contains(".") && email.contains("com")) {
			return email;
		} else {
			throw new EmailInvalido();
		}

	}

	public String validarSenha(String senha) throws SenhaInvalida {
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

	public String validarNome(String nome) throws NomeInvalido {
		if (nome.isBlank() || nome.isEmpty()) {
			throw new NomeInvalido("O nome não pode ser vazio ou em branco");
		} else if (nome.matches(".*\\d.*")) {
			throw new NomeInvalido("O nome não pode ter numeros");
		} else {
			return nome;
		}
	}

	public String confirmaçãoSenha(String senha, String confirmacaoSenha) throws SenhaInvalida {
		if (confirmacaoSenha.equals(senha)) {
			return confirmacaoSenha;
		}
		throw new SenhaInvalida("As senhas são diferentes");
	}
}
