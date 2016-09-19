package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Aluno;
import com.project.sondagemocr.Pojo.Nivel;
import com.project.sondagemocr.Pojo.SondagemAluno;
import com.project.sondagemocr.Pojo.SondagemModelo;
import com.project.sondagemocr.Pojo.Turma;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SondagemAlunoController {

    private DataBase dataBase;

    public SondagemAlunoController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void insereSondagemAluno(SondagemAluno sondagemAluno) throws ParseException {
        Log.i("Date",sondagemAluno.getData());

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

    public int consultaUltimoId(){
        int id;
        try {
            id=-1;
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Cursor cursor = connection.rawQuery("select _id from tb_sondagem_aluno order by _id desc limit 1 ;", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    id = cursor.getInt(cursor.getColumnIndex("_id"));
                } while (cursor.moveToNext());

            }
            cursor.close();
            connection.close();
            return id;
        }catch (Exception ex){
            Log.i("Error: ",ex.getMessage());
            return -1;
        }
    }

    public SondagemAluno consultaSondagemAlunoPorId(int id){
        try {
            Log.i("Script","Achou sondagem no BD"+id);
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            SondagemAluno sondagemAluno = new SondagemAluno();
            sondagemAluno.setSondagemModelo(new SondagemModelo());
            sondagemAluno.setAluno(new Aluno());
            sondagemAluno.setNivel(new Nivel());
            Cursor cursor = connection.query("tb_sondagem_aluno", null, "_id = " + id, null, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Log.i("Script","Achou sondagem no BD");
                do {
                    sondagemAluno.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    sondagemAluno.setPolissilaba(cursor.getString(cursor.getColumnIndex("polissilaba")));
                    sondagemAluno.setTrissilaba(cursor.getString(cursor.getColumnIndex("trissilaba")));
                    sondagemAluno.setDissilaba(cursor.getString(cursor.getColumnIndex("dissilaba")));
                    sondagemAluno.setMonossilaba(cursor.getString(cursor.getColumnIndex("monossilaba")));
                    sondagemAluno.setFrase(cursor.getString(cursor.getColumnIndex("frase")));
                    sondagemAluno.setData(cursor.getString(cursor.getColumnIndex("dt_sondagem")));
                    sondagemAluno.getAluno().setId(cursor.getInt(cursor.getColumnIndex("_id_aluno")));
                    sondagemAluno.getSondagemModelo().setId(cursor.getInt(cursor.getColumnIndex("_id_sondagem_modelo")));
                    sondagemAluno.getNivel().setId(cursor.getInt(cursor.getColumnIndex("_id_nivel")));
                    Log.i("Nivel ","nivel "+sondagemAluno.getNivel().getId());
                } while (cursor.moveToNext());
            }else{
                Log.i("Script","Não acho nenhuma sondagem no BD");
            }
            cursor.close();
            connection.close();
            return sondagemAluno;
        }catch (Exception ex){
            Log.i("Error ",ex.getMessage());
            return null;
        }
    }

    public ArrayList<SondagemAluno> consultaSondagensAlunos(){
        try {
            ArrayList<SondagemAluno> sondagensAlunos = new ArrayList<SondagemAluno>();
            int i = 0;
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Cursor cursor = connection.rawQuery("SELECT s._id, s.polissilaba, s.trissilaba, s.dissilaba, s.monossilaba, s.frase, strftime('%d/%m/%Y',s.dt_sondagem) as 'year', " +
                    " a.nome_aluno, t.identificacao_turma, t.ano_turma" +
                    " FROM tb_sondagem_aluno AS s" +
                    " INNER JOIN tb_aluno AS a ON(s._id_aluno = a._id)" +
                    " INNER JOIN tb_turma AS t ON(a._id_turma = t._id)" +
                    " ORDER BY s.dt_sondagem DESC; ",null);
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                String date;
                do {
                    SondagemAluno sondagemAluno = new SondagemAluno();
                    Aluno aluno = new Aluno();
                    Turma turma =  new Turma();
                    sondagemAluno.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    sondagemAluno.setPolissilaba(cursor.getString(cursor.getColumnIndex("polissilaba")));
                    sondagemAluno.setTrissilaba(cursor.getString(cursor.getColumnIndex("trissilaba")));
                    sondagemAluno.setDissilaba(cursor.getString(cursor.getColumnIndex("dissilaba")));
                    sondagemAluno.setMonossilaba(cursor.getString(cursor.getColumnIndex("monossilaba")));
                    sondagemAluno.setFrase(cursor.getString(cursor.getColumnIndex("frase")));
                    date = cursor.getString(cursor.getColumnIndex("year"));

                    sondagemAluno.setData(date);
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

    public ArrayList<SondagemAluno> consultaSondagensAlunosPorTurma(String identificacaoTurma){
        try {
            Log.i("teste","teste10");
            ArrayList<SondagemAluno> sondagensAlunos = new ArrayList<SondagemAluno>();
            int i = 0;
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Cursor cursor = connection.rawQuery("SELECT s._id, s.polissilaba, s.trissilaba, s.dissilaba, s.monossilaba, s.frase, strftime('%d/%m/%Y',s.dt_sondagem) as 'year', " +
                    " a.nome_aluno, t.identificacao_turma, t.ano_turma" +
                    " FROM tb_sondagem_aluno AS s" +
                    " INNER JOIN tb_aluno AS a ON(s._id_aluno = a._id)" +
                    " INNER JOIN tb_turma AS t ON(a._id_turma = t._id)" +
                    " WHERE t.identificacao_turma = '"+identificacaoTurma+"' " +
                    " ORDER BY s.dt_sondagem DESC; ",null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    SondagemAluno sondagemAluno = new SondagemAluno();
                    Aluno aluno = new Aluno();
                    Turma turma =  new Turma();
                    sondagemAluno.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    sondagemAluno.setPolissilaba(cursor.getString(cursor.getColumnIndex("polissilaba")));
                    sondagemAluno.setTrissilaba(cursor.getString(cursor.getColumnIndex("trissilaba")));
                    sondagemAluno.setDissilaba(cursor.getString(cursor.getColumnIndex("dissilaba")));
                    sondagemAluno.setMonossilaba(cursor.getString(cursor.getColumnIndex("monossilaba")));
                    sondagemAluno.setFrase(cursor.getString(cursor.getColumnIndex("frase")));
                    sondagemAluno.setData(cursor.getString(cursor.getColumnIndex("year")));
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

    public ArrayList<SondagemAluno> consultaSondagensAlunosPorTurmaAno(String identificacaoTurma,String anoTurma){
        try {
            Log.i("teste","teste10");
            ArrayList<SondagemAluno> sondagensAlunos = new ArrayList<SondagemAluno>();
            int i = 0;
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Cursor cursor = connection.rawQuery("SELECT s._id, s.polissilaba, s.trissilaba, s.dissilaba, s.monossilaba, s.frase, strftime('%d/%m/%Y',s.dt_sondagem) as 'year', " +
                    " strftime('%Y',s.dt_sondagem) as only_year, a.nome_aluno, t.identificacao_turma, t.ano_turma" +
                    " FROM tb_sondagem_aluno AS s" +
                    " INNER JOIN tb_aluno AS a ON(s._id_aluno = a._id)" +
                    " INNER JOIN tb_turma AS t ON(a._id_turma = t._id)" +
                    " WHERE t.identificacao_turma = '"+identificacaoTurma+"' " +
                    " AND only_year = '"+anoTurma+"' " +
                    " ORDER BY s.dt_sondagem DESC; ",null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    SondagemAluno sondagemAluno = new SondagemAluno();
                    Aluno aluno = new Aluno();
                    Turma turma =  new Turma();
                    sondagemAluno.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    sondagemAluno.setPolissilaba(cursor.getString(cursor.getColumnIndex("polissilaba")));
                    sondagemAluno.setTrissilaba(cursor.getString(cursor.getColumnIndex("trissilaba")));
                    sondagemAluno.setDissilaba(cursor.getString(cursor.getColumnIndex("dissilaba")));
                    sondagemAluno.setMonossilaba(cursor.getString(cursor.getColumnIndex("monossilaba")));
                    sondagemAluno.setFrase(cursor.getString(cursor.getColumnIndex("frase")));
                    sondagemAluno.setData(cursor.getString(cursor.getColumnIndex("year")));
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

    public void removeSondagemAlunoPorId(int id){
        try {
            SQLiteDatabase connection = this.dataBase.getWritableDatabase();
            connection.delete("tb_sondagem_aluno", "_id =" + id, null);
            connection.close();
        }catch (Exception ex){
            Log.i("Error DB ",ex.getMessage());
        }
    }

    public boolean alteraSondagemAluno(SondagemAluno sondagemAluno){
        try {
            SQLiteDatabase connection = this.dataBase.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("polissilaba", sondagemAluno.getPolissilaba());
            values.put("trissilaba", sondagemAluno.getTrissilaba());
            values.put("dissilaba", sondagemAluno.getDissilaba());
            values.put("monossilaba", sondagemAluno.getMonossilaba());
            values.put("frase", sondagemAluno.getFrase());
            values.put("_id_nivel", sondagemAluno.getNivel().getId());
            connection.update("tb_sondagem_aluno", values, "_id = " + sondagemAluno.getId(), null);
            connection.close();
            return true;
        }catch (Exception ex){
            Log.i("Error DB ",ex.getMessage());
            return false;
        }
    }

    public String dataAtual(){
        try{
            String date = "";
            SQLiteDatabase connection = this.dataBase.getReadableDatabase();
            Cursor cursor = connection.rawQuery("SELECT date('now') as 'data';",null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    date = cursor.getString(cursor.getColumnIndex("data"));
                }while (cursor.moveToNext());
            }
            cursor.close();
            connection.close();
            return date;
        }catch (Exception ex){
            ex.getStackTrace();
            return null;
        }
    }
}
