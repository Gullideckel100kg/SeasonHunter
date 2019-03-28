package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.E_CompanyJob;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.E_CompanyJob.CompanyJobSaved.CompanyJobSavedAdapter;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.Interfaces.ICompanyJobUpdate;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;

public class CompanyJobConfi extends CompanyDetailsBase implements ICompanyJobUpdate
{
    public CompanyJobConfi(RecyclerView.ViewHolder vh, ICompanyDetails listener, CompanyDetails detailObject)
    {
        super(vh, listener, detailObject);
    }

    public void Confi()
    {
        if(getJob().getRelJobs().getVisibility() == View.INVISIBLE || getJob().getRelJobs().getVisibility() == View.GONE)
        {
            getJob().getRcylJobSaved().setLayoutManager(new LinearLayoutManager(getContext()));
            getJob().getiBtnJobEdit().setOnClickListener(Edit);

            getJob().getRelJobs().setVisibility(View.VISIBLE);
            getJob().getBtnAddJobOffers().setOnClickListener(OpenJobFrag);

            ScrollToPositionDelayed(CompanyDetails.COMPANYJOBS);
            getBtnPost().setVisibility(View.GONE);;
        }
    }

    private View.OnClickListener OpenJobFrag = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            OpenJob();
        }
    };

    @Override
    protected void OnKeyPadDisappearing()
    {
        super.OnKeyPadDisappearing();
    }


    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            OpenJob();
        }
    };

    private void OpenJob()
    {
        ((IFragmentHandler) getContext()).onReplaceFragment(FragCompanyJobs.NewInstance(getListener(), getDetailObject(), this), IntFrag.ADD);
    }

    @Override
    public void OnCompanyUpdate(CompanyJobs items)
    {
        getJob().getRcylJobSaved().setAdapter(new CompanyJobSavedAdapter(items.getCompanyJobs() , getContext()));
        if(getJob().getCnstAddJobOffers().getVisibility() == View.VISIBLE)
        {
            getJob().getCnstAddJobOffers().setVisibility(View.GONE);
            getJob().getCnstJobSaved().setVisibility(View.VISIBLE);
        }
    }
}
