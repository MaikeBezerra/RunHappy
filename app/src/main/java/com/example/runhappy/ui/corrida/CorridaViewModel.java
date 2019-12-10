package com.example.runhappy.ui.corrida;

import android.app.Activity;
import android.widget.TextView;

import com.example.runhappy.R;
import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.data.SQLite.CorridaDAOSQLite;
import com.example.runhappy.data.SQLite.SQLiteHandle;
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
}
