package com.example.runhappy;

import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.runhappy.presenter.UsuarioLocationListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

public class TelaInicialActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private FragmentManager fragmentManager;

    private UsuarioLocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

//        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationListener = new UsuarioLocationListener(this, this, manager);
//        locationListener.getLocation();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                locationListener.getLocation();
            } else {
                Toast.makeText(getApplicationContext(),"Permissão de localização Negada", Toast.LENGTH_LONG).show();
            }

        }
    }
}
