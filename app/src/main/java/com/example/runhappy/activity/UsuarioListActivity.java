package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.runhappy.R;
import com.example.runhappy.ui.usuario.UsuarioListView;

public class UsuarioListActivity extends AppCompatActivity {

    private UsuarioListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);

        View rootView = findViewById( android.R.id.content );
        listView = new UsuarioListView(getApplicationContext());
        listView.inicialize(rootView);

    }
}
