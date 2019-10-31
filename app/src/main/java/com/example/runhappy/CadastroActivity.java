package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.usuario.UsuarioFormViewModel;
import com.example.runhappy.ui.usuario.UsuarioViewModel;

public class CadastroActivity extends AppCompatActivity {

    private UsuarioViewModel viewModel;
    private UsuarioFormViewModel formViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        viewModel = new UsuarioViewModel(getApplicationContext());
        formViewModel = new UsuarioFormViewModel(this);
    }

    public void cadastrar(View view) {
        Usuario usuario = formViewModel.getUsuario();
        viewModel.adicionarUsuario(usuario);

        Intent telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
        telaInicial.putExtra("nome", usuario.getNome());
        telaInicial.putExtra("email", usuario.getEmail());
        startActivity(telaInicial);
    }

}
