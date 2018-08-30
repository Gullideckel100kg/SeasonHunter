package gullideckel.seasonhunter.Objects.JobInformation;

import java.util.List;

public class JobInfoObject
{
   private CompanyInfoObject mCompanyInfo;
   private CompanyContactObject mCompanyContact;
   private List<JobOfferObject> mLstCompanyJobOffers;
   private CompanyAdditionalInfoObject mCompanyAdditionalInfo;

   public void SetCompanyInfo(CompanyInfoObject companyInfo)
   {
       mCompanyInfo = companyInfo;
   }

   public void SetCompanyContact(CompanyContactObject companyContact)
   {
       mCompanyContact = companyContact;
   }

   public void SetCompanyJobOffers(List<JobOfferObject> jobOffers)
   {
       mLstCompanyJobOffers = jobOffers;
   }

   public void SetCompanyAdditionalInfo(CompanyAdditionalInfoObject additionalInfo)
   {
       mCompanyAdditionalInfo = additionalInfo;
   }

    public CompanyInfoObject GetCompanyInfo()
    {
        return mCompanyInfo;
    }

    public CompanyContactObject GetCompanyContact()
    {
        return mCompanyContact;
    }

    public List<JobOfferObject> GetCompanyJobOffers()
    {
        return mLstCompanyJobOffers;
    }

    public CompanyAdditionalInfoObject GetCompanyAdditionalInfo()
    {
        return mCompanyAdditionalInfo;
    }
}
