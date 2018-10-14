package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyType;
import gullideckel.seasonhunter.Objects.CompanyType.CompanyTypeObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;

public class CompanyTypeConfi
{
    private CompanyTypeViewHolder mHolder;
    private ICompanyType mListener;
    private List<CompanyTypeObject> mItmes;

    public CompanyTypeConfi(CompanyTypeViewHolder holder, ICompanyType listener, List<CompanyTypeObject> items)
    {
        mHolder = holder;
        mListener = listener;
        mItmes = items;
    }

    public void Confi()
    {
//        mHolder.GetLstCompanyType().setAdapter();
    }
}
