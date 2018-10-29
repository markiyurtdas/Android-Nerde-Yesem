package com.dev.marki.nerdeyesem.Utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.util.Log;


import java.util.List;

import androidx.core.content.ContextCompat;

import static android.content.Context.LOCATION_SERVICE;


public class GpsLocation implements LocationListener {

    Context context;


    public GpsLocation(Context context) {
        super();
        this.context = context;
    }

    public Location getLocation(){
        if (ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Criteria myCriteria = new Criteria();
        myCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        try {
            LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            List<String> providers = lm.getProviders(true);

            boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled){
                Log.d("try1","Try catch te catch");

                myCriteria.setPowerRequirement(Criteria.POWER_LOW);
// let Android select the right location provider for you
                String myProvider = lm.getBestProvider(myCriteria, true);
                Log.d("try2","Try catch te catch");

// finally require updates at -at least- the desired rate
                long minTimeMillis = 500; // 600,000 milliseconds make 10 minutes
                lm.requestLocationUpdates(myProvider,minTimeMillis,0,this);
                //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,30,this);
                Log.d("try3","Try catch te catch");

                Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                for (String provider : providers) {
                    Location l = lm.getLastKnownLocation(provider);
                    Log.d("try4","Try catch te catch");

                    if (l == null) {
                        continue;
                    }
                    if (loc == null || l.getAccuracy() < loc.getAccuracy()) {
                        // Found best last known location: %s", l);
                        loc = l;
                    }
                }

                return loc;
            }else{
                Log.d("noGPS","error");
            }
        }catch (Exception e){
            Log.d("trygirdi","Try catch te catch");
            e.printStackTrace();
        }
        return null;
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



}