package com.project.sondagemocr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultadoFragment extends Fragment {

    public static TextView textAlunoPoli;
    public static TextView textAlunoTri;
    public static TextView textAlunoDissi;
    public static TextView textAlunoMono;
    public static TextView textAlunoFrase;
    private TextView textModeloPoli;
    private TextView textModeloTri;
    private TextView textModeloDissi;
    private TextView textModeloMono;
    private TextView textModeloFrase;


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


        return view;

    }




/*

    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentPoli();
        super.onDestroy();
        Log.i("Script","onDestroy Resultado Fragment");
    }*/

}
