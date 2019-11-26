package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.data.CorridaDAOSQLite;
import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.corrida.CorridaAdapter;

public class HistoricoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CorridaAdapter corridaAdapter;
    private Usuario usuario;

    private UsuarioDAOSQLite usuarioDAOSQLite;
    private CorridaDAO corridaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        recyclerView = findViewById(R.id.rvCorrida);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        SQLiteHandle handle = new SQLiteHandle(getApplicationContext());
        usuarioDAOSQLite = new UsuarioDAOSQLite(handle);
        corridaDAO = new CorridaDAOSQLite(handle);

        usuario = usuarioDAOSQLite.findByEmail(TelaInicialActivity.getEmailUsuarioLogado());
        corridaAdapter = new CorridaAdapter(corridaDAO.findAll(usuario.getId()));
        recyclerView.setAdapter(corridaAdapter);
    }
}
