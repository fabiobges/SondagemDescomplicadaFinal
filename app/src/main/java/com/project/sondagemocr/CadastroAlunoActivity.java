package com.project.sondagemocr;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.project.sondagemocr.Controller.AlunoController;
import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Aluno;
import com.project.sondagemocr.Pojo.ResponsavelAluno;
import com.project.sondagemocr.Pojo.Turma;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroAlunoActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtNome;
    EditText edtRa;
    EditText edtNasc;
    Spinner spnTurmaAluno;
    EditText edtNomeResp;
//    EditText edtCpfResp;
    EditText edtTelResp;
    EditText edtNascAluno;
    FloatingActionButton btGrava;
    Aluno aluno;
    DataBase dataBase;
    TurmaController turmaController;
    Turma turma;

    public void onStart(){
        super.onStart();
        EditText edtNascAluno=(EditText) findViewById(R.id.edtNascAluno);
        edtNascAluno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog=new DateDialog();
                    dialog.receiveView(v);
                    FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Cadastro Aluno");
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        edtNome = (EditText) findViewById(R.id.edtNomeAluno);
        edtRa = (EditText) findViewById(R.id.edtRaAluno);
        edtNasc = (EditText) findViewById(R.id.edtNascAluno);
        spnTurmaAluno = (Spinner) findViewById(R.id.spnTurma);
        edtNomeResp = (EditText) findViewById(R.id.edtNomeResponsavel);
//        edtCpfResp = (EditText) findViewById(R.id.edtCpfResponsavel);
        edtTelResp = (EditText) findViewById(R.id.edtTelResponsavel);
        btGrava = (FloatingActionButton) findViewById(R.id.btCadastraAluno);
        btGrava.setOnClickListener(this);

        aluno = new Aluno();
        aluno.setResponsavel(new ResponsavelAluno());

        dataBase = new DataBase(this,null,1);
        turmaController = new TurmaController(dataBase);

        spnTurmaAluno.setAdapter(turmaController.consultaTurma(this));

        turma = new Turma();

    }

    @Override
    public void onClick(View v) {
        if(v == btGrava){
            if(insertAluno()==true){
                try {
                    AlunoController alunoController = new AlunoController(dataBase);
                    alunoController.insereAluno(aluno);
                    Toast.makeText(this,"Aluno foi cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,PrincipalActivity.class);
                    startActivity(intent);
                }catch (Exception ex){
                    lancaAlertDialog("Erro ao inserir aluno e responsável ao Banco de dados", ex);
                }
            }
        }
    }

    public boolean insertAluno(){
        aluno.setNome(edtNome.getText().toString());
        aluno.setRa(edtRa.getText().toString());
        aluno.getResponsavel().setNome(edtNomeResp.getText().toString());
//        aluno.getResponsavel().setCpf(edtCpfResp.getText().toString());
        aluno.getResponsavel().setTelefone(edtTelResp.getText().toString());
        turma.setIdentificador(spnTurmaAluno.getSelectedItem().toString());
        turma = turmaController.consultaTurmaId(turma);
        aluno.setTurma(turma);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            aluno.setDt_nascimento(sdf.parse(edtNasc.getText().toString()));
            return true;
        } catch (Exception ex){
            lancaAlertDialog("Data não foi inserida corretamente!",null);
            return false;
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
