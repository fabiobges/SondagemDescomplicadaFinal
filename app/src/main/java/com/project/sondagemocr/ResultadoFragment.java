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


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.resultado_fragment, null);





        return view;

    }




    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentPoli();
        super.onDestroy();
        Log.i("Script","onDestroy Resultado Fragment");
    }

}
