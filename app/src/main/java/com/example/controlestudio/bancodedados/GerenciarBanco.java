package com.example.controlestudio.bancodedados;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class GerenciarBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "bancoDedados.db";
    public static int VERSAO = 1;

    public GerenciarBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql_receitas = "CREATE TABLE receitas(_id integer primary key autoincrement, titulo text, valor real, data_receita text)";
        String sql_gastos = "CREATE TABLE gastos(_id integer primary key autoincrement, titulo text, valor real, data_gasto text)";
        db.execSQL(sql_receitas);
        db.execSQL(sql_gastos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS receitas");
        db.execSQL("DROP TABLE IF EXISTS gastos");
        onCreate(db);
    }
}
