package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.IdentificacaoFragment;
import com.project.sondagemocr.Pojo.Aluno;
import com.project.sondagemocr.Pojo.Turma;

public class AlunoController {

        DataBase dataBase;

        public AlunoController(DataBase dataBase){
            this.dataBase = dataBase;
        }

        public void insereAluno(Aluno aluno){

            ResponsavelController responsavelController = new ResponsavelController(dataBase);
            //Inserindo responsável ao banco de dados e recuperando seu id
            aluno.getResponsavel().setId(responsavelController.insereResponsavel(aluno.getResponsavel()));

            SQLiteDatabase connection = dataBase.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nome_aluno",aluno.getNome());
            values.put("ra_aluno",aluno.getRa());
            values.put("dt_nasc_aluno",aluno.getDt_nascimento().toString());
            values.put("_id_responsavel",aluno.getResponsavel().getId());
            values.put("_id_turma",aluno.getTurma().getId());

            connection.insertOrThrow("tb_aluno", null, values);

        }

        public ArrayAdapter<String> consultaAlunoTurma(Context context, Turma turma){

            try {
                SQLiteDatabase connection = dataBase.getReadableDatabase();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Cursor cursor = connection.query("tb_aluno", null, "_id_turma = "+turma.getId(),null, null, null, " nome_aluno asc");
                Log.i("Script", "Entrou  turma aluno: "+cursor.getCount() );
                if(cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        arrayAdapter.add(cursor.getString(cursor.getColumnIndex("nome_aluno")));
                    }
                    cursor.close();
                    return arrayAdapter;
                }else{
                    return  null;
                }


            }catch (Exception ex){
                Log.i("Script", "Erro "+ex.getMessage() );
                return null;
            }

        }

        public Aluno consultaAlunoPorNome(Aluno aluno){
            try{
                SQLiteDatabase connection = dataBase.getReadableDatabase();
                Cursor cursor = connection.query("tb_aluno",null,"nome_aluno = '"+aluno.getNome()+"'",null,null,null,null,"1");
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    do{
                        aluno.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                        aluno.setRa(cursor.getString(cursor.getColumnIndex("ra_aluno")));
                        Log.i("Consulta:","Retornou "+aluno.getNome());
                    }while (cursor.moveToNext());
                }else{
                    aluno=null;
                }
                cursor.close();
                connection.close();
                return aluno;
            }catch(Exception ex){
                Log.i("Script", "Erro "+ex.getMessage() );
                return null;
            }
        }

}
