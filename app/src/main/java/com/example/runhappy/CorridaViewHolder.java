package com.example.runhappy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CorridaViewHolder extends RecyclerView.ViewHolder {

    public TextView tvDistancia;
    public TextView tvTempo;
    public TextView tvRitmo;


    public CorridaViewHolder(@NonNull View itemView) {
        super(itemView);

        tvDistancia = (TextView) itemView.findViewById(R.id.textViewDistancia);
        tvTempo = (TextView) itemView.findViewById(R.id.textViewTempo);
        tvRitmo = (TextView) itemView.findViewById(R.id.textViewRitmo);
    }
}
