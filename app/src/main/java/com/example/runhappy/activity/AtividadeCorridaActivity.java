package com.example.runhappy.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.runhappy.R;
import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.data.firebase.CorridaDBFirebase;

import com.example.runhappy.data.firebase.FeedFirebase;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.services.CorridaService;
import com.example.runhappy.transactions.Constantes;
import com.example.runhappy.ui.login.LoginViewModel;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AtividadeCorridaActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long milisegundos;
    private boolean estaContando;
    private Button pause;
    private Button bandeira;
    private double distancia;
    private long tempo;
    private double ritmoMedio;
    private List<Location> locations;
    private LatLng ultimaLocalizacao;

    //private CorridaDAO db;
    private FeedFirebase dbFeed;

    private LoginViewModel vmLogin;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_corrida);

        Intent corridaService = new Intent(AtividadeCorridaActivity.this, CorridaService.class);
        startService(corridaService);
        

        //db = new CorridaDBFirebase(getApplicationContext());
        dbFeed = new FeedFirebase();
        locations = new ArrayList<>();

        Button pause = (Button) findViewById(R.id.botao_pausar);
        milisegundos = 0;
        estaContando = true;
        chronometer = (Chronometer) findViewById(R.id.tempo);
        pause = (Button) findViewById(R.id.botao_pausar);
        bandeira = (Button) findViewById(R.id.botao_bandeira);
        bandeira.setVisibility(View.INVISIBLE);
        ritmoMedio = 0;

        chronometer.setBase(SystemClock.elapsedRealtime() - milisegundos);
        chronometer.start();
    }


    public void iniciarEPausar(View view){
        if (estaContando){
            milisegundos = SystemClock.elapsedRealtime() - chronometer.getBase();
            estaContando = false;
            chronometer.stop();
            bandeira.setVisibility(View.VISIBLE);
        }else{
            estaContando = true;
            chronometer.setBase(SystemClock.elapsedRealtime() - milisegundos);
            chronometer.start();
            bandeira.setVisibility(View.INVISIBLE);
        }

    }

    public void finalizarCorrida(View view){
        tempo = milisegundos;
        Intent finalizar = new Intent(this, FinalizarCorridaActivity.class);
        finalizar.putExtra("tempo", tempo);
        finalizar.putExtra("distancia", distancia);
        finalizar.putExtra("ritmoMedio", ritmoMedio);
        startActivityForResult(finalizar, Constantes.REQUEST_CONCLUIR);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constantes.REQUEST_CONCLUIR && resultCode == Constantes.REQUEST_CONCLUIR){

            vmLogin = LoginViewModel.getInstance(getApplicationContext());
            String idCorredor = vmLogin.idLogedUser();
            Corrida corrida = new Corrida(UUID.randomUUID().toString(), distancia, tempo, ritmoMedio, idCorredor);
            //db.adicionarCorrida(corrida);
            dbFeed.createFeed(corrida);
            finish();
        } else if(requestCode == Constantes.REQUEST_CONCLUIR && resultCode == Constantes.REQUEST_CANCELAR){

        }
    }

    protected void atualizarLocalizacao(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this,  new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            ultimaLocalizacao = new LatLng(location.getLatitude(), location.getLongitude());
                            MapsActivity.atualizar(ultimaLocalizacao);

                            System.out.println("aqui 2");
                        }else{
                            System.out.println("location null");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("failure aqui");

                    }
                });

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(2 * 1000);
        locationRequest.setFastestInterval(1 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(builder.build())
                .addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i("Teste", String.valueOf(locationSettingsResponse.getLocationSettingsStates().isGpsPresent()));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ResolvableApiException){
                            try {
                                ResolvableApiException resolvable = (ResolvableApiException) e;
                                resolvable.startResolutionForResult(AtividadeCorridaActivity.this, 10);
                            }catch (IntentSender.SendIntentException e1){

                            }
                        }
                    }
                });

        final LocationCallback locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult == null){
                    System.out.println("location é null");
                    return;
                }

                for (Location location : locationResult.getLocations()){
                    locations.add(location);
                    System.out.println("latitude atual: "+location.getLatitude());
                    Log.i("teste", "latitude atual: "+location.getLatitude());
                    Log.i("teste2", ""+locations.size());
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);


    }








}
