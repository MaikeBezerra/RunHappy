package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;

public class HistoricoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CorridaAdapter corridaAdapter;
    private Usuario usuario;

    private UsuarioDAOSQLite usuarioDAOSQLite;
    private SQLiteHandle handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        handle = new SQLiteHandle(this);

        recyclerView = (RecyclerView) findViewById(R.id.rvCorrida);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        usuarioDAOSQLite = new UsuarioDAOSQLite(handle);

        usuario = usuarioDAOSQLite.findByEmail(TelaInicialActivity.getEmailUsuarioLogado());
        System.out.println(usuario.getNome()+"aaaaaaaaaaaaaaaaaaaaaaaa");
        corridaAdapter = new CorridaAdapter(usuario.getCorridas());
        recyclerView.setAdapter(corridaAdapter);
    }
}
