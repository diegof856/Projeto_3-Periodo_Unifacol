package acesso_dados;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class Conexao_Banco_dados {

   private static Connection conectar = null;

    public static Connection conexao_Banco() {
    	if(conectar == null) {
    		try {
              Properties props = carregarPropriedades();
              String url = props.getProperty("acurl");

                conectar = DriverManager.getConnection(url,props);
              

            } catch (SQLException erro) {
                throw new Excecao_Dados(erro.getMessage());
            
    	}
    }
    	return conectar;
}
    public static void fecharConexao() {
    	try {
    		if(conectar != null) {
    			conectar.close();
    		}
    		
    	}
    	catch(SQLException e) {
    		throw new Excecao_Dados(e.getMessage());
    	}
    }
    private static Properties carregarPropriedades() {
    	try(FileInputStream fs = new FileInputStream("informacoesAcesso")){
    		Properties props = new Properties();
    		props.load(fs);
    		return props;
    	}
    	catch(IOException e) {
    		throw new Excecao_Dados(e.getMessage());
    	}
    }
    public static void fecharStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new Excecao_Dados(e.getMessage());
			}
		}
	}
    public static void fecharResultSet(ResultSet rs) {
  		if(rs != null) {
  			try {
  				rs.close();
  			} catch (SQLException e) {
  				throw new Excecao_Dados(e.getMessage());
  			}
  		}
  	}
	


	

		
		
	}


