package com.example.trabalhocadastro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public AlunoDAO(Context context){
        //Criei banco de dados e do banco de dados chamei a conexao
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    //Metodo para inserir aluno
    public long inserir(Aluno aluno){
        //Criei um ContentValues que são os valores que eu vou inserir na tabela (como se fosse um mapa)
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        //o tipo do metodo esta como long, pq o metodo inserir devolve pra mim o id do aluno que foi inserido
        return banco.insert("aluno", null, values);
    }

    //Metodo para consultar no banco de dados
    public List<Aluno> obterTodos(){
        //Adicionar os alunos que foram encontrados
        List<Aluno> alunos = new ArrayList<>();
        //Criar consulta (cursor - como se fosse um ponteiro apontando para uma tabela)
        //Query aqui é como se fosse um consulta padrão, select * from
        Cursor cursor = banco.query("Aluno", new String[]{"id", "nome", "cpf", "telefone"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            alunos.add(a);
        }
        return alunos;
    }

    //Metodo para excluir aluno
    public void excluir(Aluno a){
        //No lugar do meu interrogação '?' aqui ele passa o meu {a.getId().toString()}
        banco.delete("aluno", "id = ?", new String[]{a.getId().toString()});
    }

    //Metodo para atualizar aluno
    public void atualizar(Aluno aluno){
        //Criei um ContentValues que são os valores que eu vou atualizar na tabela
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        //No lugar do meu interrogação '?' aqui ele passa o meu {a.getId().toString()}
        banco.update("aluno", values,
                "id = ?", new String[]{aluno.getId().toString()});
    }

}
