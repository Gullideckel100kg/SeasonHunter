package gullideckel.seasonhunter.JobMap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

import gullideckel.seasonhunter.JobMap.Markers.MarkerOnMap;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;

public class FragJobMap extends Fragment implements OnMapReadyCallback
{
    private MapView mapView;
    private GoogleMap map;

    private EditText edtSearch;

    private volatile boolean isMapReady = false;

    private LatLngCountry latLngCountry;
    private int countryPart = -2;
    private List<CompanyDocument> docs;

    private MarkerOnMap markerOnMap;

    public static FragJobMap newInstance()
    {
        FragJobMap fragment = new FragJobMap();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_job_map, container, false);

        edtSearch = (EditText) view.findViewById(R.id.edtSearch);

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.onResume(); // needed to get the map to display immediately

        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        mapView.getMapAsync(this);

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().findViewById(R.id.edtSearch).getWindowToken(), 0);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        latLngCountry = new LatLngCountry(googleMap);
        markerOnMap = new MarkerOnMap(googleMap, getActivity());

        // For showing a move to my location button
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            googleMap.setMyLocationEnabled(true);
        }
        map = googleMap;

        isMapReady = true;
        if(docs != null && countryPart != -2)
            InitMap();
    }

    public void InitDocuments(List<CompanyDocument> docs, int countryPart)
    {
        this.countryPart = countryPart;
        this.docs = docs;

        if(isMapReady)
            InitMap();
    }

    private void InitMap()
    {
        latLngCountry.ZoomCountryPart(countryPart);
        markerOnMap.SetMarker(docs);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}