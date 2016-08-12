package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

public class MenuSondagemActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button btnSondagem;
    private Button btnSondagemModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sondagem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        btnSondagem = (Button) findViewById(R.id.btSondagem);
        btnSondagemModelo = (Button) findViewById(R.id.btSondagemModelo);
        btnSondagem.setOnClickListener(this);
        btnSondagemModelo.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v == btnSondagem){
            Intent intent = new Intent(this,CadastroSondagemActivity.class);
            startActivity(intent);
        }else {
            if (v == btnSondagemModelo) {
                Intent intent = new Intent(this,CadastroSondagemModActivity.class);
                startActivity(intent);
            }
        }
    }
}
