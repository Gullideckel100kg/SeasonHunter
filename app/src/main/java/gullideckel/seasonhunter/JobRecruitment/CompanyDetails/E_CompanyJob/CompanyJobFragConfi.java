package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import gullideckel.seasonhunter.CostumLayouts.CostumLayoutManager;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobEdit.CompanyJobEditAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobEdit.CompanyJobEditViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyJobs;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class CompanyJobFragConfi extends CompanyDetailsBase
{
    private static final String TAG ="CompanyJobConfi";

    private CompanyJobs items;
    private CostumLayoutManager layoutManager;
    private CompanyJobEditAdapter adapterEdit;

    public CompanyJobFragConfi(Fragment fragment, ICompanyDetails listener, CompanyDetails companyDetails)
    {
        super(fragment, listener, companyDetails);
        items = getObjectAtPosition(CompanyJobs.class);
    }

    public void Confi()
    {
        items.getCompanyJobs().add(new CompanyJobs.CompanyJob());
        adapterEdit = new CompanyJobEditAdapter(items, getContext());

        layoutManager = new CostumLayoutManager(getContext());
        getJobFrag().getRcylJobEdit().setLayoutManager(layoutManager);

        getJobFrag().getiBtnAddJob().setOnClickListener(AddJob);
        getJobFrag().getBtnJobSave().setOnClickListener(Save);

        getJobFrag().getRcylJobEdit().setAdapter(adapterEdit);
    }


    private View.OnClickListener Save = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(CorrectInput())
            {
                StaticMethod.RemoveKeyPad((Activity) getContext());
                getJobFrag().OnUpdateJob(items);
                getJobFrag().getActivity().onBackPressed();
                getListener().OnItemUpdate(CompanyDetails.COMPANYBENEFITS);
            }
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
            ScrollToPosition(adapterEdit.getItemCount() - 1);
        }
    };


    private boolean CorrectInput()
    {
        boolean isCorrect = true;

        if(adapterEdit.GetViewHolders().size() != items.getCompanyJobs().size())
            Log.d(TAG, "CorrectInput: ViewHolders has to be the same size like items");

        for(int i = 0; i < items.getCompanyJobs().size(); i++)
        {
            CompanyJobEditViewHolder vh = adapterEdit.GetViewHolders().get(i);

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

            if(items.getCompanyJobs().get(i).getStartDay() == null || items.getCompanyJobs().get(i).getStartMonth() == null)
            {
                vh.getTxtSpinStartHeadline().setText(getContext().getText(R.string.start_date) + " " + getContext().getText(R.string.no_start_date));
                vh.getTxtSpinStartHeadline().setTextColor(Color.RED);
                isCorrect = false;
            }
            else
                vh.getTxtSpinStartHeadline().setText(getContext().getText(R.string.start_date));

            if(items.getCompanyJobs().get(i).getEndDay() == null || items.getCompanyJobs().get(i).getEndMonth() == null)
            {
                vh.getTxtSpinEndHeadline().setText(getContext().getText(R.string.end_date) + " " + getContext().getText(R.string.no_end_date));
                vh.getTxtSpinEndHeadline().setTextColor(Color.RED);
                isCorrect = false;
            }
            else
                vh.getTxtSpinEndHeadline().setText(getContext().getText(R.string.end_date));
        }
        return isCorrect;
    }
}
