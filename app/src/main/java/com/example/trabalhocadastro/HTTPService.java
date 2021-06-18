package com.example.trabalhocadastro;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void, Void, Aluno> {

    private final String cep;

    public HTTPService(String cep) { this.cep = cep; }

    //Responsavel por fazer todo o metodo de conexao
    @Override
    protected Aluno doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("https://viacep.com.br/ws/" + this.cep + "/json/");
            //Configurando HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            //Se ele demorar mais de 5 segundos pra estabelecer essa conexao
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()){
                //Pega a linha atual e joga dentro do nosso StringBuilder
                resposta.append(scanner.next());

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(resposta.toString(), Aluno.class);
    }
}
