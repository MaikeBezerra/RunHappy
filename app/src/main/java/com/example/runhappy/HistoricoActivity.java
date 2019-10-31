package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.runhappy.data.CorridaDAOSQLite;
import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.corrida.CorridaAdapter;

public class HistoricoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CorridaAdapter corridaAdapter;
    private Usuario usuario;

    private UsuarioDAOSQLite usuarioDAOSQLite;
    private CorridaDAOSQLite corridaDAOSQLite;
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
        corridaDAOSQLite = new CorridaDAOSQLite(handle);

        usuario = usuarioDAOSQLite.findByEmail(TelaInicialActivity.getEmailUsuarioLogado());

        System.out.println(usuario.getNome()+"aaaaaaaaaaaaaaaaaaaaaaaa");
        Corrida c1 = new Corrida(1, 2000, 2000000, 13);
        Corrida c2 = new Corrida(2, 2000, 2000000, 13);
        Corrida c3 = new Corrida(3, 2000, 2000000, 13);
        Corrida c4 = new Corrida(4, 2000, 2000000, 13);
        Corrida c5 = new Corrida(5, 2000, 2000000, 13);
        Corrida c6 = new Corrida(6, 2000, 2000000, 13);
        Corrida c7 = new Corrida(7, 2000, 2000000, 13);
        Corrida c8 = new Corrida(8, 2000, 2000000, 13);
        usuario.addCorrida(c1);
        usuario.addCorrida(c2);
        usuario.addCorrida(c3);
        usuario.addCorrida(c4);
        usuario.addCorrida(c5);
        usuario.addCorrida(c6);
        usuario.addCorrida(c7);
        usuario.addCorrida(c8);
        corridaAdapter = new CorridaAdapter(corridaDAOSQLite.findAll());
        recyclerView.setAdapter(corridaAdapter);
    }
}
