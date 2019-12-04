package com.example.runhappy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String id;
    private String nome;
    private String email;

    public Usuario(){

    }

    public Usuario(String nome, String email){
        this();
        this.nome = nome;
        this.email = email;
    }

    public Usuario(String id, String nome, String email){
        this(nome, email);
        this.id = id;
    }

    protected Usuario(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readString();
        }
        nome = in.readString();
        email = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(id);
        }
        parcel.writeString(nome);
        parcel.writeString(email);
    }
}
