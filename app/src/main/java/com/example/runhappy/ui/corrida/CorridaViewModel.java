package com.example.runhappy.ui.corrida;

import android.app.Activity;
import android.widget.TextView;

import com.example.runhappy.R;
import com.example.runhappy.TelaInicialActivity;
import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.data.CorridaDAOSQLite;
import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.usuario.UsuarioViewModel;

public class CorridaViewModel {

    private Activity activity;

    private TextView distancia;
    private TextView tempo;
    private TextView ritmoMedio;

    private CorridaDAO corridaDAO;
    private UsuarioViewModel usuarioView;

    public CorridaViewModel(Activity activity){
        this.activity = activity;
        setAttributes();
    }

    private void setAttributes(){
        distancia = activity.findViewById(R.id.textViewDistancia);
        tempo = activity.findViewById(R.id.textViewTempo);
        ritmoMedio = activity.findViewById(R.id.textViewRitmoMedio);

        SQLiteHandle handle = new SQLiteHandle(activity.getApplicationContext());
        corridaDAO = new CorridaDAOSQLite(handle);
    }

    public void setValuesAttributes(){

        if(!activity.getIntent().getExtras().isEmpty()) {
            String distancia = activity.getIntent().getExtras().get("distancia").toString();
            double auxDistancia = (double) activity.getIntent().getExtras().get("distancia");

            long auxTempo = (long) activity.getIntent().getExtras().get("tempo");
            int horas = (int) Math.floor((auxTempo / (3.6 * 1000000)));
            auxTempo -= horas;
            int minutos = (int) Math.floor((((auxTempo) / 60000)));
            auxTempo -= minutos;
            int segundos = (int) Math.floor((auxTempo - minutos) / 1000);
            auxTempo -= segundos;

            String tempo = horas + "h" + " " + minutos + "min" + " " + segundos + "s";
            String ritmoMedio = (String) activity.getIntent().getExtras().get("ritmoMedio").toString();

            if (auxDistancia > 1000) {
                distancia = (int) Math.floor(auxDistancia / 1000) + "km" + " " + (int) (auxDistancia % 1000) + "m";
            } else {
                distancia = String.valueOf((int) (auxDistancia % 1000) + "m");
            }

            this.distancia.setText(distancia);
            this.tempo.setText(tempo);
            this.ritmoMedio.setText(ritmoMedio);
        }
    }

    public void salvar(){
        usuarioView = new UsuarioViewModel(activity, activity.getApplicationContext());
        Usuario usuario = usuarioView.findUsuarioByEmail("Test");

        Corrida corrida = new Corrida((double) activity.getIntent().getExtras().get("distancia"), (long) activity.getIntent().getExtras().get("tempo"),
                (double) activity.getIntent().getExtras().get("ritmoMedio"), usuario.getId());
        corridaDAO.adicionarCorrida(corrida);
    }
}
