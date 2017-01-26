package com.project.sondagemocr.Pojo;


import java.util.ArrayList;
import java.util.Date;

public class Turma {

    private int id;
    private Escola escola;
    private Usuario usuario;
    private ArrayList<Aluno> alunos;
    private String identificador;
    private String ano;

    public Turma(){
        escola = new Escola();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Escola getEscola() { return escola;}

    public void setEscola(Escola escola) {this.escola = escola;}
}
