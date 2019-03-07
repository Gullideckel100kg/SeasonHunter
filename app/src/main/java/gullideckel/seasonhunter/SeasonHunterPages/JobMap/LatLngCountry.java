package gullideckel.seasonhunter.SeasonHunterPages.JobMap;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class LatLngCountry
{
    private GoogleMap map;

    public LatLngCountry(GoogleMap map)
    {
        this.map = map;
    }

    public final static LatLng CANADA = new LatLng(55.585901, -105.750596);
    public final static LatLng CANADA_BRITISH_COLUMBIA = new LatLng(53.726669, -127.647621);

    public void ZoomCountryPart(int countryPart)
    {
        switch (countryPart)
        {
            case -1:
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLngCountry.CANADA_BRITISH_COLUMBIA, 4));
        }
    }
}
