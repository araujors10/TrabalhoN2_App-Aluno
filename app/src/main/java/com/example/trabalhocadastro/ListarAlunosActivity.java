package com.example.trabalhocadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarAlunosActivity extends AppCompatActivity {

    private ListView listView;
    private AlunoDAO dao;
    private List<Aluno> alunos;
    private List<Aluno> alunosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alunos);

        listView = findViewById(R.id.lista_alunos);
        dao = new AlunoDAO(this);
        //Obtem todos os alunos
        alunos = dao.obterTodos();
        //Obtem somente alunos filtrados
        alunosFiltrados.addAll(alunos);
        //Colocar os alunos dentro da minha listView
        //ArrayAdapter<Aluno> adaptador = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunosFiltrados);
        AlunoAdapter adaptador = new AlunoAdapter(this, alunosFiltrados);
        //Chamando o adaptador dentro do listView
        listView.setAdapter(adaptador);

        //Quando o listView (minha lista) for pressionado, ele vai abrir o contexto de menu (Excluir, Atualizar)
        registerForContextMenu(listView);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        //inflar o xml no menu e mostrar onde quero q ele apareca, que no meu caso é no menu mesmoo
        i.inflate(R.menu.menu_principal, menu);

        //SearchView esta recebendo o que foi digitado no menu pesquisar (lupa)
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        //Verificando se houve teclas pressionadas no menu pesquisar
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //Aqui passa o texto que a pessoa digitou
                System.out.println("Digitou.: " + s);
                procuraAluno(s);
                return false;
            }
        });

        return true;
    }

    //Sobrescrever o método da classe activity para chamar o menu excluir e atualizar
    //Coloca esse layout no menu de contexto
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    //Método que procura o objeto que possue naquela lista o texto que a pessoa digitou
    public void procuraAluno(String nome){
        //Limpar alunos  filtrados
        alunosFiltrados.clear();
        //Pra cada alunos que esta na minha lista de alunos filtrados
        for (Aluno a: alunos){
            //Compara tudo minusculo para não ter perigo de não achar se tiver maiusculo
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())){
                alunosFiltrados.add(a);
            }
        }
        //Invalido a lista para mostrar os dados atualizados
        listView.invalidateViews();
    }

    //Metodo excluir
    public void excluir(MenuItem item){
        //Pegando um menuInfo e passando ele para o adaptador
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //Consigo obter a posição do item na lista
        //menuInfo.position = posição do item clicado na lista (por exemplo.: 1, 2, 3 ...)
        //Pego o aluno e armazeno na lista alunoExcluir
        //final = porque não vou mudar o valor da variavel
        final Aluno alunoExcluir = alunosFiltrados.get(menuInfo.position);

        //Mensagem para ver se a pessoa quer mesmo excluir o aluno
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja EXCLUIR o aluno selecionado?")
                .setNegativeButton("NÂO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    //Se clicar em sim, ele vai executar a função abaixo
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alunosFiltrados.remove(alunoExcluir);
                        alunos.remove(alunoExcluir);
                        dao.excluir(alunoExcluir);
                        //Atualizar a lista
                        listView.invalidateViews();
                    }
                }).create();
        //Mostrar mensagem para a pessoa
        dialog.show();
    }

    //Criei esse cadastro para vincular com o xml do menu_principal
    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastroAlunoActivity.class);
        startActivity(it);
    }

    //Metodo atualizar
    public void atualizar(MenuItem item){
        //Pegando um menuInfo e passando ele para o adaptador
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //Consigo obter a posição do item na lista
        //menuInfo.position = posição do item clicado na lista (por exemplo.: 1, 2, 3 ...)
        //Pego o aluno e armazeno na lista alunoAtualizar
        //final = porque não vou mudar o valor da variavel
        final Aluno alunoAtualizar = alunosFiltrados.get(menuInfo.position);
        //Criar uma intenção
        Intent it = new Intent(this, CadastroAlunoActivity.class);
        it.putExtra("aluno", alunoAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        alunos = dao.obterTodos();
        //Limpar todos os dados de novo
        alunosFiltrados.clear();
        alunosFiltrados.addAll(alunos);
        //Invalidar os dados antigos
        listView.invalidateViews();
    }

}