
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ConnectionFactory {
    
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL    = "jdbc:postgresql://localhost:5432/projeto-tom";
    private static final String USER   = "postgres";
    private static final String PASS   = "postgres";
    
    public static Connection getConnetion(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch(Exception e){
                throw new RuntimeException("Falha na Conexão com o Banco: " + e);
        }
    }
    
    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
        }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Falha ao fechar Conexão: " + e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stm){
        try{
            if(con != null){
                closeConnection(con);
            }
            if( con != null || stm != null){
                stm.close();            
            }
        }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Falha ao fechar Conexão: " + e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stm, ResultSet rs){
        try{
            if(con != null || stm != null){
                closeConnection(con, stm);
            }    
            if(rs != null){   
                rs.close();
            }
        }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Falha ao fechar Conexão: " + e);
        }
    }
}
