package com.project.sondagemocr;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sondagemocr.Controller.SondagemModeloController;
import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemModelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SondagemModListFragment extends ListFragment  {

    EditText editTxAlunoConsul;
    Spinner spnTurmaConsul, spnAnoConsul;
    ArrayList<SondagemModelo> sondagensModelos;
    List<Map<String, Object>> listSondagensMod;
    DataBase dataBase;
    SondagemModeloController sondagemModeloController;
    TurmaController turmaController;
    ArrayAdapter<String> anoAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        listSondagensMod = new ArrayList<Map<String, Object>>();
        Context myContext = getActivity();
        dataBase = new DataBase(myContext, null, 1);
        //preencheListSondagens();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View roofView = inflater.inflate(R.layout.fragment_view_sondagem_modelo, container, false);


        return roofView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Carregando dados
        sondagensModelos = new ArrayList<SondagemModelo>();
        sondagemModeloController = new SondagemModeloController(dataBase);
        sondagensModelos = sondagemModeloController.consultaSondagemModeloArray();


        try {

            for (SondagemModelo sondagemModelo : sondagensModelos) {

                Map<String, Object> item = new HashMap<String, Object>();

                item.put("id",sondagemModelo.getId());
                item.put("sondMod", sondagemModelo.getDescSondagemMod());


                listSondagensMod.add(item);
            }


            String[] de = {"id", "sondMod"};
            int[] para = {R.id.idModelo,R.id.descModelo};

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), listSondagensMod,
                    R.layout.fragment_sondagem_modelo, de, para);

            setListAdapter(adapter);


        } catch (Exception ex) {
            Log.e("Error preencher: ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    //Caso queira preencher lista com método de reaproveitamento de código
    public void preencheList(){
        //Carregando dados
        sondagensModelos = new ArrayList<SondagemModelo>();
        sondagemModeloController = new SondagemModeloController(dataBase);
        sondagensModelos = sondagemModeloController.consultaSondagemModeloArray();


        try {

            for (SondagemModelo sondagemModelo : sondagensModelos) {

                Map<String, Object> item = new HashMap<String, Object>();

                item.put("id",sondagemModelo.getId());
                item.put("sondMod", sondagemModelo.getDescSondagemMod());


                listSondagensMod.add(item);
            }


            String[] de = {"id", "sondMod"};
            int[] para = {R.id.txViewIdSond,R.id.txViewDataSond};

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), listSondagensMod,
                    R.layout.fragment_sondagem_modelo, de, para);

            setListAdapter(adapter);


        } catch (Exception ex) {
            Log.e("Error preencher: ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView,view,position,id);
        ViewGroup viewGroup = (ViewGroup) view;
        TextView tx = (TextView) viewGroup.findViewById(R.id.idModelo);
        Log.i("Script","OnListItemClick"+tx.getText().toString());
        Toast.makeText(getActivity(), "Consultando Sondagem Modelo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),ConsultaSondagemModSimplesActivity.class);

        intent.putExtra("id_modelo",tx.getText().toString());
        startActivity(intent);

    }



}
