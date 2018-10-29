package com.dev.marki.nerdeyesem.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.an.biometric.BiometricCallback;
import com.an.biometric.BiometricManager;
import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Utils.GpsLocation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements BiometricCallback {
    double[] latlon;

    double latitude =-1;
    double longitude = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, 123);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
                Toast.makeText(MainActivity.this, latitude +"\n"+longitude, Toast.LENGTH_SHORT).show();
            }
        });











        TextView btn_login = findViewById(R.id.main_btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BiometricManager.BiometricBuilder(MainActivity.this)
                        .setTitle(getString(R.string.biometric_title))
                        .setSubtitle(getString(R.string.biometric_subtitle))
                        .setDescription(getString(R.string.biometric_description))
                        .setNegativeButtonText(getString(R.string.biometric_negative_button_text))
                        .build()
                        .authenticate(MainActivity.this);

            }
        });
    }

    private void getLocation(){
        GpsLocation gt = new GpsLocation(getApplicationContext());
        Location location= gt.getLocation();
        if( location == null){
            Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    @Override
    public void onSdkVersionNotSupported() {
        Toast.makeText(getApplicationContext(), getString(R.string.biometric_error_sdk_not_supported), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBiometricAuthenticationNotSupported() {
        Toast.makeText(getApplicationContext(), getString(R.string.biometric_error_hardware_not_supported), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
        Toast.makeText(getApplicationContext(), getString(R.string.biometric_error_fingerprint_not_available), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {
    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {
//        Toast.makeText(getApplicationContext(), getString(R.string.biometric_failure), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationCancelled() {
        Toast.makeText(getApplicationContext(), getString(R.string.biometric_cancelled), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAuthenticationSuccessful() {
        Toast.makeText(getApplicationContext(), getString(R.string.biometric_success), Toast.LENGTH_SHORT).show();
        getLocation();
        double minDouble= 0.0000000001;
        if (latitude < minDouble|| longitude <minDouble ){
            Toast.makeText(MainActivity.this, R.string.bad_location, Toast.LENGTH_SHORT).show();
        }else if(!isOnline()){
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(MainActivity.this, Listed.class);
            intent.putExtra("latitude",""+latitude);
            intent.putExtra("longtitude",""+longitude);
            startActivity(intent);
        }    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
//        Toast.makeText(getApplicationContext(), helpString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
//        Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_SHORT).show();
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }






}