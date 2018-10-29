package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import gullideckel.seasonhunter.Interfaces.ISnapShot;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.CameraMove;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.PlaceAutoComplete.PlaceAutoCompleteTextView;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;


public class CompanyAddressConfi implements OnMapReadyCallback, ICompanyAddress, ISnapShot
{
    private CompanyAddressViewHolder mHolder;
    private ICompanyAddress mListener;
    private Bitmap mLogo;
    private Context mContext;

    public CompanyAddressConfi(CompanyAddressViewHolder holder, ICompanyAddress listener, Bitmap logo, Context context)
    {
        mHolder = holder;
        mListener = listener;
        mLogo = logo;
        mContext = context;
    }

    public void Confi()
    {
        if (mHolder.GetMapView() != null)
        {
            mHolder.GetMapView().onCreate(null);
            mHolder.GetMapView().onResume();
            mHolder.GetMapView().getMapAsync(this);
        }

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
            mHolder.GetCnstMapView().setVisibility(View.GONE);
            mHolder.GetIBtnEdit().setVisibility(View.VISIBLE);
            mHolder.GetBtnSave().setVisibility(View.GONE);
        }
    };

    private View.OnClickListener Edit = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            mHolder.GetCnstMapView().setVisibility(View.VISIBLE);
            mHolder.GetIBtnEdit().setVisibility(View.GONE);
            mHolder.GetBtnSave().setVisibility(View.VISIBLE);
        }
    };


    @Override
    public void onMapReady(GoogleMap map)
    {
        MapsInitializer.initialize(mContext);

        CameraMove cameraMove = new CameraMove(mContext, new GeoMap(new Geocoder(mContext), map), this);
        cameraMove.Start();

        PlaceAutoCompleteTextView autoComplete = new PlaceAutoCompleteTextView(map, mContext, mHolder.GetTxtAutoComplete());
        autoComplete.Init();
    }

    public Bitmap resizeMapIcons(int width, int height)
    {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mLogo, width, height, false);
        return resizedBitmap;
    }

    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {
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
