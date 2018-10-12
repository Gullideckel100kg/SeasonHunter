package gullideckel.seasonhunter.JobRecruitment.CompanyAddress.Snapshot;

import android.graphics.Bitmap;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.Interfaces.ISnapShot;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding.CameraMove;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding.CurrentAddress;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.FragCompanyDetails;

public class MySnapshot implements GoogleMap.SnapshotReadyCallback, GoogleMap.OnMapLoadedCallback
{
    private GoogleMap mMap;
    private Marker mMarker;
    private ISnapShot mListener;
    private CameraMove mCameraMove;

    public MySnapshot(GoogleMap map, ISnapShot listener)
    {
        mMap = map;
        mListener = listener;
    }

    private Bitmap getMapResizedSnapshot(Bitmap mapSnapshot)
    {
        if(mapSnapshot != null)
        {
            Bitmap resizedBmp = Bitmap.createBitmap(mapSnapshot,0, (int)(mapSnapshot.getHeight()/2 - mapSnapshot.getHeight() * 0.4),mapSnapshot.getWidth(), (int)(mapSnapshot.getHeight() * 0.4));

            if(mapSnapshot.getWidth() >= mapSnapshot.getHeight())
                return Bitmap.createBitmap(mapSnapshot,0, mapSnapshot.getWidth()/4 - mapSnapshot.getHeight()/4,mapSnapshot.getHeight(), mapSnapshot.getHeight());
            else
                return Bitmap.createBitmap(mapSnapshot,0, mapSnapshot.getHeight()/2 - mapSnapshot.getWidth()/4,mapSnapshot.getWidth(), mapSnapshot.getWidth()/2);
        }
        else
            throw new NullPointerException("class:MySnapShop; First take a SnapShot then call getMapSnapshot");
    }

    @Override
    public void onSnapshotReady(Bitmap bitmap)
    {
        mListener.onSnapShotBitmap(getMapResizedSnapshot(bitmap));
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        if(mMarker != null)
            mMarker.remove();
    }

    @Override
    public void onMapLoaded()
    {
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMarker = mMap.addMarker(new MarkerOptions()
                .position(mMap.getCameraPosition().target)
                .title("JobMarker"));
        mMap.snapshot(this);
    }
}
