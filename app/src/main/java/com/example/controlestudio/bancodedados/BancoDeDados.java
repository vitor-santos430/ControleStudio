package com.example.controlestudio.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDeDados{
    public SQLiteDatabase banco;
    public GerenciarBanco gerenciarBanco;

    public BancoDeDados(Context context){
        gerenciarBanco = new GerenciarBanco(context);
    }

    public boolean criarReceitas(String titulo, Float valor, String data_receita){
        banco = gerenciarBanco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("valor", valor);
        valores.put("data_receita", data_receita);

        long resultado = banco.insert("receitas", null, valores);
        banco.close();

        return resultado > 0;
    }

    public Cursor obterReceitas(){
        String[] campos = {"_id","titulo","valor","data_receita"};
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        Cursor cursor = db.query("receitas", campos, null, null, null, null, "_id DESC");

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void atualizarReceita(int id, String titulo, Float valor, String dataReceita){
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = "+id;

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("valor", valor);
        valores.put("data_receita", dataReceita);

        db.update("receitas", valores, where, null);
        db.close();
    }

    public void excluiReceita(int id){
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = "+id;

        db.delete("receitas",where,null);
        db.close();
    }

    public boolean criarGastos(String titulo, Float valor, String data_gasto){
        banco = gerenciarBanco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("valor", valor);
        valores.put("data_gasto", data_gasto);

        long resultado = banco.insert("gastos", null, valores);
        banco.close();

        return resultado > 0;
    }

    public Cursor obterGastos(){
        String[] campos = {"_id","titulo","valor","data_gasto"};
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        Cursor cursor = db.query("gastos", campos, null, null, null, null, "_id DESC");

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void atualizarGasto(int id, String titulo, Float valor, String dataGasto){
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = "+id;

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("valor", valor);
        valores.put("data_gasto", dataGasto);

        db.update("gastos", valores, where, null);
        db.close();
    }

    public void excluiGasto(int id){
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = "+id;

        db.delete("gastos",where,null);
        db.close();
    }

    public Float obterSumReceitas(){
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(valor) FROM receitas", null);
        Float total;
        if(cursor.moveToFirst()){
            total = cursor.getFloat(0);
        }else{
            total = -1f;
        }

        cursor.close();

        return total;
    }

    public Float obterSumGastos(){
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(valor) FROM gastos", null);
        Float total;
        if(cursor.moveToFirst()){
            total = cursor.getFloat(0);
        }else{
            total = -1f;
        }

        cursor.close();

        return total;
    }



    public Cursor consultarReceitaPeloId(int notaId){
        Cursor cursor;
        String[] campos = {"_id", "titulo","valor","data_receita"};
        String where = "_id = "+ notaId;
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        cursor = db.query("receitas", campos, where, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor consultarGastoPeloId(int notaId){
        Cursor cursor;
        String[] campos = {"_id", "titulo","valor","data_gasto"};
        String where = "_id = "+ notaId;
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        cursor = db.query("gastos", campos, where, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
