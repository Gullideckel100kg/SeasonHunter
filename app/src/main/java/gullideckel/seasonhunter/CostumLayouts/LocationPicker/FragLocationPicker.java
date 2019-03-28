package gullideckel.seasonhunter.CostumLayouts.LocationPicker;

import android.graphics.Bitmap;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.C_CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.Interfaces.ICancelCommand;
import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class FragLocationPicker extends Fragment implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener
        , ICompanyAddress, GoogleMap.OnMapLoadedCallback, GoogleMap.SnapshotReadyCallback, ICancelCommand
{
    private static final String TAG = "FragLocationPicker";

    private ICompanyAddress listener;
    private MapView mapView;
    private TextView txtAddress;
    private Button btnSelect;
    private GeoMap geoMap;
    private CompanyAddress address;
    private GoogleMap map;
    private Marker marker;

    public static FragLocationPicker newInstance(ICompanyAddress listener)
    {
        FragLocationPicker fragment = new FragLocationPicker();
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_location_picker, container, false);


        txtAddress = v.findViewById(R.id.txtPickerAddress);
        btnSelect = v.findViewById(R.id.btnPickerSelect);

        mapView = (MapView) v.findViewById(R.id.mapViewPicker);
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
        btnSelect.setOnClickListener(Select);


        return v;
    }

    private View.OnClickListener Select = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(map != null)
                map.setOnMapLoadedCallback(FragLocationPicker.this);
            else
                StaticMethod.Toast(getContext().getString(R.string.picker_map_not_loaded), getContext());
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
        Geocoder geocoder = new Geocoder(getContext());
        geoMap = new GeoMap(geocoder, googleMap);
        StartCameraMove();
    }

    private void StartCameraMove()
    {
        if(geoMap.GetGoogleMap() != null)
            geoMap.GetGoogleMap().setOnCameraIdleListener(this);
        else
            throw new NullPointerException("class:CameraMove; GoogleMaps = null");

        StartAsyncTask();
    }


    @Override
    public void onCameraIdle()
    {
        StartAsyncTask();
    }

    private void StartAsyncTask()
    {
        geoMap.SetLatLng();
        new PickerTask(this).execute(geoMap);
    }

    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {
        txtAddress.setText(companyAddress.getAddress());
        address = companyAddress;

    }


    @Override
    public void onMapLoaded()
    {
        marker = map.addMarker(new MarkerOptions()
                .position(map.getCameraPosition().target)
                .title("JobMarker"));
        map.snapshot(this);
    }

    @Override
    public void onSnapshotReady(Bitmap bitmap)
    {
        if(address != null && !address.getAddress().isEmpty())
        {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.frmPickerConfirm, PickerConfirm.newInstance(listener, marker, address, StaticMethod.getMapResizedSnapshot(bitmap)));
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else
            StaticMethod.Toast(getContext().getString(R.string.picker_select_address), getContext());
    }

    @Override
    public void OnCancel()
    {
        marker.remove();
    }
}
