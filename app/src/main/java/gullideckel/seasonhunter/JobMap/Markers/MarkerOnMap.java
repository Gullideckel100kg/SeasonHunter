package gullideckel.seasonhunter.JobMap.Markers;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import gullideckel.seasonhunter.CompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;
import gullideckel.seasonhunter.Statics.TypeLogo;

public class MarkerOnMap implements GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{
    private static final String TAG = "MarkerOnMap";

    private GoogleMap map;
    private FragmentActivity activity;

    public MarkerOnMap(GoogleMap map, FragmentActivity activity)
    {
        this.map = map;
        this.activity = activity;
        map.setOnMarkerClickListener(this);
        map.setOnInfoWindowClickListener(this);
        map.setInfoWindowAdapter(new MarkerInfoWindow(activity));
    }

    public void SetMarker(List<CompanyDocument> docs)
    {
        for(CompanyDocument doc : docs)
        {
            Bitmap bmp;

            if(doc.getTypes().size() > 0)
                bmp = TypeLogo.getLogo(doc.getTypes().get(0), activity);
            else
            {
                bmp = TypeLogo.getLogo(activity.getString(R.string.other), activity);
                Log.wtf(TAG, "SetMarker: No Company type. Document: " + doc.getId());
            }


            Marker marker =  map.addMarker(new MarkerOptions()
                                .position(new LatLng(doc.getAddress().getLatitude(), doc.getAddress().getLongitude()))
                                .icon(StaticMethod.ResizeBitmap(bmp,30,30)));

            marker.setTag(doc);
        }
    }



    @Override
    public void onInfoWindowClick(Marker marker)
    {
        CompanyDocument doc = (CompanyDocument) marker.getTag();
        FragmentTransaction transaction  = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.cnstMapHunter, FragCompanyInfo.NewInstance(doc));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        marker.showInfoWindow();
        return false;
    }
}
