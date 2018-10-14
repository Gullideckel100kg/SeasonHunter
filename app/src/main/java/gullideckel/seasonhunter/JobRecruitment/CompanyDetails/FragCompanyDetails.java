package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Objects.CompanyType.CompanyTypeObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;
import gullideckel.seasonhunter.R;


public class FragCompanyDetails extends Fragment
{
    private RecyclerView mRclyCompanyDetails;
    private List<Object> mJobInfos;

    protected List<CompanyTypeObject> mCompanyTypes;

    private ComplexCompanyDetailsAdapter mAdapter;

//    private ImageView mImgAddress;
//    private ImageButton mEdtAddress;
//    private TextView mTxtAddress;
//    private TextView mTxtCoordinates;
//    private TextView mTxtCompanyName;
//    private ImageButton mEdtCompanyName;

    public static FragCompanyDetails NewInstance(List<CompanyTypeObject> companyTypes)
    {
        FragCompanyDetails fragment = new FragCompanyDetails();
        fragment.mCompanyTypes = companyTypes;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mJobInfos = new ArrayList<>();
        mJobInfos.add(new CompanyName());

        mRclyCompanyDetails = (RecyclerView) view.findViewById(R.id.rclyCompanyDetails);

        mRclyCompanyDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ComplexCompanyDetailsAdapter(mJobInfos, mCompanyTypes);
        mRclyCompanyDetails.setAdapter(mAdapter);




//        mImgAddress = (ImageView) view.findViewById(R.id.imgPrevAddress);
//        mEdtAddress = (ImageButton) view.findViewById(R.id.imgEditAddress);
//        mTxtAddress = (TextView) view.findViewById(R.id.txtPrevAddress);
//        mTxtCoordinates = (TextView) view.findViewById(R.id.txtPrevCoordinates);
//        mTxtCompanyName = (TextView) view.findViewById(R.id.txtPrevCompanyName);
//        mEdtCompanyName = (ImageButton) view.findViewById(R.id.imgEdtCompanyName);
//
//        mImgAddress.setImageBitmap(mMapShot);
//        mTxtCompanyName.setText(mJobInfo.GetCompanyName().GetCompanyName());
//        mTxtAddress.setText(mJobInfo.GetCompanyAddress().GetAddress());
//        mTxtCoordinates.setText(StaticMethod.GPSConvert(mJobInfo.GetCompanyAddress().GetLatitude(), mJobInfo.GetCompanyAddress().GetLongitude()));
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
