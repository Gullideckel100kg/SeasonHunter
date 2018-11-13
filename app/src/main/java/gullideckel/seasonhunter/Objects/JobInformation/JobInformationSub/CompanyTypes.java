package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class CompanyTypes
{
    private List<CompanyType> companyTypes;

    public void setCompanyTypes(List<CompanyType> companyTypes)
    {
        this.companyTypes = companyTypes;
    }

    private int selectedCompanyType = -1;

    public int getSelectedCompanyType()
    {
        return selectedCompanyType;
    }

    public void setSelectedCompanyType(int selectedCompanyType)
    {
        this.selectedCompanyType = selectedCompanyType;
    }

    public List<CompanyType> getCompanyTypes()
    {
        return companyTypes;
    }

    public CompanyTypes()
    {
        companyTypes = new ArrayList<>();

    }

    public static class CompanyType
    {
        private String mCompanyType;
        private Bitmap mLogo;

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
}
