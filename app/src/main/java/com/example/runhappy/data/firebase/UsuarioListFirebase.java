package com.example.runhappy.data.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.runhappy.model.Seguido;
import com.example.runhappy.model.Seguidor;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.OnUsuarioListEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

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

    public void adicionarSeguidor(final Usuario usuario, final Usuario seguido) {

        final DocumentReference quemEuSigo = firestore.collection("seguidos").document(usuario.getId());
        final DocumentReference souSeguido = firestore.collection("seguidores").document(seguido.getId());

        quemEuSigo.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();

                            if (document.exists()){
                                Map<String, Object> seguidoMap = new HashMap<>();
                                seguidoMap.put("id", seguido.getId());
                                seguidoMap.put("nome", seguido.getNome());
                                seguidoMap.put("email", seguido.getEmail());

                                quemEuSigo.update("seguidores", FieldValue.arrayUnion(seguidoMap));
                            } else {
                                Seguido seguido1 = new Seguido(Arrays.asList(seguido));

                                quemEuSigo.set(seguido1);
                            }
                        }
                    }
                });

        souSeguido.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();

                            if (document.exists()){
                                Map<String, Object> seguidor = new HashMap<>();
                                seguidor.put("id", usuario.getId());
                                seguidor.put("nome", usuario.getNome());
                                seguidor.put("email", usuario.getEmail());

                                souSeguido.update("seguidores", FieldValue.arrayUnion(seguidor));
                            } else {
                                Seguidor seguidor = new Seguidor(Arrays.asList(usuario));

                                souSeguido.set(seguidor);
                            }
                        }
                    }
                });

    }

    public void findAllSeguidores(String id){
        firestore.collection("seguidores")
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Seguidor seguidor = document.toObject(Seguidor.class);
                                eventListener.onSetList(seguidor.getSeguidores());
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

    }

    public void findAllSeguidos(String id){
        firestore.collection("seguidos")
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Seguido seguido = document.toObject(Seguido.class);
                                eventListener.onSetList(seguido.getSeguidores());
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

    }
}
