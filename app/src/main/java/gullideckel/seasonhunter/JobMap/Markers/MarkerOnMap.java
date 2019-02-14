package gullideckel.seasonhunter.JobMap.Markers;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import gullideckel.seasonhunter.ActSeasonHunter;
import gullideckel.seasonhunter.CompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyType;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class MarkerOnMap implements GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{
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
            {
                CompanyType type = new CompanyType(doc.getTypes().get(0), activity);
                bmp = type.getLogo();
            }
            else
            {
                CompanyType type = new CompanyType(activity.getString(R.string.other), activity);
                bmp = type.getLogo();
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
