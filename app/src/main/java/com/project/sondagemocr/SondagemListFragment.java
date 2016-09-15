package com.project.sondagemocr;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sondagemocr.Controller.SondagemAlunoController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemAluno;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SondagemListFragment extends ListFragment  {

    ArrayList<SondagemAluno> sondagensAlunos;
    List<Map<String, Object>> listSondagens;
    DataBase dataBase;


    TextView tx;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        listSondagens = new ArrayList<Map<String, Object>>();
        Context myContext = getActivity();
        dataBase = new DataBase(myContext, null, 1);
        //preencheListSondagens();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View roofView = inflater.inflate(R.layout.fragment_view_sondagem, container, false);

        return roofView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //load data
        sondagensAlunos = new ArrayList<SondagemAluno>();
        SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunos();


        try {

            for (SondagemAluno sondagemAluno : sondagensAlunos) {

                Map<String, Object> item = new HashMap<String, Object>();

                item.put("id",sondagemAluno.getId());
                item.put("data", sondagemAluno.getData());
                item.put("nomeAluno", sondagemAluno.getAluno().getNome());
                item.put("turma", sondagemAluno.getAluno().getTurma().getIdentificador());


                listSondagens.add(item);
            }


            String[] de = {"id", "data", "nomeAluno", "turma"};
            int[] para = {R.id.txViewIdSond,R.id.txViewDataSond, R.id.txViewNomeAluno,
                    R.id.txViewTurma};

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), listSondagens,
                    R.layout.fragment_sondagem, de, para);

            setListAdapter(adapter);


        } catch (Exception ex) {
            Log.e("Error preencher: ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void preencheListSondagens() {

        sondagensAlunos = new ArrayList<SondagemAluno>();
        SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunos();


        try {

            for (SondagemAluno sondagemAluno : sondagensAlunos) {

                Map<String, Object> item = new HashMap<String, Object>();

                item.put("id",sondagemAluno.getId());
                item.put("data", sondagemAluno.getData());
                item.put("nomeAluno", sondagemAluno.getAluno().getNome());
                item.put("turma", sondagemAluno.getAluno().getTurma().getIdentificador());


                listSondagens.add(item);
            }


            String[] de = {"id","data", "nomeAluno", "turma"};
            int[] para = {R.id.txViewIdSond, R.id.txViewDataSond, R.id.txViewNomeAluno,
                    R.id.txViewTurma};

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), listSondagens,
                    R.layout.fragment_sondagem, de, para);

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
        TextView tx = (TextView) viewGroup.findViewById(R.id.txViewIdSond);
        Log.i("Script","OnListItemClickk"+tx.getText().toString());
        Log.i("Script","dsad"+tx.getText().toString());
        Toast.makeText(getActivity(), "Consultando Sondagem de Aluno", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),ConsultaSondagemSimples.class);

        intent.putExtra("id_sondagem_aluno",tx.getText().toString());
        startActivity(intent);

    }



}


