
package model.bean;

public class ParcelaContaReceber {
    
    private Integer nro_prc_receber;
    private Integer contas_a_receber_cod_cnt_receber;
    private String dt_vcto_prc;
    private String dt_rcto_prc;
    private Double valor_prc;

    public Integer getNro_prc_receber() {
        return nro_prc_receber;
    }

    public void setNro_prc_receber(Integer nro_prc_receber) {
        this.nro_prc_receber = nro_prc_receber;
    }

    public Integer getContas_a_receber_cod_cnt_receber() {
        return contas_a_receber_cod_cnt_receber;
    }

    public void setContas_a_receber_cod_cnt_receber(Integer contas_a_receber_cod_cnt_receber) {
        this.contas_a_receber_cod_cnt_receber = contas_a_receber_cod_cnt_receber;
    }

    public String getDt_vcto_prc() {
        return dt_vcto_prc;
    }

    public void setDt_vcto_prc(String dt_vcto_prc) {
        this.dt_vcto_prc = dt_vcto_prc;
    }

    public String getDt_rcto_prc() {
        return dt_rcto_prc;
    }

    public void setDt_rcto_prc(String dt_rcto_prc) {
        this.dt_rcto_prc = dt_rcto_prc;
    }

    public Double getValor_prc() {
        return valor_prc;
    }

    public void setValor_prc(Double valor_prc) {
        this.valor_prc = valor_prc;
    }
}
