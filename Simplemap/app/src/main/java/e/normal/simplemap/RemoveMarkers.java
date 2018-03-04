package e.normal.simplemap;

import java.util.Vector;

/**
 * Created by normal on 2/18/18.
 */

public class RemoveMarkers {

    public static void removemarkers(Vector<LocMarker> markers){
        int size = markers.size();
        while(markers.size() > 0){
            System.out.println(markers.size());
            LocMarker locmarker = MapsActivity.locmarkers.get(0);
            locmarker.remove();
            markers.remove(0);
        }


    }
    public static void removemarkers(Vector<LocMarker> markers, int lm){
        if (lm > -1){
            LocMarker locmarker = markers.get(lm);
            locmarker.remove();
            markers.remove(lm);
        }


    }
}
