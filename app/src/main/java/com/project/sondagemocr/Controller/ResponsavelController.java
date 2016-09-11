package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.ResponsavelAluno;

public class ResponsavelController {

      private DataBase dataBase;

      public ResponsavelController(DataBase dataBase){
          this.dataBase = dataBase;
      }


      public int insereResponsavel(ResponsavelAluno responsavel){

          SQLiteDatabase connection = dataBase.getWritableDatabase();

          ContentValues values = new ContentValues();
          values.put("nome_responsavel",responsavel.getNome());
          values.put("cpf_responsavel",responsavel.getCpf());
          values.put("tel_responsavel",responsavel.getTelefone());

          connection.insertOrThrow("tb_responsavel_aluno", null, values);
          connection.close();
          return consultaLastResponsavel();
      }

      public int consultaLastResponsavel(){

          try {
              SQLiteDatabase connection = dataBase.getReadableDatabase();
              ResponsavelAluno responsavel = new ResponsavelAluno();
              Cursor cursor = connection.query("tb_responsavel_aluno", null, null, null, null, null, "_id desc", "1");
              cursor.moveToFirst();

              while (cursor.moveToNext()) {
                  responsavel.setNome(cursor.getString(cursor.getColumnIndex("nome_responsavel")));
                  responsavel.setId(cursor.getInt(cursor.getColumnIndex("_id")));
              }

              cursor.close();
              connection.close();
              Log.i("Script","Responsável é: "+responsavel.getId());
              return responsavel.getId();
          }catch (Exception ex){
              return 0;
          }


      }

    public ArrayAdapter consultaLastResponsavel(Context context){


        try {
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Cursor cursor = connection.query("tb_responsavel_aluno", null, null, null, null, null, "nome_responsavel asc", null);
            cursor.moveToFirst();

            while (cursor.moveToNext()) {
                arrayAdapter.add(cursor.getString(cursor.getColumnIndex("nome_responsavel")));

            }
            cursor.close();
            connection.close();
            return arrayAdapter;
        }catch (Exception ex){
            return null;
        }


    }

}
