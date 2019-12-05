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
    private TextView infoNomeUsuario;

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

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
        Usuario usuario = LoginViewModel.getInstance(context).getUsuario();

        if(usuario != null) {
            nomeUsuario.setText(usuario.getNome());
            nomeUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editarUsuario = new Intent(context, EditarUsuarioActivity.class);
                    activity.startActivity(editarUsuario);
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
                    Intent infoUsuario = new Intent(context, InfoUsuarioActivity.class);
                    infoUsuario.putExtra("nomeUsuario", usuario.getNome());
                    infoUsuario.putExtra("idBusca", usuario.getId());
                    activity.startActivity(infoUsuario);
                }
            });
        }
    }
}
