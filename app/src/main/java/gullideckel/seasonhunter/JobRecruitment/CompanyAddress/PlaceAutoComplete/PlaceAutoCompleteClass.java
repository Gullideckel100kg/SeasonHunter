package gullideckel.seasonhunter.JobRecruitment.CompanyAddress.PlaceAutoComplete;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class PlaceAutoCompleteClass
{
    private GoogleMap mMap;
    private PlaceAutocompleteFragment mFragment;

    public PlaceAutoCompleteClass(PlaceAutocompleteFragment fragment, GoogleMap map)
    {
        mMap = map;
        mFragment = fragment;
    }

    public void Start()
    {
        mFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place)
            {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 13));
            }

            @Override
            public void onError(Status status)
            {

            }
        });
    }
}