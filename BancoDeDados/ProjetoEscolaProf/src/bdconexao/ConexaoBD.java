/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdconexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 


/**
 *
 * @author M
 */
public class ConexaoBD {
    //Atributos
    private static Connection conexao = null;
    //Metodos
    private ConexaoBD(){}
    
    public static Connection getConexao() throws Exception {
        try {
            if(conexao == null) {
                /*
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/usuario";
                String user = "root";
                String password = "";
                */
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/faculdade_BD";
                String user = "postgres";
                String password = "postgres";
            
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException erro) {
            //Erro de não encontrar o drive do banco no projeto
            throw new Exception("Erro no Drive do Banco de Dados: " + erro.getMessage());
        }
        catch (SQLException erro) {
            //Erro no banco de dados: usuario, senha ou banco de dados
            throw new Exception("Erro no Banco de Dados: " + erro.getMessage());
        }
        
        return conexao; 
    }
    
    public static void closeConexao() throws Exception {
        try {
            if(conexao != null) conexao.close();
        } catch (SQLException erro) {
           //Erro no banco de dados
            throw new Exception("Erro no Banco de Dados: " + erro.getMessage());
        }
    }
}
