package gullideckel.seasonhunter.Objects.JobInformation;

import java.util.List;

public class CompanyContactObject
{
    private String mStrAddress;
    private String mStrCountry;
    private String mStrBuisnessNumber;
    private String mStrPhoneNumber;
    private String mStrEmail;
    private String mStrWebsite;
    private boolean mIsOnlineRecruitment;

    public CompanyContactObject(String address, String country, String buisnissNumber, String phoneNumber, String email, String website, boolean onlinceRecruitment)
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
}
