package gullideckel.seasonhunter.Objects.JobInformation;

import java.util.List;

public class JobInfoObject
{
    private String mStrCompanyName;
    private List<String> mLstCompanyTypes;
    private boolean mIsOrganic;

    private String mStrAddress;
    private String mStrCountry;
    private String mStrBuisnessNumber;
    private String mStrPhoneNumber;
    private String mStrEmail;
    private String mStrWebsite;
    private boolean mIsOnlineRecruitment;

    private List<JobOfferObject> mLstJobOffers;

    private List<String> mLstProducts;
    private String mStrCompanyDescription;

    public void SetAdditionalInfo(List<String> products, String companyDescription)
    {
        mLstProducts = products;
        mStrCompanyDescription = companyDescription;
    }

    public List<String> GetProducts()
    {
        return mLstProducts;
    }

    public String GetCompanyDescription()
    {
        return  mStrCompanyDescription;
    }

    public void SetJobOffers(List<JobOfferObject> jobOffers)
    {
        mLstJobOffers = jobOffers;
    }

    public List<JobOfferObject> GetJobOffers()
    {
        return  mLstJobOffers;
    }

    public void SetCompanyContact(String address, String country, String buisnissNumber, String phoneNumber, String email, String website, boolean onlinceRecruitment)
    {
        mStrAddress = address;
        mStrCountry = country;
        mStrBuisnessNumber = buisnissNumber;
        mStrPhoneNumber = phoneNumber;
        mStrEmail = email;
        mStrWebsite = website;
        mIsOnlineRecruitment = onlinceRecruitment;
    }

    public String GetAddress()
    {
        return mStrAddress;
    }

    public String GetCountry()
    {
        return mStrCountry;
    }

    public String GetBuisnessNumber()
    {
        return mStrBuisnessNumber;
    }

    public  String GetPhoneNumber()
    {
        return mStrPhoneNumber;
    }

    public String GetEmail()
    {
        return mStrEmail;
    }

    public String GetWebsite()
    {
        return mStrWebsite;
    }

    public boolean GetOnlineRecruitment()
    {
        return mIsOnlineRecruitment;
    }

    public void SetCompanyInfo(String companyName, List<String> companyTypes, boolean isOrganic)
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
