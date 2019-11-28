package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.runhappy.R;
import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.data.SQLite.CorridaDAOSQLite;
import com.example.runhappy.data.SQLite.SQLiteHandle;
import com.example.runhappy.data.SQLite.UsuarioDAOSQLite;
import com.example.runhappy.data.firebase.CorridaDBFirebase;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.corrida.CorridaAdapter;

public class HistoricoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CorridaAdapter corridaAdapter;
    private Usuario usuario;

    private CorridaDAO corridaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        recyclerView = findViewById(R.id.rvCorrida);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        corridaDAO = new CorridaDBFirebase(getApplicationContext());

        corridaAdapter = new CorridaAdapter(corridaDAO.getCorridas());
        recyclerView.setAdapter(corridaAdapter);
    }
}
