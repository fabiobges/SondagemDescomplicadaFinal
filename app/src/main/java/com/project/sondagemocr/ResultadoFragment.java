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

public class ResultadoFragment extends Fragment {

    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.resultado_fragment, null);

        gridView = (GridView) view.findViewById(R.id.gridView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        return view;

    }




    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentPoli();
        super.onDestroy();
        Log.i("Script","onDestroy Resultado Fragment");
    }

}
