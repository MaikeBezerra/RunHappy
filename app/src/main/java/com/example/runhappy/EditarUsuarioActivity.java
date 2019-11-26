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
import com.example.runhappy.presenter.UsuarioChangeListener;
import com.example.runhappy.ui.usuario.UsuarioFormViewModel;
import com.example.runhappy.ui.usuario.UsuarioViewModel;

public class EditarUsuarioActivity extends AppCompatActivity implements UsuarioObserver {

    private String idUsuario;

    private UsuarioViewModel viewModel;
    private UsuarioFormViewModel formViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        setTitle("Editar Usuario");

        viewModel = new UsuarioViewModel(this, getApplicationContext());
        formViewModel = new UsuarioFormViewModel(this);

        UsuarioChangeListener.getInstance().adicionaObeserver(this);

        String usuarioEmail = getIntent().getExtras().getString("email", "");
        Usuario usuario = viewModel.findUsuarioByEmail(usuarioEmail);

        if (usuario != null) {
            onChangeUsuario(usuario);
        }

    }

    public void salvar(View view) {
        Usuario usuario = formViewModel.updateIdField(idUsuario);
        viewModel.editarUsuario(usuario);

        Intent telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
        telaInicial.putExtra("nome", usuario.getNome());
        telaInicial.putExtra("email", usuario.getEmail());
        startActivity(telaInicial);

        finish();
    }

    public void cancelar(View view){
        finish();
    }

    @Override
    public void onChangeUsuario(Usuario usuario) {
        idUsuario = usuario.getId();
        formViewModel.setFields(usuario.getNome(), usuario.getEmail());
    }
}
