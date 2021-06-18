package com.example.trabalhocadastro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao( Context context ) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criando tabela no banco de dados
        db.execSQL("create table aluno(id integer primary key autoincrement, "+
                "nome varchar(50), cpf varchar(50), telefone varchar(50)," +
                "cep varchar(50), logradouro varchar(50), complemento varchar(50)," +
                "bairro varchar(50), localidade varchar(50), estado varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
