package interfacea;
import java.util.Scanner;

import acesso_dados.Excecao_Dados;
import excecoes.EmailInvalido;
import excecoes.SenhaInvalida;
public interface Iusuario{
    void menu(Scanner scannerMenu) throws EmailInvalido, Excecao_Dados, SenhaInvalida;
}

    

