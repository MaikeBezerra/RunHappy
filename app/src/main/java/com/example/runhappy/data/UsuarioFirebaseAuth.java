package com.example.runhappy.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UsuarioFirebaseAuth implements UsuarioAuth{

    private FirebaseAuth firebaseAuth;
    private Context context;
    private static UsuarioFirebaseAuth instance;

    private UsuarioFirebaseAuth(Context context){
        this.context = context;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public static UsuarioFirebaseAuth getInstance(Context context){
        if (instance == null) {
            instance = new UsuarioFirebaseAuth(context);
        }

        return instance;
    }

    @Override
    public void registrar(String email, String senha) {
        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(context, "Sucess", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void login(String email, String senha) {
        firebaseAuth.signInWithEmailAndPassword(email, senha);
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean isAutenticado() {
        return firebaseAuth.getCurrentUser() != null;
    }
}
