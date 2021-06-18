package com.example.trabalhocadastro;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        TextView cep = v.findViewById(R.id.txt_cep);
        TextView endereco = v.findViewById(R.id.txt_endereco);

        Aluno a = alunos.get(i);
        nome.setText(a.getNome());
        cpf.setText(a.getCpf());
        telefone.setText(a.getTelefone());
        cep.setText(a.getCep());
        endereco.setText(a.getEndereco());
        return v;
    }
}