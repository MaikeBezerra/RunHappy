package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.runhappy.ui.usuario.UsuarioListAdapter;

public class UsuarioListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);

        RecyclerView recyclerView = findViewById(R.id.usuarioListView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        UsuarioListAdapter adapter = new UsuarioListAdapter(getApplicationContext(), null);
        recyclerView.setAdapter(adapter);
    }
}
