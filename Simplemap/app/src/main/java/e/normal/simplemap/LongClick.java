package e.normal.simplemap;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.Vector;

/**
 * Created by normal on 2/18/18.
 */

public class LongClick {
    public static Vector<LocMarker> locmarkers;
    public static void longclick(GoogleMap mMap, Vector<LocMarker> importlocmarkers){
        locmarkers = importlocmarkers;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                LocMarker locmarker = new LocMarker(latLng.latitude, latLng.longitude, "blue");
                locmarkers.add(locmarker);
                try {
                    new SendEDI().execute("");

                }catch (Exception e){

                }
                //RemoveMarkers.removemarkers(locmarkers);
            }

        });
    }
}
