package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.project.sondagemocr.Controller.SondagemModeloController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemModelo;


public class CadastroSondagemModActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtPolissilba;
    EditText edtTrissilaba;
    EditText edtDissilaba;
    EditText edtMonossilaba;
    EditText edtFrase;
    EditText edtIdentSondMod;
    FloatingActionButton btCadastra, imgAjuda, btnCadastroSondModelo, plus;
    Animation FabOpen, FabClose, FabClockwise, FabAnticlockwise;
    boolean isOpen = false;
    SondagemModelo sondagemModelo;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sondagem_mod);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cadastro Sondagem Modelo");
        setSupportActionBar(toolbar);

        plus = (FloatingActionButton)findViewById(R.id.plus);
        btnCadastroSondModelo = (FloatingActionButton)findViewById(R.id.btnCadastroSondModelo);
        imgAjuda = (FloatingActionButton)findViewById(R.id.imgAjuda);
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabAnticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    imgAjuda.startAnimation(FabClose);
                    btnCadastroSondModelo.startAnimation(FabClose);
                    plus.startAnimation(FabAnticlockwise);
                    btnCadastroSondModelo.setClickable(false);
                    imgAjuda.setClickable(false);
                    isOpen = false;
                }
                else{
                    imgAjuda.startAnimation(FabOpen);
                    btnCadastroSondModelo.startAnimation(FabOpen);
                    plus.startAnimation(FabClockwise);
                    btnCadastroSondModelo.setClickable(true);
                    imgAjuda.setClickable(true);
                    isOpen = true;
                }
            }
        });


        dataBase = new DataBase(this, null,2);

        edtIdentSondMod = (EditText) findViewById(R.id.edtIdentSondMod);
        edtPolissilba = (EditText) findViewById(R.id.edtPoliModelo);
        edtTrissilaba = (EditText) findViewById(R.id.edtTriModelo);
        edtDissilaba = (EditText) findViewById(R.id.edtDiModelo);
        edtMonossilaba = (EditText) findViewById(R.id.edtMonoModelo);
        edtFrase = (EditText) findViewById(R.id.edtFraseModelo);
        imgAjuda = (FloatingActionButton) findViewById(R.id.imgAjuda);
        btCadastra = (FloatingActionButton) findViewById(R.id.btnCadastroSondModelo);

        btCadastra.setOnClickListener(this);
        imgAjuda.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v == btCadastra){
            insereSondagemModelo();
            SondagemModeloController sondModCont = new SondagemModeloController(dataBase);
            sondModCont.insereSondagemModelo(sondagemModelo);
            makeToast("A sondagem modelo foi cadastrada!");
            Intent intent = new Intent(this,MenuSondagemActivity.class);
            startActivity(intent);
        }else if(v == imgAjuda){
            makeToast("Aviso!");
            Intent intent = new Intent(this,AjudaSondagemActivity.class);
            startActivity(intent);
        }
    }

    public void insereSondagemModelo(){
        sondagemModelo = new SondagemModelo();
        sondagemModelo.setDescSondagemMod(edtIdentSondMod.getText().toString());
        sondagemModelo.setPolissilaba(edtPolissilba.getText().toString());
        sondagemModelo.setTrissilaba(edtTrissilaba.getText().toString());
        sondagemModelo.setDissilaba(edtDissilaba.getText().toString());
        sondagemModelo.setMonossilaba(edtMonossilaba.getText().toString());
        sondagemModelo.setFrase(edtFrase.getText().toString());
    }

    public void makeToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
