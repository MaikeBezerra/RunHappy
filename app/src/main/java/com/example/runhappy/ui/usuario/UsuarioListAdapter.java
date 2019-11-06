package com.example.runhappy.ui.usuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;
import com.example.runhappy.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioListAdapter extends RecyclerView.Adapter<UsuarioListHolder> {

    private List<Usuario> usuarios;

    public UsuarioListAdapter(List usuarios){
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuarioListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.line_usuario_list, parent, false);

        return new UsuarioListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioListHolder holder, int position) {
        holder.txtUsuarioNome.setText(usuarios.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
