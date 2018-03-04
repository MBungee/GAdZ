package e.normal.simplemap;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.net.sip.SipSession;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ToggleButton toggleButton;
    private FusedLocationProviderClient mFusedLocationClient;
    public static boolean tracking;
    public static boolean trueflag = true;
    public static boolean falseflag = false;
    public static GoogleMap mMap;
    public static LatLng current;
    public static int intervall;
    private Circle me;
    private Spinner spinner;
    public static Vector<LocMarker> locmarkers = new Vector<>();
    public MakeEDI makeedi = new MakeEDI();

    String txt = "";
    /*
     * onCreate method
     * gets invoked on startup
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //map implementation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //spinner implementation
        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Togglebutton init
        toggleButton            = (ToggleButton)findViewById(R.id.toggleButton);

        //default location
        current                 = new LatLng(51.4979, 13.1850);

        //configure location services ans client
        //createLocationRequest();
        mFusedLocationClient    = LocationServices.getFusedLocationProviderClient(this);

        //get regular location updates
        intervall               = 5000;
        startloop();


    }

    /*
     * onMapReady
     * gets invoked when init of map is done
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        LongClick.longclick(mMap, locmarkers);
        cicleclick();

    }


    public static void updatelocation(String tag){
        int circle = GetByTag.getbytag(tag, locmarkers);
        current= new LatLng(locmarkers.get(circle).latitude, locmarkers.get(circle).longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));

    }
    /*
     * TrackingClick
     * listens for change of "Tracking" box
     */

    public void TrackingClick(View view) {

        if (toggleButton.isChecked()) {
            tracking = true;
            mMap.getUiSettings().setScrollGesturesEnabled(false);
        }else{
            tracking = false;
            mMap.getUiSettings().setScrollGesturesEnabled(true);
        }
    }


    /*
     * startloop
     * invokes methods repeatedly
     */

    public void startloop(){

        //creating a refresh timer
        Timer timer = new Timer();
        //TimerTask runs method updatelocation
        TimerTask locationrefresh = new TimerTask() {
            @Override
            public void run() {

                try {
                    //Thread.sleep(5000);
                    new SendEDI().execute("Retr");

                }catch (Exception e){

                }

                //if(locmarkers.size()>0)RemoveMarkers.removemarkers(locmarkers, 0);
                //updatelocation();

            }
        };
        //start refresh loop
        timer.schedule(locationrefresh, 0, intervall);
    }

    /*
     * circleclick
     * reaction when any circle is clicked once
     * shows circle text oder deletes circle
     */

    public void cicleclick(){
        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {

            @Override
            public void onCircleClick(Circle circle) {
                //String edi = MakeEDI.makeedi(locmarkers);

                if(circle.getTag() != null) {
                    Toast t = Toast.makeText(getApplicationContext(), "" + circle.getTag(), Toast.LENGTH_SHORT);
                    t = Toast.makeText(getApplicationContext(), "" + txt, Toast.LENGTH_SHORT);
                    t.show();

                }else{
                    int lm = GetByCircle.getbycircle(circle, locmarkers);
                    RemoveMarkers.removemarkers(locmarkers,lm);
                    try {
                        new SendEDI().execute("");

                    }catch (Exception e){

                    }
                }




            }
        });
    }
}
