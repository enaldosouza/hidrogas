
package model.bean;

public class PessoaFisica {
    
    private Integer pessoa_cod_pessoa;
    private String cpf;
    private String rg;
    private String dt_nascimento;
    private String sexo;

    public Integer getPessoa_cod_pessoa() {
        return pessoa_cod_pessoa;
    }

    public void setPessoa_cod_pessoa(Integer pessoa_cod_pessoa) {
        this.pessoa_cod_pessoa = pessoa_cod_pessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
