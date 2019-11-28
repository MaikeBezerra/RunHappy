package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.runhappy.R;
import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginFacebookView;
import com.example.runhappy.ui.login.LoginViewModel;
import com.example.runhappy.ui.usuario.UsuarioFormViewModel;
import com.example.runhappy.ui.usuario.UsuarioViewModel;
import com.facebook.CallbackManager;

public class CadastroActivity extends AppCompatActivity implements UsuarioObserver {

    private LoginViewModel vmLogin;
    private UsuarioViewModel viewModel;
    private UsuarioFormViewModel formViewModel;
    CallbackManager callbackManager;

    private LoginFacebookView vFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        viewModel = new UsuarioViewModel(getApplicationContext());
        formViewModel = new UsuarioFormViewModel(this, getApplicationContext());
        vmLogin = new LoginViewModel(getApplicationContext());
        vmLogin.addObserver( this );

        callbackManager = CallbackManager.Factory.create();

        View rootView = findViewById(android.R.id.content);
        vFacebook = new LoginFacebookView(getApplicationContext());
        vFacebook.inicialize(rootView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cadastrar(View view) {
        formViewModel.registrar();
    }

    @Override
    public void onChangeUsuario(Usuario usuario) {
        if (vmLogin.getUsuario() != null) {
            Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
