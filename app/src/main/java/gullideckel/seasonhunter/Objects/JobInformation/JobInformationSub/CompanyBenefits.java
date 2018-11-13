package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.F_CompanyBenefits.CompanyBenefitsViewHolder;

public class CompanyBenefits
{
    private List<CompanyBenefit> companyBenefits;
    private List<CompanyBenefit> companyBenefitsSaved;

    public void setCompanyBenefitsSaved(List<CompanyBenefit> companyBenefitsSaved)
    {
        this.companyBenefitsSaved = companyBenefitsSaved;
    }

    public List<CompanyBenefit> getCompanyBenefitsSaved()

    {
        return companyBenefitsSaved;
    }

    public  CompanyBenefits()
    {
        companyBenefits = new ArrayList<>();
        companyBenefitsSaved = new ArrayList<>();
    }

    public List<CompanyBenefit> getCompanyBenefits()
    {
        return companyBenefits;
    }

    public void setCompanyBenefits(List<CompanyBenefit> companyBenefits)
    {
        this.companyBenefits = companyBenefits;
    }

    public static class CompanyBenefit
    {
        private Bitmap benefitLogo;
        private String benefit;
        private boolean isSelected;

        public void setSelected(boolean selected)
        {
            isSelected = selected;
        }


        public boolean isSelected()
        {
            return isSelected;
        }


        public Bitmap getBenefitLogo()
        {
            return benefitLogo;
        }

        public void setBenefitLogo(Bitmap benefitLogo)
        {
            this.benefitLogo = benefitLogo;
        }

        public String getBenefit()
        {
            return benefit;
        }

        public void setBenefit(String benefit)
        {
            this.benefit = benefit;
        }
    }
}
