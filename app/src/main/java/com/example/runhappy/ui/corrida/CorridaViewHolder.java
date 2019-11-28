package com.example.runhappy.ui.corrida;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;

public class CorridaViewHolder extends RecyclerView.ViewHolder {

    public TextView tvIdCorrida;
    public TextView tvDistancia;
    public TextView tvTempo;
    public TextView tvRitmo;


    public CorridaViewHolder(@NonNull View itemView) {
        super(itemView);

        tvDistancia =  itemView.findViewById(R.id.textViewDistancia);
        tvTempo =  itemView.findViewById(R.id.textViewTempo);
        tvRitmo = itemView.findViewById(R.id.textViewRitmo);
        tvIdCorrida = itemView.findViewById(R.id.textViewIdCorrida);
    }
}
