package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Nivel;

public class NivelController {

    DataBase dataBase;

    public NivelController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void insereNivel(Nivel nivel){
        try {
            SQLiteDatabase connection = dataBase.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nome_nivel", nivel.getNome());
            values.put("desc_nivel", nivel.getDescricao());
            connection.insertOrThrow("tb_nivel", null, values);
            Log.i("Inseriu",nivel.getNome());
            connection.close();
        }catch (Exception ex){
            Log.i("Error",ex.getMessage());
        }


    }

    public Nivel consultaNivelPorNome(String nome){
        Nivel nivel = new Nivel();
        try{
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_nivel",null,"nome_nivel = '"+nome+"'",null,null,null,null,"1");
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    nivel.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    nivel.setNome(cursor.getString(cursor.getColumnIndex("nome_nivel")));
                    nivel.setDescricao(cursor.getString(cursor.getColumnIndex("desc_nivel")));
                    Log.i("Consulta: ","Retornou "+nivel.getNome());
                }while (cursor.moveToNext());
                cursor.close();
            }
            connection.close();
            return  nivel;
        }catch (Exception ex){
            Log.i("Error",ex.getMessage());
            return  null;
        }
    }

    public Nivel consultaNivelPorId(int id){
        Nivel nivel = new Nivel();
        try{
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_nivel",null,"_id = "+id,null,null,null,null,null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    nivel.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    nivel.setNome(cursor.getString(cursor.getColumnIndex("nome_nivel")));
                    nivel.setDescricao(cursor.getString(cursor.getColumnIndex("desc_nivel")));
                    Log.i("Nivel: ","Retornou "+nivel.getNome());
                }while (cursor.moveToNext());
                cursor.close();
            }
            Log.i("Nivel: ", id+ " Retornou "+nivel.getNome());
            connection.close();
            return  nivel;
        }catch (Exception ex){
            Log.i("Error",ex.getMessage());
            return  null;
        }
    }

    public boolean consultaNivel(){
        SQLiteDatabase connection = this.dataBase.getReadableDatabase();
        Cursor cursor = connection.query("tb_nivel",null,null,null,null,null,null);
        if(cursor.getCount() > 0){
            Log.i("Script","Já estão cadastradas as hipóteses");
            cursor.close();
            connection.close();
            return true;
        }else{
            cursor.close();
            connection.close();
            return false;
        }

    }

}
