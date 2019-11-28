package com.example.runhappy.activity.data.firebase;

import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.OnUsuarioListEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioListFirebase {

    private FirebaseFirestore firestore;
    private OnUsuarioListEventListener eventListener;

    public UsuarioListFirebase(OnUsuarioListEventListener eventListener){
        this.eventListener = eventListener;
        this.firestore = FirebaseFirestore.getInstance();
    }

    public void findAll(final String id){
        firestore.collection("usuarios")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List <Usuario> usuarios = new ArrayList<>();

                        for ( Usuario usuario : queryDocumentSnapshots.toObjects(Usuario.class)) {
                            if (!id.equals(usuario.getId())){
                                usuarios.add(usuario);
                            }
                        }

                        eventListener.onSetList(usuarios);
                    }
                });

    }

    public void adicionarSeguidor(Usuario usuario, String idSeguido) {
        Map<String, Object> seguir = new HashMap<>();
        seguir.put("idSeguidor", usuario.getId());
        seguir.put("idSeguido", idSeguido);

        firestore.collection("seguidores")
                .add(seguir)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                });
    }
}
