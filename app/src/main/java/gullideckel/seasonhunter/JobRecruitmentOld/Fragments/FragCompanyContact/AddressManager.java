package gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyContact;

import android.app.Activity;

import com.google.android.gms.maps.model.LatLng;

import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;

public class AddressManager
{
    private static final String TAG = "AddressManagaer";
    private LatLng mLatLng;

    public void AutoCompleteListener(Activity activity, final CompanyContact contactInfo)
    {
//        PlaceAutocompleteFragment autocompleteFragment =
//                (PlaceAutocompleteFragment) activity.getFragmentManager().findFragmentById(R.id.frag_place_autocomplete);
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place)
//            {
//                // TODO: Get info about the selected place.
//                contactInfo.SetAddress(place.getAddress().toString());
//                Log.i(TAG, "Place: " + place.getName());
//            }
//
//            @Override
//            public void onError(Status status)
//            {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });
    }
}
