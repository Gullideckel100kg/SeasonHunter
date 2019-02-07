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

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gullideckel.seasonhunter.ActivityMap.Jobs.JobsMarker;
import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.ActivityMap.MapHunterClickListener.ButtonClicks;
import gullideckel.seasonhunter.Interfaces.IAddressHandler;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyTypes;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticVariabels;

public class SeasonHunter extends FragmentActivity implements OnMapReadyCallback, IFragmentHandler
{

    //TODO:Check is Thread save. More proccess access this variable
    private volatile GoogleMap map;

    private Button mBtnLogOut;
    private FirebaseAuth mAuth;
    private Geocoder mGeocoder;
    private ButtonClicks mButtonClicks;
    private boolean isLoggedIn = false;

    private boolean mIsSelectCompanyLocation = false;

    public static List<Marker> mLstJobMarkers = new ArrayList<>();

    public static final HashMap<String, Bitmap> companyTypes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map_hunter);


        onReplaceFragment(new LoadingScreen(), IntFrag.ADD);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGeocoder = new Geocoder(this);

        mButtonClicks = new ButtonClicks(getWindow().getDecorView(), this);
        mButtonClicks.AddNewCompanyClickEvent();
        mButtonClicks.AddNewLogOutClickEvent();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        FillCompanyTypes();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;

        GoogleMapOptions options = new GoogleMapOptions();

        options.compassEnabled(true);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50,-120), 7));

        LoadMarkers();
    }

    @Override
    public void onReplaceFragment(Fragment fragment, int intFrag)
    {
        switch (intFrag)
        {
            case IntFrag.ADD:
                AddFragment(getSupportFragmentManager().beginTransaction(), fragment);
                break;
            case IntFrag.REPLACE:
                ReplaceFragment(getSupportFragmentManager().beginTransaction(), fragment);
                break;
            case IntFrag.POPSTACK:
                onBackPressed();
                break;
            case IntFrag.POPSTACKCOMPLETLY:
                getSupportFragmentManager().popBackStackImmediate();
                break;
            case IntFrag.REMOVE:
                RemoveFragment(getSupportFragmentManager().beginTransaction(), fragment);
                break;
        }
    }

    private void AddFragment(FragmentTransaction transaction, Fragment fragment)
    {
        transaction.add(R.id.frmJobRecruitment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void ReplaceFragment(FragmentTransaction transaction, Fragment fragment)
    {
        transaction.replace(R.id.frmJobRecruitment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void RemoveFragment(FragmentTransaction transaction, Fragment fragment)
    {
        transaction.remove(fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == StaticVariabels.Result_LogIn_Ok)
        {
            AddFragment(getSupportFragmentManager().beginTransaction(), new LoadingScreen());
            if(map != null)
            {
                LoadMarkers();
            }
        }
    }

    private void LoadMarkers()
    {
        JobsMarker jobsMarker = new JobsMarker(map, this, this);
        jobsMarker.LoadJobs();
    }

    //TODO: Has to be changed
    private void FillCompanyTypes()
    {
        companyTypes.put( "Farm", BitmapFactory.decodeResource(getResources(), R.drawable.farm));
        companyTypes.put( "Orchard", BitmapFactory.decodeResource(getResources(), R.drawable.farm));
        companyTypes.put( "Packhouse", BitmapFactory.decodeResource(getResources(), R.drawable.packing));
        companyTypes.put( "Fruit farm", BitmapFactory.decodeResource(getResources(), R.drawable.fruit));
        companyTypes.put( "Restaurant", BitmapFactory.decodeResource(getResources(), R.drawable.chef));
        companyTypes.put( "Vineyard", BitmapFactory.decodeResource(getResources(), R.drawable.wine));
        companyTypes.put( "Tree planting", BitmapFactory.decodeResource(getResources(), R.drawable.tree));
        companyTypes.put( "Factory", BitmapFactory.decodeResource(getResources(), R.drawable.factory));
        companyTypes.put( "Christmas", BitmapFactory.decodeResource(getResources(), R.drawable.christmas));
        companyTypes.put( "Others", BitmapFactory.decodeResource(getResources(), R.drawable.otherwork));
    }


}
