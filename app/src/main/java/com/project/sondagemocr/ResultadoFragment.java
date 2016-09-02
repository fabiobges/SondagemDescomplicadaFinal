package com.project.sondagemocr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.project.sondagemocr.Sondagem.AnalisePalavras;

import org.w3c.dom.Text;

public class ResultadoFragment extends Fragment implements View.OnClickListener {

    public static TextView textAlunoPoli;
    public static TextView textAlunoTri;
    public static TextView textAlunoDissi;
    public static TextView textAlunoMono;
    public static TextView textAlunoFrase;
    public static TextView textModeloPoli;
    public static TextView textModeloTri;
    public static TextView textModeloDissi;
    public static TextView textModeloMono;
    public static TextView textModeloFrase;
    private Button btGravar;

    public AnalisePalavras analisePalavras;

    public int alfabetico = 0,silabAlfabetico = 0,silabComValor = 0,silabSemValor = 0,preSilabico = 0;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.resultado_fragment, null);

        textAlunoPoli = (TextView) view.findViewById(R.id.textAlunoPoli);
        textAlunoTri = (TextView) view.findViewById(R.id.textAlunoTri);
        textAlunoDissi = (TextView) view.findViewById(R.id.textAlunoDissi);
        textAlunoMono = (TextView) view.findViewById(R.id.textAlunoMono);
        textAlunoFrase = (TextView) view.findViewById(R.id.textAlunoFrase);
        textModeloPoli = (TextView) view.findViewById(R.id.textModeloPoli);
        textModeloTri = (TextView) view.findViewById(R.id.textModeloTri);
        textModeloDissi = (TextView) view.findViewById(R.id.textModeloDissi);
        textModeloMono = (TextView) view.findViewById(R.id.textModeloMono);
        textModeloFrase = (TextView) view.findViewById(R.id.textModeloFrase);
        btGravar = (Button) view.findViewById(R.id.btCadastraResultadoSondagem);

        btGravar.setOnClickListener(this);


        //analisePalavras = new AnalisePalavras(textModeloFrase.getText().toString(),textAlunoFrase.getText().toString());




        return view;

    }


    public void sondagemAvaliacao(){

        if(analisePalavras.alfabetico() == true){
            alfabetico++;
            Log.i("Sondagem: ","Alfabético");
        }else{
            if(analisePalavras.silabicoAlfabetico() == true){
                silabAlfabetico++;
                Log.i("Sondagem: ","Silábico Alfabético");
            }else{
                if(analisePalavras.silabicoComValor() == true){
                    silabComValor++;
                    Log.i("Sondagem: ","Silábico com Valor");
                }else{
                    if(analisePalavras.silabicoSemValor() == true){
                        silabSemValor++;
                        Log.i("Sondagem: ","Silábico sem Valor");
                    }else {
                        preSilabico++;
                        Log.i("Sondagem: ","Pré-Silábico");
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        if(v == btGravar){
            analisePalavras = new AnalisePalavras(textModeloPoli.getText().toString(),textAlunoPoli.getText().toString());
            sondagemAvaliacao();
            analisePalavras = new AnalisePalavras(textModeloTri.getText().toString(),textAlunoTri.getText().toString());
            sondagemAvaliacao();
            analisePalavras = new AnalisePalavras(textModeloDissi.getText().toString(),textAlunoDissi.getText().toString());
            sondagemAvaliacao();
            analisePalavras = new AnalisePalavras(textModeloMono.getText().toString(),textAlunoMono.getText().toString());
            sondagemAvaliacao();
        }
    }


/*

    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentPoli();
        super.onDestroy();
        Log.i("Script","onDestroy Resultado Fragment");
    }*/

}
