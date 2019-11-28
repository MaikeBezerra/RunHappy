package com.example.runhappy.data.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.OnLoginEventListener;
import com.example.runhappy.ui.login.LoginViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UsuarioDBFirebase implements UsuarioDAO {

    public static final String COLECAO = "usuarios";
    private OnLoginEventListener eventListener;

    private FirebaseFirestore firestore;

    private List<Usuario> usuarios;

    public UsuarioDBFirebase() {

        this.firestore = FirebaseFirestore.getInstance();

        this.usuarios = new ArrayList<>();
        findAllUser();
    }

    @Override
    public void addUsuario(final Usuario usuario) {
        firestore.collection(COLECAO)
                .add(usuario)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "Usuario adicionado");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG , "Error");
                    }
                });

    }

    @Override
    public void logar(String id){
        firestore.collection("usuarios")
                .whereEqualTo("id", id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Usuario> usuario = queryDocumentSnapshots.toObjects(Usuario.class);
                        //eventListener.onUsuarioLoged(usuario.get(0));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error ao busca usuario");
                    }
                });
    }

    @Override
    public void adicionarSeguidor(Usuario usuario, String emailSeguido) {

    }

    @Override
    public void editUsuario(final String id, Map<String, Object> update) {
        firestore.collection("usuarios")
                .document(id)
                .update(update)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        //logar(id);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
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

    private void findAllUser() {
        firestore.collection("usuarios")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        usuarios.clear();
                       // String id = LoginViewModel.getInstance(context).idLogedUser();
                         usuarios = queryDocumentSnapshots.toObjects(Usuario.class);

                    }
                });

    }

    @Override
    public List<Usuario> findAllSeguidores(String myEmail) {
        return null;
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}