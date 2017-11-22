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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import data.connexion.Connect;
import cfpt.target.R;
import data.outils.DBManager;
import domaine.Match;
import domaine.User;
import metier.CarteManagement;
import metier.HomeManagement;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Home extends AppCompatActivity {

    private ScrollView s;
    private SupportMapFragment mMapFragment;
    private static HomeManagement.MatchesAsync mMatchAsync = null;
    private static CarteManagement.GetPositionAsync getPosAsync = new CarteManagement.GetPositionAsync();
    private SupportMapFragment mapFragment;
    private ArrayList<Marker> markers = new ArrayList();
    private ArrayList<Match> m = User.get_Matchs();


    private Handler h = new Handler();
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            h.postDelayed(this, 1000);
            getPosAsync = new CarteManagement.GetPositionAsync();
            getPosAsync.execute((Void)null);
            updateMarkers();
        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                cleanLayout();
                afficheProfil();
                h.removeCallbacks(r);
                return true;
            case R.id.navigation_chat:
                cleanLayout();
                setTitle("Chat");
                h.removeCallbacks(r);
                return true;
            case R.id.navigation_Localisation:
                cleanLayout();
                setMap();
                CarteManagement.gps(this);
                h.postDelayed(r, 100);
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
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mMatchAsync = new HomeManagement.MatchesAsync();
        mMatchAsync.execute((Void) null);


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

    private void cleanLayout() {
        TableLayout container = (TableLayout) findViewById(R.id.table);
        container.removeAllViews();
        mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map));
        mMapFragment.getView().setVisibility(View.INVISIBLE);
    }


    private void setMap(){
        mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map));
        mMapFragment.getView().setVisibility(View.VISIBLE);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (markers.size() != User.get_Matchs().size()) {
                    markers = new ArrayList();
                    for (Match m : User.get_Matchs()) {

                        markers.add(googleMap.addMarker(new MarkerOptions().title(m.get_pseudo()).position(new LatLng(m.get_lat(), m.get_long()))));
                    }
                }
            }
        });
    }

    public void updateMarkers(){
         m = User.get_Matchs();
        for (int i = 0; i < User.get_Matchs().size(); i++){
            System.out.println(m.get(i).get_lat()+" - "+ m.get(i).get_long());
            markers.get(i).setPosition(new LatLng(m.get(i).get_lat(), m.get(i).get_long()));
        }
    }
}
