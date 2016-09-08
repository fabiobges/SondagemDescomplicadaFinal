package com.project.sondagemocr;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.project.sondagemocr.Controller.AlunoController;
import com.project.sondagemocr.Controller.SondagemModeloController;
import com.project.sondagemocr.Controller.TurmaController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Turma;


public class IdentificacaoFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    public static Spinner spnTurma,spnAluno,spnSondagemModelo;
    private TurmaController turmaController;
    private DataBase dataBase;
    private SondagemModeloController sondagemModeloController;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.identificacao_fragment, null);
        context = container.getContext();

        spnTurma = (Spinner) view.findViewById(R.id.spnTurma);
        spnAluno = (Spinner) view.findViewById(R.id.spnAluno);
        spnSondagemModelo = (Spinner) view.findViewById(R.id.spnSondagemMod);

        dataBase = new DataBase(container.getContext(),null,1);

        sondagemModeloController = new SondagemModeloController(dataBase);
        ArrayAdapter<String> adapterModelo = sondagemModeloController.consultaSondagemModelo(container.getContext());
        adapterModelo.insert("Sondagem Modelo",0);
        spnSondagemModelo.setAdapter(adapterModelo);

        turmaController = new TurmaController(dataBase);
        ArrayAdapter<String> adapterTurma = turmaController.consultaTurma(container.getContext());
        adapterTurma.insert("Turma",0);

        spnAluno.setEnabled(false);

        spnTurma.setAdapter(adapterTurma);
        spnTurma.setOnItemSelectedListener(this);


        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == spnTurma){
            if(!spnTurma.getSelectedItem().equals("Turma")){
                //Verificando se spinner de turma foi selecionada, caso tenha sido escolhido turma
                //os alunos da turma seão consultados no Banco de dados
                Turma turma = new Turma();
                turma.setIdentificador(parent.getSelectedItem().toString());
                turma = turmaController.consultaTurmaId(turma);
                AlunoController alunoController = new AlunoController(dataBase);
                ArrayAdapter<String> adpAluno = alunoController.consultaAlunoTurma(context, turma);
                spnAluno.setEnabled(true);
                spnAluno.setAdapter(adpAluno);


            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
