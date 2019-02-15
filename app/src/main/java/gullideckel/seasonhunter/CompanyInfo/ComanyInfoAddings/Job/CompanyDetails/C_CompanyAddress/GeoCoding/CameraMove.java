package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.C_CompanyAddress.GeoCoding;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.Interfaces.ICompanyAddress;

public class CameraMove implements GoogleMap.OnCameraIdleListener
{

    private AddressTask mAsyncGeo;
    private GeoMap mGeoMap;
    private Context mContext;
    private ICompanyAddress mListener;

    public CameraMove(Context context, GeoMap geoMap, ICompanyAddress listener)
    {
        mGeoMap = geoMap;
        mContext = context;
        mListener = listener;
    }

    public void Start()
    {
        if(mGeoMap.GetGoogleMap() != null)
            mGeoMap.GetGoogleMap().setOnCameraIdleListener(this);
        else
            throw new NullPointerException("class:CameraMove; GoogleMaps = null");

        StartAsyncTask();
    }

    @Override
    public void onCameraIdle()
    {
        StartAsyncTask();
    }

    //TODO: Make sure Address is loaded before Click ok. Istakefinish is not used so far
    private void StartAsyncTask()
    {
        AddressTask.ISTASKFINISH = false;
        mGeoMap.SetLatLng();
        new AddressTask(mListener, mContext).execute(mGeoMap);
    }
}
