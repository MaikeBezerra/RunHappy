package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.runhappy.R;
import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.activity.data.UsuarioDAO;
import com.example.runhappy.activity.data.firebase.UsuarioDBFirebase;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements UsuarioObserver {

    FirebaseAuth auth;
    UsuarioDAO dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

        auth = FirebaseAuth.getInstance();
        dbUsuario = new UsuarioDBFirebase();

        LoginViewModel vmLogin = LoginViewModel.getInstance(getApplicationContext());
        vmLogin.addObserver( this );
        vmLogin.setIdLoged();

    }

    public void cadastrar(View view){
        Intent casdastro = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(casdastro);
    }

    public void logar(View view) {
        Intent login = new Intent(getApplicationContext(), LoginPrincipalActivity.class);
        startActivity(login);
    }

    @Override
    public void onChangeUsuario(Usuario usuario) {
        if(usuario != null) {
            Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
            startActivity(intent);
        }
    }
}
