package com.example.runhappy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.LOCATION_SERVICE;
import static android.widget.Toast.*;
import static androidx.core.content.ContextCompat.getSystemService;

public class MapsActivity extends SupportMapFragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private LatLng latLng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);

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
        LatLng quixada = new LatLng(-4.97813, -39.0188);
        mMap.addMarker(new MarkerOptions().position(quixada).title("Marker in Quixada"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(quixada));
        float zoomLevel = 10.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(quixada, zoomLevel));
    }

//    public void pedirPermissoes(){
//        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        }
//        else
//            configurarServico();
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 1: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    configurarServico();
//                } else {
//
//                }
//                return;
//            }
//        }
//    }

//    public void configurarServico(){
//        try {
//            LocationManager locationManager = getSystemService(LOCATION_SERVICE);
//
//            LocationListener locationListener = new LocationListener() {
//                public void onLocationChanged(Location location) {
//                    atualizar(location);
//                }
//
//                public void onStatusChanged(String provider, int status, Bundle extras) { }
//
//                public void onProviderEnabled(String provider) { }
//
//                public void onProviderDisabled(String provider) { }
//            };
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//        }catch(SecurityException ex){
//
//        }
//    }


    public void atualizar(Location location){
        Double latPoint = location.getLatitude();
        Double lngPoint = location.getLongitude();

        latLng = new LatLng(latPoint.doubleValue(), lngPoint.doubleValue());
    }


}
