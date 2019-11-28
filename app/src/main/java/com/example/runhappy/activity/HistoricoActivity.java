package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.runhappy.R;

import com.example.runhappy.ui.corrida.CorridaListView;

public class HistoricoActivity extends AppCompatActivity {

    private CorridaListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        listView = new CorridaListView(getApplicationContext());
        View rootView = findViewById( android.R.id.content );

        listView.inicialize( rootView );

    }
}
