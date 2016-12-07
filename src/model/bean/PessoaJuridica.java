
package model.bean;

public class PessoaJuridica {
    
    private Integer pessoa_cod_pessoa;
    private String cnpj;
    private String ie;
    private String im;
    private String nome_fantasia;

    public Integer getPessoa_cod_pessoa() {
        return pessoa_cod_pessoa;
    }

    public void setPessoa_cod_pessoa(Integer pessoa_cod_pessoa) {
        this.pessoa_cod_pessoa = pessoa_cod_pessoa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }
}
