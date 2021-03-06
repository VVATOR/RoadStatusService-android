package by.gsu.roadstatusservice_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import by.gsu.RoadStatusService.models.Picture;
import by.gsu.RoadStatusService.models.Point;
import by.gsu.client.Client;
import by.gsu.client.ClientInMemory;
import by.gsu.client.IRoadStatusClient;
import by.gsu.roadstatusservice_android.exceptions.LocationException;
import by.gsu.roadstatusservice_android.lazylist.ListActivity;
import by.gsu.roadstatusservice_android.utils.LocationUtils;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private IRoadStatusClient client = new ClientInMemory();
    private LocationUtils locationUtils = new LocationUtils(this);

    private static final boolean TODO = true;
    LocationManager lm;
    private Location location;
    private ImageView iv;
    double latitude = 0, longitude = 0;
    TextView helloTextView;
    private String locationInfo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        helloTextView = (TextView) findViewById(R.id.picTextView);
        iv = (ImageView) findViewById(R.id.newImageView);

        try {
            location = locationUtils.getLocation();
            locationInfo = ("Latitude:" + location.getLatitude() + "; Latitude:" + location.getLatitude() + ";");
        } catch (LocationException e) {
            locationInfo = (e.getMessage());
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, locationInfo, Snackbar.LENGTH_LONG)
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
        if (id == R.id.nav_listCustom) {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_location) {
            Intent intent = new Intent(MainActivity.this, LocationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_base64_picture) {
            Picture p = new Picture();
            p.setId(11);
            p.setName("pict");
            p.setData("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");
            //try {
            //p=client.methodGetPicture(5);
            helloTextView.setText(p.toString());

            byte[] decodedString = Base64.decode(p.getData(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ImageView image = (ImageView) findViewById(R.id.newImageView);
            image.setImageBitmap(decodedByte);

            helloTextView.setText("999");

            //} catch (IOException e) {
            //    helloTextView.setText(e.getMessage());
            //}
        } else if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            //intent.putExtra("list", client);
            startActivityForResult(intent, 0);

            helloTextView.setText("ssssssssssssssssss");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();

            Bitmap bitmap1 = drawable.getBitmap();
            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);


            Point point = new Point(location.getLatitude(), location.getLongitude());
            Picture newPicture = new Picture(331, System.currentTimeMillis() +".jpg", "description", point, encoded);

            client.methodPostPicture(newPicture);


        } else if (id == R.id.nav_map) {
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

            try {
                helloTextView.setText("aaa " + client.getListPictures());
            } catch (IOException e) {
                e.printStackTrace();
            }

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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bitmap);
    }
}
