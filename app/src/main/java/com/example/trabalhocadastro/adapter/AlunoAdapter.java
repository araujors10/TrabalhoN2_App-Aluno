package com.example.trabalhocadastro.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhocadastro.R;
import com.example.trabalhocadastro.dto.Aluno;

import java.util.List;

public class AlunoAdapter extends BaseAdapter {

    private List<Aluno> alunos;
    private Activity activity;

    public AlunoAdapter(Activity activity, List<Aluno> alunos){
        this.activity = activity;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        //Quantos alunos tem na lista
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        //Qual o item que ele vai devolver, o aluno na posição que ele pegou
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    //Aqui nesse trecho do codigo que tudo faz acontecer
    @Override
    public View getView(int i, View view, ViewGroup viewgroup) {
        //Criar um item novo para cada elemento
        View v = activity.getLayoutInflater().inflate(R.layout.item, viewgroup, false);

        //Variaveis do layout item.xml
        TextView nome = v.findViewById(R.id.txt_nome);
        TextView cpf = v.findViewById(R.id.txt_cpf);
        TextView telefone = v.findViewById(R.id.txt_telefone);

        TextView rua = v.findViewById(R.id.txt_nome); //TODO Adicionar txt_rua
        TextView numero = v.findViewById(R.id.txt_cpf); //TODO Adicionar txt_cpf
        TextView bairro = v.findViewById(R.id.txt_telefone); //TODO Adicionar txt_telefone

        TextView trabalho1 = v.findViewById(R.id.txt_nome); //TODO Adicionar txt_trabalho1
        TextView trabalho2 = v.findViewById(R.id.txt_cpf); //TODO Adicionar txt_trabalho2
        TextView avaliacao1 = v.findViewById(R.id.txt_telefone); //TODO Adicionar avaliacao1

        Aluno a = alunos.get(i);
        nome.setText(a.getNome());
        cpf.setText(a.getCpf());
        telefone.setText(a.getTelefone());

        rua.setText(a.getEndereco().getRua());
        numero.setText(a.getEndereco().getNumero());
        bairro.setText(a.getEndereco().getBairro());

        trabalho1.setText(Double.toString(a.getAvaliacoes().getTrabalho1()));
        trabalho2.setText(Double.toString(a.getAvaliacoes().getTrabalho2()));
        avaliacao1.setText(Double.toString(a.getAvaliacoes().getAvaliacao1()));
        return v;
    }
}