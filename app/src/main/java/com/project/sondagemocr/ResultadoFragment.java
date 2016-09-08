package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.sondagemocr.Controller.AlunoController;
import com.project.sondagemocr.Controller.NivelController;
import com.project.sondagemocr.Controller.SondagemAlunoController;
import com.project.sondagemocr.Controller.SondagemModeloController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Aluno;
import com.project.sondagemocr.Pojo.Nivel;
import com.project.sondagemocr.Pojo.SondagemAluno;
import com.project.sondagemocr.Pojo.SondagemModelo;
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
    public String resultPoli,resultTri,resultDissi,resultMono,resultFrase;
    private Spinner spnHipotese;
    private Button btGravar, btDetalhes, btAutomatizada;

    public AnalisePalavras analisePalavras;
    public DataBase dataBase;

    public int alfabetico = 0, silabAlfabetico = 0, silabComValor = 0, silabSemValor = 0, preSilabico = 0;


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
        spnHipotese = (Spinner) view.findViewById(R.id.spnHipotese);
        btGravar = (Button) view.findViewById(R.id.btCadastraResultadoSondagem);
        btDetalhes = (Button) view.findViewById(R.id.btDetalhesResultadoSondagem);
        btAutomatizada = (Button) view.findViewById(R.id.btAutomatizaSondagem);

        ArrayAdapter<String> adaptHipotese = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item);
        adaptHipotese.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptHipotese.add("Hipótese do Aluno");
        adaptHipotese.add("Alfabético");
        adaptHipotese.add("Silábico Alfabético");
        adaptHipotese.add("Silábico com Valor Sonoro");
        adaptHipotese.add("Silábico sem Valor Sonoro");
        adaptHipotese.add("Pré-Silábico");

        spnHipotese.setAdapter(adaptHipotese);

        btGravar.setOnClickListener(this);
        btDetalhes.setOnClickListener(this);
        btAutomatizada.setOnClickListener(this);


        return view;

    }


    @Override
    public void onClick(View v) {
        if (v == btGravar) {

            try {
                dataBase = new DataBase(getContext(), null, 1);
                NivelController nivelController = new NivelController(dataBase);
                Nivel nivel = nivelController.consultaNivelPorNome(spnHipotese.getSelectedItem().toString());
                AlunoController alunoController = new AlunoController(dataBase);
                Aluno aluno = new Aluno();
                aluno.setNome(IdentificacaoFragment.spnAluno.getSelectedItem().toString());
                aluno = alunoController.consultaAlunoPorNome(aluno);
                SondagemModeloController sondagemModeloController = new SondagemModeloController(dataBase);
                SondagemModelo sondagemModelo = new SondagemModelo();
                sondagemModelo.setDescSondagemMod(IdentificacaoFragment.spnSondagemModelo.getSelectedItem().toString());
                sondagemModelo = sondagemModeloController.consultaSondagemModeloPorIdentificador(sondagemModelo);
                SondagemAluno sondagemAluno = new SondagemAluno();
                sondagemAluno.setAluno(aluno);
                sondagemAluno.setSondagemModelo(sondagemModelo);
                sondagemAluno.setPolissilaba(textAlunoPoli.getText().toString());
                sondagemAluno.setTrissilaba(textAlunoTri.getText().toString());
                sondagemAluno.setDissilaba(textAlunoDissi.getText().toString());
                sondagemAluno.setMonossilaba(textAlunoMono.getText().toString());
                sondagemAluno.setFrase(textAlunoFrase.getText().toString());
                sondagemAluno.setNivel(nivel);
                SondagemAlunoController sondagemAlunoController = new SondagemAlunoController(dataBase);
                sondagemAlunoController.insereSondagemAluno(sondagemAluno);
                Toast.makeText(getContext(),"O cadastro da sondagem foi realizada com sucesso!",Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(getContext(),PrincipalActivity.class);
                startActivity(intent);
            }catch (Exception ex){
                chamaAlertDialog("erro: "+ex.getMessage());
            }


        } else if (v == btDetalhes) {
            if(resultPoli == null){
                resultPoli = "A Sondagem automatizada não foi acionada!";
                resultTri = "A Sondagem automatizada não foi acionada!";
                resultDissi =  "A Sondagem automatizada não foi acionada!";
                resultMono  = "A Sondagem automatizada não foi acionada!";
                resultFrase = "A Sondagem automatizada não foi acionada!";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Detalhes do resultado da sondagem automatizada \n\n");
            stringBuilder.append("Polissílaba \n");
            stringBuilder.append("Modelo: " + silabasParaString(AnalisePalavras.separaSilabas(textModeloPoli.getText().toString())) + "\n");
            stringBuilder.append("Aluno: " + silabasParaString(AnalisePalavras.separaSilabas(textAlunoPoli.getText().toString())) + "\n");
            stringBuilder.append("Hipótese parcial: "+resultPoli+"\n\n");
            stringBuilder.append("Trissílabas \n");
            stringBuilder.append("Modelo: " + silabasParaString(AnalisePalavras.separaSilabas(textModeloTri.getText().toString())) + "\n");
            stringBuilder.append("Aluno: " + silabasParaString(AnalisePalavras.separaSilabas(textAlunoTri.getText().toString())) + "\n");
            stringBuilder.append("Hipótese parcial: "+resultTri+"\n\n");
            stringBuilder.append("Dissílabas \n");
            stringBuilder.append("Modelo: " + silabasParaString(AnalisePalavras.separaSilabas(textModeloDissi.getText().toString())) + "\n");
            stringBuilder.append("Aluno: " + silabasParaString(AnalisePalavras.separaSilabas(textAlunoDissi.getText().toString())) + "\n");
            stringBuilder.append("Hipótese parcial: "+resultDissi+"\n\n");
            stringBuilder.append("Monossílabas \n");
            stringBuilder.append("Modelo: " + silabasParaString(AnalisePalavras.separaSilabas(textModeloMono.getText().toString())).toString() + "\n");
            stringBuilder.append("Aluno: " + silabasParaString(AnalisePalavras.separaSilabas(textAlunoMono.getText().toString())) + "\n");
            stringBuilder.append("Hipótese parcial: "+resultMono+"\n\n");
            stringBuilder.append("Frases \n");
            stringBuilder.append("Modelo: " + silabasParaString(AnalisePalavras.separaSilabas(textModeloFrase.getText().toString())).toString() + "\n");
            stringBuilder.append("Aluno: " + silabasParaString(AnalisePalavras.separaSilabas(textAlunoFrase.getText().toString())) + "\n");
            stringBuilder.append("Hipótese parcial: "+resultFrase+"\n\n");
            chamaAlertDialog(stringBuilder.toString());
        }else if(v == btAutomatizada){
            try {
                analisePalavras = new AnalisePalavras(textModeloPoli.getText().toString(), textAlunoPoli.getText().toString());
                resultPoli = sondagemAvaliacao();
                analisePalavras = new AnalisePalavras(textModeloTri.getText().toString(), textAlunoTri.getText().toString());
                resultTri = sondagemAvaliacao();
                analisePalavras = new AnalisePalavras(textModeloDissi.getText().toString(), textAlunoDissi.getText().toString());
                resultDissi = sondagemAvaliacao();
                analisePalavras = new AnalisePalavras(textModeloMono.getText().toString(), textAlunoMono.getText().toString());
                resultMono = sondagemAvaliacao();
                analisePalavras = new AnalisePalavras(textModeloFrase.getText().toString(), textAlunoFrase.getText().toString());
                resultFrase = sondagemAvaliacao();

                verificaResultado();
                Toast.makeText(getContext(), "Sondagem Automatizada foi realizada!", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                chamaAlertDialog("Erro: "+ex.getMessage());
            }
        }
    }

    public void chamaAlertDialog(String message) {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(getContext());
        alBuilder.setMessage(message);
        alBuilder.setNeutralButton("OK", null);
        alBuilder.show();
    }


    public String sondagemAvaliacao() {

        if (analisePalavras.alfabetico() == true) {
            alfabetico++;
            Log.i("Sondagem: ", "Alfabético");
            return "Alfabético";
        } else {
            if (analisePalavras.silabicoAlfabetico() == true) {
                silabAlfabetico++;
                Log.i("Sondagem: ", "Silábico Alfabético");
                return "Silábico Alfabético";
            } else {
                if (analisePalavras.silabicoComValor() == true) {
                    silabComValor++;
                    Log.i("Sondagem: ", "Silábico com Valor");
                    return "Silábico com Valor";
                } else {
                    if (analisePalavras.silabicoSemValor() == true) {
                        silabSemValor++;
                        Log.i("Sondagem: ", "Silábico sem Valor");
                        return "Silábico sem Valor";
                    } else {
                        preSilabico++;
                        Log.i("Sondagem: ", "Pré-Silábico");
                        return "Pré-Silábico";
                    }
                }
            }
        }

    }

    public void verificaResultado() {
        if ((alfabetico >= silabAlfabetico) && (alfabetico >= silabComValor) && (alfabetico >= silabSemValor) && (alfabetico >= preSilabico)) {
            spnHipotese.setSelection(1);
        } else if ((silabAlfabetico > silabComValor) && (silabAlfabetico > silabSemValor) && (silabAlfabetico > preSilabico)) {
            spnHipotese.setSelection(2);
        } else if ((silabComValor > silabSemValor) && (silabComValor > preSilabico)) {
            spnHipotese.setSelection(3);
        } else if ((silabSemValor > preSilabico)) {
            spnHipotese.setSelection(4);
        } else {
            spnHipotese.setSelection(5);
        }
        alfabetico = 0;
        silabAlfabetico = 0;
        silabComValor = 0;
        silabSemValor = 0;
        preSilabico = 0;
    }

    public String silabasParaString(String[] listaSilabasCorreta){
        String palavraSeparada="";
        for(String silaba:listaSilabasCorreta){
            if (silaba.isEmpty()) {
                break;
            }
            if (!palavraSeparada.isEmpty()) {
                palavraSeparada = palavraSeparada + "-" + silaba;
            } else {
                palavraSeparada = palavraSeparada + silaba;
            }

        }
        return palavraSeparada;
    }
/*

    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentPoli();
        super.onDestroy();
        Log.i("Script","onDestroy Resultado Fragment");
    }*/

}
