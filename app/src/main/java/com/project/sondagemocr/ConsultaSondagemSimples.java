package com.project.sondagemocr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.sondagemocr.Controller.SondagemAlunoController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemAluno;

public class ConsultaSondagemSimples extends AppCompatActivity implements View.OnClickListener {

    TextView txViewSondModPoli, txViewSondModTri, txViewSondModDi, txViewSondModMono, txViewSondModFrase, txViewSondModUti;
    EditText edtTextSondAluPoli ,edtTextSondAluTri, edtTextSondAluDi, edtTextSondAluMono, edtTextSondAluFrase;
    Button btAlterar;

    DataBase dataBase;
    SondagemAluno sondagemAluno;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_sondagem_simples);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if(bundle.containsKey("id_sondagem_aluno")){
            id = Integer.parseInt(bundle.getString("id_sondagem_aluno"));
            Log.i("Script","Contem o ID "+id);
        }

        txViewSondModPoli = (TextView) findViewById(R.id.txViewSondModPoli);
        txViewSondModTri = (TextView) findViewById(R.id.txViewSondModTri);
        txViewSondModDi = (TextView) findViewById(R.id.txViewSondModDi);
        txViewSondModMono = (TextView) findViewById(R.id.txViewSondModMono);
        txViewSondModFrase = (TextView) findViewById(R.id.txViewSondModFrase);
        txViewSondModUti = (TextView) findViewById(R.id.txViewSondModUti);
        edtTextSondAluPoli = (EditText) findViewById(R.id.edtTxSondAluPoli);
        edtTextSondAluTri = (EditText) findViewById(R.id.edtTxSondAluTri);
        edtTextSondAluDi = (EditText) findViewById(R.id.edtTxSondAluDi);
        edtTextSondAluMono = (EditText) findViewById(R.id.edtTxSondAluMono);
        edtTextSondAluFrase = (EditText) findViewById(R.id.edtTxSondAluFrase);
        btAlterar = (Button) findViewById(R.id.btAlterar);

        dataBase = new DataBase(this,null,1);

        SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagemAluno = sondagemAlunoController.consultaSondagemAlunoPorId(id);

        edtTextSondAluPoli.setText(sondagemAluno.getPolissilaba());
        edtTextSondAluTri.setText(sondagemAluno.getTrissilaba());
        edtTextSondAluDi.setText(sondagemAluno.getDissilaba());
        edtTextSondAluMono.setText(sondagemAluno.getMonossilaba());
        edtTextSondAluFrase.setText(sondagemAluno.getFrase());

        btAlterar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v == btAlterar){

        }
    }
}
