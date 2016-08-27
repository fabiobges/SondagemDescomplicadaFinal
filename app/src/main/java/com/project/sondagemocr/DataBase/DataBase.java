package com.project.sondagemocr.DataBase;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.sondagemocr.IdentificacaoFragment;


public class DataBase extends SQLiteOpenHelper {

    private final static String dbName = "SondagemBD";

    public DataBase(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //
        db.execSQL(ScriptSQL.createTableEndereco());
        db.execSQL(ScriptSQL.createTableLoginUsuario());
        db.execSQL(ScriptSQL.createTableUsuario());
        db.execSQL(ScriptSQL.createTableTurma());
        db.execSQL(ScriptSQL.createTableAluno());
        db.execSQL(ScriptSQL.createTableResponsavel());
        db.execSQL(ScriptSQL.createTableSondagemModelo());
        db.execSQL(ScriptSQL.createTableNivel());
        db.execSQL(ScriptSQL.createTableSondagemAluno());
        Log.i("Script","A tabela foi criada!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ScriptSQL.createTableEndereco());
        Log.i("Script","A tabela foi criada!");
        db.execSQL(ScriptSQL.createTableLoginUsuario());
        Log.i("Script","A tabela foi criada!");
        db.execSQL(ScriptSQL.createTableUsuario());
        db.execSQL(ScriptSQL.createTableTurma());
        db.execSQL(ScriptSQL.createTableAluno());
        db.execSQL(ScriptSQL.createTableResponsavel());
        db.execSQL(ScriptSQL.createTableSondagemModelo());
        db.execSQL(ScriptSQL.createTableNivel());
        db.execSQL(ScriptSQL.createTableSondagemAluno());
    }

}
