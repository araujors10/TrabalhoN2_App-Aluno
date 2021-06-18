package com.example.trabalhocadastro;

import java.io.Serializable;

public class Aluno implements Serializable {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;

    //Chamando cep via http
    private String logradouro;
    private String complemento;
    private String cep;
    private String bairro;
    private String localidade;
    private String uf;

    public String getLocalidade() { return localidade; }

    public void setLocalidade(String localidade) { this.localidade = localidade; }

    public String getUf() { return uf; }

    public void setUf(String uf) { this.uf = uf; }

    public String getLogradouro() { return logradouro; }

    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getComplemento() { return complemento; }

    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getCep() { return cep; }

    public void setCep(String cep) { this.cep = cep; }

    public String getBairro() { return bairro; }

    public void setBairro(String bairro) { this.bairro = bairro; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toString(){ return nome; }

    /*public String toString(){
        return  "CEP: " + getCep()
                +"\nLogradouro: " + getLogradouro()
                +"\nComplemento: " + getComplemento()
                +"\nBairro: " + getBairro()
                +"\nCidade: " + getLocalidade()
                +"\nEstado: " + getUf();
    }*/
}
