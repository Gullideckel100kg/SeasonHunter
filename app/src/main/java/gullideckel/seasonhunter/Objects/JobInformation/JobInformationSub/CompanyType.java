package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

import android.graphics.Bitmap;

public class CompanyType
{
    private String mCompanyType;
    private Bitmap mLogo;

    public CompanyType(){}

    public CompanyType(Bitmap logo, String companyName)
    {
        mCompanyType = companyName;
        mLogo = logo;
    }

    public void SetCompanyTypeLogo(Bitmap logo)
    {
        mLogo = logo;
    }

    public Bitmap GetLogo()
    {
        return mLogo;
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
