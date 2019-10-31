package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;

public class LoginPrincipalActivity extends AppCompatActivity {

    private UsuarioAuth auth;
    private UsuarioDAO usuarioDAO;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //auth = UsuarioFirebaseAuth.getInstance(getApplicationContext());

        SQLiteHandle handle = new SQLiteHandle(getApplicationContext());
        usuarioDAO = new UsuarioDAOSQLite(handle);

        email = findViewById(R.id.username);
        senha = findViewById(R.id.password);

    }

    public void login(View view) {
        //auth.login(email.getText().toString(), senha.getText().toString());
        Usuario usuario = usuarioDAO.findByEmail(email.getText().toString());

        if (usuario != null && usuario.getSenha().equals(senha.getText().toString())){
            Intent telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
            telaInicial.putExtra("nome", usuario.getNome());
            telaInicial.putExtra("email", usuario.getEmail());
            startActivity(telaInicial);
        } else {

            Toast.makeText(getApplicationContext(), "Erro nos parametros", Toast.LENGTH_SHORT).show();
        }
    }

}