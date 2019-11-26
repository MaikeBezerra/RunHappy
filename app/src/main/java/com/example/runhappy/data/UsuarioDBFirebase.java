package com.example.runhappy.data;

import android.app.Activity;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UsuarioDBFirebase implements UsuarioDAO {

    public static final String COLECAO = "usuarios";

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    private static UsuarioDBFirebase instance;

    private Context context;
    private Activity tela;

    private UsuarioDBFirebase(Activity tela, Context context) {
        this.tela = tela;
        this.context = context;
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }

    public static UsuarioDBFirebase getInstance(Activity tela, Context context) {
        if (instance == null) {
            instance = new UsuarioDBFirebase(tela, context);
        }

        return instance;
    }

    @Override
    public void addUsuario(final Usuario usuario) {

        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser user = auth.getCurrentUser();
                            assert user != null;
                            String idUser = user.getUid();

                            usuario.setId(idUser);
                            firestore.collection(COLECAO)
                                    .add(usuario)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(context, "Usuario adicionado", Toast.LENGTH_SHORT).show();

                                            Intent telaInicial = new Intent(context, TelaInicialActivity.class);
                                            telaInicial.putExtra("nome", usuario.getNome());
                                            telaInicial.putExtra("email", usuario.getEmail());
                                            tela.startActivity(telaInicial);
                                            tela.finish();
                                        }
                                    });

                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void logar(String email, String senha){
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent telaInicial = new Intent(context, TelaInicialActivity.class);
                            tela.startActivity(telaInicial);
                            tela.finish();
                        } else {
                            Toast.makeText(context, "Authentication failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void logout(){

    }

    @Override
    public void adicionarSeguidor(Usuario usuario, String emailSeguido) {

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

    @Override
    public List<Usuario> findAllSeguidores(String myEmail) {
        return null;
    }

}
