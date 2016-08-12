package com.project.sondagemocr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.project.sondagemocr.Controller.AlunoController;
import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Turma;


//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

public class CadastroSondagemActivity extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private Bitmap bitmapMono;
    private Bitmap bitmapDissi;
    private Bitmap bitmapTri;
    private Bitmap bitmapPoli;
    private Bitmap bitmapFrase;
    private String escritaMono;
    private TextView textViewMono;
    private TextView textViewDissi;
    private TextView textViewTri;
    private TextView textViewPoli;
    private TextView textViewFrase;
    private FragmentManager fragManag = getSupportFragmentManager();
    private Spinner spnTurma;
    private Spinner spnAluno;

    private DataBase dataBase;
    private TurmaController turmaController;
    private Turma turma;
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sondagem);

        //Caso seja a primeira vez que a activity foi instanciada
        //automaticamante a MonoFragment será convocada
        if(savedInstanceState == null){
            MonoFragment fragMono = new MonoFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            fragTrans.add(R.id.layout_frag,fragMono,"fragmono");
            fragTrans.commit();
        }

        turma = new Turma();

        textViewMono = (TextView) findViewById(R.id.mono_text) ;
        textViewDissi = (TextView) findViewById(R.id.di_text);
        textViewTri = (TextView) findViewById(R.id.tri_text);
        textViewPoli = (TextView) findViewById(R.id.poli_text);
        textViewFrase = (TextView) findViewById(R.id.frase_text);
        spnTurma = (Spinner) findViewById(R.id.spinner_turma);
        spnAluno = (Spinner) findViewById(R.id.spinner_aluno);

        textViewMono.setOnClickListener(this);
        textViewDissi.setOnClickListener(this);
        textViewTri.setOnClickListener(this);
        textViewPoli.setOnClickListener(this);
        textViewFrase.setOnClickListener(this);

        dataBase = new DataBase(this,null,1);
        turmaController = new TurmaController(dataBase);
        ArrayAdapter<String> adpTurma = turmaController.consultaTurma(this);
        adpTurma.insert("Turma",0);
        spnTurma.setAdapter(adpTurma);

        spnAluno.setEnabled(false);

        spnTurma.setOnItemSelectedListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v == textViewMono){
            //Instanciando MonoFragment e enviando dados já existentes
            textViewMono.setBackgroundColor(1);
            MonoFragment fragMono = new MonoFragment();
            FragmentTransaction  fragTrans = fragManag.beginTransaction();
            Log.i("Script","Mono button");
            fragTrans.replace(R.id.layout_frag,fragMono,"fragmono");
            fragTrans.commit();
            if(bitmapMono != null){
               Log.i("Script","Transferiu bitmap para MonoFragment");
                MonoFragment.bitmap = bitmapMono;
                MonoFragment.strEscritaMono = escritaMono;
            }
            String palavra = null;


        }else if(v == textViewDissi){
            //Instanciando DissiFragment e enviando dados já existentes
            DissiFragment fragDissi = new DissiFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            Log.i("Script","Dissi button");
            fragTrans.replace(R.id.layout_frag,fragDissi,"fragdissi");
            fragTrans.commit();
            if(bitmapDissi != null){
                Log.i("Script","Transferiu bitmap para DissiFragment");
                DissiFragment.bitmap = bitmapDissi;
            }
        }else if(v == textViewTri){
            //Instanciando TriFragment e enviando dados já existentes
            TriFragment triFragment = new TriFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            fragTrans.replace(R.id.layout_frag,triFragment,"fragtri");
            fragTrans.commit();
            if(bitmapTri != null){
                Log.i("Script","Transferiu bitmap para TriFragment");
                TriFragment.bitmap = bitmapTri;
            }
        }else if(v == textViewPoli){
            //Instanciando PoliFragment e enviando dados já existentes
            PoliFragment poliFragment = new PoliFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            fragTrans.replace(R.id.layout_frag,poliFragment,"fragpoli");
            fragTrans.commit();
            if(bitmapTri != null){
                Log.i("Script","Transferiu bitmap para PoliFragment");
                PoliFragment.bitmap = bitmapPoli;
            }
        }else if(v == textViewFrase){
            //Instanciando FraseFragment e enviando dados já existentes
            FraseFragment fraseFragment = new FraseFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            fragTrans.replace(R.id.layout_frag,fraseFragment,"fragfrase");
            fragTrans.commit();
            if(bitmapTri != null){
                Log.i("Script","Transferiu bitmap para TriFragment");
                FraseFragment.bitmap = bitmapFrase;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Script","onDestroy da Activity");
    }


    //Recebendo dados de cada um dos fragments
    public void salvarDadosFragmentMono(){
        MonoFragment monoFragment = (MonoFragment) fragManag.findFragmentByTag("fragmono");
        bitmapMono = monoFragment.bitmap;
        escritaMono = monoFragment.strEscritaMono;
    }

    public void salvarDadosFragmentDissi(){
        DissiFragment dissiFragment = (DissiFragment) fragManag.findFragmentByTag("fragdissi");
        bitmapDissi = dissiFragment.bitmap;
    }

    public void salvarDadosFragmentTri(){
        TriFragment triFragment = (TriFragment) fragManag.findFragmentByTag("fragtri");
        bitmapTri = triFragment.bitmap;
    }

    public void salvarDadosFragmentPoli(){
        PoliFragment poliFragment = (PoliFragment) fragManag.findFragmentByTag("fragpoli");
        bitmapPoli = poliFragment.bitmap;
    }

    public void salvarDadosFragmentFrase(){
        FraseFragment fraseFragment = (FraseFragment) fragManag.findFragmentByTag("fragfrase");
        bitmapFrase = fraseFragment.bitmap;
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == spnTurma){
            if(!spnTurma.getSelectedItem().equals("Turma")){
                //Verificando se spinner de turma foi selecionada, caso tenha sido escolhido turma
                //os alunos da turma seão consultados no Banco de dados
                turma.setIdentificador(parent.getSelectedItem().toString());
                turma = turmaController.consultaTurmaId(turma);
                AlunoController alunoController = new AlunoController(dataBase);
                ArrayAdapter<String> adpAluno = alunoController.cosultaAlunoTurma(this, turma);
                spnAluno.setEnabled(true);
                spnAluno.setAdapter(adpAluno);

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}




}
