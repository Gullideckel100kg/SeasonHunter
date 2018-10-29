package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CostumLayoutManager;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;
import gullideckel.seasonhunter.R;


public class FragCompanyDetails extends Fragment
{
    protected List<CompanyType> mCompanyTypes;

    private List<Object> mJobInfos;

    private RecyclerView mRclyCompanyDetails;
    private CostumLayoutManager mLayoutManager;

    public static FragCompanyDetails NewInstance(List<CompanyType> companyTypes)
    {
        FragCompanyDetails fragment = new FragCompanyDetails();
        fragment.mCompanyTypes = companyTypes;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.bringToFront();
        view.setBackgroundColor(Color.WHITE);

        mJobInfos = new ArrayList<>();
        mJobInfos.add(new CompanyName());

        mRclyCompanyDetails = (RecyclerView) view.findViewById(R.id.rclyCompanyDetails);

        mLayoutManager = new CostumLayoutManager(getContext());
        mRclyCompanyDetails.setLayoutManager(mLayoutManager);

        ComplexCompanyDetailsAdapter adapter = new ComplexCompanyDetailsAdapter(mJobInfos, mCompanyTypes, getContext(), mLayoutManager);
        mRclyCompanyDetails.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_company_details, container, false);
    }
}
