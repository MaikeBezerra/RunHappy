package com.example.runhappy.ui.Navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.runhappy.EditarUsuarioActivity;
import com.example.runhappy.R;
import com.example.runhappy.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

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
        nomeUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent editarUsuario = new Intent(context, EditarUsuarioActivity.class);
            activity.startActivity(editarUsuario);
            }
        });
    }

    private void setNomeUsuario(){
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        String id = auth.getUid();
        firestore.collection("usuarios")
                .whereEqualTo("id", id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Usuario> usuario = queryDocumentSnapshots.toObjects(Usuario.class);
                        nomeUsuario.setText(usuario.get(0).getNome());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error ao busca usuario", Toast.LENGTH_SHORT).show();
                    }
        });
    }
}
