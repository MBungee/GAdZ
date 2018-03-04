package e.normal.simplemap;

import java.util.Vector;

/**
 * Created by normal on 2/18/18.
 */

public class GetByTag {

    public static int getbytag(String tag, Vector<LocMarker> locmarkers){

        for(int i = 0; i < locmarkers.size(); i++) {
            LocMarker locmarker = locmarkers.get(i);
            if (locmarker.tag == tag) {
                return (i);
            }

        }
        return (-1);
    }
}
