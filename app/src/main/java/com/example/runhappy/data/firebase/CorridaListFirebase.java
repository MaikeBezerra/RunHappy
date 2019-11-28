package com.example.runhappy.data.firebase;

import com.example.runhappy.model.Corrida;
import com.example.runhappy.presenter.OnCorridaListEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CorridaListFirebase {

    private OnCorridaListEventListener eventListener;
    private FirebaseFirestore firestore;

    public CorridaListFirebase(OnCorridaListEventListener eventListener) {
        this.eventListener = eventListener;
        this.firestore = FirebaseFirestore.getInstance();
    }

    public void findAllByCorredor(String id){
        firestore.collection("corridas")
                .whereEqualTo("corredor", id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<Corrida> corridas = queryDocumentSnapshots.toObjects(Corrida.class);
                    eventListener.onSetList(corridas);
                    }
                });
    }

    public void findAllBySeguidos(String id){
        firestore.collection("seguidores")
                .whereEqualTo("idSeguidor", id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<Corrida> corridas = new ArrayList<>();

                        for ( String seguido : queryDocumentSnapshots.toObjects(String.class)
                             ) {
                            firestore.collection("usuarios")
                                    .whereEqualTo("id", seguido)
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                        }
                                    });
                        }
                    }
                });
    }
}
