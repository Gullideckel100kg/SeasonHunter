package gullideckel.seasonhunter.Objects.JobInformation;

import java.util.ArrayList;
import java.util.List;

public class CompanyContact
{
    private List<String> phoneNumber = new ArrayList<>();
    private List<String> email = new ArrayList<>();
    private String website;
    private boolean onlineRecruitment = false;

    public List<String> getPhoneNumber()
    {
        return phoneNumber;
    }

    public List<String> getEmail()
    {
        return email;
    }

    public String getWebsite()
    {
        return website;
    }

    public boolean isOnlineRecruitment()
    {
        return onlineRecruitment;
    }

    public void setPhoneNumber(List<String> phoneNumber)
    {

        this.phoneNumber = phoneNumber;
    }

    public void setEmail(List<String> email)
    {
        this.email = email;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public void setOnlineRecruitment(boolean onlineRecruitment)
    {
        this.onlineRecruitment = onlineRecruitment;
    }
}
