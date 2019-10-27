package com.example.runhappy.data;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.runhappy.TelaInicialActivity;
import com.example.runhappy.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UsuarioDBFirebase implements UsuarioDAO {

    private FirebaseFirestore firestore;


    private Context context;
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
    public void addUsuario(final Usuario usuario) {
        firestore
                .collection("usuarios")
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
    public List<Usuario> findAll() {
        return null;
    }

}
