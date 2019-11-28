package com.example.runhappy.ui.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.runhappy.activity.EditarUsuarioActivity;
import com.example.runhappy.R;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.login.LoginViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class HeaderNavigationViewModel {

    private Activity activity;
    private Context context;

    private NavigationView navigation;
    private TextView nomeUsuario;

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
        setNomeUsuario();

    }

    private void setNomeUsuario(){
        Usuario usuario = new LoginViewModel(context).getUsuario();

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
}
