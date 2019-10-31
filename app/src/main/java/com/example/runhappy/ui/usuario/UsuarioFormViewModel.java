package com.example.runhappy.ui.usuario;

import android.view.View;
import android.widget.EditText;

import com.example.runhappy.R;
import com.example.runhappy.model.Usuario;

public class UsuarioFormViewModel {

    private EditText nome;
    private EditText email;
    private EditText senha;

    public UsuarioFormViewModel(View view) {
        this.nome = view.findViewById(R.id.txtNome);
        this.email = view.findViewById(R.id.txtEmail);
        this.senha = view.findViewById(R.id.txtSenha);
    }

    public Usuario getUsuario() {
        String nome = this.nome.getText().toString();
        String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();

        return new Usuario(nome, email, senha);
    }
}
