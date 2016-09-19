package com.project.sondagemocr;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.*;
import android.database.*;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.*;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
import com.project.sondagemocr.Controller.NivelController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Nivel;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button button;
    private TextView txCadastroUsuario;
    private DataBase dataBase;


    //private GoogleApiClient client;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inserindo Hipoteses no Banco de dados
        dataBase = new DataBase(this,null,1);
        NivelController nivelController =new NivelController(dataBase);
        //Verificando se Hipóteses já foram cadastradas
        if(nivelController.consultaNivel() == false) {
            Nivel nivel = new Nivel();
            nivel.setNome("Pré-Silábico");
            nivelController.insereNivel(nivel);
            nivel.setNome("Silábico sem Valor Sonoro");
            nivelController.insereNivel(nivel);
            nivel.setNome("Silábico com Valor Sonoro");
            nivelController.insereNivel(nivel);
            nivel.setNome("Silábico Alfabético");
            nivelController.insereNivel(nivel);
            nivel.setNome("Alfabético");
            nivelController.insereNivel(nivel);
        }

        button = (Button)findViewById(R.id.btEntra);
        txCadastroUsuario = (TextView) findViewById(R.id.textView6);

        button.setOnClickListener(this);

        txCadastroUsuario.setOnClickListener(this);

        try {
            /*
            dataBase = new DataBase(this, null, 1);
            NivelController nivelController = new NivelController(dataBase);
            Nivel nivel = new Nivel();
            nivel.setNome("Alfabético");
            nivelController.insereNivel(nivel);
            nivel.setNome("Silábico Alfabético");
            nivelController.insereNivel(nivel);
            nivel.setNome("Silábico com Valor Sonoro");
            nivelController.insereNivel(nivel);
            nivel.setNome("Silábico sem Valor Sonoro");
            nivelController.insereNivel(nivel);
            nivel.setNome("Pré-Silábico");
            nivelController.insereNivel(nivel);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Banco foi criado com sucesso!");
            alertDialog.setNeutralButton("Ok",null);
            alertDialog.show();
            */
        }catch (SQLException ex){
            //AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            //alertDialog.setMessage("Banco nao foi criado!"+ex.getMessage());
            //alertDialog.setNeutralButton("Ok",null);
            //alertDialog.show();
        }



    }

    @Override
    public void onClick(View v) {
        if(v == button){
            Intent intent = new Intent(this,PrincipalActivity.class);
            startActivity(intent);
        }else if(v == txCadastroUsuario){
            Intent intent = new Intent(this,CadastroUsuarioActivity.class);
            startActivity(intent);
        }
    }
}
