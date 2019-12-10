package com.example.runhappy.services;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.runhappy.activity.AtividadeCorridaActivity;
import com.example.runhappy.activity.MapsActivity;
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
import java.util.concurrent.Executor;

public class CorridaService extends Service implements Runnable {

    private FusedLocationProviderClient fusedLocationClient;
    private List<Location> locations;
    private LatLng ultimaLocalizacao;
    private boolean running;

    public CorridaService() {
//        super("atualizarLocalizacao");
    }


    @Override
    public void onCreate(){

        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        running = true;

        locations = new ArrayList<>();
//        Thread thread = new Thread(this);
//        thread.start();
        atualizarLocalizacao();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//        if (intent ==null){
//            return;
//        }


//    }

    protected void atualizarLocalizacao(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener( new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
//                            ultimaLocalizacao = new LatLng(location.getLatitude(), location.getLongitude());
//                            MapsActivity.atualizar(ultimaLocalizacao);

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

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());


    }

    public List<Location> getLocations(){
        return this.locations;
    }

    @Override
    public void run() {
        atualizarLocalizacao();
    }
}
