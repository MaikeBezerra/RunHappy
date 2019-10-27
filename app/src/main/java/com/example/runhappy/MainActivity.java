package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private UsuarioAuth usuarioAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

        usuarioAuth = UsuarioFirebaseAuth.getInstance(this);
        if (usuarioAuth.isAutenticado()) {
            startActivity(new Intent(getApplicationContext(), TelaInicialActivity.class));
        }
    }

    public void cadastrar(View view){
        Intent casdastro = new Intent(this, CadastroActivity.class);
        startActivity(casdastro);
    }

    public void logar(View view) {
        Intent login = new Intent(this, LoginPrincipalActivity.class);
        startActivity(login);
    }
}
