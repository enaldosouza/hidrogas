
package model.dao;
 
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cidade;

/**
 * @author enaldo.souza <enaldo@unitri.edu.br>
 */
public class CidadeDao {

    private String sql = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con;
    
    Cidade cidade = new Cidade();
    
    public void CidadeDao(){
    
    }
    
    public boolean create(Cidade cidade){
    
        sql = "insert into cidade (desc_cidade, estado_cidade) values(?,?)";
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cidade.getDesc_cidade());
            ps.setString(2, cidade.getEstado_cidade());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cidade cadastrada com Sucesso!",
            "Aviso", JOptionPane.WARNING_MESSAGE);
            return true;
            
        }catch (SQLException sqle) {
            String sqlState = sqle.getSQLState();
            if(sqlState.equals("23505")){ 
                JOptionPane.showMessageDialog(null, "Cidade já cadastrada!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Falha na Conexão:  " + sqle);
            }
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
        return false;
    }
    
    public boolean update(Cidade cidade){ 
        sql = "UPDATE cidade SET desc_cidade = ?, estado_cidade = ? WHERE cod_cidade = ?";
        con = ConnectionFactory.getConnetion();
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cidade.getDesc_cidade());
            ps.setString(2, cidade.getEstado_cidade());
            ps.setInt(3, Integer.parseInt(cidade.getCod_cidade()));
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cidade atualizada com Sucesso!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return true;
        }catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha na Conexão: " + sqle);
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
        return false;
    } 
    
        public void delete(Cidade cidade){
        sql = "DELETE FROM cidade where cod_cidade = ?";
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(cidade.getCod_cidade()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cidade excluída com sucesso!", "Aviso", 
            JOptionPane.WARNING_MESSAGE);
          }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Cidade não excluída! " + e, "Aviso", 
            JOptionPane.WARNING_MESSAGE);
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
    }
    
    public List<Cidade> listaCidades(){
        con = ConnectionFactory.getConnetion();
        List<Cidade> cidades = new ArrayList<>();
        try{
            ps = con.prepareStatement("SELECT * FROM cidade");
            rs = ps.executeQuery();
            while(rs.next()){
                Cidade cidade = new Cidade();
                cidade.setCod_cidade(rs.getString("cod_cidade"));
                cidade.setDesc_cidade(rs.getString("desc_cidade"));
                cidade.setEstado_cidade(rs.getString("estado_cidade"));
                cidades.add(cidade);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha na Conexão! " + e);
        }finally{
            ConnectionFactory.closeConnection(con, ps, rs);
        }
        return cidades;
    }
}
