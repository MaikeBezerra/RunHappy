package com.example.runhappy;

import android.content.Intent;
import android.os.Bundle;

import com.example.runhappy.ui.Navigation.HeaderNavigationViewModel;
import com.example.runhappy.ui.Navigation.NavigationViewModel;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

public class TelaInicialActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;

    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout layout;
    ImageView imagemUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle("Run Happy");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigation = findViewById(R.id.nav_menu);
        navigation.setNavigationItemSelectedListener(new NavigationViewModel(this, getApplicationContext()));

        HeaderNavigationViewModel headerView = new HeaderNavigationViewModel(this, getApplicationContext(), navigation);
        headerView.inicializeParam();

//        imagemUsuario = (CircleImageView) headerView.findViewById(R.id.imagemUsuario);
//        Picasso.get().load((String)getIntent().getExtras().get("imagem")).into(imagemUsuario);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void iniciar(View view) {
        Intent telaCorrida = new Intent(getApplicationContext(), AtividadeCorridaActivity.class);
        startActivity(telaCorrida);
    }

}
