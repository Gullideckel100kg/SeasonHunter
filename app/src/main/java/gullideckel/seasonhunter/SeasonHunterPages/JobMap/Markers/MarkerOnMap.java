package gullideckel.seasonhunter.SeasonHunterPages.JobMap.Markers;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.CompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.Interfaces.IDocumentReview;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;
import gullideckel.seasonhunter.Statics.StaticTypes;

public class MarkerOnMap implements GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{
    private static final String TAG = "MarkerOnMap";

    private GoogleMap map;
    private final FragmentActivity activity;
    private List<Marker> markers;
    private GoogleApiClient client;

    public MarkerOnMap(GoogleMap map, FragmentActivity activity, GoogleApiClient client)
    {
        this.map = map;
        this.activity = activity;
        this.client = client;
        markers = new ArrayList<>();
        map.setOnMarkerClickListener(this);
        map.setOnInfoWindowClickListener(this);
        map.setInfoWindowAdapter(new MarkerInfoWindow(activity));
    }

    public void SetMarker(List<CompanyDocument> docs)
    {
        if(markers.size() > 0)
            for(Marker marker : markers)
                marker.remove();

        for(CompanyDocument doc : docs)
        {
            Bitmap bmp;

            if(doc.getTypes().size() > 0)
                bmp = StaticTypes.getLogo(doc.getTypes().get(0), activity);
            else
            {
                bmp = StaticTypes.getLogo(activity.getString(R.string.other), activity);
                Log.wtf(TAG, "SetMarker: No Company type. Document: " + doc.getId());
            }

            Marker marker =  map.addMarker(new MarkerOptions()
                    .position(new LatLng(doc.getAddress().getLatitude(), doc.getAddress().getLongitude()))
                    .icon(StaticMethod.ResizeBitmap(bmp,30,30)));

            marker.setTag(doc);
            markers.add(marker);
        }
    }



    @Override
    public void onInfoWindowClick(Marker marker)
    {
        CompanyDocument doc = (CompanyDocument) marker.getTag();
        if(doc != null)
        {
            FragmentTransaction transaction  = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.cnstMapHunter, FragCompanyInfo.NewInstance(doc, (IDocumentReview) activity, client));
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else
            Log.wtf(TAG, "onInfoWindowClick: doc is null. Tag didnt work");

    }

//    HhXZc6nZvl3L3jdy3LlJ
    @Override
    public boolean onMarkerClick(Marker marker)
    {
        marker.showInfoWindow();
        return false;
    }
}
