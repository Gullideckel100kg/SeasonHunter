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
import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.Interfaces.IAddressHandler;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IFragmentHandlerCompany;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitment.CompanyName.FragCompanyName;
import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class MapHunter extends FragmentActivity implements OnMapReadyCallback, IAddressHandler
{

    private GoogleMap mMap;

    private Button mBtnLogOut;

    private ImageView mImgMarker;
    private Button mBtnSelectAddress;
    private TextView mTxtCompanyName;
    private TextView mTxtMapAddress;
    private TextView mTxtMapCoordinates;
    private LinearLayout mLinMapAddress;

    private LinearLayout mLinMapTopBar;
    private FirebaseAuth mAuth;
    private JobInfoObject mJobInfo;
    private RelativeLayout mFrmAddCompany;

    private FrameLayout mFrmAutoPlace;

    private PlaceAutocompleteFragment mAutocompleteFragment;

    private Geocoder mGeocoder;

    private boolean mIsSelectCompanyLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map_hunter);



        mGeocoder = new Geocoder(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mAuth = FirebaseAuth.getInstance();

        mFrmAddCompany = (RelativeLayout) findViewById(R.id.frmJobRecruitment);
        mLinMapTopBar = (LinearLayout) findViewById(R.id.linMapTopBar);

        mBtnLogOut = (Button) findViewById(R.id.btnLogout);
        mImgMarker = (ImageView) findViewById(R.id.imgMarker);
        mTxtCompanyName = (TextView) findViewById(R.id.txtCompanyName);
        mBtnSelectAddress = (Button) findViewById(R.id.btnSelectAddress);
        mTxtMapAddress = (TextView) findViewById(R.id.txtMapAddress);
        mTxtMapCoordinates = (TextView) findViewById(R.id.txtMapCoordinates);
        mLinMapAddress = (LinearLayout) findViewById(R.id.linMapAddress);

        mFrmAutoPlace= (FrameLayout) findViewById(R.id.frmAutoPlace);
        mAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.frag_place_autocomplete);

        mBtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mAuth.signOut();
                OpenSignInHunter();
            }
        });

        Button btnAddCompany = (Button) findViewById(R.id.btnAddCompany);

        btnAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ReplaceFragment(new FragCompanyName());
            }
        });

        mJobInfo = new JobInfoObject();

        StaticMethod.RemoveKeyPad(this);
    }





    private void OpenSignInHunter()
    {
        Intent intent = new Intent(this, SignInHunter.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onBackPressed()
    {
        if(!mIsSelectCompanyLocation)
            super.onBackPressed();
        else
        {
            SetVisibilities(View.VISIBLE, View.GONE);
            mIsSelectCompanyLocation = false;
        }
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmJobRecruitment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void SetAddress(String companyName)
    {
        SetVisibilities(View.INVISIBLE, View.VISIBLE);
        mTxtCompanyName.setText(companyName);
        onBackPressed();
        mIsSelectCompanyLocation = true;

        StaticMethod.RemoveKeyPad(this);

        CameraMove cameraMove = new CameraMove(mTxtMapAddress, mTxtMapCoordinates, this);
        cameraMove.Start(new GeoMap(mGeocoder,mMap));

    }

    private void SetVisibilities(int visiTopBar, int visiAddress)
    {
        mLinMapTopBar.setVisibility(visiTopBar);
        mTxtCompanyName.setVisibility(visiAddress);
        mBtnSelectAddress.setVisibility(visiAddress);
        mImgMarker.setVisibility(visiAddress);
        mLinMapAddress.setVisibility(visiAddress);
        mFrmAutoPlace.setVisibility(visiAddress);
    }
}
