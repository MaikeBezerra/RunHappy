package com.example.runhappy.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MapsActivity extends SupportMapFragment implements OnMapReadyCallback{

    private static GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private LatLng latLng;
    private FusedLocationProviderClient fusedLocationClient;
    private static LatLng ultimaLocalizacao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
//
//        System.out.println("aqui 1");
//
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener((Activity) getContext(),  new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            ultimaLocalicacao = new LatLng(location.getLatitude(), location.getLongitude());
//                            System.out.println("aqui 2");
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        System.out.println("failure aqui");
//                    }
//                });






        getMapAsync(this);

    }

    public static void setUltimaLocalizacao(LatLng latLong){
        ultimaLocalizacao = latLong;
    }


    @Override
    public void onResume(){
        super.onResume();

        int errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());

        switch (errorCode){
            case ConnectionResult.SERVICE_MISSING:
            case ConnectionResult.SERVICE_DISABLED:
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                break;
            case ConnectionResult.SUCCESS:
                System.out.println("google play services disponível");
                break;
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng localizacao;
        if(ultimaLocalizacao != null){
            localizacao = ultimaLocalizacao;
            System.out.println("aqui 3");

        }else{
            localizacao = new LatLng(-4.97813, -39.0188);
            System.out.println("aqui 4");
        }




//        mMap.addMarker(new MarkerOptions().position(localizacao).title("Marker in Quixada"));
//        //mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacao));
//        float zoomLevel = 10.0f; //This goes up to 21
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacao, zoomLevel));
    }

    public static void atualizar(LatLng location){
        ultimaLocalizacao = location;
        mMap.addMarker(new MarkerOptions().position(ultimaLocalizacao).title("Você"));
        float zoomLevel = 15.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ultimaLocalizacao, zoomLevel));
    }


    public void desenharTrajeto(List<LatLng> latLngs){

        if (latLngs != null && latLngs.size() > 0) {
            LatLng latLng = latLngs.get(0);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Início"));
        }

        float zoomLevel = 15.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ultimaLocalizacao, zoomLevel));
        System.out.println("entrou no desenhar");

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .addAll(latLngs)
                .width(5)
                .color(Color.GREEN));
    }


}
