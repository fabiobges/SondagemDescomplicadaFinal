package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Turma;


public class CadastroTurmaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtIdentTurma;
    EditText edtAno;
    Button btCadastra,btCancela;
    Turma turma;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edtIdentTurma = (EditText) findViewById(R.id.edtIdentTurma);
        edtAno = (EditText) findViewById(R.id.edtAnoTurma);
        btCadastra = (Button) findViewById(R.id.btCadastraTurma);
        btCancela = (Button) findViewById(R.id.btCancelaTurma);

        btCancela.setOnClickListener(this);
        btCadastra.setOnClickListener(this);

        dataBase = new DataBase(this, null, 1);
        turma = new Turma();

    }

    @Override
    public void onClick(View v) {
        if(v == btCadastra){
            if(edtAno.getText() != null && edtIdentTurma.getText() != null){
                insertTurma();
                try{
                    TurmaController turmaController = new TurmaController(dataBase);
                    turmaController.insereTurma(turma);
                    Toast.makeText(this,"Turma cadastrada com sucesso!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, PrincipalActivity.class);
                    startActivity(intent);
                }catch (Exception ex){
                    lancaAlertDialog("Não foi possível inserir Turma ao Banco de dados",ex);
                }
            }else{
                lancaAlertDialog("Os campos não foram preenchidos corretamente!",null);
            }
        }else if(v == btCancela){
            Intent intent = new Intent(this,PrincipalActivity.class);
            startActivity(intent);
        }
    }

    private void insertTurma(){
        turma.setIdentificador(edtIdentTurma.getText().toString());
        turma.setAno(edtAno.getText().toString());
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
