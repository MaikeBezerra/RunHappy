package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.runhappy.R;
import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.UsuarioChangeListener;
import com.example.runhappy.ui.login.LoginViewModel;
import com.example.runhappy.ui.usuario.UsuarioFormViewModel;

public class EditarUsuarioActivity extends AppCompatActivity implements UsuarioObserver {

    private LoginViewModel vmLogin;
    private UsuarioFormViewModel vmForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        setTitle("Editar Usuario");

        View rootView = findViewById(android.R.id.content);

        vmLogin = LoginViewModel.getInstance(getApplicationContext());
        vmForm = new UsuarioFormViewModel(getApplicationContext());
        vmForm.inicialize(rootView);

        UsuarioChangeListener.getInstance().adicionaObeserver(this);

        Usuario usuario = vmLogin.getUsuario();

        if (usuario != null) {
            onChangeUsuario(usuario);
        }

    }

    public void salvar(View view) {

        vmForm.editarUsuario();
//
//        Intent telaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
//        telaInicial.putExtra("nome", usuario.getNome());
//        telaInicial.putExtra("email", usuario.getEmail());
//        startActivity(telaInicial);
//
//        finish();
        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
    }

    public void cancelar(View view){
        finish();
    }

    @Override
    public void onChangeUsuario(Usuario usuario) {
        vmForm.setFields(usuario.getNome(), usuario.getEmail());
    }
}
