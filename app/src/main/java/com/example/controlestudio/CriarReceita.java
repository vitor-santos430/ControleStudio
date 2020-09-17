package com.example.controlestudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controlestudio.bancodedados.BancoDeDados;

public class CriarReceita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_receita);

        TextView titulo = (TextView) findViewById(R.id.text_title);
        titulo.setText("Adicione Aqui Valores Recebidos");
    }

    public void voltar(View v){
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void criarReceita(View v){
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText valor = (EditText) findViewById(R.id.campoValor);
        EditText data_receita = (EditText) findViewById(R.id.campoData);

        Float valorDouble = Float.parseFloat(valor.getText().toString());
        boolean resultado = bancoDeDados.criarReceitas(titulo.getText().toString(), valorDouble, data_receita.getText().toString());

        if(resultado){
            Toast.makeText(getApplicationContext(), "Receita salva com Sucesso!", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "Infelizmente ocorreu um erro, tente novamente.", Toast.LENGTH_SHORT).show();
        }
        voltar(v);
    }
}
