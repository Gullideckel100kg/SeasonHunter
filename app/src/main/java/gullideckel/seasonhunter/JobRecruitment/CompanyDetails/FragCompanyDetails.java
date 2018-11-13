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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CostumLayoutManager;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.IPost;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyTypes;
import gullideckel.seasonhunter.R;


public class FragCompanyDetails extends Fragment implements IPost
{
    protected List<CompanyTypes.CompanyType> companyTypes;

    private RecyclerView mRclyCompanyDetails;

    private CompanyDetailsObject companyDetails;

    public static FragCompanyDetails NewInstance(List<CompanyTypes.CompanyType> companyTypes)
    {
        FragCompanyDetails fragment = new FragCompanyDetails();
        fragment.companyTypes = companyTypes;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.bringToFront();
        view.setBackgroundColor(Color.WHITE);

        List<Object> jobInfos = new ArrayList<>();
        jobInfos.add(new CompanyName());

        mRclyCompanyDetails = (RecyclerView) view.findViewById(R.id.rclyCompanyDetails);

        CostumLayoutManager layoutManager = new CostumLayoutManager(getContext());
        mRclyCompanyDetails.setLayoutManager(layoutManager);

        Button btnPost = (Button) view.findViewById(R.id.btnPostJob);
        btnPost.setOnClickListener(Post);

        companyDetails = new CompanyDetailsObject(jobInfos, layoutManager, getContext(), btnPost);

        ComplexCompanyDetailsAdapter adapter = new ComplexCompanyDetailsAdapter(companyDetails, companyTypes);
        mRclyCompanyDetails.setAdapter(adapter);


    }

    private View.OnClickListener Post = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_company_details, container, false);
    }

    @Override
    public void OnPost()
    {
        companyDetails.getBtnPost().setVisibility(View.VISIBLE);
    }
}
