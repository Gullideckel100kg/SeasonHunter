package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.C_CompanyAddress.Snapshot;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;

public class MySnapshot implements GoogleMap.SnapshotReadyCallback, GoogleMap.OnMapLoadedCallback
{
    private GoogleMap mMap;
    private ImageView imgSnapLogo;
    private Bitmap mLogo;

    public MySnapshot(GoogleMap map,  Bitmap logo, ImageView imgSnapLogo)
    {
        mMap = map;
        mLogo = logo;
        this.imgSnapLogo = imgSnapLogo;
    }

    private Bitmap getMapResizedSnapshot(Bitmap mapSnapshot)
    {
        if(mapSnapshot != null)
        {
            Bitmap resizedBmp = Bitmap.createBitmap(mapSnapshot,0, (int)(mapSnapshot.getHeight()/2 - mapSnapshot.getHeight() * 0.4),mapSnapshot.getWidth(), (int)(mapSnapshot.getHeight() * 0.4));

            return Bitmap.createBitmap(mapSnapshot,0, mapSnapshot.getHeight()/2 - mapSnapshot.getWidth()/4,mapSnapshot.getWidth(), mapSnapshot.getWidth()/2);
        }
        else
            throw new NullPointerException("class:MySnapShop; First take a SnapShot then call getMapSnapshot");
    }

    @Override
    public void onSnapshotReady(Bitmap bitmap)
    {
//        mListener.onSnapShotBitmap(getMapResizedSnapshot(bitmap));
        mMap.getUiSettings().setScrollGesturesEnabled(true);
    }

    @Override
    public void onMapLoaded()
    {
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.clear();
//        mMarker = mMap.addMarker(new MarkerOptions()
//                .position(mMap.getCameraPosition().target)
//                .title("JobMarker")
//                .icon(BitmapDescriptorFactory.fromBitmap(StaticMethod.GetResizedBitmap(mLogo, 40, 40))));
        imgSnapLogo.setImageBitmap(mLogo);
        mMap.snapshot(this);
    }
}
