package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;

public class EditarUsuarioActivity extends AppCompatActivity {

    private Integer idUsuario;
    private UsuarioDAO usuarioDAO;

    private EditText nome;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        setTitle("Editar Usuario");

        SQLiteHandle handle = new SQLiteHandle(getApplicationContext());
        usuarioDAO = new UsuarioDAOSQLite(handle);

        nome = findViewById(R.id.txtNome);
        email = findViewById(R.id.txtEmail);
        senha = findViewById(R.id.txtSenha);

        String usuarioEmail = getIntent().getExtras().getString("email", "");
        Usuario usuario = usuarioDAO.findByEmail(usuarioEmail);

        if (usuario != null) {
            idUsuario = usuario.getId();
            nome.setText(usuario.getNome());
            email.setText(usuario.getEmail());
        }

    }

    public void salvar(View view) {
        Usuario usuario = new Usuario(idUsuario, nome.getText().toString(), email.getText().toString(),
                    senha.getText().toString());

        usuarioDAO.editUsuario(usuario);

        Intent telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
        telaInicial.putExtra("nome", usuario.getNome());
        telaInicial.putExtra("email", usuario.getEmail());
        startActivity(telaInicial);

        finish();
    }

    public void cancelar(View view){
        finish();
    }
}
