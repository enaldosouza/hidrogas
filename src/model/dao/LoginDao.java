package model.dao;

import connection.ConnectionFactory;
import model.bean.UsuarioLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginDao {
    
    private String sql = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con;
    UsuarioLogin login = new UsuarioLogin();
    String tipo_usr = "";
    String nome_usr = "";
    String senha_usr = "";
    
    public LoginDao(){
//        UIManager.getDefaults().put("OptionPane.background",new Color(217,80,210));
//        UIManager.put ("Panel.background", new Color(217,80,210));
    }
    
    public boolean cadastrarLogin(UsuarioLogin login){ 
//        cria optionframe para entrada de dados
//        String nome =  JOptionPane.showInputDialog("Informe seu nome");
        
        sql = "INSERT INTO usuario_login(nome_usr, senha_usr, tipo_usr) VALUES (? , ?, ?)";
        
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, login.getNome_usr());
            ps.setString(2, login.getSenha_usr());
            ps.setString(3, login.getTipo_usr());
            ps.executeUpdate();

//            chama classe para alterar cor do JOptionPane
//            Mensagem msg = new Mensagem();
//            msg.showMessageDialog(null, "Usuário cadastrado com Sucesso!", "Confirmação", JOptionPane.PLAIN_MESSAGE);
            
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com Sucesso!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return true;
        }catch (SQLException sqle) {
            String sqlState = sqle.getSQLState();
            if(sqlState.equals("23505")){ 
                JOptionPane.showMessageDialog(null, "Usuário já cadastrado!", "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Falha na Conexão: " + sqle);
//                JOptionPane.showMessageDialog(null, "Falha na Conexão!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
        return false;
    }
    
    public boolean verificarLogin(UsuarioLogin login){ 
        sql = "SELECT * FROM usuario_login WHERE nome_usr = " + '?' + " and senha_usr = " + '?';
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, login.getNome_usr());
            ps.setString(2, login.getSenha_usr());
            rs = ps.executeQuery(); 
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Usuário ou Senha incorretos!", "Aviso", 
                JOptionPane.WARNING_MESSAGE);
                return false;
            }
            tipo_usr  = rs.getString("tipo_usr");
            nome_usr  = rs.getString("nome_usr");
            senha_usr = rs.getString("senha_usr");
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha na Conexão! " + e);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, ps, rs);
        }
    }
      
    public String toStringTipo(){
        return tipo_usr;
    }
    
    public String toStringNome(){
        return nome_usr;
    }
    
    public String toStringSenha(){
        return senha_usr;
    }
        
    public List<UsuarioLogin> listaUsuariosLogin(){
        con = ConnectionFactory.getConnetion();
        List<UsuarioLogin> usuarios = new ArrayList<>();
        try{
            ps = con.prepareStatement("SELECT * FROM usuario_login");
            rs = ps.executeQuery();
            while(rs.next()){
                UsuarioLogin usuario = new UsuarioLogin();
                usuario.setNome_usr(rs.getString("nome_usr"));
                usuario.setSenha_usr(rs.getString("senha_usr"));
                usuario.setTipo_usr(rs.getString("tipo_usr"));
                usuarios.add(usuario);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha na Conexão! " + e);
        }finally{
            ConnectionFactory.closeConnection(con, ps, rs);
        }
        return usuarios;
    }
    
  public boolean atualizarLogin(UsuarioLogin login){ 
        sql = "UPDATE usuario_login SET senha_usr = ?, tipo_usr = ? WHERE nome_usr = ?";
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, login.getSenha_usr());
            ps.setString(2, login.getTipo_usr());
            ps.setString(3, login.getNome_usr());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com Sucesso!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return true;
        }catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Falha na Conexão: " + sqle);
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
        return false;
    }  

    public void apagarLogin(UsuarioLogin login){
        sql = "DELETE FROM usuario_login where nome_usr = ?";
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, login.getNome_usr());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "Aviso", 
            JOptionPane.WARNING_MESSAGE);
          }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Usuário não excluído! " + e, "Aviso", 
            JOptionPane.WARNING_MESSAGE);
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
    }
}
