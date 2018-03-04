package e.normal.simplemap;

import com.google.android.gms.maps.model.Circle;

import java.util.Vector;

/**
 * Created by normal on 2/18/18.
 */

public class GetByCircle {

    public static int getbycircle(Circle circle, Vector<LocMarker> locmarkers){

        for(int i = 0; i < locmarkers.size(); i++) {
            LocMarker locmarker = locmarkers.get(i);

            if ((locmarker.circle.getCenter().longitude == circle.getCenter().longitude)
                && (locmarker.circle.getCenter().latitude == circle.getCenter().latitude)){
                return (i);
            }

        }
        return (-1);
    }
}
