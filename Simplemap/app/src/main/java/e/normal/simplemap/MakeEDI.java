package e.normal.simplemap;

import java.util.Vector;

/**
 * Created by normal on 2/18/18.
 */

public class MakeEDI {
    public static String edi = "";
    public static String makeedi(Vector<LocMarker> locmarkers){
        edi = "";
        for(int i = 0; i < locmarkers.size(); i++){
            LocMarker locmarker = locmarkers.get(i);
            edi = edi + locmarker.markertype + locmarker.latitude + locmarker.longitude + locmarker.tag + "**";
            //System.exit(0);
        }
        return(edi+"#");
    }
    public static String makeedi(String string){
        edi = "Retr";

        return(edi);
    }
}
