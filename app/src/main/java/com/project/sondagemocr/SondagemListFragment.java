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

import com.project.sondagemocr.Controller.SondagemAlunoController;
import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.SondagemAluno;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SondagemListFragment extends ListFragment  implements  AdapterView.OnItemSelectedListener {

    EditText editTxAlunoConsul;
    Spinner spnTurmaConsul, spnAnoConsul;
    ArrayList<SondagemAluno> sondagensAlunos;
    List<Map<String, Object>> listSondagens;
    DataBase dataBase;
    SondagemAlunoController sondagemAlunoController;
    TurmaController turmaController;
    ArrayAdapter<String> anoAdapter;


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

        editTxAlunoConsul = (EditText) roofView.findViewById(R.id.edtTxAlunoConsul);
        spnTurmaConsul = (Spinner) roofView.findViewById(R.id.spnTurmaConsul);
        spnAnoConsul = (Spinner) roofView.findViewById(R.id.spnAnoConsul);

        dataBase =  new DataBase(getActivity(),null,1);
        turmaController = new TurmaController(dataBase);

        anoAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item);
        anoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anoAdapter.insert("Ano",0);
        spnAnoConsul.setAdapter(anoAdapter);

        ArrayAdapter<String> turmaAdapter = turmaController.consultaTurma(getActivity());
        turmaAdapter.insert("Turma",0);
        spnTurmaConsul.setAdapter(turmaAdapter);

        //Ação ao digitar campo de editTxAlunoConsul
        editTxAlunoConsul.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                preencheListSondagensNomeAluno(spnTurmaConsul.getSelectedItem().toString(),
                        spnAnoConsul.getSelectedItem().toString(),
                        editTxAlunoConsul.getText().toString());

            }
        });
        spnTurmaConsul.setOnItemSelectedListener(this);
        spnAnoConsul.setOnItemSelectedListener(this);

        return roofView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Carregando dados
        sondagensAlunos = new ArrayList<SondagemAluno>();
        sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunos();


        try {

            for (SondagemAluno sondagemAluno : sondagensAlunos) {

                Map<String, Object> item = new HashMap<String, Object>();

                item.put("id",sondagemAluno.getId());
                item.put("data", sondagemAluno.getData());
                item.put("nomeAluno", sondagemAluno.getAluno().getNome());
                item.put("turma", sondagemAluno.getAluno().getTurma().getIdentificador());
                item.put("anoTurma", sondagemAluno.getAluno().getTurma().getAno());


                listSondagens.add(item);
            }


            String[] de = {"id", "data", "nomeAluno", "turma", "anoTurma"};
            int[] para = {R.id.txViewIdSond,R.id.txViewDataSond, R.id.txViewNomeAluno,
                    R.id.txViewTurma, R.id.txViewAnoTurma};

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), listSondagens,
                    R.layout.fragment_sondagem, de, para);

            setListAdapter(adapter);


        } catch (Exception ex) {
            Log.e("Error preencher: ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void preencheListSondagensNomeAluno(String identificacaoTurma,String anoTurma,String nomeAluno) {

        listSondagens =  new ArrayList<Map<String, Object>>();

        sondagensAlunos = new ArrayList<SondagemAluno>();
        SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunosPorNomeAluno(identificacaoTurma,anoTurma,nomeAluno);

        preencheList();

    }

    private void preencheListSondagensTurma(String identificacaoTurma,String nomeAluno) {

        listSondagens =  new ArrayList<Map<String, Object>>();

        sondagensAlunos = new ArrayList<SondagemAluno>();
        SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunosPorTurma(identificacaoTurma,nomeAluno);

        preencheList();

    }

    private void preencheListSondagensTurmaAno(String identificacaoTurma,String anoTurma,String nomeAluno) {

        listSondagens =  new ArrayList<Map<String, Object>>();

        sondagensAlunos = new ArrayList<SondagemAluno>();
        SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunosPorTurmaAno(identificacaoTurma,anoTurma,nomeAluno);

        preencheList();

    }

    public void preencheListSondagensGeral(){

        listSondagens =  new ArrayList<Map<String, Object>>();

        //Carregando dados
        sondagensAlunos = new ArrayList<SondagemAluno>();
        sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunos();

        preencheList();
    }

    public void preencheList(){
        try {

            for (SondagemAluno sondagemAluno : sondagensAlunos) {

                Map<String, Object> item = new HashMap<String, Object>();


                item.put("id",sondagemAluno.getId());
                item.put("data",  sondagemAluno.getData());
                item.put("nomeAluno", sondagemAluno.getAluno().getNome());
                item.put("turma", sondagemAluno.getAluno().getTurma().getIdentificador());
                item.put("anoTurma", sondagemAluno.getAluno().getTurma().getAno());


                listSondagens.add(item);
            }


            String[] de = {"id","data", "nomeAluno", "turma", "anoTurma"};
            int[] para = {R.id.txViewIdSond, R.id.txViewDataSond, R.id.txViewNomeAluno,
                    R.id.txViewTurma,  R.id.txViewAnoTurma};

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
        Log.i("Script","OnListItemClick"+tx.getText().toString());
        Log.i("Script","dsad"+tx.getText().toString());
        Toast.makeText(getActivity(), "Consultando Sondagem de Aluno", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),ConsultaSondagemSimplesActivity.class);

        intent.putExtra("id_sondagem_aluno",tx.getText().toString());
        startActivity(intent);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == spnTurmaConsul){
            if(!spnTurmaConsul.getSelectedItem().equals("Turma")){
                preencheListSondagensTurma(spnTurmaConsul.getSelectedItem().toString(),editTxAlunoConsul.getText().toString());
                anoAdapter = turmaController.consultaTurmaPorIdentificacao(getActivity(),spnTurmaConsul.getSelectedItem().toString());
                spnAnoConsul.setAdapter(anoAdapter);
            }else if(spnTurmaConsul.getSelectedItem().equals("Turma")){
                anoAdapter.clear();
                anoAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item);
                anoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                anoAdapter.add("Ano");
                spnAnoConsul.setAdapter(anoAdapter);
                preencheListSondagensGeral();
            }
        }else if(parent == spnAnoConsul){
            if(!spnAnoConsul.getSelectedItem().equals("Ano")){
                preencheListSondagensTurmaAno(spnTurmaConsul.getSelectedItem().toString(),spnAnoConsul.getSelectedItem().toString(),editTxAlunoConsul.getText().toString());
            }
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}


