package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

import java.util.ArrayList;
import java.util.List;

public class CompanyContact
{
    private List<String> mStrPhoneNumber = new ArrayList<>();
    private List<String> mStrEmail = new ArrayList<>();
    private String mStrWebsite;
    private boolean mIsOnlineRecruitment = false;


    public void SetPhoneNumber(String phonenumber)
    {
        mStrPhoneNumber.add(phonenumber);
    }

    public void SetEmail(String email)
    {
        mStrEmail.add(email);
    }

    public void SetWebsite(String website)
    {
        mStrWebsite = website;
    }

    public void SetOnlineRecruitment(boolean isOnlineRecruitment)
    {
        mIsOnlineRecruitment = isOnlineRecruitment;
    }

    public  List<String> GetPhoneNumber()
    {
        return mStrPhoneNumber;
    }

    public List<String> GetEmail()
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
