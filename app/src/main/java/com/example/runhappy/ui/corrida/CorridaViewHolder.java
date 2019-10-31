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

        tvDistancia = (TextView) itemView.findViewById(R.id.textViewDistancia);
        tvTempo = (TextView) itemView.findViewById(R.id.textViewTempo);
        tvRitmo = (TextView) itemView.findViewById(R.id.textViewRitmo);
        tvIdCorrida = (TextView) itemView.findViewById(R.id.textViewIdCorrida);
    }
}
