package com.project.sondagemocr.Controller;


import android.content.ContentValues;
import android.database.Cursor;
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
        try {
            ContentValues values = new ContentValues();
            values.put("login_user", usuario.getLoginUser());
            values.put("nome_usuario", usuario.getNome());

            SQLiteDatabase connection = dataBase.getWritableDatabase();

            connection.insertOrThrow("tb_usuario", null, values);

            values = new ContentValues();
            values.put("usuario",usuario.getLoginUser());
            values.put("senha",usuario.getSenhaUser());

            connection.insertOrThrow("tb_login_usuario",null,values);

            connection.close();
        }catch (Exception ex){
            Log.i("Alert","Não foi possível inserir usuário ao Banco de dados!");
        }

    }

    public Usuario consultaUsuario(Usuario usuario){
        try {
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.rawQuery("SELECT usuario FROM tb_login_usuario WHERE usuario = '"+usuario.getLoginUser()+"' AND senha = '"+usuario.getSenhaUser()+"' ; ",null);
            cursor.moveToFirst();
            usuario = new Usuario();
            do{
                usuario.setLoginUser(cursor.getString(cursor.getColumnIndex("usuario")));
            }while (cursor.moveToNext());
            cursor.close();
            connection.close();
            if(usuario.getLoginUser() == null){
                return null;
            }else {
                return usuario;
            }
        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );
            return null;
        }

    }

    public void consultaListaUsuarios(){
        try {
            SQLiteDatabase connection = dataBase.getReadableDatabase();
            Cursor cursor = connection.query("tb_login_usuario",null,null,null,null,null,null);
            cursor.moveToFirst();

            cursor.moveToFirst();
            while (cursor.moveToNext()){
                Usuario usuario = new Usuario();
                usuario.setLoginUser(cursor.getString(cursor.getColumnIndex("usuario")));
                usuario.setSenhaUser(cursor.getString(cursor.getColumnIndex("senha")));
                Log.i("Users:",usuario.getLoginUser()+"-"+usuario.getSenhaUser());
            }
            cursor.close();
            connection.close();

        }catch (Exception ex){
            Log.i("Script", "Erro "+ex.getMessage() );

        }

    }

}
