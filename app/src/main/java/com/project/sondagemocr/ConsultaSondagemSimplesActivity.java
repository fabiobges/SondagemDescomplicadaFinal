package com.project.sondagemocr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sondagemocr.Controller.NivelController;
import com.project.sondagemocr.Controller.SondagemAlunoController;
import com.project.sondagemocr.Controller.SondagemModeloController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemAluno;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ConsultaSondagemSimplesActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txViewSondModPoli, txViewSondModTri, txViewSondModDi, txViewSondModMono, txViewSondModFrase, txViewSondModUti;
    EditText edtTextSondAluPoli ,edtTextSondAluTri, edtTextSondAluDi, edtTextSondAluMono, edtTextSondAluFrase;
    Button btAlterar, btRemover;
    Spinner spnHipoteseConsul;
    ImageView imgPoliConsul, imgTriConsul, imgDiConsul, imgMonoConsul,imgFraseConsul;

    DataBase dataBase;
    SondagemAluno sondagemAluno;
    NivelController nivelController;
    SondagemAlunoController sondagemAlunoController;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_sondagem_simples);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Consulta Sondagem Aluno");
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

        spnHipoteseConsul = (Spinner) findViewById(R.id.spnHipoteseConsul);
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
        imgPoliConsul = (ImageView) findViewById(R.id.imgPoliConsul);
        imgTriConsul = (ImageView) findViewById(R.id.imgTriConsul);
        imgDiConsul = (ImageView) findViewById(R.id.imgDiConsul);
        imgMonoConsul = (ImageView) findViewById(R.id.imgMonoConsul);
        imgFraseConsul = (ImageView) findViewById(R.id.imgFraseConsul);
        btAlterar = (Button) findViewById(R.id.btAlterar);
        btRemover = (Button) findViewById(R.id.btRemover);


        dataBase = new DataBase(this,null,1);

        sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagemAluno = sondagemAlunoController.consultaSondagemAlunoPorId(id);
        SondagemModeloController sondagemModeloController = new SondagemModeloController(dataBase);
        sondagemAluno.setSondagemModelo(sondagemModeloController.consultaSondagemModeloPorId(sondagemAluno.getSondagemModelo()));
        nivelController = new NivelController(dataBase);
        sondagemAluno.setNivel(nivelController.consultaNivelPorId(sondagemAluno.getNivel().getId()));


        txViewSondModUti.setText(txViewSondModUti.getText()+sondagemAluno.getSondagemModelo().getDescSondagemMod());
        txViewSondModPoli.setText(txViewSondModPoli.getText()+sondagemAluno.getSondagemModelo().getPolissilaba());
        txViewSondModTri.setText(txViewSondModTri.getText()+sondagemAluno.getSondagemModelo().getTrissilaba());
        txViewSondModDi.setText(txViewSondModDi.getText()+sondagemAluno.getSondagemModelo().getDissilaba());
        txViewSondModMono.setText(txViewSondModMono.getText()+sondagemAluno.getSondagemModelo().getMonossilaba());
        txViewSondModFrase.setText(txViewSondModFrase.getText()+sondagemAluno.getSondagemModelo().getFrase());
        edtTextSondAluPoli.setText(sondagemAluno.getPolissilaba());
        edtTextSondAluTri.setText(sondagemAluno.getTrissilaba());
        edtTextSondAluDi.setText(sondagemAluno.getDissilaba());
        edtTextSondAluMono.setText(sondagemAluno.getMonossilaba());
        edtTextSondAluFrase.setText(sondagemAluno.getFrase());


        if(sondagemAluno.getId() > 0) {
            File filePath = Environment.getExternalStorageDirectory();
            File dir = new File(filePath.getAbsolutePath() + "/Sondagem Descomplicada");
            dir.mkdir();
            try {
                for (int i = 1; i < 6; i++) {
                    File file = new File(dir+"/" + sondagemAluno.getId() + "_" + i + ".png");
                    Log.i("Valor I:", "" + i);
                    if (file.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        if (myBitmap != null){
                            if (i == 1) {
                                imgMonoConsul.setImageBitmap(myBitmap);
                            } else if (i == 2) {
                                imgDiConsul.setImageBitmap(myBitmap);
                            } else if (i == 3) {
                                imgTriConsul.setImageBitmap(myBitmap);
                            } else if (i == 4) {
                                imgPoliConsul.setImageBitmap(myBitmap);
                            } else if (i == 5) {
                                imgFraseConsul.setImageBitmap(myBitmap);
                            }
                        }
                    } else {
                        Log.i("Script", "Imagem não foi encontrada no SdCard!");
                    }

                }

            } catch (Exception ex) {
                ex.getStackTrace();
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHipoteseConsul.setAdapter(arrayAdapter);
        //arrayAdapter.add(sondagemAluno.getNivel().getNome());
        arrayAdapter.add("Alfabético");
        arrayAdapter.add("Silábico Alfabético");
        arrayAdapter.add("Silábico com Valor Sonoro");
        arrayAdapter.add("Silábico sem Valor Sonoro");
        arrayAdapter.add("Pré-Silábico");

        if(sondagemAluno.getNivel().getNome().equals("Alfabético")) {
            spnHipoteseConsul.setSelection(0);
        }else if(sondagemAluno.getNivel().getNome().equals("Silábico Alfabético")){
            spnHipoteseConsul.setSelection(1);
        }else if(sondagemAluno.getNivel().getNome().equals("Silábico com Valor Sonoro")){
            spnHipoteseConsul.setSelection(2);
        }else if(sondagemAluno.getNivel().getNome().equals("Silábico sem Valor Sonoro")){
            spnHipoteseConsul.setSelection(3);
        }else{
            spnHipoteseConsul.setSelection(4);
        }

        btAlterar.setOnClickListener(this);
        btRemover.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view == btAlterar){
            sondagemAluno.setPolissilaba(edtTextSondAluPoli.getText().toString());
            sondagemAluno.setTrissilaba(edtTextSondAluTri.getText().toString());
            sondagemAluno.setDissilaba(edtTextSondAluDi.getText().toString());
            sondagemAluno.setMonossilaba(edtTextSondAluMono.getText().toString());
            sondagemAluno.setFrase(edtTextSondAluFrase.getText().toString());
            sondagemAluno.setNivel(nivelController.consultaNivelPorNome(spnHipoteseConsul.getSelectedItem().toString()));
            if(sondagemAlunoController.alteraSondagemAluno(sondagemAluno)==true) {
                chamaToast("Alteração foi realizada!");
                Intent intent = new Intent(this, MenuSondagemActivity.class);
                startActivity(intent);
            }

        }else if(view == btRemover){
            try {
                SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
                sondagemAlunoController.removeSondagemAlunoPorId(sondagemAluno.getId());
                chamaToast("A sondagem foi removida com sucesso!");
                Intent intent = new Intent(this, MenuSondagemActivity.class);
                startActivity(intent);
            }catch (Exception ex){}

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

    public void chamaToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }



}
