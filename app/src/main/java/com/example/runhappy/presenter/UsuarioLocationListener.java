package com.example.runhappy.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

public class UsuarioLocationListener implements android.location.LocationListener {

    private LocationManager manager;
    private Context context;
    private Activity activity;

    public UsuarioLocationListener(Activity activity, Context context, LocationManager manager) {
        this.activity = activity;
        this.context = context;
        this.manager = manager;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity ,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);

        } else {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1000, this);
        }
    }
}
