package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Endereco;

public class EnderecoController {

    DataBase dataBase;


    public EnderecoController(DataBase database){
        this.dataBase = database;
    }

    public void insereEndereco(Endereco endereco){

        SQLiteDatabase connection = dataBase.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rua_end", endereco.getRua());
        values.put("numero_end", endereco.getNumero());
        values.put("bairro_end", endereco.getBairro());
        values.put("cep_end", endereco.getCep());
        values.put("cidade_end", endereco.getCidade());
        values.put("uf_end", endereco.getUf());

        connection.insertOrThrow("tb_endereco",null,values);
        connection.close();

    }

}
