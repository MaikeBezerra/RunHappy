package com.example.runhappy.ui.usuario;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;
import com.example.runhappy.activity.InfoUsuarioActivity;
import com.example.runhappy.model.Usuario;

import java.util.List;

public class UsuarioListAdapter extends RecyclerView.Adapter<UsuarioListHolder> {

    private Context context;
    private UsuarioListView listView;

    private List<Usuario> usuarios;

    public UsuarioListAdapter(Context context, List usuarios){
        this.context = context;
        this.usuarios = usuarios;
        this.listView = new UsuarioListView(context);
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
    public void onBindViewHolder(@NonNull UsuarioListHolder holder, final int position) {

        holder.txtUsuarioNome.setText(usuarios.get(position).getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Tela de detalhes", Toast.LENGTH_SHORT).show();
                Intent infoUsuario = new Intent(context, InfoUsuarioActivity.class);
                infoUsuario.putExtra("idBusca", usuarios.get(position).getId());
                infoUsuario.putExtra("nomeUsuario", usuarios.get(position).getNome());
                infoUsuario.putExtra("idUsuario", usuarios.get(position).getId());
                infoUsuario.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(infoUsuario);

            }
        });

        ImageButton btnSeguir = holder.itemView.findViewById(R.id.img_seguir);
        btnSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.seguir(usuarios.get(position));
                Toast.makeText(context, "Seguir", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

}
