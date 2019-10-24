package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.edtNome);
        email = (EditText) findViewById(R.id.edtEmail);
        senha = (EditText) findViewById(R.id.edtSenha);
    }

    public void cadastrar(View view) {
        SQLiteHandle handler = new SQLiteHandle(this);
        UsuarioDAOSQLite db = new UsuarioDAOSQLite(handler);

        Usuario usuario = new Usuario(nome.getText().toString(), email.getText().toString(), senha.getText().toString());
        db.addUsuario(usuario);

        Intent telaInicial = new Intent(this, TelaInicialActivity.class);
        startActivity(telaInicial);
    }


}
