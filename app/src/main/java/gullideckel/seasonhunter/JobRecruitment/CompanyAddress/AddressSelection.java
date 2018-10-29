package gullideckel.seasonhunter.JobRecruitment.CompanyAddress;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.Interfaces.ISnapShot;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.CameraMove;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding.GeoMap;
import gullideckel.seasonhunter.R;

public class AddressSelection
{
//    private ImageView mImgMarker;
////    private FrameLayout mFrmAutoPlace;
//    private Activity mActivity;
//    private IReplaceFragment mListener;
//
//    private PlaceAutocompleteFragment mAutocompleteFragment;
//
//    public CameraMove mCameraMove;
////    private PlaceAutoCompleteClass mPlaceAutoComplete;
//
//    public AddressSelection(View view, Activity activity)
//    {
//        mListener = (IReplaceFragment) activity;
//        mImgMarker = (ImageView) view.findViewById(R.id.imgJobMarker);
//
////        mFrmAutoPlace= (FrameLayout) view.findViewById(R.id.frmAutoPlace);
//
//        mActivity = activity;
//
//        mAutocompleteFragment = (PlaceAutocompleteFragment) activity.getFragmentManager().findFragmentById(R.id.frag_place_autocomplete);
//    }
//
//    public void SetVisibilities(int visiTopBar, int visiAddress)
//    {
//        mImgMarker.setVisibility(visiAddress);
//        mFrmAutoPlace.setVisibility(visiAddress);
//    }



//    private void OpenCompanyDetails(Bitmap bitmap)
//    {
//        if(mCameraMove != null && mCameraMove.GetCurrentAddress() != null)
//        {
//            if(!mCameraMove.GetCurrentAddress().GetCountry().isEmpty())
//            {
//                JobInfoObject jobInfo = new JobInfoObject();
//                jobInfo.GetCompanyAddress().SetAddress(mCameraMove.GetCurrentAddress().GetAddressLine());
//                jobInfo.GetCompanyAddress().SetCountry(mCameraMove.GetCurrentAddress().GetCountry());
//                jobInfo.GetCompanyAddress().SetLatitude(mCameraMove.GetCurrentAddress().GetLatitude());
//                jobInfo.GetCompanyAddress().SetLongitude(mCameraMove.GetCurrentAddress().GetLongitude());
//
//
//                mListener.onReplaceFragment(FragAddress.newInstance(jobInfo, bitmap));
//            }
//            else
//            {
//
//            }
//        }
//    }

//    public void SetAddress(String companyName, GeoMap geoMap, PlaceAutoCompleteClass autoComplete)
//    {
////        autoComplete.Start();
////        SetVisibilities(View.INVISIBLE, View.VISIBLE);
////        mTxtCompanyName.setText(companyName);
////
////        StaticMethod.RemoveKeyPad(mActivity);
////
////        mCameraMove = new CameraMove(mTxtMapAddress, mTxtMapCoordinates, mActivity);
////        mCameraMove.Start(geoMap);
//    }

//    @Override
//    public void onSnapShotBitmap(Bitmap bitmap)
//    {
//        OpenCompanyDetails(bitmap);
//    }
}
