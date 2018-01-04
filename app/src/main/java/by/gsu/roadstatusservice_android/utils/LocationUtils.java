package by.gsu.roadstatusservice_android.utils;

import android.Manifest;
import android.app.Activity;
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

import by.gsu.roadstatusservice_android.MainActivity;
import by.gsu.roadstatusservice_android.R;
import by.gsu.roadstatusservice_android.exceptions.LocationException;

/**
 * Created by Vitali_Vikhliayeu on 2018-01-04.
 */

public class LocationUtils  implements LocationListener {
    private Activity activity;
    public LocationUtils(Activity activity) {
        this.activity = activity;
    }

    public Location getLocation() throws LocationException {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw new LocationException();
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onLocationChanged(Location location) {
        //  txtLat = (TextView) findViewById(R.id.txtLocationText);
        //  txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Provider", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Provider", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Provider", "status:"+status);
    }
}