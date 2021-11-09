package fr.hamchez.roundnettracker.ui.createGame;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.adapters.TeamSpinnerAdapter;
import fr.hamchez.roundnettracker.database.dao.GameDAO;
import fr.hamchez.roundnettracker.database.dao.TeamDAO;
import fr.hamchez.roundnettracker.models.Game;
import fr.hamchez.roundnettracker.models.Team;

public class CreateGameActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    SupportMapFragment mapFragment;
    LocationManager locationManager;
    GoogleMap googleMap;

    Spinner teamOneSpinner;
    Spinner teamTwoSpinner;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        teamOneSpinner = findViewById(R.id.teamOneSpinner);
        teamTwoSpinner = findViewById(R.id.teamTwoSpinner);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        populateMap();

    }

    private void populateMap() {

        new Thread(() -> {

            List<Team> teamList = new TeamDAO(this).getAll();

            handler.post(new Thread(() -> {

                TeamSpinnerAdapter teamOneAdapter = new TeamSpinnerAdapter(this,R.layout.team_spinner,teamList);
                TeamSpinnerAdapter teamTwoAdapter = new TeamSpinnerAdapter(this,R.layout.team_spinner, teamList);

                teamOneSpinner.setAdapter(teamOneAdapter);
                teamTwoSpinner.setAdapter(teamTwoAdapter);

            }));

        }).start();

    }

    public void onCreateGame(View view){

        Team teamOne = (Team) teamOneSpinner.getSelectedItem();
        Team teamTwo = (Team) teamTwoSpinner.getSelectedItem();

        String localisation = googleMap.getCameraPosition().target.longitude + "," + googleMap.getCameraPosition().target.latitude;

        boolean result = new GameDAO(this).insert(
                new Game(0,teamOne.getId(),teamTwo.getId(),localisation)
        );

        if(result){
            Toast toast = Toast.makeText(this,R.string.createGameSucess,Toast.LENGTH_LONG);
            toast.show();

            finish();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 10, this);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION },1);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}