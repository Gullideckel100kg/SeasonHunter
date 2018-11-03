package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CostumLayoutManager;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.ComplexCompanyDetailsAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;

public class CompanyJobConfi extends CompanyDetailsBase
{

    public CompanyJobConfi(RecyclerView.ViewHolder vh, Context context, ICompanyDetails listener, CostumLayoutManager layoutmanager)
    {
        super(vh, context, listener);
        layoutmanager.scrollToPosition(ComplexCompanyDetailsAdapter.COMPANYJOBS);
    }

    public void Confi()
    {

    }
}
