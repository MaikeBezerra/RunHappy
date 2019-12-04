package com.example.runhappy.ui.usuario;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;
import com.example.runhappy.data.firebase.UsuarioListFirebase;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.OnUsuarioListEventListener;
import com.example.runhappy.ui.login.LoginViewModel;

import java.util.List;

public class UsuarioListView implements OnUsuarioListEventListener {

    private Context context;
    private RecyclerView recyclerView;
    private UsuarioListAdapter listAdapter;

    private List<Usuario> usuarios;
    private LoginViewModel vmLogin;
    private UsuarioListFirebase firebase;

    public UsuarioListView(Context context){
        this.context = context;
        this.vmLogin = LoginViewModel.getInstance(context);
        this.firebase = new UsuarioListFirebase( this );
    }

    public void inicialize(View view){
        recyclerView = view.findViewById(R.id.usuarioListView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void inicializaFindAll(){
        firebase.findAll(vmLogin.idLogedUser());
    }

    public void inicializaSeguidos(){
        firebase.findAllSeguidos(vmLogin.idLogedUser());
    }

    public void inicializaSeguidores(){
        firebase.findAllSeguidores(vmLogin.idLogedUser());
    }

    public void seguir(Usuario seguido){
        firebase.adicionarSeguidor(vmLogin.getUsuario(), seguido);
    }

    @Override
    public void onSetList(List<Usuario> usuarios) {
        setUsuarios(usuarios);
    }

    private void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        setRecyclerView();
    }

    private void setRecyclerView(){
        listAdapter = new UsuarioListAdapter(context, usuarios);
        recyclerView.setAdapter(listAdapter);
    }
}
