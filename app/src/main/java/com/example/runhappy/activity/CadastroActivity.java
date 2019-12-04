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
import com.facebook.CallbackManager;

public class CadastroActivity extends AppCompatActivity implements UsuarioObserver {

    private LoginViewModel vmLogin;
    private UsuarioFormViewModel vmForm;
    private CallbackManager callbackManager;
    private LoginFacebookView vFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        vmForm = new UsuarioFormViewModel(getApplicationContext());
        vFacebook = new LoginFacebookView(getApplicationContext());

        vmLogin = LoginViewModel.getInstance(getApplicationContext());
        vmLogin.addObserver( this );

        callbackManager = CallbackManager.Factory.create();
        View rootView = findViewById(android.R.id.content);

        vmForm.inicialize(rootView);
        vFacebook.inicialize( rootView , callbackManager );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cadastrar(View view) {
        vmForm.registrar();
    }

    @Override
    public void onChangeUsuario(Usuario usuario) {
        if (vmLogin.isAuthenticated()) {
            Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
