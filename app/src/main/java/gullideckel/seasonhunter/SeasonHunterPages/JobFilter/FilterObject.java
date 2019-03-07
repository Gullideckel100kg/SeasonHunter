package gullideckel.seasonhunter.SeasonHunterPages.JobFilter;

import java.util.ArrayList;
import java.util.List;

public class FilterObject
{
    private String keywordJob = "";
    private List<String> types;
    private boolean isPhone;
    private boolean isEmail;
    private boolean isWebSite;
    private boolean isFacilities;
    private String keywordFacilities = "";
    private String startMonth = "";
    private String endMonth = "";

    public FilterObject()
    {
        types = new ArrayList<>();
    }

    public String getKeywordJob()
    {
        return keywordJob;
    }

    public void setKeywordJob(String keywordJob)
    {
        this.keywordJob = keywordJob;
    }

    public List<String> getTypes()
    {
        return types;
    }

    public void setTypes(List<String> types)
    {
        this.types = types;
    }

    public boolean isPhone()
    {
        return isPhone;
    }

    public void setPhone(boolean phone)
    {
        isPhone = phone;
    }

    public boolean isEmail()
    {
        return isEmail;
    }

    public void setEmail(boolean email)
    {
        isEmail = email;
    }

    public boolean isWebSite()
    {
        return isWebSite;
    }

    public void setWebSite(boolean webSite)
    {
        isWebSite = webSite;
    }

    public boolean isFacilities()
    {
        return isFacilities;
    }

    public void setFacilities(boolean facilities)
    {
        isFacilities = facilities;
    }

    public String getKeywordFacilities()
    {
        return keywordFacilities;
    }

    public void setKeywordFacilities(String keywordFacilities)
    {
        this.keywordFacilities = keywordFacilities;
    }


    public String getStartMonth()
    {
        return startMonth;
    }

    public void setStartMonth(String startMonth)
    {
        this.startMonth = startMonth;
    }

    public String getEndMonth()
    {
        return endMonth;
    }

    public void setEndMonth(String endMonth)
    {
        this.endMonth = endMonth;
    }
}
