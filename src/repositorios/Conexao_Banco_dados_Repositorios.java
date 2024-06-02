package repositorios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_Banco_dados_Repositorios {

    static Connection conectar = null;

    public static Connection conexao_Banco() {
        try {
            final String url = "jdbc:mysql://localhost:3306/popcorn_geral";
            final String user = "Andinho";
            final String password = "Waud3r1@1@";

            conectar = DriverManager.getConnection(url,user,password);
            return conectar;

        } catch (SQLException erro) {
            erro.printStackTrace();
            return null;
        }
    }
}

