
package model.bean;

public class ContasApagar {
   
    private Integer fornecedor_pessoa_cod_pessoa;
    private String descricao_cnt_pagar;
    private Integer nro_parcelas;

    public Integer getFornecedor_pessoa_cod_pessoa() {
        return fornecedor_pessoa_cod_pessoa;
    }

    public void setFornecedor_pessoa_cod_pessoa(Integer fornecedor_pessoa_cod_pessoa) {
        this.fornecedor_pessoa_cod_pessoa = fornecedor_pessoa_cod_pessoa;
    }

    public String getDescricao_cnt_pagar() {
        return descricao_cnt_pagar;
    }

    public void setDescricao_cnt_pagar(String descricao_cnt_pagar) {
        this.descricao_cnt_pagar = descricao_cnt_pagar;
    }

    public Integer getNro_parcelas() {
        return nro_parcelas;
    }

    public void setNro_parcelas(Integer nro_parcelas) {
        this.nro_parcelas = nro_parcelas;
    }
}
