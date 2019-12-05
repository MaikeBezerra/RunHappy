package com.example.runhappy.ui.corrida;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;
import com.example.runhappy.data.firebase.CorridaListFirebase;
import com.example.runhappy.data.firebase.FeedListFirebase;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.presenter.OnCorridaListEventListener;
import com.example.runhappy.ui.login.LoginViewModel;

import java.util.List;

public class CorridaListView implements OnCorridaListEventListener {

    private Context context;
    private RecyclerView recyclerView;
    private CorridaAdapter corridaAdapter;

    private List<Corrida> corridas;
    private LoginViewModel vmLogin;

    private FeedListFirebase feedList;

    public CorridaListView(Context context){
        this.context = context;
        this.vmLogin = LoginViewModel.getInstance(context);
        this.feedList = new FeedListFirebase(this);
    }

    public void inicialize(View view){

        recyclerView = view.findViewById(R.id.rvCorrida);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        String id = vmLogin.idLogedUser();
        feedList.findMyFeeds(id);

    }

    @Override
    public void onSetList(List<Corrida> corridas) {
        this.corridas = corridas;
        setCorridas();
    }

    private void setCorridas(){
        this.corridaAdapter = new CorridaAdapter(corridas);
        recyclerView.setAdapter(corridaAdapter);
    }
}
