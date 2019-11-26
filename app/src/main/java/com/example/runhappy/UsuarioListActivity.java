package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.corrida.CorridaAdapter;
import com.example.runhappy.ui.usuario.UsuarioListAdapter;
import com.example.runhappy.ui.usuario.UsuarioViewModel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);

        RecyclerView recyclerView = findViewById(R.id.usuarioListView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        UsuarioViewModel model = new UsuarioViewModel(getApplicationContext());
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(model.getUsuarios());
        UsuarioListAdapter adapter = new UsuarioListAdapter(getApplicationContext(), usuarios);

        recyclerView.setAdapter(adapter);
    }
}
