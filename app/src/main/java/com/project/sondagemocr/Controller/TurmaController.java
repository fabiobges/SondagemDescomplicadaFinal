package com.project.sondagemocr.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;


import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.IdentificacaoFragment;
import com.project.sondagemocr.Pojo.Turma;

public class TurmaController {

        DataBase dataBase;

        public TurmaController(DataBase dataBase){
            this.dataBase = dataBase;
        }

        public void insereTurma(Turma turma){

            ContentValues values = new ContentValues();
            values.put("identificacao_turma",turma.getIdentificador());
            values.put("ano_turma",turma.getAno());

            SQLiteDatabase connection = dataBase.getWritableDatabase();
            connection.insertOrThrow("tb_turma",null,values);

        }

        public ArrayAdapter<String> consultaTurma(Context context){

            Log.i("Script","Consultando Turma");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            try {
                SQLiteDatabase connection = dataBase.getReadableDatabase();
                Cursor cursor = connection.query( "tb_turma", null, null, null, null, null, null, null);
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do{
                        int i;
                        arrayAdapter.add(cursor.getString(cursor.getColumnIndex("identificacao_turma")));
                        i = cursor.getInt(cursor.getColumnIndex("_id"));
                        Log.i("Script", "Retornou: " + cursor.getString(cursor.getColumnIndex("identificacao_turma")) + " + " + i);
                    }while (cursor.moveToNext());
                } else {
                    Log.i("Script", "Não achou nenhuma turma em BD");
                }
            }catch (Exception ex){
                Log.i("Script", "Erro sss"+ex.getMessage() );
            }


            return arrayAdapter;
        }

    public Turma consultaTurmaId(Turma turma){

        try {
            Log.i("Script", "Entrou  no consulta turma" );
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_turma", null, "identificacao_turma = "+"'"+turma.getIdentificador()+"'", null, null,
                    null, null);
            Log.i("Script", "Entrou  no consulta turma count id: "+cursor.getCount() );
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    turma.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    Log.i("Script","ID da Turma eh: database"+turma.getId());
                    turma.setIdentificador(cursor.getString(cursor.getColumnIndex("identificacao_turma")));
                    turma.setAno(cursor.getString(cursor.getColumnIndex("ano_turma")));
                }
                return turma;
            } else {
                Log.i("Script","ID da else"+turma.getId());
                return null;
            }
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
            return null;
        }



    }

}
