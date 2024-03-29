package com.example.runhappy.ui.usuario;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.R;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.firebase.UsuarioDBFirebase;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginViewModel;

import java.util.HashMap;
import java.util.Map;

public class UsuarioFormViewModel {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Context context;

    private UsuarioDAO db;
    private LoginViewModel vmLogin;

    public UsuarioFormViewModel(Context context) {
        this.context = context;
        this.vmLogin = LoginViewModel.getInstance(context);
    }

    public void inicialize(View view){
        this.nome = view.findViewById(R.id.txtNome);
        this.email = view.findViewById(R.id.txtEmail);
        this.senha = view.findViewById(R.id.txtSenha);
    }

    public void registrar() {
        String nome = this.nome.getText().toString();
        String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();

        vmLogin.registrar(nome, email, senha);
    }

    public Usuario updateIdField(String id){
        Usuario usuario = vmLogin.getUsuario();
        usuario.setId(id);
        return usuario;
    }

    public void setFields(String nome, String email) {
        this.nome.setText(nome);
        this.email.setText(email);
    }


    public void editarUsuario() {
        Usuario usuario = vmLogin.getUsuario();

        Map<String, Object> param = new HashMap<>();
        param.put("nome", nome.getText().toString());
        param.put("email", email.getText().toString());
        param.put("senha", senha.getText().toString());

        db = new UsuarioDBFirebase();
        db.editUsuario(usuario.getId(), param);
    }
}
