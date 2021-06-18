package com.example.trabalhocadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhocadastro.db.Conexao;
import com.example.trabalhocadastro.dto.Aluno;
import com.example.trabalhocadastro.dto.Avaliacoes;
import com.example.trabalhocadastro.dto.Endereco;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public AlunoDAO(Context context) {
        //Criei banco de dados e do banco de dados chamei a conexao
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    //Metodo para inserir aluno
    public long inserir(Aluno aluno) {

        //Criei um ContentValues que são os valores que eu vou inserir na tabela (como se fosse um mapa)
        ContentValues values = getContentValues(aluno);

        //o tipo do metodo esta como long, pq o metodo inserir devolve pra mim o id do aluno que foi inserido
        return banco.insert("aluno", null, values);
    }

    //Metodo para consultar no banco de dados
    public List<Aluno> obterTodos() {

        //Adicionar os alunos que foram encontrados
        List<Aluno> alunos = new ArrayList<>();

        //Criar consulta (cursor - como se fosse um ponteiro apontando para uma tabela)
        //Query aqui é como se fosse um consulta padrão, select * from
        Cursor cursor = banco.query("Aluno", new String[]{"id", "nome", "cpf", "telefone", "rua", "numero", "bairro", "trabalho1", "trabalho2", "avaliacao1"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            cursor.getString(3);

            a.setEndereco(new Endereco());
            a.getEndereco().setRua(cursor.getString(4));
            a.getEndereco().setNumero(cursor.getString(5));
            a.getEndereco().setBairro(cursor.getString(6));

            a.setAvaliacoes(new Avaliacoes());
            a.getAvaliacoes().setTrabalho1(cursor.getDouble(7));
            a.getAvaliacoes().setTrabalho2(cursor.getDouble(8));
            a.getAvaliacoes().setAvaliacao1(cursor.getDouble(9));
            alunos.add(a);
        }

        return alunos;
    }

    //Metodo para excluir aluno
    public void excluir(Aluno a) {

        //No lugar do meu interrogação '?' aqui ele passa o meu {a.getId().toString()}
        banco.delete("aluno", "id = ?", new String[]{a.getId().toString()});
    }

    //Metodo para atualizar aluno
    public void atualizar(Aluno aluno) {

        //Criei um ContentValues que são os valores que eu vou atualizar na tabela
        ContentValues values = getContentValues(aluno);

        //No lugar do meu interrogação '?' aqui ele passa o meu {a.getId().toString()}
        banco.update("aluno", values,
                "id = ?", new String[]{aluno.getId().toString()});
    }

    private ContentValues getContentValues(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        values.put("rua", aluno.getEndereco().getRua());
        values.put("numero", aluno.getEndereco().getNumero());
        values.put("bairro", aluno.getEndereco().getBairro());
        values.put("trabalho1", aluno.getAvaliacoes().getTrabalho1());
        values.put("trabalho2", aluno.getAvaliacoes().getTrabalho2());
        values.put("avaliacao1", aluno.getAvaliacoes().getAvaliacao1());
        return values;
    }

}
