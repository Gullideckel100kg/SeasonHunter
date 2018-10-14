package gullideckel.seasonhunter.Objects.CompanyType;

import android.graphics.Bitmap;

public class CompanyTypeObject
{
    private Bitmap mLogo;
    private String mCompanyType;

    public CompanyTypeObject (Bitmap logo, String companyType)
    {
        mLogo = logo;
        mCompanyType = companyType;
    }

    public Bitmap GetLogo()
    {
        return mLogo;
    }

    public String GetCompanyType()
    {
        return mCompanyType;
    }
}
