package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemModelo;

import java.util.ArrayList;

public class SondagemModeloController {

    DataBase dataBase;

    public SondagemModeloController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void insereSondagemModelo(SondagemModelo sondagemModelo){

        SQLiteDatabase connection = dataBase.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("desc_sondagem_mod",sondagemModelo.getDescSondagemMod());
        values.put("polissilaba",sondagemModelo.getPolissilaba());
        values.put("trissilaba",sondagemModelo.getTrissilaba());
        values.put("dissilaba",sondagemModelo.getDissilaba());
        values.put("monossilaba",sondagemModelo.getMonossilaba());
        values.put("frase",sondagemModelo.getFrase());

        connection.insertOrThrow("tb_sondagem_modelo",null,values);
        connection.close();
    }

    public ArrayAdapter<String> consultaSondagemModelo(Context context){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try{
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_sondagem_modelo",null,null,null,null,null,"desc_sondagem_mod",null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                 do{
                    arrayAdapter.add(cursor.getString(cursor.getColumnIndex("desc_sondagem_mod")));
                }while (cursor.moveToNext());

            }else {
                Log.i("Script", "Não achou nenhuma Sondagem em BD");
            }
            cursor.close();
            connection.close();
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
        }


        return arrayAdapter;


    }

    public ArrayList<SondagemModelo> consultaSondagemModeloArray(){

        try{
            ArrayList<SondagemModelo> sondagemModelos = new ArrayList<>();
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_sondagem_modelo",null,null,null,null,null,"desc_sondagem_mod",null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do{
                    SondagemModelo sondagemModelo = new SondagemModelo();
                    sondagemModelo.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    sondagemModelo.setDescSondagemMod(cursor.getString(cursor.getColumnIndex("desc_sondagem_mod")));
                    sondagemModelos.add(sondagemModelo);
                }while (cursor.moveToNext());

            }else {
                Log.i("Script", "Não achou nenhuma Sondagem em BD");
            }
            cursor.close();
            connection.close();
            return sondagemModelos;
        }catch (Exception ex) {
            Log.i("Script", "Erro " + ex.getMessage());
            return null;
        }


    }

    public SondagemModelo consultaSondagemModeloPorIdentificador(SondagemModelo sondagemModelo){

        try {
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_sondagem_modelo", null, "desc_sondagem_mod = '" + sondagemModelo.getDescSondagemMod()+"'", null, null, null, null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    sondagemModelo.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    sondagemModelo.setPolissilaba(cursor.getString(cursor.getColumnIndex("polissilaba")));
                    sondagemModelo.setTrissilaba(cursor.getString(cursor.getColumnIndex("trissilaba")));
                    sondagemModelo.setDissilaba(cursor.getString(cursor.getColumnIndex("dissilaba")));
                    sondagemModelo.setMonossilaba(cursor.getString(cursor.getColumnIndex("monossilaba")));
                    sondagemModelo.setFrase(cursor.getString(cursor.getColumnIndex("frase")));
                } while (cursor.moveToNext());
            } else {
                sondagemModelo = null;
                Log.i("Script", "Não achou nenhuma Sondagem Modelo em BD");
            }
            cursor.close();
            connection.close();
            return sondagemModelo;
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
            return null;
        }

    }

    public SondagemModelo consultaSondagemModeloPorId(SondagemModelo sondagemModelo){

        try {
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_sondagem_modelo", null, "_id = " + sondagemModelo.getId(), null, null, null, null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    sondagemModelo.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    sondagemModelo.setDescSondagemMod(cursor.getString(cursor.getColumnIndex("desc_sondagem_mod")));
                    sondagemModelo.setPolissilaba(cursor.getString(cursor.getColumnIndex("polissilaba")));
                    sondagemModelo.setTrissilaba(cursor.getString(cursor.getColumnIndex("trissilaba")));
                    sondagemModelo.setDissilaba(cursor.getString(cursor.getColumnIndex("dissilaba")));
                    sondagemModelo.setMonossilaba(cursor.getString(cursor.getColumnIndex("monossilaba")));
                    sondagemModelo.setFrase(cursor.getString(cursor.getColumnIndex("frase")));
                } while (cursor.moveToNext());
            } else {
                sondagemModelo = null;
                Log.i("Script", "Não achou nenhuma Sondagem Modelo em BD");
            }
            cursor.close();
            connection.close();
            return sondagemModelo;
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
            return null;
        }

    }

    public void removeSondagemModelo(SondagemModelo sondagemModelo){
        try{
            SQLiteDatabase connection = this.dataBase.getWritableDatabase();
            connection.delete("tb_sondagem_modelo", "_id =" + sondagemModelo.getId(), null);
            Log.i("Script","Erro passou :"+sondagemModelo.getId());
            connection.close();
        }catch (Exception ex){
            Log.i("Script","Erro ao remover sondagem modelo:"+ex);
        }
    }

}
