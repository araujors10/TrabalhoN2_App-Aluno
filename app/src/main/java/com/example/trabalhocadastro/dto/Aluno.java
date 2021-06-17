package com.example.trabalhocadastro.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno implements Serializable {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private Avaliacoes avaliacoes;

    public String toString(){
        return nome;
    }
}
