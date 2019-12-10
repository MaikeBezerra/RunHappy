package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.R;
import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginFacebookView;
import com.example.runhappy.ui.login.LoginViewModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class LoginPrincipalActivity extends AppCompatActivity implements UsuarioObserver {

    private EditText email;
    private EditText senha;
    private CallbackManager callbackManager;
    private LoginViewModel vmLogin;
    private LoginFacebookView vFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            checkPermission();
        }


        email = findViewById(R.id.username);
        senha = findViewById(R.id.password);

        vmLogin = LoginViewModel.getInstance(getApplicationContext());
        vmLogin.addObserver( this );

        callbackManager = CallbackManager.Factory.create();
        View rootView = findViewById(android.R.id.content);
        vFacebook = new LoginFacebookView(getApplicationContext());
        vFacebook.inicialize( rootView , callbackManager );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void login(View view) {
        vmLogin.logar(email.getText().toString(), senha.getText().toString());
    }

    @Override
    public void onChangeUsuario(Usuario usuario) {
        if (vmLogin.isAuthenticated()) {
            Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }
}