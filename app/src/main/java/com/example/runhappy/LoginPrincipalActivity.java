package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginPrincipalActivity extends AppCompatActivity {

    private UsuarioAuth auth;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = UsuarioFirebaseAuth.getInstance(getApplicationContext());

        email = findViewById(R.id.username);
        senha = findViewById(R.id.password);

    }

    public void login(View view) {
        auth.login(email.getText().toString(), senha.getText().toString());

        Intent telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
        startActivity(telaInicial);
    }
}