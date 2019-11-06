package com.example.runhappy.ui.usuario;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;

public class UsuarioListHolder extends RecyclerView.ViewHolder {

    public TextView txtUsuarioNome;

    public UsuarioListHolder(@NonNull View item) {
        super(item);

        txtUsuarioNome = item.findViewById(R.id.txtNome);
    }



}
