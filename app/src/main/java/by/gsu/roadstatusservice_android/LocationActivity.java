package by.gsu.roadstatusservice_android;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import by.gsu.roadstatusservice_android.exceptions.LocationException;
import by.gsu.roadstatusservice_android.utils.LocationUtils;

public class LocationActivity extends AppCompatActivity {
    private LocationUtils locationUtils = new LocationUtils(this);
    private TextView txtLat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        txtLat = (TextView) findViewById(R.id.txtLocationText);


        try {
            Location location = locationUtils.getLocation();
            txtLat.setText("Latitude:" + location.getLatitude() + "; Latitude:" + location.getLatitude() + ";");
        } catch (LocationException e) {
            txtLat.setText(e.getMessage());

        }

    }
}
