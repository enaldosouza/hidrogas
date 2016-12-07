
package model.bean;

public class Pessoa {
    
    private Integer cod_pessoa;
    private Integer cidade_cod_cidade;
    private String  nome;
    private String  logradouro;
    private String  num_logradouro;
    private String  tipo_logradouro;
    private String  bairro;
    private String  cep;
    private String  uf;
    private String  telefone_res;
    private String  telefone_com;
    private String  celular;
    private String  tipo_pessoa;
    private String  dt_cadastro;
    private String  dt_desativacao;
    private String  senha;
    
    public Integer getCidadeCodCidade(){
        return cidade_cod_cidade;
    }

    public void setCidadeCodCidade(Integer cidade_cod_cidade){
        this.cidade_cod_cidade = cidade_cod_cidade;
    }
    
    public String getLogradouro(){
        return logradouro;
    }

    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }
    
    public String getNumLogradouro(){
        return num_logradouro;
    }

    public void setNumLogradouro(String num_logradouro){
        this.num_logradouro = num_logradouro;
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo_logradouro() {
        return tipo_logradouro;
    }

    public void setTipo_logradouro(String tipo_logradouro) {
        this.tipo_logradouro = tipo_logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone_res() {
        return telefone_res;
    }

    public void setTelefone_res(String telefone_res) {
        this.telefone_res = telefone_res;
    }

    public String getTelefone_com() {
        return telefone_com;
    }

    public void setTelefone_com(String telefone_com) {
        this.telefone_com = telefone_com;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public String getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(String dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public String getDt_desativacao() {
        return dt_desativacao;
    }

    public void setDt_desativacao(String dt_desativacao) {
        this.dt_desativacao = dt_desativacao;
    }    
    
    public String getSenha() {
        return senha;
    }

     public void setSenha(String senha) {
        this.senha = senha;
    }
     
    public String toString() {
        return this.nome;
    }

    public Integer getCod_pessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(Integer cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }
}
