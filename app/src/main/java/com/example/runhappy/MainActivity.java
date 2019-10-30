package com.example.runhappy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private UsuarioAuth usuarioAuth;
    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        usuarioAuth = UsuarioFirebaseAuth.getInstance(this);
//
//        if (usuarioAuth.isAutenticado()) {
//            setContentView(R.layout.activity_tela_inicial);
//            toolbar = findViewById(R.id.toolbar);
//            this.setSupportActionBar(toolbar);
//
//            layout = findViewById(R.id.drawer_layout);
//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer);
//            layout.addDrawerListener(toggle);
//            toggle.syncState();
//
//        } else {
            setContentView(R.layout.activity_boas_vindas);
//        }
    }

    public void cadastrar(View view){
        Intent casdastro = new Intent(this, CadastroActivity.class);
        startActivity(casdastro);
    }

    public void logar(View view) {
        Intent login = new Intent(this, LoginPrincipalActivity.class);
        startActivity(login);
    }

    public void iniciar(View view) {
        Intent telaCorrida = new Intent(getApplicationContext(), AtividadeCorridaActivity.class);
        startActivity(telaCorrida);
    }
}
