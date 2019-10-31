package com.example.runhappy.ui.usuario;

import android.app.Activity;;
import android.widget.EditText;

import com.example.runhappy.R;
import com.example.runhappy.model.Usuario;

public class UsuarioFormViewModel {

    private EditText nome;
    private EditText email;
    private EditText senha;

    public UsuarioFormViewModel(Activity tela) {
        this.nome = tela.findViewById(R.id.txtNome);
        this.email = tela.findViewById(R.id.txtEmail);
        this.senha = tela.findViewById(R.id.txtSenha);
    }

    public Usuario getUsuario() {
        String nome = this.nome.getText().toString();
        String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();

        return new Usuario(nome, email, senha);
    }

    public Usuario updateIdField(int id){
        Usuario usuario = getUsuario();
        usuario.setId(id);
        return usuario;
    }

    public void setFields(String nome, String email) {
        this.nome.setText(nome);
        this.email.setText(email);
    }
}
