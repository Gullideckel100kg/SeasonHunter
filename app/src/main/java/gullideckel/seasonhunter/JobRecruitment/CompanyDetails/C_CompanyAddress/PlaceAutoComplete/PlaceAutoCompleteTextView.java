package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.PlaceAutoComplete;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class PlaceAutoCompleteTextView
{
    private GoogleMap mMap;
    private Context mContext;
    private AutoCompleteTextView mTxtAutoComplete;


    private GoogleApiClient mGoogleApiClient;
    private PlaceAutoCompleteAdapter mAutoCompleteAdapter;

    private static final LatLngBounds LAT_LNG_BOUNDSC = new LatLngBounds(new LatLng(-40, -168), new LatLng(71,146));
    private static final String TAG = "PlaceAutoCompleteTV";

    public PlaceAutoCompleteTextView(GoogleMap map, Context context, AutoCompleteTextView txtAutoComplete, GoogleApiClient googleApiClient)
    {
        mMap = map;
        mContext = context;
        mTxtAutoComplete = txtAutoComplete;
        mGoogleApiClient = googleApiClient;
    }

    public void Init()
    {
        mAutoCompleteAdapter = new PlaceAutoCompleteAdapter(mContext, Places.getGeoDataClient((Activity)mContext), LAT_LNG_BOUNDSC, null);
        mTxtAutoComplete.setAdapter(mAutoCompleteAdapter);

        mTxtAutoComplete.setOnItemClickListener(ItemClick);
    }

    private AdapterView.OnItemClickListener ItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            StaticMethod.RemoveKeyPad((Activity)mContext);

            final AutocompletePrediction item = mAutoCompleteAdapter.getItem(position);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(UpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> UpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places)
        {
            if(!places.getStatus().isSuccess())
            {
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(places.get(0).getLatLng(), 13));
        }
    };

}
