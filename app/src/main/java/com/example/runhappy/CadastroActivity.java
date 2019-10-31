package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.usuario.UsuarioViewModel;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;

    private UsuarioViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        viewModel = new UsuarioViewModel(getApplicationContext());

        nome = findViewById(R.id.edtNome);
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
    }

    public void cadastrar(View view) {
        Usuario usuario = new Usuario(nome.getText().toString(), email.getText().toString(), senha.getText().toString());
        viewModel.adicionarUsuario(usuario);

        Intent telaInicial = new Intent(this, TelaInicialActivity.class);
        telaInicial.putExtra("nome", usuario.getNome());
        telaInicial.putExtra("email", usuario.getEmail());
        startActivity(telaInicial);

        //finish();
    }

}
