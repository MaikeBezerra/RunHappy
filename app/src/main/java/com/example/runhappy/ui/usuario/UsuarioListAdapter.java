package com.example.runhappy.ui.usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;
import com.example.runhappy.model.Usuario;

import java.util.List;

public class UsuarioListAdapter extends RecyclerView.Adapter<UsuarioListHolder> {

    private List<Usuario> usuarios;
    private Context context;
    public UsuarioListAdapter(Context context, List usuarios){
        this.context = context;
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

        ImageButton button = holder.itemView.findViewById(R.id.img_seguir);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Tela de detalhes", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Seguir", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
