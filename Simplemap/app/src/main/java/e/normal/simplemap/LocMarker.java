package e.normal.simplemap;

import android.graphics.Color;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by normal on 2/18/18.
 */

public class LocMarker {
    String markertype;
    double latitude;
    double longitude;
    String tag;
    Circle circle;
    public LocMarker(String tag, double latitude, double longitude, String markertype){
        this.tag = tag;
        this.latitude = latitude;
        this.longitude = longitude;
        this.markertype = markertype;
        this.circle = MapsActivity.mMap.addCircle(new CircleOptions()
                .center(new LatLng(this.latitude, this.longitude))
                .radius(1)
                .strokeColor(Color.BLUE)
                .clickable(true));
       this.circle.setTag(this.tag);
    }

    public LocMarker(double latitude, double longitude, String markertype){
        this.latitude = latitude;
        this.longitude = longitude;
        this.markertype = markertype;
        this.circle = MapsActivity.mMap.addCircle(new CircleOptions()
                .center(new LatLng(this.latitude, this.longitude))
                .radius(1)
                .strokeColor(Color.RED)
                .clickable(true));
        //circle.setTag(this.tag);


    }

    public void remove(){
        this.circle.remove();
    }
}
