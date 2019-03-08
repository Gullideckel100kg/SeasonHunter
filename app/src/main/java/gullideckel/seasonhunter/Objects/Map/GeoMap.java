package gullideckel.seasonhunter.Objects.Map;

import android.location.Geocoder;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class GeoMap
{
    private GoogleMap map;
    private Geocoder geo;
    private LatLng latLng;

    public GeoMap(Geocoder geo, GoogleMap map)
    {
        this.geo = geo;
        this.map = map;
    }

    public Geocoder GetGeocoder()
    {
        return geo;
    }

    public GoogleMap GetGoogleMap()
    {
        return map;
    }

    public void SetLatLng() {if(map != null) latLng = map.getCameraPosition().target; }

    public LatLng GetLatLng() { return latLng; }
}
