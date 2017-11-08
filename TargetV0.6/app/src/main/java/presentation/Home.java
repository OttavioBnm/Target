package presentation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;

import java.sql.ResultSet;
import java.sql.Statement;
import data.connexion.Connect;
import cfpt.target.R;
import data.outils.DBManager;
import domaine.User;
import metier.HomeManagement;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Home extends AppCompatActivity {

    private LocationListener locationListener;
    private Button button;
    private TextView textView;
    private LocationManager locationManager;
    private ScrollView s;
    private SupportMapFragment mMapFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                cleanLayout();
                afficheProfil();
                return true;
            case R.id.navigation_chat:
                cleanLayout();
                setTitle("Chat");
                return true;
            case R.id.navigation_Localisation:
                cleanLayout();
                location();
                gps();
                setMap();
                s.setVisibility(View.INVISIBLE);
                setTitle("Localiser");

                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().hide();
        s = (ScrollView) findViewById(R.id.sbScroll);
        cleanLayout();
        afficheProfil();
    }

    private void afficheProfil() {
        s.setVisibility(View.VISIBLE);
        TableLayout container = (TableLayout) findViewById(R.id.table);
        int[] icones = new int[]{R.drawable.ic_action_name, R.drawable.ic_description, R.drawable.ic_home, R.drawable.ic_genre, R.drawable.ic_calendrier, R.drawable.ic_langue, R.drawable.ic_hauteur, R.drawable.ic_poids, R.drawable.ic_job};
        String[][] profil = new String[13][2];
        TableRow.LayoutParams layout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, 200);
        profil = HomeManagement.elementsProfil();
        for (int i = 0; i < profil.length; i++) {
            TableRow myRow = new TableRow(this);
            for (int j = 0; j < 2; j += 2) {
                if (profil[i][j + 1] != null) {
                    ImageView myImage = new ImageView(this);
                    myImage.setLayoutParams(new TableRow.LayoutParams(150, 200));
                    Drawable myDrawable = getResources().getDrawable(icones[i]);
                    myImage.setImageDrawable(myDrawable);
                    myRow.addView(myImage);

                    TextView myText = new TextView(this);
                    myText.setLayoutParams(layout);
                    myText.setTextSize(24);
                    myText.setText(profil[i][j] + ":\n" + profil[i][j + 1]);
                    myRow.addView(myText);
                }
            }
            container.addView(myRow);
        }
    }

    private void location(){
        TableLayout t = (TableLayout) findViewById(R.id.table);
        button = new Button(this);
        button.setLayoutParams(new ConstraintLayout.LayoutParams(150, 200));
        t.addView(button);

        textView = new TextView(this);
        textView.setLayoutParams(new ConstraintLayout.LayoutParams(300, 150));
        t.addView(textView);
    }

    private void cleanLayout() {
        TableLayout container = (TableLayout) findViewById(R.id.table);
        container.removeAllViews();
        mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map));
        mMapFragment.getView().setVisibility(View.INVISIBLE);
    }

    public void gps() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                textView.append("Latitude : " + location.getLatitude() + "\n Longitude : " + location.getLongitude());
                DBManager.setLocation();
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
                startActivity(intent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission .ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
            else{
                configureButton();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    configureButton();

                }
                break;
        }
    }

    private void configureButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates("gps", 5000, 1, locationListener);
            }
        });
    }

    private void setMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map));
        mMapFragment.getView().setVisibility(View.VISIBLE);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions().title(User.get_nom()).position(new LatLng(45,4)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45,4),15));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }
        });
    }
}
