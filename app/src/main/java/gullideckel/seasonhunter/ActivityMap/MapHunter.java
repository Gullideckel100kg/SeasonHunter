package gullideckel.seasonhunter.ActivityMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.ActivityMap.MapHunterClickListener.ButtonClicks;
import gullideckel.seasonhunter.Interfaces.IAddressHandler;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.AddressSelection;
import gullideckel.seasonhunter.R;

public class MapHunter extends FragmentActivity implements OnMapReadyCallback, IAddressHandler, IReplaceFragment,
        GoogleMap.OnMarkerClickListener
{

    private GoogleMap mMap;

    private Button mBtnLogOut;
    private FirebaseAuth mAuth;
    private Geocoder mGeocoder;
    ButtonClicks mButtonClicks;

//    private AddressSelection mAddressSelection;

    private boolean mIsSelectCompanyLocation = false;

    public static List<Marker> mLstJobMarkers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map_hunter);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGeocoder = new Geocoder(this);


//        mAddressSelection = new AddressSelection(getWindow().getDecorView(),this);

        mButtonClicks = new ButtonClicks(getWindow().getDecorView(), this);
        mButtonClicks.AddNewCompanyClickEvent();
        mButtonClicks.AddNewLogOutClickEvent();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

//        mButtonClicks.AddNewAddressClickEvent(googleMap, mAddressSelection);

        GoogleMapOptions options = new GoogleMapOptions();

        options.compassEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50,-120), 7));
        ExampleMarkers(googleMap);


//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    //TODO: Just for test. Have to be deleted later
    private void ExampleMarkers(GoogleMap map)
    {
        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(50,-120))
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("red_barn",50,50))));

        mLstJobMarkers.add(marker);

        marker = map.addMarker(new MarkerOptions().position(new LatLng(49.731491,-119.797831))
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("red_barn",50,50))));

        mLstJobMarkers.add(marker);

        marker = map.addMarker(new MarkerOptions().position(new LatLng(49.596290,-119.734889))
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("red_barn",50,50))));

        mLstJobMarkers.add(marker);
    }



    @Override
    public boolean onMarkerClick(Marker marker)
    {
        Intent intent = new Intent(this, TestCompanyInfo.class);
        startActivity(intent);
        return false;
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }



    @Override
    public void SetAddress(String companyName)
    {
//        mAddressSelection.SetAddress(companyName, new GeoMap(mGeocoder, mMap), new PlaceAutoCompleteClass((PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.frag_place_autocomplete), mMap));
        getSupportFragmentManager().popBackStackImmediate();
        mIsSelectCompanyLocation = true;
    }

    @Override
    public void onReplaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmJobRecruitment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
