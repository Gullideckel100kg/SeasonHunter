package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

import java.util.List;

import gullideckel.seasonhunter.Objects.CompanyType.CompanyTypeObject;

public class CompanyType
{
    private List<CompanyTypeObject> mCompanyTypes;
    private String mCompanyType;

    public CompanyType(List<CompanyTypeObject> companyTypes)
    {
        mCompanyTypes = companyTypes;
    }

    public List<CompanyTypeObject> GetCompanyTypes()
    {
        return  mCompanyTypes;
    }

    public void SetCompanyType(String companyType)
    {
        mCompanyType = companyType;
    }

    public String GetCompanyType()
    {
        return mCompanyType;
    }
}
