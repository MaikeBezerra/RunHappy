package com.example.runhappy.data.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.runhappy.data.UsuarioAuth;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.OnLoginEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UsuarioFirebaseAuth implements UsuarioAuth {

    private OnLoginEventListener eventListener;
    private FirebaseAuth auth;

    private UsuarioDAO dbUsuario;

    public UsuarioFirebaseAuth( OnLoginEventListener eventListener ){
        this.eventListener = eventListener;
        this.auth = FirebaseAuth.getInstance();
        this.dbUsuario = new UsuarioDBFirebase();
    }

    @Override
    public void registrar(final String nome, final String email, final String senha) {
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            assert user != null;
                            String id = user.getUid();

                            Usuario usuario = new Usuario(id, nome, email, senha);
                            eventListener.onUsuarioLoged(usuario);
                            dbUsuario.addUsuario(usuario);
                        } else {
                            Log.e(TAG, "Error nos campos");
                        }
                    }
                });
    }

    @Override
    public void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String id = auth.getUid();

                        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                        firestore.collection("usuarios")
                                .whereEqualTo("id", id)
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        List<Usuario> usuario = queryDocumentSnapshots.toObjects(Usuario.class);
                                        eventListener.onUsuarioLoged(usuario.get(0));
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e(TAG, "Error ao busca usuario");
                                    }
                                });
                    } else {
                        Log.e(TAG, "Authentication failed!");
                    }
                    }
                });
    }

    @Override
    public void loginWithFacebook(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String id = auth.getUid();

                            FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                            firestore.collection("usuarios")
                                    .whereEqualTo("id", id)
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            List<Usuario> usuario = queryDocumentSnapshots.toObjects(Usuario.class);
                                            eventListener.onUsuarioLoged(usuario.get(0));
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e(TAG, "Error ao busca usuario");
                                        }
                                    });
                        } else {
                            Log.e(TAG, "Authentication failed!");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        eventListener.onUsuarioFacebookNotLoged();
                    }
        });
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean isAutenticado() {
        return auth.getCurrentUser() != null;
    }

    @Override
    public String idLoged() {
        return auth.getUid();
    }
}
