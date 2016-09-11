package com.project.sondagemocr;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class SondagemListFragment extends ListFragment {

    ArrayList<SondagemAluno> sondagensAlunos;
    List<Map<String,Object>> listSondagens;
    DataBase dataBase;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        listSondagens = new ArrayList<Map<String, Object>>();
        Context myContext = getActivity();
        dataBase = new DataBase(myContext,null,1);
        preencheListSondagens();

    }




    private void preencheListSondagens() {

        sondagensAlunos = new ArrayList<SondagemAluno>();
        SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
        sondagensAlunos = sondagemAlunoController.consultaSondagensAlunos();



        try {

            for (SondagemAluno sondagemAluno : sondagensAlunos) {


                Map<String, Object> item = new HashMap<String, Object>();

                Log.i("Aluno10: ", sondagemAluno.getData());
                item.put("data", "asdsadasd");
                Log.i("Aluno11: ", sondagemAluno.getAluno().getNome());
                item.put("nomeAluno", sondagemAluno.getAluno().getNome());
                item.put("turma", sondagemAluno.getAluno().getTurma().getIdentificador());


                listSondagens.add(item);
            }


            Log.i("Aluno12: ", "=="+getActivity().getLocalClassName());
            String[] de = {"data", "nomeAluno", "turma"};
            int[] para = {R.id.txViewDataSond, R.id.txViewNomeAluno,
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
    public void onListItemClick(ListView listView,View view,int position, long id){
        ViewGroup viewGroup = (ViewGroup) view;
        TextView tx =(TextView) viewGroup.findViewById(R.id.txViewNomeAluno);
        Toast.makeText(getActivity(),tx.getText(),Toast.LENGTH_SHORT).show();
    }
}
