package interfacea;
import java.util.Scanner;

import excecoes.EmailInvalido;
import excecoes.Excecao_Dados;
import excecoes.SenhaInvalida;
public interface Iusuario{
    void menu(Scanner scanner) throws EmailInvalido, Excecao_Dados, SenhaInvalida;
}

    

