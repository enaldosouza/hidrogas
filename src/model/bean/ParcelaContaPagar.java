
package model.bean;

public class ParcelaContaPagar {
   
    private Integer nro_prc_pagar;
    private Integer contas_a_receber_cod_cnt_pagar;
    private String dt_vcto_prc;
    private String dt_pagto_prc;
    private Double valor_prc;

    public Integer getNro_prc_pagar() {
        return nro_prc_pagar;
    }

    public void setNro_prc_pagar(Integer nro_prc_pagar) {
        this.nro_prc_pagar = nro_prc_pagar;
    }

    public Integer getContas_a_receber_cod_cnt_pagar() {
        return contas_a_receber_cod_cnt_pagar;
    }

    public void setContas_a_receber_cod_cnt_pagar(Integer contas_a_receber_cod_cnt_pagar) {
        this.contas_a_receber_cod_cnt_pagar = contas_a_receber_cod_cnt_pagar;
    }

    public String getDt_vcto_prc() {
        return dt_vcto_prc;
    }

    public void setDt_vcto_prc(String dt_vcto_prc) {
        this.dt_vcto_prc = dt_vcto_prc;
    }

    public String getDt_pagto_prc() {
        return dt_pagto_prc;
    }

    public void setDt_pagto_prc(String dt_pagto_prc) {
        this.dt_pagto_prc = dt_pagto_prc;
    }

    public Double getValor_prc() {
        return valor_prc;
    }

    public void setValor_prc(Double valor_prc) {
        this.valor_prc = valor_prc;
    }
}
