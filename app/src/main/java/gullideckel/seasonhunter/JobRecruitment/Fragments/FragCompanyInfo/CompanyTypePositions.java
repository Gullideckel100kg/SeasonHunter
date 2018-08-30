package gullideckel.seasonhunter.JobRecruitment.Fragments.FragCompanyInfo;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.Fragments.Adapters.AdapterAddedCompanyType;
import gullideckel.seasonhunter.JobRecruitment.Fragments.Adapters.AdapterCompanyType;
import gullideckel.seasonhunter.Objects.CompanyTypeObject;

public class CompanyTypePositions
{
    private boolean mIsCompanyChanged = false;

    //TODO: if I wanna have an animation I have to figure out how its gonna work without notifyDataSetChanged
    public void SetPositionCompanyType(CompanyTypeObject companyType, AdapterAddedCompanyType adapterAdded, AdapterCompanyType adapter, List<CompanyTypeObject> companyTypes, List<CompanyTypeObject> addedCompanyTypes, int position)
    {
        if(addedCompanyTypes.contains(companyType) && !mIsCompanyChanged)
        {
            addedCompanyTypes.remove(companyType);
            adapterAdded.notifyDataSetChanged();

            if(companyTypes.contains(companyType) && companyTypes.get(companyTypes.indexOf(companyType)).GetChecked())
            {
                companyTypes.get(companyTypes.indexOf(companyType)).SetCompany(companyType.GetCompanyType(), false);
                mIsCompanyChanged = true;
                adapter.notifyDataSetChanged();
            }
        }
        else if(position > addedCompanyTypes.size() - 1 && !mIsCompanyChanged)
        {
            addedCompanyTypes.add(companyType);
            adapterAdded.notifyDataSetChanged();
        }
        else if (!mIsCompanyChanged)
        {
            addedCompanyTypes.add(position, companyType);
            adapterAdded.notifyDataSetChanged();
        }
        else
            mIsCompanyChanged = false;
    }

}
