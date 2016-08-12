package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Usuario;

public class UsuarioController {

    DataBase dataBase;

    public UsuarioController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void insereUsuario(Usuario usuario){

        ContentValues values = new ContentValues();
        //values.put("_id_endereco",usuario.getEndereco().getId());
        values.put("login_user",usuario.getLoginUser());
        values.put("nome_usuario", usuario.getNome());
        values.put("rg_usuario", usuario.getRg());
        values.put("cpf_usuario", usuario.getCpf());
        values.put("dt_nasc_usuario", usuario.getDt_nascimento());
        values.put("tel_usuario", usuario.getTelefone());
        values.put("escolaridade_usuario", usuario.getGrau_escolaridade());
        values.put("coordenador_usuario", usuario.getCoordenador());


        SQLiteDatabase connection = dataBase.getWritableDatabase();
        Log.i("Script",dataBase.getDatabaseName());

        connection.insertOrThrow("tb_usuario",null,values);

    }

}
