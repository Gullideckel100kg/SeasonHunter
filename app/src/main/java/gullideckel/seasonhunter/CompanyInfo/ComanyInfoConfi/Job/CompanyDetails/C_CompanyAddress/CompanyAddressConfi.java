package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.C_CompanyAddress;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import gullideckel.seasonhunter.CostumLayouts.PlaceAutoComplete.PlaceAutoCompleteTextView;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.Statics.StaticMethod;


public class CompanyAddressConfi extends CompanyDetailsBase implements ICompanyAddress
{
    private GoogleMap map;
    private Bitmap logo;
    private volatile CompanyAddress companyAddress;
    PlaceAutoCompleteTextView autoComplete;

    public CompanyAddressConfi(CompanyAddressViewHolder vh, ICompanyDetails listener, CompanyDetails detailsObject)
    {
        super(vh, listener, detailsObject);
        companyAddress = getObjectAtPosition(CompanyAddress.class);
    }

    public void Confi(Bitmap logo)
    {
        if(getAddress().getRelAddress().getVisibility() == View.INVISIBLE || getAddress().getRelAddress().getVisibility() == View.GONE)
        {
            autoComplete = new PlaceAutoCompleteTextView(getContext(), getAddress().GetTxtAutoComplete(), getGoogleApiClient(), this);
            autoComplete.Init();
//            if (getAddress().GetMapView() != null)
//            {
//                getAddress().GetMapView().onCreate(null);
//                getAddress().GetMapView().onResume();
//                getAddress().GetMapView().getMapAsync(this);
//            }

            OpenAddress();

            getAddress().GetBtnSave().setOnClickListener(Select);
            getAddress().GetIBtnEdit().setOnClickListener(Edit);

            getAddress().getRelAddress().setVisibility(View.VISIBLE);
        }

//        getAddress().GetLogo().setImageBitmap(logo);
        this.logo = logo;

    }



    //TODO: Wait progress bar or Loading Points. Example Code blended out
    private View.OnClickListener Select = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            if(getAddress().getTxtAddressEdit().getText().toString().isEmpty() || getAddress().getTxtCoordinatesEdit().getText().toString().isEmpty())
            {
                getAddress().GetTxtAddressHeadLine().setTextColor(Color.RED);
            }
            else
            {
                getAddress().GetTxtAddressHeadLine().setTextColor(Color.BLACK);
                getLayoutManager().setScrollEnabled(true);

                getAddress().getCnstAddressEdit().setVisibility(View.GONE);
                getAddress().getCnstAddressSaved().setVisibility(View.VISIBLE);

//                TakeSnapshot();

                getListener().OnItemUpdate(CompanyDetails.COMPANYCONTACT);
            }
        }
    };

    private View.OnClickListener Edit = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            OpenAddress();
        }
    };

//    private void TakeSnapshot()
//    {
//        map.setOnMapLoadedCallback(new MySnapshot(map, this, logo, getAddress().GetSnapLogo()));
//    }

//    @Override
//    public void onMapReady(GoogleMap map)
//    {
//        MapsInitializer.initialize(getContext());
//
//        this.map = map;
//
//        CameraMove cameraMove = new CameraMove(getContext(), new GeoMap(new Geocoder(getContext()), map), this);
//        cameraMove.Start();
//
//
//    }

    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {
        this.companyAddress.setAddress(companyAddress.getAddress());
        this.companyAddress.setCountry(companyAddress.getCountry());
        this.companyAddress.setLatitude(companyAddress.getLatitude());
        this.companyAddress.setLongitude(companyAddress.getLongitude());
        SetAddressInTxtView(getAddress().getTxtAddressEdit(), getAddress().getTxtCoordinatesEdit());
        SetAddressInTxtView(getAddress().getTxtAddressSaved(), getAddress().getTxtCoordinatesSaved());
    }

    private void SetAddressInTxtView(TextView address, TextView coordinates)
    {
        address.setText(companyAddress.getAddress());
        coordinates.setText(StaticMethod.GPSConvert(companyAddress.getLatitude(), companyAddress.getLongitude()));
    }

//    @Override
//    public void onSnapShotBitmap(Bitmap bitmap)
//    {
//        getAddress().GetSnapAddress().setImageBitmap(bitmap);
//    }

    private void OpenAddress()
    {
        getBtnPost().setVisibility(View.GONE);
        getLayoutManager().scrollToPosition(CompanyDetails.COMPANYADDRESS);
        getLayoutManager().setScrollEnabled(false);
        getAddress().getCnstAddressEdit().setVisibility(View.VISIBLE);
        getAddress().getCnstAddressSaved().setVisibility(View.GONE);
    }

//    public void SetLogo(Bitmap logo)
//    {
//        getAddress().GetLogo().setImageBitmap(logo);
//        getAddress().GetSnapLogo().setImageBitmap(logo);
//    }

    @Override
    protected void OnKeyPadDisappearing()
    {
        super.OnKeyPadDisappearing();
        ScrollToPosition(CompanyDetails.COMPANYADDRESS);
    }
}
