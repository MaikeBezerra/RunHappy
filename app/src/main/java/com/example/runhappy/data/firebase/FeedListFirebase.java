package com.example.runhappy.data.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.runhappy.model.Feed;
import com.example.runhappy.presenter.OnCorridaListEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FeedListFirebase {

    private static final String FEEDS = "feeds";
    private static final String FEEDS_SEGUIDOS = "feed_seguidos";

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    private OnCorridaListEventListener eventListener;

    public FeedListFirebase(OnCorridaListEventListener eventListener) {
        this.eventListener = eventListener;
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }

    public void findMyFeeds(String id){
        DocumentReference reference = firestore.collection(FEEDS).document(id);
        setFeeds(reference);
    }

    public void findFeedsSeguidos(String id){
        DocumentReference reference = firestore.collection(FEEDS_SEGUIDOS).document(id);
        setFeeds(reference);
    }

    private void setFeeds(DocumentReference reference){
        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Feed feed = document.toObject(Feed.class);
                                eventListener.onSetList(feed.getCorridas());
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }
}
