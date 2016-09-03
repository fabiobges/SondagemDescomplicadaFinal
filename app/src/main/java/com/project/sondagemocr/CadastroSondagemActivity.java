package com.project.sondagemocr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.project.sondagemocr.Adapters.MyFragmentPagerStateAdapter;

import com.project.sondagemocr.Controller.SondagemModeloController;
import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemModelo;
import com.project.sondagemocr.Pojo.Turma;




//public class CadastroSondagemActivity extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
public class CadastroSondagemActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener{

    private TabLayout mTabLayout_cadastro_sondagem;
    private ViewPager mViewPager_cadastro_sondagem;
    private FloatingActionButton fabMenuSondagem;

    public static SondagemModelo sondagemModelo;

    private Bitmap bitmapMono;
    private Bitmap bitmapDissi;
    private Bitmap bitmapTri;
    private Bitmap bitmapPoli;
    private Bitmap bitmapFrase;
    private String escritaMono;
    private String escritaDissi;
    private String escritaTri;
    private String escritaPoli;
    private String escritaFrase;
    private TextView textViewMono;
    private TextView textViewDissi;
    private TextView textViewTri;
    private TextView textViewPoli;
    private TextView textViewFrase;
    private TextView textViewResultado;
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

        mTabLayout_cadastro_sondagem = (TabLayout) findViewById(R.id.tab_layout_cadastro_sondagem);
        mViewPager_cadastro_sondagem = (ViewPager) findViewById(R.id.view_pager_cadastro_sondagem);
        fabMenuSondagem = (FloatingActionButton) findViewById(R.id.fabMenuSondagem);

        fabMenuSondagem.setOnClickListener(this);

        mViewPager_cadastro_sondagem.setAdapter(new MyFragmentPagerStateAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titles_tab_cadastro_sondagem)));
        mTabLayout_cadastro_sondagem.setupWithViewPager(mViewPager_cadastro_sondagem);

        mTabLayout_cadastro_sondagem.setOnTabSelectedListener(this);

        Log.i("Script :","zdas2222");


        //Caso seja a primeira vez que a activity foi instanciada
        //automaticamante a MonoFragment será convocada
 /*       if(savedInstanceState == null){
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
        textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        spnTurma = (Spinner) findViewById(R.id.spinner_turma);
        spnAluno = (Spinner) findViewById(R.id.spinner_aluno);

        textViewMono.setOnClickListener(this);
        textViewDissi.setOnClickListener(this);
        textViewTri.setOnClickListener(this);
        textViewPoli.setOnClickListener(this);
        textViewFrase.setOnClickListener(this);
        textViewResultado.setOnClickListener(this);

        dataBase = new DataBase(this,null,1);
        turmaController = new TurmaController(dataBase);
        ArrayAdapter<String> adpTurma = turmaController.consultaTurma(this);
        adpTurma.insert("Turma",0);
        spnTurma.setAdapter(adpTurma);

        spnAluno.setEnabled(false);

        spnTurma.setOnItemSelectedListener(this);

*/

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.i("Script :","zdas4444: "+tab.getPosition());
        if(tab.getPosition() == 6){
            escritaPoli = PoliFragment.edtPoli.getText().toString();
            ResultadoFragment.textAlunoPoli.setText(escritaPoli.replaceAll(" ",""));  //Tratando qualquer espaço que Usuário deixe
            escritaTri = TriFragment.edtTri.getText().toString();
            ResultadoFragment.textAlunoTri.setText(escritaTri.replaceAll(" ",""));
            escritaDissi = DissiFragment.edtDissi.getText().toString();
            ResultadoFragment.textAlunoDissi.setText(escritaDissi.replaceAll(" ",""));
            escritaMono = MonoFragment.edtTextMono.getText().toString();
            ResultadoFragment.textAlunoMono.setText(escritaMono.replaceAll(" ",""));
            ResultadoFragment.textAlunoFrase.setText(FraseFragment.edtFrase.getText());
            ResultadoFragment.textModeloPoli.setText(sondagemModelo.getPolissilaba());
            ResultadoFragment.textModeloTri.setText(sondagemModelo.getTrissilaba());
            ResultadoFragment.textModeloDissi.setText(sondagemModelo.getDissilaba());
            ResultadoFragment.textModeloMono.setText(sondagemModelo.getMonossilaba());
            ResultadoFragment.textModeloFrase.setText(sondagemModelo.getFrase());
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        if(v == fabMenuSondagem){
            if(mTabLayout_cadastro_sondagem.getSelectedTabPosition()==0){
                if(!IdentificacaoFragment.spnSondagemModelo.getSelectedItem().equals("Sondagem Modelo")){
                    SondagemModeloController sondagemModeloController = new SondagemModeloController(new DataBase(this,null,1));
                    sondagemModelo = new SondagemModelo();
                    sondagemModelo.setDescSondagemMod(IdentificacaoFragment.spnSondagemModelo.getSelectedItem().toString());
                    sondagemModelo = sondagemModeloController.consultaSondagemModeloPorIdentificador(this,sondagemModelo);
                    mViewPager_cadastro_sondagem.setCurrentItem(1);

                }else {
                    Toast.makeText(this,"A sondagem modelo não foi selecionada!",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }









/*
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
                DissiFragment.strEscritaDissi = escritaDissi;
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
                TriFragment.strEscritaTri = escritaTri;
            }
        }else if(v == textViewPoli){
            //Instanciando PoliFragment e enviando dados já existentes
            PoliFragment poliFragment = new PoliFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            fragTrans.replace(R.id.layout_frag,poliFragment,"fragpoli");
            fragTrans.commit();
            if(bitmapPoli != null){
                Log.i("Script","Transferiu bitmap para PoliFragment");
                PoliFragment.bitmap = bitmapPoli;
                PoliFragment.strEscritaPoli = escritaPoli;
            }
        }else if(v == textViewFrase){
            //Instanciando FraseFragment e enviando dados já existentes
            FraseFragment fraseFragment = new FraseFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            fragTrans.replace(R.id.layout_frag,fraseFragment,"fragfrase");
            fragTrans.commit();
            if(bitmapFrase != null){
                Log.i("Script","Transferiu bitmap para TriFragment");
                FraseFragment.bitmap = bitmapFrase;
                FraseFragment.strEscritaFrase = escritaFrase;
            }
        }else if(v == textViewResultado){
            ResultadoFragment resultadoFragment = new ResultadoFragment();
            FragmentTransaction fragTrans = fragManag.beginTransaction();
            fragTrans.replace(R.id.layout_frag,resultadoFragment,"fragresultado");
            fragTrans.commit();
            ResultadoFragment.strAlunoPoli = escritaPoli;
            ResultadoFragment.strAlunoTri = escritaTri;
            ResultadoFragment.strAlunoDissi = escritaDissi;
            ResultadoFragment.strAlunoMono = escritaMono;
            ResultadoFragment.strAlunoFrase = escritaFrase;
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
        escritaDissi = dissiFragment.strEscritaDissi;
    }

    public void salvarDadosFragmentTri(){
        TriFragment triFragment = (TriFragment) fragManag.findFragmentByTag("fragtri");
        bitmapTri = triFragment.bitmap;
        escritaTri = triFragment.strEscritaTri;
    }

    public void salvarDadosFragmentPoli(){
        PoliFragment poliFragment = (PoliFragment) fragManag.findFragmentByTag("fragpoli");
        bitmapPoli = poliFragment.bitmap;
        escritaPoli = poliFragment.strEscritaPoli;
    }

    public void salvarDadosFragmentFrase(){
        FraseFragment fraseFragment = (FraseFragment) fragManag.findFragmentByTag("fragfrase");
        bitmapFrase = fraseFragment.bitmap;
        escritaFrase = fraseFragment.strEscritaFrase;
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

*/


}
