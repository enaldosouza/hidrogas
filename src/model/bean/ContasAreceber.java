
package model.bean;

public class ContasAreceber {
    
    private Integer orcamento_cliente_pessoa_cod_pessoa;
    private Integer orcamento_cod_orc;
    private Integer cliente_pessoa_cod_pessoa;
    private String descricao_cnt_receber;
    private Integer nro_parcelas;

    public Integer getOrcamento_cliente_pessoa_cod_pessoa() {
        return orcamento_cliente_pessoa_cod_pessoa;
    }

    public void setOrcamento_cliente_pessoa_cod_pessoa(Integer orcamento_cliente_pessoa_cod_pessoa) {
        this.orcamento_cliente_pessoa_cod_pessoa = orcamento_cliente_pessoa_cod_pessoa;
    }

    public Integer getOrcamento_cod_orc() {
        return orcamento_cod_orc;
    }

    public void setOrcamento_cod_orc(Integer orcamento_cod_orc) {
        this.orcamento_cod_orc = orcamento_cod_orc;
    }

    public Integer getCliente_pessoa_cod_pessoa() {
        return cliente_pessoa_cod_pessoa;
    }

    public void setCliente_pessoa_cod_pessoa(Integer cliente_pessoa_cod_pessoa) {
        this.cliente_pessoa_cod_pessoa = cliente_pessoa_cod_pessoa;
    }

    public String getDescricao_cnt_receber() {
        return descricao_cnt_receber;
    }

    public void setDescricao_cnt_receber(String descricao_cnt_receber) {
        this.descricao_cnt_receber = descricao_cnt_receber;
    }

    public Integer getNro_parcelas() {
        return nro_parcelas;
    }

    public void setNro_parcelas(Integer nro_parcelas) {
        this.nro_parcelas = nro_parcelas;
    }
    
}
