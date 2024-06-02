package servicos;

import java.util.Scanner;

import entidade.Usuario_Login;
import excecoes.EntradaInvalida;
import repositorios.Usuario_repositorio;

public class Menu_Servico {

	// MENU PRINCIPAL
	public void menu_geral(Scanner scannerMenuGeral, Usuario_Login usuario) {

		System.out.printf("POPCORN%n Olá!! Bem vindo"+usuario.getNome()+ "%n%n 1.PERFIL%n2.CATÁLOGO%n3.USUÁRIOS%n%nOPÇÃO:");

		int opcao = scannerMenuGeral.nextInt();
		scannerMenuGeral.nextLine();

		switch (opcao) {
		case 1:
			System.out.println("""
					PERFIL

					AINDA NÃO ADICIONADO
					""");
			menu_geral(scannerMenuGeral,usuario);
			break;
		case 2:
			new Filme_servico().catalogo();;
			break;
		case 3:
			menu_usuario(scannerMenuGeral, usuario);
			break;

		default:
			System.out.println("\nERRO, TENTE NOVAMENTE\n");
			menu_geral(scannerMenuGeral,usuario);
		}
	}

	// MENU USUÁRIOS
	public void menu_usuario(Scanner ScannerUsuario, Usuario_Login usuario) {
		System.out.println("""

				LISTA DE USUÁRIOS

				""");
		// Usuario_dados.listar_usuario();

		// MENU DE OPÇÕES DE USÚARIOS

		System.out.println("""

				1.Quantidade de Usuario cadastrados
				2.REMOVER
				3.VOLTAR PARA O MENU

				OPÇÃO:
				""");

		int opcao_usuario = ScannerUsuario.nextInt();
		ScannerUsuario.nextLine();

		
	try {
		switch (opcao_usuario) {
		
		case 1:
			System.out.println("Quantidade de Usuarios");
		 new Usuario_repositorio().mostrarTamanhoDaFila(ScannerUsuario, usuario);;
				
			
			menu_usuario(ScannerUsuario, usuario);
		case 2:
			System.out.println("REMOVER USUÁRIO");
		case 3:
			System.out.println("VOLTAR MENU");
			menu_geral(ScannerUsuario, usuario);
		default:
		}

	
	
	}
	catch (EntradaInvalida e) {
		System.out.println(e.getMessage());
	}
	}
}
