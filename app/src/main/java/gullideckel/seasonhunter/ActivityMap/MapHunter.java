package gullideckel.seasonhunter.ActivityMap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

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

import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.ActivityMap.MapHunterClickListener.ButtonClicks;
import gullideckel.seasonhunter.Interfaces.IAddressHandler;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.AddressSelection;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.PlaceAutoComplete.PlaceAutoCompleteClass;
import gullideckel.seasonhunter.Objects.CompanyType.CompanyTypeObject;
import gullideckel.seasonhunter.R;

public class MapHunter extends FragmentActivity implements OnMapReadyCallback, IAddressHandler, IReplaceFragment,
        GoogleMap.OnMarkerClickListener
{

    private GoogleMap mMap;

    private Button mBtnLogOut;
    private FirebaseAuth mAuth;
    private Geocoder mGeocoder;
    private PlaceAutocompleteFragment mAutocompleteFragment;
    ButtonClicks mButtonClicks;

    private AddressSelection mAddressSelection;

    private boolean mIsSelectCompanyLocation = false;

    private List<CompanyTypeObject> companyTypes = new ArrayList<CompanyTypeObject>()


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map_hunter);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGeocoder = new Geocoder(this);

        mAddressSelection = new AddressSelection(getWindow().getDecorView(),this);

        mButtonClicks = new ButtonClicks(getWindow().getDecorView(), this);
        mButtonClicks.AddNewCompanyClickEvent();
        mButtonClicks.AddNewLogOutClickEvent();

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        mButtonClicks.AddNewAddressClickEvent(googleMap, mAddressSelection);

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
        MarkerOptions marker = new MarkerOptions().position(new LatLng(50,-120));
        marker.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("red_barn",50,50)));

        map.addMarker(marker);

        marker = new MarkerOptions().position(new LatLng(49.477895,-119.498461));
        marker.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("red_barn",50,50)));

        map.addMarker(marker);

        marker = new MarkerOptions().position(new LatLng(49.731491,-119.797831));
        marker.title("Philo Apple Orchard\nShow more info");
        marker.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("red_barn",50,50)));

        map.addMarker(marker);

        map.setOnMarkerClickListener(this);

        marker = new MarkerOptions().position(new LatLng(49.596290,-119.734889));
        marker.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("red_barn",50,50)));

        map.addMarker(marker);
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
    public void onBackPressed()
    {
        if(!mIsSelectCompanyLocation)
            super.onBackPressed();
        else
        {
            mAddressSelection.SetVisibilities(View.VISIBLE, View.GONE);
            mIsSelectCompanyLocation = false;
        }
    }

    @Override
    public void SetAddress(String companyName)
    {
        mAddressSelection.SetAddress(companyName, new GeoMap(mGeocoder, mMap), new PlaceAutoCompleteClass((PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.frag_place_autocomplete), mMap));
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
