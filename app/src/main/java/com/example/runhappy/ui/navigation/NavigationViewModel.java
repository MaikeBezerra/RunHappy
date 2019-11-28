package com.example.runhappy.ui.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.runhappy.activity.HistoricoActivity;
import com.example.runhappy.FeedActivity;
import com.example.runhappy.R;
import com.example.runhappy.activity.UsuarioListActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationViewModel implements NavigationView.OnNavigationItemSelectedListener{

    private Activity activity;
    private Context context;

    private FirebaseAuth auth;

    public NavigationViewModel(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_correr: {
                Toast.makeText(context, "Correr", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_feed: {
                Intent feed = new Intent(context, FeedActivity.class);
                activity.startActivity(feed);
                break;
            }
            case R.id.nav_usuarios:
                Intent usuarios = new Intent(context, UsuarioListActivity.class);
                activity.startActivity(usuarios);
                break;
            case R.id.nav_historico: {
                Intent historico = new Intent(context, HistoricoActivity.class);
                //usuarioDAOSQLite = new UsuarioDAOSQLite(handle);
                //Usuario usuario = usuarioDAOSQLite.findByEmail(getIntent().getExtras().get("email").toString());

                //historico.putExtra("usuario", usuario);
                 activity.startActivity(historico);

                Toast.makeText(context, "Histórico", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_sair: {
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                activity.finish();
                break;
            }
            default: {
                Toast.makeText(context, "Menu Default", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        //layout.closeDrawer(GravityCompat.START);
        return true;
    }
}

