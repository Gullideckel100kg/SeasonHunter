package gullideckel.seasonhunter.ActivityMap.GeoCoding;

import android.location.Geocoder;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class GeoMap
{
    private GoogleMap mMap;
    private Geocoder mGeo;
    private LatLng mLatLng;

    public GeoMap(Geocoder geo, GoogleMap map)
    {
        mMap = map;
        mGeo = geo;
    }

    public void SetGoogleMap(GoogleMap map)
    {
        mMap = map;
    }

    public Geocoder GetGeocoder()
    {
        return mGeo;
    }

    public GoogleMap GetGoogleMap()
    {
        return mMap;
    }

    public void SetLatLng() {if(mMap != null) mLatLng = mMap.getCameraPosition().target; }

    public LatLng GetLatLng() { return mLatLng; }
}
