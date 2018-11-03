package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName.CompanyNameViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact.CompanyContactViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;

public class CompanyDetailsBase
{
    private RecyclerView.ViewHolder vh;
    private Context context;
    private ICompanyDetails listener;

    public CompanyDetailsBase(RecyclerView.ViewHolder vh, Context context, ICompanyDetails listener)
    {
        this.vh  = vh;
        this.context = context;
        this.listener = listener;
    }

    public Context getContext()
    {
        return context;
    }

    public CompanyNameViewHolder getName()
    {
        return (CompanyNameViewHolder) vh;
    }

    public CompanyTypeViewHolder getType()
    {
        return (CompanyTypeViewHolder) vh;
    }

    public CompanyAddressViewHolder getAddress()
    {
        return (CompanyAddressViewHolder) vh;
    }

    public CompanyContactViewHolder getContact()
    {
        return (CompanyContactViewHolder) vh;
    }

    public CompanyJobViewHolder getJob()
    {
        return  (CompanyJobViewHolder) vh;
    }

    public ICompanyDetails getListener()
    {
        return listener;
    }
}
