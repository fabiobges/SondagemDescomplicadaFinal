package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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

}
