
package model.bean;

public class ItensOrcamento {
    
    private Integer orcamento_cliente_pessoa_cod_pessoa;
    private Integer prc_conta_receber_cod_item;
    private Integer orcamento_cod_orc;
    private Integer quantidade;
    private Double valor_unitario;

    public Integer getOrcamento_cliente_pessoa_cod_pessoa() {
        return orcamento_cliente_pessoa_cod_pessoa;
    }

    public void setOrcamento_cliente_pessoa_cod_pessoa(Integer orcamento_cliente_pessoa_cod_pessoa) {
        this.orcamento_cliente_pessoa_cod_pessoa = orcamento_cliente_pessoa_cod_pessoa;
    }

    public Integer getPrc_conta_receber_cod_item() {
        return prc_conta_receber_cod_item;
    }

    public void setPrc_conta_receber_cod_item(Integer prc_conta_receber_cod_item) {
        this.prc_conta_receber_cod_item = prc_conta_receber_cod_item;
    }

    public Integer getOrcamento_cod_orc() {
        return orcamento_cod_orc;
    }

    public void setOrcamento_cod_orc(Integer orcamento_cod_orc) {
        this.orcamento_cod_orc = orcamento_cod_orc;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
}
