package com.example.runhappy.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.example.runhappy.R;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.firebase.UsuarioDBFirebase;
import com.example.runhappy.transactions.Constantes;
import com.example.runhappy.ui.navigation.HeaderNavigationViewModel;
import com.example.runhappy.ui.navigation.NavigationViewModel;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.runhappy.transactions.Constantes.REQUEST_CHECK_SETTINGS;

public class TelaInicialActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;

    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout layout;
    ImageView imagemUsuario;

    private FirebaseAuth auth;
    UsuarioDAO dbUsuario;
    LocationRequest mLocationRequest;
    Fragment maps;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng ultimaLocalizacao;

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tela_inicial);
        setTitle("Run Happy");


        System.out.println("aqui 1");


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


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer);
        layout.addDrawerListener(toggle);
        toggle.syncState();

        auth = FirebaseAuth.getInstance();
        dbUsuario = new UsuarioDBFirebase();
        if (auth.getCurrentUser() != null){
            auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();

            String id = user.getUid();
            dbUsuario.logar(id);
        }

//        imagemUsuario = (CircleImageView) headerView.findViewById(R.id.imagemUsuario);
//        Picasso.get().load((String)getIntent().getExtras().get("imagem")).into(imagemUsuario);

        createLocationRequest();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // Todas as configurações estão ok. Podemos iniciliazar requests de localização aqui 🙂
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                // As configurações não estão ok. Precisamos pedir para o usuário alterá-las
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(TelaInicialActivity.this, REQUEST_CHECK_SETTINGS);

                    } catch (IntentSender.SendIntentException sendEx) { }
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Toast.makeText(this, "Sua localização foi ativada", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(this, "Sua localização foi ativada, por favor ative-a", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        NavigationView navigation = findViewById(R.id.nav_menu);
        navigation.setNavigationItemSelectedListener(new NavigationViewModel(this, getApplicationContext()));

        HeaderNavigationViewModel headerView = new HeaderNavigationViewModel(this, getApplicationContext(), navigation);
        headerView.inicializeParam();




//        fusedLocationClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
//            @Override
//            public void onComplete(@NonNull Task<Location> task) {
//                if (task.isSuccessful() && task.getResult() != null){
//                    System.out.println("aqui 5");
//                    MapsActivity.atualizar(new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude()));
//                }else{
//                    System.out.println("falha por aqui" + task.getResult().getLatitude());
//                }
//
//            }
//        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void iniciar(View view) {
        Intent telaCorrida = new Intent(getApplicationContext(), AtividadeCorridaActivity.class);
        startActivity(telaCorrida);
    }

}
