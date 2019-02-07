package gullideckel.seasonhunter.Objects.JobInformation;

import android.graphics.Bitmap;

import com.google.firebase.firestore.Exclude;

import java.lang.annotation.Documented;
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
        private String companyType;
        private Bitmap logo;

        public CompanyType(Bitmap logo, String companyType)
        {
            this.companyType = companyType;
            this.logo = logo;
        }

        public String getCompanyType()
        {
            return companyType;
        }

        public void setCompanyType(String companyType)
        {
            this.companyType = companyType;
        }

        @Exclude
        public Bitmap getLogo()
        {
            return logo;
        }

        public void setLogo(Bitmap logo)
        {
            this.logo = logo;
        }
    }
}
