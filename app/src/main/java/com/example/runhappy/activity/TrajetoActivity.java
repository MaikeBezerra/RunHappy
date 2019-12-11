package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import com.example.runhappy.R;
import com.google.android.gms.maps.model.LatLng;


import java.util.ArrayList;
import java.util.List;

public class TrajetoActivity extends AppCompatActivity {

    private List<LatLng> latLngs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trajeto);

        // trocar por getParcelable()
        latLngs = new ArrayList<>();
        List<com.google.android.gms.maps.model.LatLng> locations = getIntent().getParcelableArrayListExtra("locations");
        if (locations != null){
            latLngs = locations;
        }


        MapsActivity mapsActivity = new MapsActivity();

        mapsActivity.desenharTrajeto(latLngs);

    }
}
