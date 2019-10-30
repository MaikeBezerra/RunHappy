package com.example.runhappy.data;

import androidx.annotation.NonNull;

import com.example.runhappy.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UsuarioDBFirebase implements UsuarioDAO {

    public static final String COLECAO = "usuarios";

    private FirebaseFirestore firestore;
    private static UsuarioDBFirebase instance;

    private UsuarioDBFirebase() {
        this.firestore = FirebaseFirestore.getInstance();
    }

    public static UsuarioDBFirebase getInstance() {
        if (instance == null) {
            instance = new UsuarioDBFirebase();
        }

        return instance;
    }

    @Override
    public void addUsuario(Usuario usuario) {
        firestore
                .collection(COLECAO)
                .add(usuario)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    @Override
    public void editUsuario(Usuario usuario) {

    }

    @Override
    public void deleteUsuario(int usuarioId) {

    }

    @Override
    public Usuario getUsuario(int usuarioId) {
        return null;
    }

    @Override
    public Usuario findByEmail(String email) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

}
