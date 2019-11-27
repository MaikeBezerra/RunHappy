package com.example.runhappy.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.runhappy.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioFirebaseAuth implements UsuarioAuth{

    private FirebaseAuth auth;
    private Context context;
    private static UsuarioFirebaseAuth instance;

    private UsuarioDAO dbUsuario;

    private UsuarioFirebaseAuth(Context context){
        this.context = context;
        this.auth = FirebaseAuth.getInstance();
        this.dbUsuario = UsuarioDBFirebase.getInstance(context);
    }

    public static UsuarioFirebaseAuth getInstance(Context context){
        if (instance == null) {
            instance = new UsuarioFirebaseAuth(context);
        }

        return instance;
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
                            dbUsuario.addUsuario(usuario);
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
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
                        dbUsuario.logar(id);
                    } else {
                        Toast.makeText(context, "Authentication failed!", Toast.LENGTH_SHORT).show();
                    }
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
