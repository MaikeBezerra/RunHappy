package com.example.runhappy.ui.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.runhappy.activity.EditarUsuarioActivity;
import com.example.runhappy.R;
import com.example.runhappy.activity.InfoUsuarioActivity;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeaderNavigationViewModel {

    private Activity activity;
    private Context context;

    private NavigationView navigation;
    private TextView nomeUsuario;
    private CircleImageView fotoUsuario;

    public HeaderNavigationViewModel(Activity activity, Context context, NavigationView navigation){
        this.navigation = navigation;
        this.activity = activity;
        this.context = context;
    }

    public void inicializeParam(){
        View headerView = navigation.getHeaderView(0);
        nomeUsuario = headerView.findViewById(R.id.txtNomeUsuario);
        fotoUsuario = headerView.findViewById(R.id.imagemUsuario);
        verPerfil();
        setNomeUsuario();

    }

    private void setNomeUsuario(){
        final Usuario usuario = LoginViewModel.getInstance(context).getUsuario();

        if(usuario != null) {
            nomeUsuario.setText(usuario.getNome());
            nomeUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startInfoUsuarioPage(usuario);
                }
            });
        }

    }

    private void verPerfil(){
        final Usuario usuario = LoginViewModel.getInstance(context).getUsuario();

        if(usuario != null) {
            fotoUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startInfoUsuarioPage(usuario);
                }
            });
        }
    }

    private void startInfoUsuarioPage(Usuario usuario){
        Intent infoUsuario = new Intent(context, InfoUsuarioActivity.class);
        infoUsuario.putExtra("idBusca", usuario.getId());
        infoUsuario.putExtra("nomeUsuario", usuario.getNome());
        infoUsuario.putExtra("idUsuario", usuario.getId());
        infoUsuario.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(infoUsuario);
    }
}
