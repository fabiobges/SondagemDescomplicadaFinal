package com.project.sondagemocr.Pojo;

import java.util.Date;

public class Usuario {

    private int id;
    private String loginUser;
    private String senhaUser;
    private String nome;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getSenhaUser() {return senhaUser;}

    public void setSenhaUser(String senhaUser) {this.senhaUser = senhaUser;}
}
