package com.project.sondagemocr.Pojo;


public class Escola {

    private int id;
    private Turma turma;
    private String codCie;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getCodCie() {
        return codCie;
    }

    public void setCodCie(String codCie) {
        this.codCie = codCie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
