package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemModelo;

public class SondagemModeloController {

    DataBase dataBase;

    public SondagemModeloController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void insereSondagemModelo(SondagemModelo sondagemModelo){

        SQLiteDatabase connection = dataBase.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("polissilaba",sondagemModelo.getPolissilaba());
        values.put("trissilaba",sondagemModelo.getTrissilaba());
        values.put("dissilaba",sondagemModelo.getDissilaba());
        values.put("monossilaba",sondagemModelo.getMonossilaba());
        values.put("frase",sondagemModelo.getFrase());

        connection.insertOrThrow("tb_sondagem_modelo",null,values);

    }

    public ArrayAdapter<String> consultaSondagemModelo(Context context){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try{
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_sondagem_modelo",null,null,null,null,null,"_id",null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    arrayAdapter.add(cursor.getString(cursor.getColumnIndex("_id")));
                }
            }else {
                Log.i("Script", "Não achou nenhuma Sondagem em BD");
            }
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
        }


        return arrayAdapter;


    }

}
