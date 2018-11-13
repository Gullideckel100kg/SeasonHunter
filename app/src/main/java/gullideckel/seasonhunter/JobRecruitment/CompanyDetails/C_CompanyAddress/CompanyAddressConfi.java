package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Geocoder;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import gullideckel.seasonhunter.Interfaces.ISnapShot;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.CameraMove;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.PlaceAutoComplete.PlaceAutoCompleteTextView;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.Snapshot.MySnapshot;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsObject;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.ComplexCompanyDetailsAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;


public class CompanyAddressConfi extends CompanyDetailsBase implements OnMapReadyCallback, ICompanyAddress, ISnapShot
{
    private Bitmap mLogo;
    private GoogleMap mMap;
    private volatile CompanyAddress companyAddress;

    public CompanyAddressConfi(CompanyAddressViewHolder vh, ICompanyDetails listener, Bitmap logo, CompanyDetailsObject detailsObject)
    {
        super(vh, listener, detailsObject);
        mLogo = logo;
        companyAddress = getObjectAtPosition(CompanyAddress.class);
    }

    public void Confi()
    {
        if (getAddress().GetMapView() != null)
        {
            getAddress().GetMapView().onCreate(null);
            getAddress().GetMapView().onResume();
            getAddress().GetMapView().getMapAsync(this);
        }

        getLayoutManager().scrollToPosition(3);
        getLayoutManager().setScrollEnabled(false);

        getAddress().GetLogo().setImageBitmap(mLogo);

        getAddress().GetBtnSave().setOnClickListener(Select);
        getAddress().GetIBtnEdit().setOnClickListener(Edit);
    }



    //TODO: Wait progress bar or Loading Points. Example Code blended out
    private View.OnClickListener Select = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            if(getAddress().GetTxtAddress().getText().toString().isEmpty() || getAddress().GetTxtCoordinates().getText().toString().isEmpty())
            {
                getAddress().GetTxtAddressHeadLine().setTextColor(Color.RED);
            }
            else
            {
                getAddress().GetTxtAddressHeadLine().setTextColor(Color.BLACK);
                getLayoutManager().setScrollEnabled(true);
                getAddress().GetCnstMapView().setVisibility(View.GONE);
                getAddress().GetIBtnEdit().setVisibility(View.VISIBLE);
                getAddress().GetBtnSave().setVisibility(View.GONE);
                getAddress().GetTxtAddressHeadLine().setVisibility(View.GONE);
                getAddress().GetTxtAutoComplete().setVisibility(View.GONE);
                getAddress().getRelSnapPic().setVisibility(View.VISIBLE);
                TakeSnapshot();


                getListener().OnItemUpdate(ComplexCompanyDetailsAdapter.COMPANYADDRESS);
            }
        }
    };

    private View.OnClickListener Edit = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            getLayoutManager().scrollToPosition(2);
            getLayoutManager().setScrollEnabled(false);
            getAddress().GetCnstMapView().setVisibility(View.VISIBLE);
            getAddress().GetIBtnEdit().setVisibility(View.GONE);
            getAddress().GetBtnSave().setVisibility(View.VISIBLE);
            getAddress().GetTxtAutoComplete().setVisibility(View.VISIBLE);
            getAddress().GetTxtAddressHeadLine().setVisibility(View.VISIBLE);
            getAddress().getRelSnapPic().setVisibility(View.GONE);

        }
    };

    private void TakeSnapshot()
    {
        mMap.setOnMapLoadedCallback(new MySnapshot(mMap, this, mLogo, getAddress().GetSnapLogo()));
    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        MapsInitializer.initialize(getContext());

        mMap = map;

        CameraMove cameraMove = new CameraMove(getContext(), new GeoMap(new Geocoder(getContext()), map), this);
        cameraMove.Start();

        PlaceAutoCompleteTextView autoComplete = new PlaceAutoCompleteTextView(map, getContext(), getAddress().GetTxtAutoComplete());
        autoComplete.Init();
    }

    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {
        companyAddress = companyAddress;
        getAddress().GetTxtAddress().setText(companyAddress.GetAddress());
        getAddress().GetTxtCoordinates().setText(StaticMethod.GPSConvert(companyAddress.GetLatitude(), companyAddress.GetLongitude()));
    }

    @Override
    public void onSnapShotBitmap(Bitmap bitmap)
    {
        getAddress().GetSnapAddress().setImageBitmap(bitmap);
    }

    public void SetLogo(Bitmap logo)
    {
        getAddress().GetLogo().setImageBitmap(logo);
        getAddress().GetSnapLogo().setImageBitmap(logo);
    }
}
