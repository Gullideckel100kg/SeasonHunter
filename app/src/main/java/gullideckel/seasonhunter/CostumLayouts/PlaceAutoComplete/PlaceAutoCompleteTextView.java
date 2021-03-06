package gullideckel.seasonhunter.CostumLayouts.PlaceAutoComplete;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class PlaceAutoCompleteTextView
{
    private Context mContext;
    private AutoCompleteTextView mTxtAutoComplete;

    private ICompanyAddress listener;

    private GoogleApiClient mGoogleApiClient;
    private PlaceAutoCompleteAdapter mAutoCompleteAdapter;

    private static final LatLngBounds LAT_LNG_BOUNDSC = new LatLngBounds(new LatLng(-40, -168), new LatLng(71,146));
    private static final String TAG = "PlaceAutoCompleteTV";

    public PlaceAutoCompleteTextView(Context context, AutoCompleteTextView txtAutoComplete, GoogleApiClient googleApiClient, ICompanyAddress listener)
    {
        this.listener = listener;
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

            CompanyAddress address = new CompanyAddress();
            address.setAddress(places.get(0).getAddress().toString());
            address.setCountry(places.get(0).getLocale().getCountry());
            address.setLatitude(places.get(0).getLatLng().latitude);
            address.setLongitude(places.get(0).getLatLng().longitude);

            listener.OnCompanyAddress(address);
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(places.get(0).getLatLng(), 13));
        }
    };

}
