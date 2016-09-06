package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class AjudaSondagemActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton imgVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_sondagem);

        imgVolta = (FloatingActionButton) findViewById(R.id.imgVolta);

        imgVolta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==imgVolta){
            Intent intent = new Intent(this,CadastroSondagemModActivity.class);
            startActivity(intent);
        }
    }
}
