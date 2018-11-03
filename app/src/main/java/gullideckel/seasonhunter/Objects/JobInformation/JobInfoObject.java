package gullideckel.seasonhunter.Objects.JobInformation;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAdditionalInfoObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyInfoObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.JobOfferObject;

public class JobInfoObject
{
    private CompanyName mCompanyName;
   private CompanyInfoObject mCompanyInfo;
   private CompanyContact mCompanyContact;
   private List<JobOfferObject> mLstCompanyJobOffers;
   private CompanyAdditionalInfoObject mCompanyAdditionalInfo;
   private CompanyAddress mCompanyAddress;

    public JobInfoObject()
    {
        mCompanyName = new CompanyName();
        mCompanyAddress = new CompanyAddress();
    }

    public CompanyName GetCompanyName()
    {
        return mCompanyName;
    }

    public CompanyAddress GetCompanyAddress()
    {
        return mCompanyAddress;
    }


    public void SetCompanyInfo(CompanyInfoObject companyInfo)
   {
       mCompanyInfo = companyInfo;
   }

    public void SetCompanyContact(CompanyContact companyContact)
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

    public CompanyContact GetCompanyContact()
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
