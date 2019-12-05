package com.example.runhappy.ui.corrida;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;
import com.example.runhappy.model.Corrida;

import java.util.List;

public class CorridaAdapter extends RecyclerView.Adapter<CorridaViewHolder> {

    private List<Corrida> corridas;

    public CorridaAdapter(List<Corrida> corridas){
        this.corridas = corridas;
    }

    @NonNull
    @Override
    public CorridaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.corrida_recycler, parent, false);
        CorridaViewHolder corridaViewHolder = new CorridaViewHolder(view);

        return corridaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CorridaViewHolder holder, int position) {
        if(corridas != null && corridas.size() > 0){
            Corrida corrida = corridas.get(position);
            double distancia = corrida.getDistancia();
            long tempo = corrida.getTempo();
            double ritmo = corrida.getRitmoMedio();
            holder.tvDistancia.setText(corrida.getDistanciaFormatada());
            holder.tvTempo.setText(corrida.getTempoFormatado());
            holder.tvRitmo.setText(corrida.getRitmoMedioFormatado());
            holder.tvIdCorrida.setText(String.valueOf(corrida.getId()));
        }
    }

    @Override
    public int getItemCount() {
        return corridas.size();
    }
}
