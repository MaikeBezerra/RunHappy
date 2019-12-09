package com.example.runhappy.ui.corrida;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runhappy.R;
import com.example.runhappy.data.SQLite.PostSQLite;
import com.example.runhappy.data.SQLite.SQLiteHandle;
import com.example.runhappy.model.Corrida;

import java.util.List;

public class CorridaAdapter extends RecyclerView.Adapter<CorridaViewHolder> {

    private List<Corrida> corridas;
    private PostSQLite sqLite;

    public CorridaAdapter(List<Corrida> corridas){
        this.corridas = corridas;
    }

    @NonNull
    @Override
    public CorridaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.corrida_recycler, parent, false);

        SQLiteHandle handle = new SQLiteHandle(view.getContext());
        sqLite = new PostSQLite(handle);

        return new CorridaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CorridaViewHolder holder, int position) {

        final Corrida corrida = corridas.get(position);

        if(corridas != null && corridas.size() > 0){
            double distancia = corrida.getDistancia();
            long tempo = corrida.getTempo();
            double ritmo = corrida.getRitmoMedio();
            holder.tvDistancia.setText(corrida.getDistanciaFormatada());
            holder.tvTempo.setText(corrida.getTempoFormatado());
            holder.tvRitmo.setText(corrida.getRitmoMedioFormatado());
            holder.tvIdCorrida.setText(String.valueOf(corrida.getId()));
        }

        holder.button.setChecked(sqLite.isCurtido(corrida.getId()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.button.isChecked()){
                    sqLite.curtirPost(corrida.getId());
                } else {
                    sqLite.descurtirPost(corrida.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return corridas.size();
    }
}
