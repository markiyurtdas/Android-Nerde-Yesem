package com.dev.marki.nerdeyesem.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.fingerprint.FingerprintManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.an.biometric.BiometricCallback;
import com.an.biometric.BiometricManager;
import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Utils.GpsLocation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements BiometricCallback {

    private boolean pressedBefore = false;
    double latitude =-1;
    double longitude = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Access fine location
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        final FingerprintManager fingerprintManager = (FingerprintManager) this.getSystemService(Context.FINGERPRINT_SERVICE);


        //startActivity(new Intent(MainActivity.this, Listed.class));

        //For entering program Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if  get location then resume
                if (getLocation()){
                    //if device has internet connection then resume
                    if(!isOnline()){
                        Toast.makeText(MainActivity.this, R.string.no_internet, Toast.LENGTH_SHORT).show();
                    }else{
                        //if device doesn't support finger print make Toast message and resume
                        if (!fingerprintManager.isHardwareDetected()) {
                            // Device doesn't support fingerprint authentication
                            Toast.makeText(MainActivity.this, "Device doesn't support fingerprint authentication ", Toast.LENGTH_SHORT).show();
                            seconActivity();

                            // Device  support finger print but there is no fingerprint
                        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                            Toast.makeText(MainActivity.this, "Please enroll a Fingerprint ", Toast.LENGTH_SHORT).show();
                        } else {//Everything OK then resume
                            if (pressedBefore)
                                seconActivity();
                            else
                                getFingerPrintManager();
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this, R.string.bad_location, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    //Calling fingerprint manager for biometric(Fingerprint ) authentication
    private void getFingerPrintManager(){
        new BiometricManager.BiometricBuilder(MainActivity.this)
                .setTitle(getString(R.string.biometric_title))
                .setSubtitle(getString(R.string.biometric_subtitle))
                .setDescription(getString(R.string.biometric_description))
                .setNegativeButtonText(getString(R.string.biometric_negative_button_text))
                .build()
                .authenticate(MainActivity.this);
    }

    //if there is no reason for stop program then pass to another activity page
    private void seconActivity(){
            Intent intent = new Intent(MainActivity.this, Listed.class);
            intent.putExtra("latitude",""+latitude);
            intent.putExtra("longtitude",""+longitude);
            startActivity(intent);

    }

    //getting location. (latitude longitude)
    private boolean getLocation(){
        GpsLocation gt = new GpsLocation(getApplicationContext());
        Location location= gt.getLocation();
        boolean check = false;
        if( location == null){

            Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            check = true;
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        return check;
    }


    //if finger print authentication is succed this blog is running
    @Override
    public void onAuthenticationSuccessful() {
        pressedBefore = true;
        Toast.makeText(getApplicationContext(), getString(R.string.biometric_success), Toast.LENGTH_SHORT).show();
        getLocation();
        double minDouble= 0.0000000001;
        seconActivity();
    }

    //Check for internet connection
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
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
    public void onAuthenticationFailed() {    }


    //if user press the cancel button then close app
    @Override
    public void onAuthenticationCancelled() {
        Toast.makeText(getApplicationContext(), getString(R.string.biometric_cancelled), Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {    }








}