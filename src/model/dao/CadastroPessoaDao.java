
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.PessoaJuridica;

/**
 * @author Enaldo
 */
public class CadastroPessoaDao {
    
    Pessoa        pessoa = new Pessoa();
    PessoaFisica pFisica = new PessoaFisica();  
    PessoaJuridica pJur  = new PessoaJuridica();
    
    private String sql            = null;
    private String sql2           = null;
    private PreparedStatement ps  = null;
    private PreparedStatement ps2 = null;
    private PreparedStatement ps3 = null;
    
    private ResultSet rs   = null;
    private ResultSet rs2  = null;
    private ResultSet rs3  = null;
    
    private Connection con;
    
    Integer ret_codPessoa = 0;
    
    public CadastroPessoaDao(){
    
    }

    public boolean createPessoaFisica(Pessoa pessoa, PessoaFisica pFisica) throws SQLException{
     
        sql = "insert into pessoa (cidade_cod_cidade, nome, tipo_logadouro, logradouro, "
              + "num_logradouro, bairro, cep, uf, telefone_res, telefone_com, celular, "
              + "tipo_pessoa, dt_cadastro) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        sql2 = "insert into pessoa_fisica (pessoa_cod_pessoa, cpf, rg, dt_nascimento, sexo) values(?,?,?,?,?)";        
        
        con = ConnectionFactory.getConnetion();
        con.setAutoCommit(false);

        try{
            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
            ps.setInt   (1,  pessoa.getCidadeCodCidade());
            ps.setString(2,  pessoa.getNome());
            ps.setString(3,  pessoa.getTipo_logradouro());
            ps.setString(4,  pessoa.getLogradouro());
            ps.setString(5,  pessoa.getNumLogradouro());
            ps.setString(6,  pessoa.getBairro());
            ps.setString(7,  pessoa.getCep());
            ps.setString(8,  pessoa.getUf());
            ps.setString(9,  pessoa.getTelefone_res());
            ps.setString(10, pessoa.getTelefone_com());
            ps.setString(11, pessoa.getCelular());
            ps.setString(12, "F");
            ps.setString(13, pessoa.getDt_cadastro());       
            ps.executeUpdate();
            
            // Recupera o id
            rs = ps.getGeneratedKeys();
            int id = 0;
            if(rs.next()){
                id = rs.getInt(1);
            }

            ps = con.prepareStatement(sql2);
            ps.setInt   (1, id);
            ps.setString(2, pFisica.getCpf());
            ps.setString(3, pFisica.getRg());
            ps.setString(4, pFisica.getDt_nascimento());
            ps.setString(5, pFisica.getSexo());
            ps.executeUpdate();
            
            con.commit();
            
            JOptionPane.showMessageDialog(null, "Pessoa Física cadastrada com Sucesso!",
            "Aviso", JOptionPane.WARNING_MESSAGE);
            
            return true;
            
        }catch (SQLException sqle) {
            String sqlState = sqle.getSQLState();
            if(sqlState.equals("23505")){ 
                JOptionPane.showMessageDialog(null, "Pessoa Física já cadastrada!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
                con.rollback();
            }else{
                JOptionPane.showMessageDialog(null, "Falha na Conexão:  " + sqle);
                con.rollback();
            }
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
        return false;
    }
    
    public boolean createPessoaJuridica(Pessoa pessoa, PessoaJuridica pJur) throws SQLException{
    
        sql = "insert into pessoa (cidade_cod_cidade, nome, tipo_logadouro, logradouro, "
              + "num_logradouro, bairro, cep, uf, telefone_com, celular, "
              + "tipo_pessoa, dt_cadastro) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        sql2 = "insert into pessoa_juridica (pessoa_cod_pessoa, cnpj, ie, im, nome_fantasia) values(?,?,?,?,?)";        
        
        con = ConnectionFactory.getConnetion();
        con.setAutoCommit(false);

        try{
            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
            ps.setInt   (1,  pessoa.getCidadeCodCidade());
            ps.setString(2,  "PESSOA JURIDICA");
            ps.setString(3,  pessoa.getTipo_logradouro());
            ps.setString(4,  pessoa.getLogradouro());
            ps.setString(5,  pessoa.getNumLogradouro());
            ps.setString(6,  pessoa.getBairro());
            ps.setString(7,  pessoa.getCep());
            ps.setString(8,  pessoa.getUf());            
            ps.setString(9,  pessoa.getTelefone_com());
            ps.setString(10, pessoa.getCelular());
            ps.setString(11, "J");
            ps.setString(12, pessoa.getDt_cadastro());       
            ps.executeUpdate();
            
            // Recupera o id
            rs = ps.getGeneratedKeys();
            int id = 0;
            if(rs.next()){
                id = rs.getInt(1);
            }

            ps = con.prepareStatement(sql2);
            ps.setInt   (1, id);
            ps.setString(2, pJur.getCnpj());
            ps.setString(3, pJur.getIe());
            ps.setString(4, pJur.getIm());
            ps.setString(5, pJur.getNome_fantasia());
     
            ps.executeUpdate();
            
            con.commit();
            
            JOptionPane.showMessageDialog(null, "Pessoa Jurídica cadastrada com Sucesso!",
            "Aviso", JOptionPane.WARNING_MESSAGE);
            
            return true;
            
        }catch (SQLException sqle) {
            String sqlState = sqle.getSQLState();
            if(sqlState.equals("23505")){ 
                JOptionPane.showMessageDialog(null, "Pessoa Jurídica já cadastrada!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
                con.rollback();
            }else{
                System.out.println("dentro do else e try");
                
                JOptionPane.showMessageDialog(null, "Falha na Conexão:  " + sqle);
                con.rollback();
                
                
            }
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
        return false;
    }
    
    public List<Pessoa> listaPessoas(){
        con = ConnectionFactory.getConnetion();
        List<Pessoa> pessoas = new ArrayList<>();
        try{
            ps  = con.prepareStatement("SELECT * FROM pessoa");
//            ps2 = con.prepareStatement("SELECT * FROM pessoa_fisica");
//            ps3 = con.prepareStatement("SELECT * FROM pessoa_juridica");
            rs = ps.executeQuery();
            while(rs.next()){
                pessoa.setCod_pessoa(rs.getInt("cod_pessoa"));
                pessoa.setCidadeCodCidade(rs.getInt("cidade_cod_cidade"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTipo_pessoa(rs.getString("tipo_pessoa"));
                pessoa.setTelefone_res(rs.getString("telefone_res"));
                pessoa.setTelefone_com(rs.getString("telefone_com"));
                pessoa.setCelular(rs.getString("celular"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setTipo_logradouro(rs.getString("tipo_logradouro"));
                pessoa.setNumLogradouro(rs.getString("num_logradouro"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setCep(rs.getString("cep"));
                pessoa.setUf(rs.getString("uf"));
                pessoa.setDt_cadastro(rs.getString("dt_cadastro"));
                        
                pessoas.add(pessoa);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha na Conexão! " + e);
        }finally{
            ConnectionFactory.closeConnection(con, ps, rs);
        }
        return pessoas;
    }    
    
    
    public Integer retornaCodPessoaDePessoa(){ 
        sql = "SELECT * FROM pessoa ORDER BY cod_pessoa DESC LIMIT 1";
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); 
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Código de pessoa não encontrado!", "Aviso", 
                JOptionPane.WARNING_MESSAGE);
                return 0;
            }
            ret_codPessoa  = rs.getInt("cod_pessoa");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha na Conexão! " + e);
        }finally{
            ConnectionFactory.closeConnection(con, ps, rs);
        }
        return ret_codPessoa;
    }
    
    public boolean apagaCadastroPessoa(int cod){
        sql = "DELETE FROM pessoa where cod_pessoa = cod";
        con = ConnectionFactory.getConnetion();
        try{
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
          }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Falha ao apagar código parcial de pessoa! " + e, "Aviso", 
            JOptionPane.WARNING_MESSAGE);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, ps);
        }
        return true;
    }
}
