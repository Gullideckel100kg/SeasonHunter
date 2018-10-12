package gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import gullideckel.seasonhunter.Interfaces.IUpdateAddress;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class CameraMove implements GoogleMap.OnCameraIdleListener, IUpdateAddress
{

    private AddressTask mAsyncGeo;
    private GeoMap mGeoMap;
    private TextView mTxtAddress;
    private TextView mTxtCoordinates;
    private Context mContext;

    private CurrentAddress mCurrentAddress;

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
        mTxtAddress.setTextColor(Color.BLACK);
    }

    //TODO: Make sure Address is loaded before Click ok. Istakefinish is not used so far
    private void StartAsyncTask()
    {
        AddressTask.ISTASKFINISH = false;
        mGeoMap.SetLatLng();
        new AddressTask(this, mContext).execute(mGeoMap);
    }

    @Override
    public void UpdatedAddress(CurrentAddress address)
    {
        if(address.GetAddressLine() != "" && address.GetAddressLine() != null)
        {
            mTxtAddress.setText(address.GetAddressLine());
            mTxtCoordinates.setText(StaticMethod.GPSConvert(address.GetLatitude(), address.GetLongitude()));
        }
        mCurrentAddress = address;
    }

    public CurrentAddress GetCurrentAddress()
    {
        return mCurrentAddress;
    }
}
