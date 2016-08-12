package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    Button btCadastra, btCancela;
    ImageButton imgAjuda;
    SondagemModelo sondagemModelo;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sondagem_mod);

        dataBase = new DataBase(this, null,2);

        edtPolissilba = (EditText) findViewById(R.id.edtPoliModelo);
        edtTrissilaba = (EditText) findViewById(R.id.edtTriModelo);
        edtDissilaba = (EditText) findViewById(R.id.edtDiModelo);
        edtMonossilaba = (EditText) findViewById(R.id.edtMonoModelo);
        edtFrase = (EditText) findViewById(R.id.edtFraseModelo);
        imgAjuda = (ImageButton) findViewById(R.id.imgAjuda);
        btCancela = (Button) findViewById(R.id.btnCancelarSondModelo);
        btCadastra = (Button) findViewById(R.id.btnCadastroSondModelo);


        btCancela.setOnClickListener(this);
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
        }else if(v == btCancela){
            makeToast("O cadastro de sondagem modelo foi cancelada!");
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
