package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemAluno;

public class SondagemAlunoController {

    private DataBase dataBase;

    public SondagemAlunoController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void insereSondagemAluno(SondagemAluno sondagemAluno){

        try{
            SQLiteDatabase connection = this.dataBase.getWritableDatabase();
            ContentValues values =  new ContentValues();
            values.put("_id_sondagem_modelo",sondagemAluno.getSondagemModelo().getId());
            values.put("_id_aluno",sondagemAluno.getAluno().getId());
            values.put("_id_nivel",sondagemAluno.getNivel().getId());
            values.put("polissilaba",sondagemAluno.getPolissilaba());
            values.put("trissilaba",sondagemAluno.getTrissilaba());
            values.put("dissilaba",sondagemAluno.getDissilaba());
            values.put("monossilaba",sondagemAluno.getMonossilaba());
            values.put("frase",sondagemAluno.getFrase());
            connection.insertOrThrow("tb_sondagem_aluno",null,values);
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
        }

    }
}
