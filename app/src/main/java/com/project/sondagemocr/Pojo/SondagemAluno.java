package com.project.sondagemocr.Pojo;


public class SondagemAluno {

    private int id;
    private SondagemModelo sondagemModelo;
    private Usuario usuario;
    private Aluno aluno;
    private String monossilaba;
    private String dissilaba;
    private String trissilaba;
    private String polissilaba;
    private String frase;
    private Nivel nivel;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SondagemModelo getSondagemModelo() {
        return sondagemModelo;
    }

    public void setSondagemModelo(SondagemModelo sondagemModelo) {
        this.sondagemModelo = sondagemModelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getMonossilaba() {
        return monossilaba;
    }

    public void setMonossilaba(String monossilaba) {
        this.monossilaba = monossilaba;
    }

    public String getDissilaba() {
        return dissilaba;
    }

    public void setDissilaba(String dissilaba) {
        this.dissilaba = dissilaba;
    }

    public String getTrissilaba() {
        return trissilaba;
    }

    public void setTrissilaba(String trissilaba) {
        this.trissilaba = trissilaba;
    }

    public String getPolissilaba() {
        return polissilaba;
    }

    public void setPolissilaba(String polissilaba) {
        this.polissilaba = polissilaba;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getData() {return data;}

    public void setData(String data) {this.data = data;}
}
