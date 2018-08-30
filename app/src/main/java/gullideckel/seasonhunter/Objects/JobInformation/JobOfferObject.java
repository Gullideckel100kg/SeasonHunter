package gullideckel.seasonhunter.Objects.JobInformation;

public class JobOfferObject
{
    private String mStrJobName;
    private boolean mIsSeasonal;
    private String mStrSeasonStart;
    private String mStrSeasonEnd;
    private boolean mIsPaid;
    private String mStrVolunteerBenifits;
    private String mStrAdditionalInfo;

    public JobOfferObject(String jobName, boolean isSeasonal, String seasonStart, String seasonEnd, boolean isPaid, String volunteerBenifits, String additionalInfo)
    {
        mStrJobName = jobName;
        mIsSeasonal = isSeasonal;
        mStrSeasonStart = seasonStart;
        mStrSeasonEnd = seasonEnd;
        mIsPaid = isPaid;
        mStrVolunteerBenifits = volunteerBenifits;
        mStrAdditionalInfo = additionalInfo;
    }

    public JobOfferObject()
    {
    }


    public void SetJobName(String jobName)
    {
        mStrJobName = jobName;
    }

    public String GetJobName()
    {
        return mStrJobName;
    }

    public void SetSeasonal(boolean isSeasonal)
    {
        mIsSeasonal = isSeasonal;
    }

    public boolean GetSeasonal()
    {
        return  mIsSeasonal;
    }

    public void SetSeasonStart(String seasonStart)
    {
        mStrSeasonStart = seasonStart;
    }

    public String GetSeasonStart()
    {
        return mStrSeasonStart;
    }

    public void SetSeasonEnd(String seasonEnd)
    {
        mStrSeasonEnd = seasonEnd;
    }

    public String GetSeasonEnd()
    {
        return mStrSeasonEnd;
    }

    public boolean GetIsPaid()
    {
        return mIsPaid;
    }

    public String GetVolunteerBenefits()
    {
        return  mStrVolunteerBenifits;
    }

    public String GetAdditionalInfo()
    {
        return mStrAdditionalInfo;
    }
}
