package com.example.trabalhocadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class CadastroAlunoActivity extends AppCompatActivity {

    //Criar atributos para associar com os campos edit
    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private TextView cep;
    private TextView logradouro;
    private TextView complemento;
    private TextView bairro;
    private TextView localidade;
    private TextView estado;

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

        cep = findViewById(R.id.editCep);
        logradouro = findViewById(R.id.txtLogradouro);
        complemento = findViewById(R.id.txtComplemento);
        bairro = findViewById(R.id.txtBairro);
        localidade = findViewById(R.id.txtLocalidade);
        estado = findViewById(R.id.txtUf);

        Button botao = findViewById(R.id.btnBuscarCep);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cep.getText().toString().length()>0 && !cep.getText().toString().equals("") && cep.length() == 8){
                    HTTPService service = new HTTPService(cep.getText().toString());

                    try {
                        Aluno retorno = service.execute().get();
                        logradouro.setText(retorno.getLogradouro());
                        complemento.setText(retorno.getComplemento());
                        bairro.setText(retorno.getBairro());
                        localidade.setText(retorno.getLocalidade());
                        estado.setText(retorno.getUf());

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    //Mensagem simples
                    Toast.makeText(CadastroAlunoActivity.this, "CEP invalido!",
                            Toast.LENGTH_LONG).show();
                    logradouro.setText("");
                    complemento.setText("");
                    bairro.setText("");
                    localidade.setText("");
                    estado.setText("");
                    return;
                }
            }
        });

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

            cep.setText(aluno.getCep());
            logradouro.setText(aluno.getLogradouro());
            complemento.setText(aluno.getComplemento());
            bairro.setText(aluno.getBairro());
            localidade.setText(aluno.getLocalidade());
            estado.setText(aluno.getUf());
        }
    }

    public void salvar(View view){

        if (aluno == null){
            //Criando objeto aluno (preencher eles com osvalores dos campos de texto)
            aluno = new Aluno();
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());

            aluno.setCep(cep.getText().toString());
            aluno.setLogradouro(logradouro.getText().toString());
            aluno.setComplemento(complemento.getText().toString());
            aluno.setBairro(bairro.getText().toString());
            aluno.setLocalidade(localidade.getText().toString());
            aluno.setUf(estado.getText().toString());

            //Criei uma variavel só pra receber o id (long), apenas para ver se funcionou
            long id = dao.inserir(aluno);
            //Mensagem simples
            Toast.makeText(this, "Aluno inserido com sucesso! id: " + id, Toast.LENGTH_SHORT).show();
        }else{
            //Editar o aluno
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());

            aluno.setCep(cep.getText().toString());
            aluno.setLogradouro(logradouro.getText().toString());
            aluno.setComplemento(complemento.getText().toString());
            aluno.setBairro(bairro.getText().toString());
            aluno.setLocalidade(localidade.getText().toString());
            aluno.setUf(estado.getText().toString());

            dao.atualizar(aluno);
            //Mensagem simples
            Toast.makeText(this, "Aluno ATUALIZADO com sucesso!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}