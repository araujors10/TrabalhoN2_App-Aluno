package com.example.trabalhocadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAlunoActivity extends AppCompatActivity {

    //Criar atributos para associar com os campos edit
    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private AlunoDAO dao;

    //Variavel global
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        //Associar xml com os variaveis do java
        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        telefone = findViewById(R.id.editTelefone);

        dao = new AlunoDAO(this);

        //Pega a intenção - para editar o aluno
        Intent it = getIntent();
        //Se tem esse aluno na classe atualizar
        if (it.hasExtra("aluno")){
            //Pego a variavel aluno que criei la em cima e coloco dentro desse aluno o que veio na intenção
            //Então aluno vai estar disponivel pra mim dentro da variavel aluno
            //Faz isso tudo somente se veio um aluno na intenção
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome());
            cpf.setText(aluno.getCpf());
            telefone.setText(aluno.getTelefone());
        }
    }

    public void salvar(View view){

        if (aluno == null){
            //Criando objeto aluno (preencher eles com osvalores dos campos de texto)
            aluno = new Aluno();
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            //Criei uma variavel só pra receber o id (long), apenas para ver se funcionou
            long id = dao.inserir(aluno);
            //Mensagem simples
            Toast.makeText(this, "Aluno inserido com sucesso! id: " + id, Toast.LENGTH_SHORT).show();
        }else{
            //Editar o aluno
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            dao.atualizar(aluno);
            //Mensagem simples
            Toast.makeText(this, "Aluno ATUALIZADO com sucesso!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}