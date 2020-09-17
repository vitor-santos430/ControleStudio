package com.example.controlestudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlestudio.bancodedados.BancoDeDados;

public class EditarGasto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_gasto);


        BancoDeDados bancoDeDados = new BancoDeDados(getApplicationContext());
        final Cursor cursor = bancoDeDados.consultarGastoPeloId(this.getIntent().getIntExtra("id",0));

        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText valor = (EditText) findViewById(R.id.campoValor);
        EditText data = (EditText) findViewById(R.id.campoData);

        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        valor.setText(cursor.getString(cursor.getColumnIndexOrThrow("valor")));
        data.setText(cursor.getString(cursor.getColumnIndexOrThrow("data_gasto")));
    }

    public void voltar(View v){
        Intent startNewActivity = new Intent(this,MainActivity.class);
        startActivity(startNewActivity);
    }


    public void atualizarGasto(View v){
        BancoDeDados bancoDeDados = new BancoDeDados(getApplicationContext());
        EditText titulo = (EditText)findViewById(R.id.campoTitulo);
        EditText valor = (EditText)findViewById(R.id.campoValor);
        EditText data = (EditText)findViewById(R.id.campoData);

        try{
            bancoDeDados.atualizarGasto(this.getIntent().getIntExtra("id",0), titulo.getText().toString(), new Float(valor.getText().toString()).floatValue(),data.getText().toString());
            Toast.makeText(getApplicationContext(), "Gasto atualizada com sucesso!", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Não foi possível atualizar o gasto, tente novamente!", Toast.LENGTH_LONG).show();
        }
        voltar(v);
    }

    public void excluirGasto(View v){
        BancoDeDados bancoDeDados = new BancoDeDados(getApplicationContext());
        EditText titulo = (EditText)findViewById(R.id.campoTitulo);
        EditText valor = (EditText)findViewById(R.id.campoValor);
        EditText data = (EditText)findViewById(R.id.campoData);

        try{
            bancoDeDados.excluiGasto(this.getIntent().getIntExtra("id",0));
            Toast.makeText(getApplicationContext(), "Gasto excluida com sucesso!", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Não foi possível excluir o gasto, tente novamente!", Toast.LENGTH_LONG).show();
        }
        voltar(v);
    }
}
