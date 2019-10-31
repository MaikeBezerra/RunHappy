package com.example.runhappy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.UsuarioLocationListener;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TelaInicialActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private FragmentManager fragmentManager;
    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout layout;
    private UsuarioLocationListener locationListener;
    private UsuarioDAOSQLite usuarioDAOSQLite;
    private SQLiteHandle handle;

    private static String emailUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle("Run Happy");

        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        layout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigation = findViewById(R.id.nav_menu);
        navigation.setNavigationItemSelectedListener(this);

        View headerView = navigation.getHeaderView(0);

        TextView nomeUsuario = (TextView) headerView.findViewById(R.id.txtNomeUsuario);
        nomeUsuario.setText(getIntent().getExtras().getString("nome", "Nome do Usuário"));
        nomeUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editarUsuario = new Intent(getApplicationContext(), EditarUsuarioActivity.class);
                editarUsuario.putExtra("nome", getIntent().getExtras().getString("nome"));
                editarUsuario.putExtra("email", getIntent().getExtras().getString("email"));
                startActivity(editarUsuario);

            }
        });
//        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationListener = new UsuarioLocationListener(this, this, manager);
//        locationListener.getLocation();
        emailUsuarioLogado = getIntent().getExtras().get("email").toString();
        handle = new SQLiteHandle(this);
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

    public void openActivity(){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_correr: {
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_historico: {
               Intent historico = new Intent(this, HistoricoActivity.class);
               usuarioDAOSQLite = new UsuarioDAOSQLite(handle);
                Usuario usuario = usuarioDAOSQLite.findByEmail(getIntent().getExtras().get("email").toString());
               historico.putExtra("usuario", usuario);
               System.out.println(usuario.getNome());
               startActivity(historico);
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_sair: {
                Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
            default: {
                Toast.makeText(this, "Menu Default", Toast.LENGTH_SHORT).show();
                break;
            }
        }

//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawerLayout.closeDrawer(GravityCompat.START);
        layout.closeDrawer(GravityCompat.START);
        return true;
    }

    public static String getEmailUsuarioLogado(){
        return emailUsuarioLogado;
    }

}
