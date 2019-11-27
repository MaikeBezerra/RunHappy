package com.example.runhappy.data.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CorridaDBFirebase implements CorridaDAO {

    private FirebaseFirestore firestore;

    public CorridaDBFirebase(){
        this.firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void adicionarCorrida(Corrida corrida) {
        firestore.collection("corridas")
                .add(corrida)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public void editarCorrida(Corrida corrida) {

    }

    @Override
    public void deleteCorrida(int corridaId) {

    }

    @Override
    public Corrida getCorrida(int corridaId) {
        return null;
    }

    @Override
    public List<Corrida> findAll() {
        return null;
    }

    @Override
    public List<Corrida> findAll(String id) {
        firestore.collection("corridas")
                .whereEqualTo("corredor", id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    }
                });
        return null;
    }

    public void corridasDosSeguidores(){
        List<Usuario> seguidores = new ArrayList<>();
        for (Usuario usuario : seguidores){
            firestore.collection("corridas")
                    .whereEqualTo("corredor", usuario.getId())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        }
                    });
        }

    }
}
