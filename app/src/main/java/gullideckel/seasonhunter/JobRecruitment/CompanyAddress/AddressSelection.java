package gullideckel.seasonhunter.JobRecruitment.CompanyAddress;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.Interfaces.ISnapShot;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding.CameraMove;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.PlaceAutoComplete.PlaceAutoCompleteClass;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.FragCompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class AddressSelection implements ISnapShot
{
    private ImageView mImgMarker;
    private Button mBtnSelectAddress;
    private TextView mTxtCompanyName;
    private TextView mTxtMapAddress;
    private TextView mTxtMapCoordinates;
    private LinearLayout mLinMapAddress;
    private LinearLayout mLinMapTopBar;
    private FrameLayout mFrmAutoPlace;
    private Activity mActivity;
    private IReplaceFragment mListener;

    private PlaceAutocompleteFragment mAutocompleteFragment;

    public CameraMove mCameraMove;
    private PlaceAutoCompleteClass mPlaceAutoComplete;

    public AddressSelection(View view, Activity activity)
    {
        mListener = (IReplaceFragment) activity;
        mImgMarker = (ImageView) view.findViewById(R.id.imgMarker);
        mTxtCompanyName = (TextView) view.findViewById(R.id.txtCompanyName);
        mBtnSelectAddress = (Button) view.findViewById(R.id.btnSelectAddress);
        mTxtMapAddress = (TextView) view.findViewById(R.id.txtMapAddress);
        mTxtMapCoordinates = (TextView) view.findViewById(R.id.txtMapCoordinates);
        mLinMapAddress = (LinearLayout) view.findViewById(R.id.linMapAddress);
        mFrmAutoPlace= (FrameLayout) view.findViewById(R.id.frmAutoPlace);
        mLinMapTopBar = (LinearLayout) view.findViewById(R.id.linMapTopBar);

        mActivity = activity;

        mAutocompleteFragment = (PlaceAutocompleteFragment) activity.getFragmentManager().findFragmentById(R.id.frag_place_autocomplete);
    }

    public void SetVisibilities(int visiTopBar, int visiAddress)
    {
        mLinMapTopBar.setVisibility(visiTopBar);
        mTxtCompanyName.setVisibility(visiAddress);
        mBtnSelectAddress.setVisibility(visiAddress);
        mImgMarker.setVisibility(visiAddress);
        mLinMapAddress.setVisibility(visiAddress);
        mFrmAutoPlace.setVisibility(visiAddress);
    }


    //TODO: Set set button listener at this class, ClickAddress has no declaration atm
    private View.OnClickListener ClickAddress = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

        }
    };

    private void OpenCompanyDetails(Bitmap bitmap)
    {
        if(mCameraMove != null && mCameraMove.GetCurrentAddress() != null)
        {
            if(!mCameraMove.GetCurrentAddress().GetCountry().isEmpty())
            {
                JobInfoObject jobInfo = new JobInfoObject();
                jobInfo.GetCompanyAddress().SetAddress(mCameraMove.GetCurrentAddress().GetAddressLine());
                jobInfo.GetCompanyAddress().SetCountry(mCameraMove.GetCurrentAddress().GetCountry());
                jobInfo.GetCompanyAddress().SetLatitude(mCameraMove.GetCurrentAddress().GetLatitude());
                jobInfo.GetCompanyAddress().SetLongitude(mCameraMove.GetCurrentAddress().GetLongitude());

                jobInfo.GetCompanyAddress().SetCompanyName(mTxtCompanyName.getText().toString());

                mListener.onReplaceFragment(FragCompanyDetails.newInstance(jobInfo, bitmap));
            }
            else
            {
                mTxtMapAddress.setTextColor(Color.RED);
            }
        }
    }

    public void SetAddress(String companyName, GeoMap geoMap, PlaceAutoCompleteClass autoComplete)
    {
        autoComplete.Start();
        SetVisibilities(View.INVISIBLE, View.VISIBLE);
        mTxtCompanyName.setText(companyName);

        StaticMethod.RemoveKeyPad(mActivity);

        mCameraMove = new CameraMove(mTxtMapAddress, mTxtMapCoordinates, mActivity);
        mCameraMove.Start(geoMap);
    }

    @Override
    public void onSnapShotBitmap(Bitmap bitmap)
    {
        OpenCompanyDetails(bitmap);
    }
}
