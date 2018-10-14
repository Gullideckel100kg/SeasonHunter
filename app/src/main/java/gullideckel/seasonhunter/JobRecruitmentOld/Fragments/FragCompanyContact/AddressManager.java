package gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyContact;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContactObject;
import gullideckel.seasonhunter.R;

public class AddressManager
{
    private static final String TAG = "AddressManagaer";
    private LatLng mLatLng;

    public void AutoCompleteListener(Activity activity, final CompanyContactObject contactInfo)
    {
        PlaceAutocompleteFragment autocompleteFragment =
                (PlaceAutocompleteFragment) activity.getFragmentManager().findFragmentById(R.id.frag_place_autocomplete);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place)
            {
                // TODO: Get info about the selected place.
                contactInfo.SetAddress(place.getAddress().toString());
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status)
            {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }
}
