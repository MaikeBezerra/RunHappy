package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDBFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    UsuarioDAO dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

        auth = FirebaseAuth.getInstance();
        dbUsuario = UsuarioDBFirebase.getInstance(getApplicationContext());

        if (auth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), TelaInicialActivity.class);
            startActivity(intent);
        }
    }

    public void cadastrar(View view){
        Intent casdastro = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(casdastro);
    }

    public void logar(View view) {
        Intent login = new Intent(getApplicationContext(), LoginPrincipalActivity.class);
        startActivity(login);
    }

}
