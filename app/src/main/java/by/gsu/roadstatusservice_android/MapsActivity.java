package by.gsu.roadstatusservice_android;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Fragment implements OnMapReadyCallback {
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public MapsActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.activity_maps,container,false);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mMapView.findViewById(R.id.map);
        if(mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this.getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(40.689247,-74.044502);

        googleMap.addMarker(new MarkerOptions().position(latLng).title("lol").snippet("aaa"));
        CameraPosition liberty = CameraPosition.builder().target(latLng).zoom(16).bearing(0).tilt(45).build();
    }
}
