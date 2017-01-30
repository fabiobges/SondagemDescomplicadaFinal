package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.Toolbar;

import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Turma;


public class CadastroTurmaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtIdentTurma;
    EditText edtAno;
    FloatingActionButton btCadastra;
    Turma turma;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Cadastro Turma");
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        edtIdentTurma = (EditText) findViewById(R.id.edtIdentTurma);
        edtAno = (EditText) findViewById(R.id.edtAnoTurma);
        btCadastra = (FloatingActionButton) findViewById(R.id.btCadastraTurma);
        btCadastra.setOnClickListener(this);

        dataBase = new DataBase(this, null, 1);
        turma = new Turma();

    }

    @Override
    public void onClick(View v) {
        if(v == btCadastra){
            boolean flag = insertTurma();
            if(flag == false){

                try{
                    TurmaController turmaController = new TurmaController(dataBase);
                    turmaController.insereTurma(turma);
                    Toast.makeText(this,"Turma cadastrada com sucesso!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, PrincipalActivity.class);
                    startActivity(intent);
                }catch (Exception ex){
                    lancaAlertDialog("Não foi possível inserir Turma ao Banco de dados!",ex);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Ativa tela anterior
        if (item.getItemId() == android.R.id.home) {
            finish();//fecha tela atual
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean insertTurma(){
        turma.setIdentificador(edtIdentTurma.getText().toString());
        if(turma.getIdentificador().equals("")){
            lancaAlertDialog("A turma não foi informada!",null);
            return true;
        }
        turma.setAno(edtAno.getText().toString());
        if(turma.getAno().length() <= 3){
            lancaAlertDialog("O ano da turma não foi informado corretamente!",null);
            return true;
        }
        return false;
    }

    public void lancaAlertDialog(String message,Exception ex){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        if(ex == null) {
            alertDialog.setMessage(message);
        }else{
            alertDialog.setMessage(message+": "+ex.getMessage());
        }
        alertDialog.setNeutralButton("OK", null);
        alertDialog.show();
    }
}
