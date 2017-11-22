package metier;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import data.outils.DBManager;
import domaine.User;

import static android.content.Context.LOCATION_SERVICE;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by ottavio on 21/11/2017.
 */

public class CarteManagement{

    private static LocationListener locationListener;
    private static LocationManager locationManager;
    private static CarteManagement.GpsAsync mGpsAsync = null;
    private static Location l = new Location("gps");

    public static void gps(Context c){
        locationManager = (LocationManager) c.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                l = location;
                mGpsAsync = new CarteManagement.GpsAsync();
                mGpsAsync.execute((Void) null);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(c,intent, Bundle.EMPTY);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) c, new String[]{
                        Manifest.permission .ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
            else{
                locationManager.requestLocationUpdates("gps", 1000, 1, locationListener);
            }
        }
    }

    public static class GpsAsync extends AsyncTask<Void,Void, Boolean> {

        public GpsAsync(){

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (l.getLatitude() != 0 && l.getLongitude() != 0) {
                DBManager.setLocation(User.get_idUser(), l.getLatitude(), l.getLongitude());
            }

            return null;
        }
    }
    public static class GetPositionAsync extends AsyncTask<Void,Void, Boolean> {

        public GetPositionAsync(){

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            DBManager.getPosition();
            return null;
        }


    }
}



