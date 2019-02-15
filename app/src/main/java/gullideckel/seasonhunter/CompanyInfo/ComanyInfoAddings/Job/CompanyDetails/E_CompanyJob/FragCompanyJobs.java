package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.E_CompanyJob;

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
import android.widget.ImageButton;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.Interfaces.ICompanyJobUpdate;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;


public class FragCompanyJobs extends Fragment
{

    private ImageButton iBtnAddJob;
    private Button btnJobSave;
    private RecyclerView rcylJobEdit;

    private ICompanyDetails listener;
    private CompanyDetails companyDetails;
    private ICompanyJobUpdate updateListener;


    public static FragCompanyJobs NewInstance(ICompanyDetails listener, CompanyDetails companyDetails, ICompanyJobUpdate updateListener)
    {
        FragCompanyJobs fragment = new FragCompanyJobs();
        fragment.listener = listener;
        fragment.companyDetails = companyDetails;
        fragment.updateListener = updateListener;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(v, savedInstanceState);

        v.bringToFront();
        v.setBackgroundColor(Color.WHITE);

        rcylJobEdit = (RecyclerView) v.findViewById(R.id.rcylJobEdit);
        iBtnAddJob = (ImageButton) v.findViewById(R.id.iBtnAddJob);
        btnJobSave = (Button) v.findViewById(R.id.btnSaveJob);


        CompanyJobFragConfi jobConfi = new CompanyJobFragConfi(this, listener, companyDetails);
        jobConfi.Confi();
    }

    public void OnUpdateJob(CompanyJobs items)
    {
        updateListener.OnCompanyUpdate(items);
    }

    public RecyclerView getRcylJobEdit()
    {
        return rcylJobEdit;
    }

    public ImageButton getiBtnAddJob()
    {
        return iBtnAddJob;
    }

    public Button getBtnJobSave()
    {
        return btnJobSave;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.details_company_job_fragment, container, false);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        updateListener = null;
    }
}
