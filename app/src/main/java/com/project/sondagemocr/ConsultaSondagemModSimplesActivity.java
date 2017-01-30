package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.sondagemocr.Controller.SondagemModeloController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemModelo;

public class ConsultaSondagemModSimplesActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtIdent,edtMono,edtDissi,edtTri,edtPoli,edtFrase;
    private Button btRemoverModelo;
    private SondagemModeloController sondagemModeloController;
    private SondagemModelo sondagemModelo;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_sondagem_mod_simples);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Consulta Sondagem Modelo");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtIdent = (EditText) findViewById(R.id.edtConsIdentSondMod);
        edtMono = (EditText) findViewById(R.id.edtConsMonoModelo);
        edtDissi = (EditText) findViewById(R.id.edtConsDiModelo);
        edtTri = (EditText) findViewById(R.id.edtConsTriModelo);
        edtPoli = (EditText) findViewById(R.id.edtConsPoliModelo);
        edtFrase = (EditText) findViewById(R.id.edtConsFraseModelo);
        btRemoverModelo = (Button) findViewById(R.id.btRemoverModelo);

        Bundle bundle = getIntent().getExtras();
        int id = 0;
        if(bundle.containsKey("id_modelo")){
            id = Integer.parseInt(bundle.getString("id_modelo"));
            Log.i("Script","Contem o ID "+id);
        }

        dataBase = new DataBase(this,null,1);
        sondagemModeloController = new SondagemModeloController(dataBase);
        sondagemModelo = new SondagemModelo();
        sondagemModelo.setId(id);
        sondagemModelo = sondagemModeloController.consultaSondagemModeloPorId(sondagemModelo);

        edtIdent.setText(sondagemModelo.getDescSondagemMod());
        edtMono.setText(sondagemModelo.getMonossilaba());
        edtDissi.setText(sondagemModelo.getDissilaba());
        edtTri.setText(sondagemModelo.getTrissilaba());
        edtPoli.setText(sondagemModelo.getPolissilaba());
        edtFrase.setText(sondagemModelo.getFrase());

        btRemoverModelo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btRemoverModelo){
            sondagemModeloController = new SondagemModeloController(dataBase);
            sondagemModeloController.removeSondagemModelo(sondagemModelo);
            Toast.makeText(this,"A sondagem modelo foi removida!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MenuSondagemActivity.class);
            startActivity(intent);
        }
    }
}
