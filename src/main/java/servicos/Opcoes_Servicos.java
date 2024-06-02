package servicos;

import entidade.Usuario;
import excecoes.EmailInvalido;
import excecoes.SenhaInvalida;

import java.util.Scanner;

import menus.Usuario_Premium_Menu;

public class Opcoes_Servicos {

    public static void retornar_menu(Scanner scanner, Usuario usuario) throws EmailInvalido, SenhaInvalida {
                Usuario_Premium_Menu.menu(scanner,usuario);
        }
    }
