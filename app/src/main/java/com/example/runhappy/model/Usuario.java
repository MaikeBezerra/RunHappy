package com.example.runhappy.model;

import java.util.Date;
import java.util.List;

public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Date dataNacimento;
    private List<Corrida> corridas;

    public Usuario(){}

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
       // this.dataNacimento = dataNacimento;
    }

    public Usuario(Integer id, String nome, String email, String senha){
        this(nome, email, senha);
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(Date dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Corrida> getCorridas() {
        return corridas;
    }

    public void setCorridas(List<Corrida> corridas) {
        this.corridas = corridas;
    }
}
