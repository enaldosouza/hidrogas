
package model.bean;

public class UsuarioLogin {
    
    private static UsuarioLogin instance = null;    
    
    private String nome_usr;
    private String senha_usr;
    private String tipo_usr;

    public void UsuarioLogin(){
    
    }
    
    public String getNome_usr() {
        return nome_usr;
    }

    public void setNome_usr(String nome_usr) {
        this.nome_usr = nome_usr;
    }

    public String getSenha_usr() {
        return senha_usr;
    }

    public void setSenha_usr(String senha_usr) {
        this.senha_usr = senha_usr;
    }

    public String getTipo_usr() {
        return tipo_usr;
    }

    public void setTipo_usr(String tipo_usr) {
        this.tipo_usr = tipo_usr;
    }
    
    public static UsuarioLogin getInstance(){
      if(instance == null){
            instance = new UsuarioLogin();
      }
     return instance;
   }
    
    //método sobrescrito  
    public String toStringTipo() {  
      return getTipo_usr();  
    }
    
    public String toStringNome() {  
      return getNome_usr();  
    }
    
    public String toStringSenha() {  
      return getSenha_usr();  
    }
}
