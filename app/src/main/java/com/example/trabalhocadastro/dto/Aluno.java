package com.example.trabalhocadastro.dto;

import java.io.Serializable;


public class Aluno implements Serializable {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private Avaliacoes avaliacoes;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Avaliacoes getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(Avaliacoes avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public String toString() {
        return nome;
    }
}
