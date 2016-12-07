
package model.bean;

public class Cidade {
    
    private String cod_cidade;
    private String desc_cidade;
    private String estado_cidade;
    
    public Cidade(){
    
    }
    
    public String getCod_cidade(){
        return cod_cidade;
    }

    public String getDesc_cidade() {
        return desc_cidade;
    }
    
    public String getEstado_cidade(){
        return estado_cidade;
    }

    public void setCod_cidade(String cod_cidade){
        this.cod_cidade = cod_cidade;
    }
    
    public void setDesc_cidade(String desc_cidade) {
        this.desc_cidade = desc_cidade;
    }
    
    public void setEstado_cidade(String estado_cidade){
        this.estado_cidade = estado_cidade;
    }
}
