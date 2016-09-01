package com.project.sondagemocr.Pojo;


public class SondagemModelo {

    private int id;
    private Usuario usuario;
    private String descSondagemMod;
    private String monossilaba;
    private String dissilaba;
    private String trissilaba;
    private String polissilaba;
    private String frase;

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

    public String getDescSondagemMod() {
        return descSondagemMod;
    }

    public void setDescSondagemMod(String descSondagemMod) {
        this.descSondagemMod = descSondagemMod;
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
}
