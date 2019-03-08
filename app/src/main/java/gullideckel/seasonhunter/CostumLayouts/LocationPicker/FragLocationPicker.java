package gullideckel.seasonhunter.CostumLayouts.LocationPicker;

import android.content.Context;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.C_CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.R;

public class FragLocationPicker extends Fragment implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener, ICompanyAddress
{
    private ICompanyAddress listener;
    private MapView mapView;
    private TextView txtAddress;
    private Button btnSelect;
    private GeoMap geoMap;
    private CompanyAddress address;

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
            listener.OnCompanyAddress(address);
            getActivity().onBackPressed();
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
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
}
