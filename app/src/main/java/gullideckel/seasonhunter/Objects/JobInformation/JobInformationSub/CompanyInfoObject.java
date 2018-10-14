package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

import java.util.List;

public class CompanyInfoObject
{
    private String mStrCompanyName;
    private List<String> mLstCompanyTypes;
    private boolean mIsOrganic;

    public CompanyInfoObject(String companyName, List<String> companyTypes, boolean isOrganic)
    {
        mStrCompanyName = companyName;
        mLstCompanyTypes = companyTypes;
        mIsOrganic = isOrganic;
    }

    public String GetCompanyName()
    {
        return  mStrCompanyName;
    }

    public List<String> GetCompanyTypes()
    {
        return mLstCompanyTypes;
    }

    public boolean GetOrganic()
    {
        return mIsOrganic;
    }
}
