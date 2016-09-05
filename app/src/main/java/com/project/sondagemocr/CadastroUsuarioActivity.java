package com.project.sondagemocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.project.sondagemocr.Controller.UsuarioController;
import com.project.sondagemocr.DataBase.DataBase;
import com.project.sondagemocr.Pojo.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNome;
    private EditText edtRg;
    private EditText edtCpf;
    private EditText edtNasc;
    private Spinner edtEscolaridade;
    private EditText edtCoord;
    private EditText edtLoginUsuario;
    private EditText edtSenha;
    private FloatingActionButton btCadastra;
    private DataBase dataBase;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.add("Grau de Escolaridade");
        arrayAdapter.add("Superior");
        arrayAdapter.add("Pós-Graduação");

        edtNome = (EditText) findViewById(R.id.edtNomeUsuario);
        edtRg = (EditText) findViewById(R.id.edtRgUsuario);
        edtCpf = (EditText) findViewById(R.id.edtCpfUsuario);
        edtNasc = (EditText) findViewById(R.id.edtNascUsuario);
        edtEscolaridade = (Spinner) findViewById(R.id.spinnerEscolaridade);
        edtEscolaridade.setAdapter(arrayAdapter);
        edtCoord = (EditText) findViewById(R.id.edtCoord);
        edtLoginUsuario = (EditText) findViewById(R.id.edtLoginUsuario);

        btCadastra = (FloatingActionButton) findViewById(R.id.btCadastraUsuario);

        btCadastra.setOnClickListener(this);

        usuario = new Usuario();

        dataBase = new DataBase(this, null, 1);

    }

    @Override
    public void onClick(View v) {
        if(v == btCadastra){
            try {
                if(!edtEscolaridade.getSelectedItem().equals("Grau de Escolaridade")) {
                    insertUsuario();
                    UsuarioController usuarioController = new UsuarioController(dataBase);
                    usuarioController.insereUsuario(usuario);
                    Toast.makeText(this, "Cadastro de usuário realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CadastroUsuarioActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setMessage("O grau de escolaridade não foi selecionado!");
                    alertDialog.setNeutralButton("OK", null);
                    alertDialog.show();
                }
            }catch (Exception ex){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("Não foi possível realizar inserção ao Banco de dados: "+ex.getMessage());
                alertDialog.setNeutralButton("Ok",null);
                alertDialog.show();
            }
        }

    }

    private void insertUsuario(){
        Log.i("Script",edtNome.getText().toString());
        usuario.setNome(edtNome.getText().toString());
        Log.i("Script",usuario.getNome());
        usuario.setRg(edtRg.getText().toString());
        usuario.setCpf(edtCpf.getText().toString());
        usuario.setDt_nascimento(edtNasc.getText().toString());
        usuario.setGrau_escolaridade(edtEscolaridade.getSelectedItem().toString());
        usuario.setCoordenador(edtCoord.getText().toString());
        usuario.setLoginUser(edtLoginUsuario.getText().toString());
    }
}
