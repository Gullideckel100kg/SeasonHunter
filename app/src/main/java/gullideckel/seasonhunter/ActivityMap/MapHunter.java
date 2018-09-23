package gullideckel.seasonhunter.ActivityMap;

import android.content.Intent;
import android.location.Geocoder;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import gullideckel.seasonhunter.ActivityMap.GeoCoding.CameraMove;
import gullideckel.seasonhunter.ActivityMap.GeoCoding.GeoMap;
import gullideckel.seasonhunter.ActivityMap.MapHunterClickListener.ButtonClicks;
import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.Interfaces.IAddressHandler;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IFragmentHandlerCompany;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.AddressSelection;
import gullideckel.seasonhunter.JobRecruitment.CompanyName.FragCompanyName;
import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class MapHunter extends FragmentActivity implements OnMapReadyCallback, IAddressHandler, IReplaceFragment
{

    private GoogleMap mMap;

    private Button mBtnLogOut;
    private FirebaseAuth mAuth;
    private Geocoder mGeocoder;
    private PlaceAutocompleteFragment mAutocompleteFragment;

    private AddressSelection mAddressSelection;

    private boolean mIsSelectCompanyLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map_hunter);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGeocoder = new Geocoder(this);

        mAddressSelection = new AddressSelection(getWindow().getDecorView());

        mAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.frag_place_autocomplete);

        ButtonClicks buttonClicks = new ButtonClicks(getWindow().getDecorView(), this);
        buttonClicks.AddNewCompanyClickEvent();
        buttonClicks.AddNewLogOutClickEvent();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
        mAddressSelection.SetAddress(companyName, this, new GeoMap(mGeocoder, mMap));
        onBackPressed();
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
