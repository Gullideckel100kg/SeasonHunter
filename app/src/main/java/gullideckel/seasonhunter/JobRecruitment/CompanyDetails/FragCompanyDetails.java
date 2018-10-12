package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding.CurrentAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;


public class FragCompanyDetails extends Fragment
{

    protected JobInfoObject mJobInfo;
    protected Bitmap mMapShot;

    private ImageView mImgAddress;
    private ImageButton mEdtAddress;
    private TextView mTxtAddress;
    private TextView mTxtCoordinates;
    private TextView mTxtCompanyName;
    private ImageButton mEdtCompanyName;

    public static FragCompanyDetails newInstance(JobInfoObject jobInfo, Bitmap mapShot)
    {
        FragCompanyDetails fragment = new FragCompanyDetails();
        fragment.mJobInfo = jobInfo;
        fragment.mMapShot =mapShot;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mImgAddress = (ImageView) view.findViewById(R.id.imgPrevAddress);
        mEdtAddress = (ImageButton) view.findViewById(R.id.imgEditAddress);
        mTxtAddress = (TextView) view.findViewById(R.id.txtPrevAddress);
        mTxtCoordinates = (TextView) view.findViewById(R.id.txtPrevCoordinates);
        mTxtCompanyName = (TextView) view.findViewById(R.id.txtPrevCompanyName);
        mEdtCompanyName = (ImageButton) view.findViewById(R.id.imgEdtCompanyName);

        mImgAddress.setImageBitmap(mMapShot);
        mTxtCompanyName.setText(mJobInfo.GetCompanyAddress().GetCompanyName());
        mTxtAddress.setText(mJobInfo.GetCompanyAddress().GetAddress());
        mTxtCoordinates.setText(StaticMethod.GPSConvert(mJobInfo.GetCompanyAddress().GetLatitude(), mJobInfo.GetCompanyAddress().GetLongitude()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_company_details, container, false);
    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri)
//    {
//        if (mListener != null)
//        {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context)
//    {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener)
//        {
//            mListener = (OnFragmentInteractionListener) context;
//        } else
//        {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach()
//    {
//        super.onDetach();
//        mListener = null;
//    }

}
