package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);
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
