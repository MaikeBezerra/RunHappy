package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.data.UsuarioDBFirebase;
import com.example.runhappy.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;

    private UsuarioAuth usuarioAuth;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        //usuarioAuth = UsuarioFirebaseAuth.getInstance(this);

        //usuarioDAO = UsuarioDBFirebase.getInstance();
        SQLiteHandle handle = new SQLiteHandle(getApplicationContext());
        usuarioDAO = new UsuarioDAOSQLite(handle);

        nome = findViewById(R.id.edtNome);
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
    }

    public void cadastrar(View view) {
        Usuario usuario = new Usuario(nome.getText().toString(), email.getText().toString(), senha.getText().toString());

        //usuarioAuth.registrar(email.getText().toString(), senha.getText().toString());
        usuarioDAO.addUsuario(usuario);

        Intent telaInicial = new Intent(this, TelaInicialActivity.class);
        startActivity(telaInicial);
    }


}
