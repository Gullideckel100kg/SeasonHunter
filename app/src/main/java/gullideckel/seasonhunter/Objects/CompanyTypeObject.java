package gullideckel.seasonhunter.Objects;

public class CompanyTypeObject
{
    private String mCompanyType;
    private boolean mIsChecked;

    public CompanyTypeObject(String companyType, boolean isChecked)
    {
        mCompanyType = companyType;
        mIsChecked = isChecked;
    }

    public void SetCompany(String companyType, boolean isChecked)
    {
        mCompanyType = companyType;
        mIsChecked = isChecked;
    }

    public String GetCompanyType()
    {
        return mCompanyType;
    }

    public boolean GetChecked()
    {
        return  mIsChecked;
    }

    public void SetChecked(boolean isChecked)
    {
        mIsChecked = isChecked;
    }
}
