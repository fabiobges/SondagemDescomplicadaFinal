package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Aluno;
import com.project.sondagemocr.Pojo.SondagemAluno;
import com.project.sondagemocr.Pojo.Turma;

import java.util.ArrayList;

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
            values.put("dt_sondagem",sondagemAluno.getData());
            connection.insertOrThrow("tb_sondagem_aluno",null,values);
            connection.close();
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
        }

    }

    public ArrayList<SondagemAluno> consultaSondagensAlunos(){
        try {
            ArrayList<SondagemAluno> sondagensAlunos = new ArrayList<SondagemAluno>();
            int i = 0;
            Log.i("Passou!: ","Passou1");
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Log.i("Passou!: ","Passou2");
            Cursor cursor = connection.rawQuery("SELECT s.polissilaba, s.trissilaba, s.dissilaba, s.monossilaba, s.frase, s.dt_sondagem, " +
                    " a.nome_aluno, t.identificacao_turma, t.ano_turma" +
                    " FROM tb_sondagem_aluno AS s" +
                    " INNER JOIN tb_aluno AS a ON(s._id_aluno = a._id)" +
                    " INNER JOIN tb_turma AS t ON(a._id_turma = t._id)" +
                    " ORDER BY s.dt_sondagem DESC; ",null);
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {


                do {
                    SondagemAluno sondagemAluno = new SondagemAluno();
                    Aluno aluno = new Aluno();
                    Turma turma =  new Turma();
                    Log.i("Aluno 1: ","Nome aluno:"+cursor.getColumnIndex("nome_aluno")+
                                        "Poli:"+cursor.getString(cursor.getColumnIndex("polissilaba"))+
                                        "Tri:"+cursor.getColumnIndex("trissilaba")+
                            "Dissi:"+cursor.getColumnIndex("monossilaba")+
                            "frase:"+cursor.getColumnIndex("frase")+
                            "dta: "+cursor.getColumnIndex("dt_sondagem")+
                            "Ano Turma:"+cursor.getColumnIndex("ano_turma")+
                            "Id Turma:"+cursor.getColumnIndex("identificacao_turma")+
                            "column:"+cursor.getColumnName(0));
                    sondagemAluno.setPolissilaba(cursor.getString(cursor.getColumnIndex("polissilaba")));
                    sondagemAluno.setTrissilaba(cursor.getString(cursor.getColumnIndex("trissilaba")));
                    sondagemAluno.setDissilaba(cursor.getString(cursor.getColumnIndex("dissilaba")));
                    sondagemAluno.setMonossilaba(cursor.getString(cursor.getColumnIndex("monossilaba")));
                    sondagemAluno.setFrase(cursor.getString(cursor.getColumnIndex("frase")));
                    sondagemAluno.setData(cursor.getString(cursor.getColumnIndex("dt_sondagem")));
                    aluno.setNome(cursor.getString(cursor.getColumnIndex("nome_aluno")));
                    turma.setIdentificador(cursor.getString(cursor.getColumnIndex("identificacao_turma")));
                    turma.setAno(cursor.getString(cursor.getColumnIndex("ano_turma")));
                    aluno.setTurma(turma);
                    sondagemAluno.setAluno(aluno);
                    sondagensAlunos.add(i, sondagemAluno);
                    Log.i("Aluno : ",aluno.getNome());
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            connection.close();
            return sondagensAlunos;
        }catch (Exception ex){
            Log.i("Error DB ",ex.getMessage());
            return null;
        }

    }
}
