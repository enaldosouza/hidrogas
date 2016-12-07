
package model.bean;

public class Item {
    
    private Integer cod_item;
    private String des_item;
    private String un;
    private Double valor_unitario;
    private String gera_estoque;
    private Double qtd_estoque;
    private Double vlr_unit_compra;

    public Integer getCod_item() {
        return cod_item;
    }

    public void setCod_item(Integer cod_item) {
        this.cod_item = cod_item;
    }

    public String getDes_item() {
        return des_item;
    }

    public void setDes_item(String des_item) {
        this.des_item = des_item;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public String getGera_estoque() {
        return gera_estoque;
    }

    public void setGera_estoque(String gera_estoque) {
        this.gera_estoque = gera_estoque;
    }

    public Double getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Double qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public Double getVlr_unit_compra() {
        return vlr_unit_compra;
    }

    public void setVlr_unit_compra(Double vlr_unit_compra) {
        this.vlr_unit_compra = vlr_unit_compra;
    }
}
