package gui;

import java.text.ParseException;
import java.util.Scanner;

import acesso_dados.Excecao_Dados;
import interfacea.Iusuario;
import servicos.Usuario_Servico;

public class Geral {

	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		Iusuario usuario = new Usuario_Servico();
	try {
		usuario.menu(scanner);
	}
	
	catch (Excecao_Dados e) {
		System.out.print(e.getMessage());
	}
	catch(Exception e) {
		System.out.print(e.getMessage());
	}
		finally {
			scanner.close();
		}
		
		

	}

}
