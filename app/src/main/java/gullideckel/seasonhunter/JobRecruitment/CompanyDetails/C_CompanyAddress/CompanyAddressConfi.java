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
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;


public class CompanyAddressConfi implements OnMapReadyCallback, ICompanyAddress, ISnapShot
{
    private CompanyAddressViewHolder mHolder;
    private ICompanyAddress mListener;
    private Bitmap mLogo;
    private Context mContext;
    private CostumLayoutManager mLayoutManager;
    private GoogleMap mMap;
    private volatile CompanyAddress mCompanyAddress;

    public CompanyAddressConfi(CompanyAddressViewHolder holder, ICompanyAddress listener, Bitmap logo, Context context, CostumLayoutManager layoutManager, CompanyAddress companyAddress)
    {
        mHolder = holder;
        mListener = listener;
        mLogo = logo;
        mContext = context;
        mLayoutManager = layoutManager;
        mCompanyAddress = companyAddress;
    }

    public void Confi()
    {
        if (mHolder.GetMapView() != null)
        {
            mHolder.GetMapView().onCreate(null);
            mHolder.GetMapView().onResume();
            mHolder.GetMapView().getMapAsync(this);
        }

        mLayoutManager.scrollToPosition(3);
        mLayoutManager.setScrollEnabled(false);

        mHolder.GetLogo().setImageBitmap(mLogo);

        mHolder.GetBtnSave().setOnClickListener(Select);
        mHolder.GetIBtnEdit().setOnClickListener(Edit);
    }



    //TODO: Wait progress bar or Loading Points. Example Code blended out
    private View.OnClickListener Select = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            if(mHolder.GetTxtAddress().getText().toString().isEmpty() || mHolder.GetTxtCoordinates().getText().toString().isEmpty())
            {
                mHolder.GetTxtAddressHeadLine().setTextColor(Color.RED);
            }
            else
            {
                mHolder.GetTxtAddressHeadLine().setTextColor(Color.BLACK);
                mLayoutManager.setScrollEnabled(true);
                mHolder.GetCnstMapView().setVisibility(View.GONE);
                mHolder.GetIBtnEdit().setVisibility(View.VISIBLE);
                mHolder.GetBtnSave().setVisibility(View.GONE);
                mHolder.GetTxtAddressHeadLine().setVisibility(View.GONE);
                mHolder.GetTxtAutoComplete().setVisibility(View.GONE);
                mHolder.GetSnapAddress().setVisibility(View.VISIBLE);
                TakeSnapshot();
                mListener.OnCompanyAddress(mCompanyAddress);
            }
        }
    };

    private View.OnClickListener Edit = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            mLayoutManager.scrollToPosition(2);
            mLayoutManager.setScrollEnabled(false);
            mHolder.GetCnstMapView().setVisibility(View.VISIBLE);
            mHolder.GetIBtnEdit().setVisibility(View.GONE);
            mHolder.GetBtnSave().setVisibility(View.VISIBLE);
            mHolder.GetTxtAutoComplete().setVisibility(View.VISIBLE);
            mHolder.GetTxtAddressHeadLine().setVisibility(View.VISIBLE);
        }
    };

    private void TakeSnapshot()
    {
        mMap.setOnMapLoadedCallback(new MySnapshot(mMap, this, mLogo));
    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        MapsInitializer.initialize(mContext);

        mMap = map;

        CameraMove cameraMove = new CameraMove(mContext, new GeoMap(new Geocoder(mContext), map), this);
        cameraMove.Start();

        PlaceAutoCompleteTextView autoComplete = new PlaceAutoCompleteTextView(map, mContext, mHolder.GetTxtAutoComplete());
        autoComplete.Init();
    }

    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {
        mCompanyAddress = companyAddress;
        mHolder.GetTxtAddress().setText(companyAddress.GetAddress());
        mHolder.GetTxtCoordinates().setText(StaticMethod.GPSConvert(companyAddress.GetLatitude(), companyAddress.GetLongitude()));
    }

    @Override
    public void onSnapShotBitmap(Bitmap bitmap)
    {
        mHolder.GetSnapAddress().setImageBitmap(bitmap);
    }

    public void SetLogo(Bitmap logo)
    {
        mHolder.GetLogo().setImageBitmap(logo);
    }
}
