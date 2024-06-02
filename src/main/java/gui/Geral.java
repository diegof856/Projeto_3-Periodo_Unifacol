package gui;

import java.text.ParseException;
import java.util.Scanner;
import excecoes.Excecao_Dados;
import interfacea.Iusuario;
import menus.Usuario_Menu;

public class Geral {

   public static void main(String[] args) throws ParseException {
      Scanner scanner = new Scanner(System.in);
      Iusuario usuario = new Usuario_Menu() {

      };
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
