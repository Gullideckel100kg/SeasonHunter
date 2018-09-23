package gullideckel.seasonhunter.ActivityMap.GeoCoding;

import android.content.Context;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import gullideckel.seasonhunter.Interfaces.IUpdateAddress;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class CameraMove implements GoogleMap.OnCameraIdleListener, IUpdateAddress
{

    private AddressTask mAsyncGeo;
    private GeoMap mGeoMap;
    private TextView mTxtAddress;
    private TextView mTxtCoordinates;
    private Context mContext;

    public CameraMove(TextView txtAddress, TextView txtCoordinates, Context context)
    {
        mTxtAddress = txtAddress;
        mTxtCoordinates = txtCoordinates;
        mContext = context;
    }

    public void Start(GeoMap geoMap)
    {
        if(geoMap.GetGoogleMap() != null)
            geoMap.GetGoogleMap().setOnCameraIdleListener(this);
        else
            throw new NullPointerException("class:CameraMove; GoogleMaps = null");

        mGeoMap = geoMap;
        StartAsyncTask();
    }


    @Override
    public void onCameraIdle()
    {
        StartAsyncTask();
    }

    private void StartAsyncTask()
    {
        AddressTask.ISTASKFINISH = false;
        mGeoMap.SetLatLng();
        new AddressTask(this).execute(mGeoMap);
    }

    @Override
    public void UpdatedAddress(CurrentAddress address)
    {
        if(address.GetAddressLine() != "" && address.GetAddressLine() != null)
        {
            mTxtAddress.setText(address.GetAddressLine());
            mTxtCoordinates.setText(StaticMethod.GPSConvert(address.GetLatitude(), address.GetLongitude()));
        }
        else
        {
            mTxtAddress.setText(mContext.getString(R.string.no_address_found));
            address.SetAddressLine(mContext.getString(R.string.no_address_found));
        }
        mTxtAddress.setText(address.GetAddressLine());

    }
}
