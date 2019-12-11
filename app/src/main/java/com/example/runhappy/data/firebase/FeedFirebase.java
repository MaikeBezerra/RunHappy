package com.example.runhappy.data.firebase;

import androidx.annotation.NonNull;

import com.example.runhappy.model.Corrida;
import com.example.runhappy.model.Feed;
import com.example.runhappy.model.Seguido;
import com.example.runhappy.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FeedFirebase {

    private static final String FEEDS = "feeds";
    private static final String FEEDS_SEGUIDOS = "feed_seguidos";
    private static final String SEGUIDORES = "seguidores";

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    public FeedFirebase(){
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }

    public void createFeed(final Corrida corrida){

        final DocumentReference reference = firestore.collection(FEEDS).document(auth.getUid());
        setFeedInRefrence(reference, corrida);

        DocumentReference seguidoresReference = firestore.collection(SEGUIDORES).document(auth.getUid());

        seguidoresReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            Seguido seguido = document.toObject(Seguido.class);
                            for (Usuario usuario: seguido.getSeguidores()
                                 ) {
                                addFeedSeguidore(usuario, corrida);
                            }
                        }
                    }
                });
    }

    private void addFeedSeguidore(Usuario usuario, final Corrida corrida){
        final DocumentReference reference = firestore.collection(FEEDS_SEGUIDOS).document(usuario.getId());
        setFeedInRefrence(reference, corrida);
    }

    private void setFeedInRefrence(final DocumentReference reference, final Corrida corrida){
        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();

                            if (document.exists()){
                                Map<String, Object> corridaMap = new HashMap<>();
                                corridaMap.put("id", corrida.getId());
                                corridaMap.put("distancia", corrida.getDistancia());
                                corridaMap.put("corredor", corrida.getCorredor());
                                corridaMap.put("distanciaFormatada", corrida.getDistanciaFormatada());
                                corridaMap.put("ritmoMedio", corrida.getRitmoMedio());
                                corridaMap.put("tempo", corrida.getTempo());
                                corridaMap.put("ritmoMedioFormatado", corrida.getRitmoMedioFormatado());
                                corridaMap.put("tempoFormatado", corrida.getTempoFormatado());
                                corridaMap.put("descricao", corrida.getDescricao());
                                corridaMap.put("localizacao", corrida.getLocations());

                                reference.update("corridas", FieldValue.arrayUnion(corridaMap));
                            } else {
                                Feed feed = new Feed(Arrays.asList(corrida));

                                reference.set(feed);
                            }
                        }
                    }
                });
    }



}