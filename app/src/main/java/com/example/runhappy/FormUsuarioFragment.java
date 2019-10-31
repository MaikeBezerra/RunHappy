package com.example.runhappy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;

public class FormUsuarioFragment extends Fragment {

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;

    private UsuarioDAO usuarioDAO;

    private int id;

    public FormUsuarioFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_usuario, container, false);

        //id = getArguments().getInt("id", 0);
//        String nome = getArguments().getString("nome", "");
//        String email = getArguments().getString("email", "");
//        String senha = getArguments().getString("senha", "");
//
//        txtNome = view.findViewById(R.id.txtNome);
//        txtEmail = view.findViewById(R.id.txtEmail);
//        txtSenha = view.findViewById(R.id.txtSenha);
//
//        txtNome.setText(nome);
//        txtEmail.setText(email);
//        txtSenha.setText(senha);


//
//        SQLiteHandle handle = new SQLiteHandle(view.getContext());
//        usuarioDAO = new UsuarioDAOSQLite(handle);
        return view;
    }

    public void salvar(View view){

        if(id != 0) {
            Usuario usuario = new Usuario(id, txtNome.getText().toString(), txtEmail.getText().toString(),
                    txtSenha.getText().toString());
            usuarioDAO.editUsuario(usuario);
        }

        Intent telaInicial = new Intent(view.getContext(), TelaInicialActivity.class);
        telaInicial.putExtra("Nome", txtNome.getText().toString());
        startActivity(telaInicial);
    }
}
