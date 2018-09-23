package gullideckel.seasonhunter.ActivityMap.PlaceAutoComplete;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class PlaceAutoComplete
{
    public void Autcomplete(PlaceAutocompleteFragment placeAutoComplete)
    {

        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place)
            {

            }

            @Override
            public void onError(Status status)
            {

            }
        });

    }
}