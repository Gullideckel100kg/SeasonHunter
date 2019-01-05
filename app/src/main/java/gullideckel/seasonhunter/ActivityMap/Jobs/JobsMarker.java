package gullideckel.seasonhunter.ActivityMap.Jobs;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gullideckel.seasonhunter.ActivityMap.MapHunter;
import gullideckel.seasonhunter.CompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;

public class JobsMarker implements GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{
    private static final String TAG = "JobsMarker";

    private GoogleMap map;

    private FirebaseFirestore db;

    private List<CompanyDocument> docs;

    HashMap<Marker, CompanyDocument> markers;

    private IFragmentHandler listener;
    private  Context context;

    public JobsMarker(GoogleMap map, Context context, IFragmentHandler listener)
    {
        db = FirebaseFirestore.getInstance();
        docs = new ArrayList<>();

        this.map = map;
        this.listener = listener;
        map.setOnMarkerClickListener(this);
        map.setOnInfoWindowClickListener(this);
        map.setInfoWindowAdapter(new MarkerInfoWindow(context));
        this.context = context;
        markers = new HashMap<>();
    }

    public void LoadJobs()
    {
        db.collection("companies").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        for(DocumentSnapshot document : task.getResult())
                        {
                            CompanyDocument doc = document.toObject(CompanyDocument.class);
                            Marker marker = SetMarker(doc);
                            marker.setTag(doc);
                            marker.showInfoWindow();
                            markers.put(marker, doc);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.w(TAG, "onFailure: Error getting documents. ", e);
                    }
                });
    }

    private Marker SetMarker(CompanyDocument document)
    {
        return map.addMarker(new MarkerOptions()
                            .position(new LatLng(document.getAddress().getLatitude(), document.getAddress().getLongitude()))
                            .icon(resizeMapIcons(MapHunter.companyTypes.get(document.getType()),30,30)));
    }

    private BitmapDescriptor resizeMapIcons(Bitmap bmp, int width, int height){
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bmp, width, height, false);
        return BitmapDescriptorFactory.fromBitmap(resizedBitmap);
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
            marker.showInfoWindow();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        CompanyDocument doc = (CompanyDocument) marker.getTag();
        listener.onReplaceFragment(FragCompanyInfo.NewInstance(doc), IntFrag.REPLACE);
    }
}
