package by.gsu.roadstatusservice_android;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import by.gsu.RoadStatusService.models.Picture;
import by.gsu.client.Client;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Client client = new Client();

    private static final boolean TODO = true;
    LocationManager lm;
    Location location;
    double latitude = 0, longitude = 0;
    TextView helloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        setContentView(R.layout.activity_main);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        helloTextView = (TextView) findViewById(R.id.picTextView);
        helloTextView.setText("000");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            iv = (ImageView) findViewById(R.id.newImageView);
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);

           /* LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            if (lm != null) {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                if (location != null) {
                    update(location.getLatitude(), location.getLongitude());
                }

                //lm.requestLocationUpdatesLocationManager.GPS_PROVIDER, 0, 0, intent);
            }*/

            // Picture p = new Picture();
            // Client client = new Client();


            /*TextView ptv =(TextView) findViewById(R.id.picTextView);
            try {
                p = client.methodGetPicture(5);
                ptv.setText(p.toString());
            } catch (IOException e) {
              //  e.printStackTrace();
                ptv.setText(e.getMessage());
            }
*/
            // p.setId(11);
           /* try {
                helloTextView.setText("lolaaaaa"+p.toString()+client.methodGetPicture(4));
            } catch (IOException e) {
                helloTextView.setText(e.getMessage());
            }
*/
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
         /*   HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("https://road-status-service-impl.herokuapp.com/picture/1");
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                byte[] buffer = new byte[1024];
                in.read(buffer);
                helloTextView.setText(new String(buffer));
            } catch (MalformedURLException e) {
                helloTextView.setText(e.getMessage());
            } catch (IOException e) {
                helloTextView.setText(e.getMessage());
            } finally {
                urlConnection.disconnect();
            }*/

            helloTextView.setText("aaa "+client.methodGetListPicturesString());

        } else if (id == R.id.nav_manage) {
            Picture p = new Picture();

            try {
                helloTextView.setText(client.methodGetPicture(4).toString());
            } catch (IOException e) {
                helloTextView.setText(e.getMessage());
            }
        } else if (id == R.id.nav_share) {
            helloTextView.setText("1010101010");
            StringBuilder returnString = new StringBuilder();
            returnString.append("xxx");
            URL url = null; // URL for request
            try {
                url = new URL("https://road-status-service-impl.herokuapp.com/picture/1");
            } catch (MalformedURLException e) {
                returnString.append("1" + e.getMessage());
            }
            String sParameters = ""; // POST data
            HttpsURLConnection urlConnection = null;
            BufferedReader input = null;


            try {
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
               /* urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("Content-Length",sParameters != null ? ("" + Integer.toString(sParameters.getBytes().length)) : "0");
                urlConnection.setRequestProperty("Content-Language", "en-US");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setUseCaches(false);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(sParameters != null);*/

                // Send request
                /*if(sParameters != null) {
                    DataOutputStream output = new DataOutputStream(urlConnection.getOutputStream());
                    output.writeBytes(sParameters);
                    output.flush();
                    output.close();
                }*/

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"), 8);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    returnString.append(line);
                }
            } catch (Exception e) {
                returnString.append("2" + e.getMessage());
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }


            helloTextView.setText(returnString.toString());
        } else if (id == R.id.nav_send) {
            LocationManager enabledManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (enabledManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return TODO;
                }
                location = enabledManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    //Location wasnt gathered
                } else {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }

            helloTextView.setText("latitude=" + latitude + "; longitude=" + longitude);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ImageView iv;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }
}
