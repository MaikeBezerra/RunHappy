package com.example.runhappy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.runhappy.presenter.UsuarioLocationListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TelaInicialActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private FragmentManager fragmentManager;
    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout layout;
    private UsuarioLocationListener locationListener;

    TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle("Run Happy");


//        if (getIntent().getExtras() != null) {
//            setContentView(R.layout.nav_header_tela_inicial);
//            nome = (TextView) findViewById(R.id.nomeUsuario);
//            String nomeUsuario = (String) getIntent().getExtras().get("nomeUsuario");
//            System.out.println("aquiii"+nomeUsuario+nome.getText().toString());
//            nome.setText(nomeUsuario);
//            setContentView(R.layout.activity_tela_inicial);
//        }


        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        layout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer);
        layout.addDrawerListener(toggle);
        toggle.syncState();

//        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationListener = new UsuarioLocationListener(this, this, manager);
//        locationListener.getLocation();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                locationListener.getLocation();
            } else {
                Toast.makeText(getApplicationContext(),"Permissão de localização Negada", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void iniciar(View view) {
        Intent telaCorrida = new Intent(getApplicationContext(), AtividadeCorridaActivity.class);
        startActivity(telaCorrida);
    }

    public void sair(View view){
        //fazer logout corretamente

        finish();
    }

}
