
package model.bean;

public class Orcamento {
   
    private Integer cod_orc;
    private Integer cliente_pessoa_cod_pessoa;
    private String dt_orc;
    private Integer dt_ger_srv;

    public Integer getCod_orc() {
        return cod_orc;
    }

    public void setCod_orc(Integer cod_orc) {
        this.cod_orc = cod_orc;
    }

    public Integer getCliente_pessoa_cod_pessoa() {
        return cliente_pessoa_cod_pessoa;
    }

    public void setCliente_pessoa_cod_pessoa(Integer cliente_pessoa_cod_pessoa) {
        this.cliente_pessoa_cod_pessoa = cliente_pessoa_cod_pessoa;
    }

    public String getDt_orc() {
        return dt_orc;
    }

    public void setDt_orc(String dt_orc) {
        this.dt_orc = dt_orc;
    }

    public Integer getDt_ger_srv() {
        return dt_ger_srv;
    }

    public void setDt_ger_srv(Integer dt_ger_srv) {
        this.dt_ger_srv = dt_ger_srv;
    }
}
