package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.R;
import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.data.SQLite.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.SQLite.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginFacebookView;
import com.example.runhappy.ui.login.LoginViewModel;
import com.facebook.CallbackManager;

public class LoginPrincipalActivity extends AppCompatActivity implements UsuarioObserver {

    private UsuarioDAO usuarioDAO;
    private EditText email;
    private EditText senha;
    CallbackManager callbackManager;

    private LoginViewModel vmLogin;
    private LoginFacebookView vFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SQLiteHandle handle = new SQLiteHandle(getApplicationContext());
        usuarioDAO = new UsuarioDAOSQLite(handle);

        email = findViewById(R.id.username);
        senha = findViewById(R.id.password);

        vmLogin = new LoginViewModel(getApplicationContext());
        vmLogin.addObserver( this );

        View rootView = findViewById( android.R.id.content );
        vFacebook = new LoginFacebookView(getApplicationContext());
        vFacebook.inicialize( rootView );

        callbackManager = CallbackManager.Factory.create();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void login(View view) {
        vmLogin.logar(email.getText().toString(), senha.getText().toString());
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