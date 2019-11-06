package com.example.runhappy;

import android.content.Intent;
import android.os.Bundle;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TelaInicialActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout layout;
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
                editarUsuario.putExtra("email", getIntent().getExtras().getString("email"));
                startActivity(editarUsuario);

            }
        });

        emailUsuarioLogado = getIntent().getExtras().get("email").toString();
        handle = new SQLiteHandle(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_correr: {
                Toast.makeText(getApplicationContext(), "Menu 1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_usuarios:
                Intent usuarios = new Intent(getApplicationContext(), UsuarioListActivity.class);
                startActivity(usuarios);
                finish();
                break;
            case R.id.nav_historico: {
                Intent historico = new Intent(getApplicationContext(), HistoricoActivity.class);
                usuarioDAOSQLite = new UsuarioDAOSQLite(handle);
                Usuario usuario = usuarioDAOSQLite.findByEmail(getIntent().getExtras().get("email").toString());

                historico.putExtra("usuario", usuario);
                startActivity(historico);

                Toast.makeText(getApplicationContext(), "Menu 2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_sair: {
                Toast.makeText(getApplicationContext(), "Menu 3", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
            default: {
                Toast.makeText(getApplicationContext(), "Menu Default", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        layout.closeDrawer(GravityCompat.START);
        return true;
    }

    public static String getEmailUsuarioLogado(){
        return emailUsuarioLogado;
    }

}
