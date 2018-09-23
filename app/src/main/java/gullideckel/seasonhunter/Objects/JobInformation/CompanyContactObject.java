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

    public void SetAddress(String address)
    {
        mStrAddress = address;
    }

    public void SetCountry(String country)
    {
        mStrCountry = country;
    }

    public void SetBuisnessNumber(String buisnessNumber)
    {
        mStrBuisnessNumber = buisnessNumber;
    }

    public void SetPhoneNumber(String phonenumber)
    {
        mStrPhoneNumber = phonenumber;
    }

    public void SetEmail(String email)
    {
        mStrEmail = email;
    }

    public void SetWebsite(String website)
    {
        mStrWebsite = website;
    }

    public void SetOnlineRecruitment(boolean isOnlineRecruitment)
    {
        mIsOnlineRecruitment = isOnlineRecruitment;
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
