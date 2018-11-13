package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CostumLayoutManager;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsObject;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.ComplexCompanyDetailsAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobEdit.CompanyJobEditAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobEdit.CompanyJobEditViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobSaved.CompanyJobSavedAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyJobs;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class CompanyJobConfi extends CompanyDetailsBase
{
    private CompanyJobs items;
    private CostumLayoutManager layoutManager;
    private CompanyJobEditAdapter adapterEdit;
    private CompanyJobSavedAdapter adapterSaved;

    //TODO: A lot to do!!!
    public CompanyJobConfi(RecyclerView.ViewHolder vh, ICompanyDetails listener, CompanyDetailsObject companyDetails)
    {
        super(vh, listener, companyDetails);

        items = getObjectAtPosition(CompanyJobs.class);
    }

    public void Confi()
    {
        ScrollToPositionDelayed(ComplexCompanyDetailsAdapter.COMPANYJOBS);
        getLayoutManager().setScrollEnabled(false);

        items.getCompanyJobs().add(new CompanyJobs.CompanyJob());
        adapterEdit = new CompanyJobEditAdapter(items, getContext());

        layoutManager = new CostumLayoutManager(getContext());
        getJob().getRcylJobEdit().setLayoutManager(layoutManager);
        getJob().getRcylJobSaved().setLayoutManager(new LinearLayoutManager(getContext()));

        getJob().getiBtnAddJob().setOnClickListener(AddJob);
        getJob().getBtnJobSave().setOnClickListener(Save);
        getJob().getiBtnJobEdit().setOnClickListener(Edit);

        getJob().getRcylJobEdit().setAdapter(adapterEdit);
    }

    private View.OnClickListener Save = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(CorrectInput())
            {
                getLayoutManager().setScrollEnabled(true);
                adapterSaved = new CompanyJobSavedAdapter(items.getCompanyJobs(), getContext());
                getJob().getRcylJobSaved().setAdapter(adapterSaved);
                getJob().getCnstJobEdit().setVisibility(View.GONE);
                getJob().getCnstJobSaved().setVisibility(View.VISIBLE);
                getListener().OnItemUpdate(ComplexCompanyDetailsAdapter.COMPANYJOBS);
            }

        }
    };

    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            ScrollToPositionDelayed(ComplexCompanyDetailsAdapter.COMPANYJOBS);
            getLayoutManager().setScrollEnabled(false);

            getBtnPost().setVisibility(View.GONE);

            getJob().getCnstJobSaved().setVisibility(View.GONE);
            getJob().getCnstJobEdit().setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener AddJob = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            StaticMethod.RemoveKeyPad((Activity)getContext());
            items.getCompanyJobs().add(new CompanyJobs.CompanyJob());
            adapterEdit.notifyItemInserted(items.getCompanyJobs().size() - 1);
            getJob().getRcylJobEdit().scrollToPosition(adapterEdit.getItemCount() - 1);
            ScrollToPositionDelayed(ComplexCompanyDetailsAdapter.COMPANYJOBS);
        }
    };

    private boolean CorrectInput()
    {
        boolean isCorrect = true;

        for(int i = 0; i < items.getCompanyJobs().size(); i++)
        {
            CompanyJobEditViewHolder vh = (CompanyJobEditViewHolder) getJob().getRcylJobEdit().findViewHolderForAdapterPosition(i);

            if(items.getCompanyJobs().get(i).getJobTitle() == null || items.getCompanyJobs().get(i).getJobTitle().isEmpty())
            {
                vh.getEdtJobTitle().setHintTextColor(Color.RED);
                isCorrect = false;

            }

            if(items.getCompanyJobs().get(i).isVolunteering() == false
                    && items.getCompanyJobs().get(i).isPieceWork() == false
                    && items.getCompanyJobs().get(i).isHourlyPaid() == false)
            {
                vh.getTxtNoPayment().setVisibility(View.VISIBLE);
                isCorrect = false;
            }
            else
                vh.getTxtNoPayment().setVisibility(View.GONE);

            if(items.getCompanyJobs().get(i).getStartDay() == getContext().getString(R.string.day)
                    || items.getCompanyJobs().get(i).getStartMonth() == getContext().getString(R.string.month))
            {
                vh.getTxtSpinStartHeadline().setText(getContext().getText(R.string.start_date) + " " + getContext().getText(R.string.no_start_date));
                isCorrect = false;
            }
            else
                vh.getTxtSpinStartHeadline().setText(getContext().getText(R.string.start_date));

            if(items.getCompanyJobs().get(i).getEndDay() == getContext().getString(R.string.day)
                    || items.getCompanyJobs().get(i).getEndMonth() == getContext().getString(R.string.month))
            {
                vh.getTxtSpinEndHeadline().setText(getContext().getText(R.string.end_date) + " " + getContext().getText(R.string.no_end_date));
                isCorrect = false;
            }
            else
                vh.getTxtSpinStartHeadline().setText(getContext().getText(R.string.end_date));
        }
        return isCorrect;
    }
}
