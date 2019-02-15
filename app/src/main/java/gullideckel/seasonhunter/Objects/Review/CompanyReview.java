package gullideckel.seasonhunter.Objects.Review;

import com.google.firebase.firestore.Exclude;

public class CompanyReview
{
    private String id;
    private String uid = "";
    private String companyId = "";
    private String companyPosition = "";
    private String companyTime = "";
    private Integer wage = -1;
    private CompanyWageHourly hourly;
    private CompanyWagePiece piece;
    private CompanyWageVolunteer volunteer;
    private Integer earnings = -1;
    private Integer atmosphere = -1;
    private Integer organisation = -1;
    private String comment = "";

    @Exclude
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }

    public String getCompanyPosition()
    {
        return companyPosition;
    }

    public void setCompanyPosition(String companyPosition)
    {
        this.companyPosition = companyPosition;
    }

    public String getCompanyTime()
    {
        return companyTime;
    }

    public void setCompanyTime(String companyTime)
    {
        this.companyTime = companyTime;
    }

    public Integer getWage()
    {
        return wage;
    }

    public void setWage(Integer wage)
    {
        this.wage = wage;
    }

    public CompanyWageHourly getHourly()
    {
        return hourly;
    }

    public void setHourly(CompanyWageHourly hourly)
    {
        this.hourly = hourly;
    }

    public CompanyWagePiece getPiece()
    {
        return piece;
    }

    public void setPiece(CompanyWagePiece piece)
    {
        this.piece = piece;
    }

    public CompanyWageVolunteer getVolunteer()
    {
        return volunteer;
    }

    public void setVolunteer(CompanyWageVolunteer volunteer)
    {
        this.volunteer = volunteer;
    }

    public Integer getEarnings()
    {
        return earnings;
    }

    public void setEarnings(Integer earnings)
    {
        this.earnings = earnings;
    }

    public Integer getAtmosphere()
    {
        return atmosphere;
    }

    public void setAtmosphere(Integer atmosphere)
    {
        this.atmosphere = atmosphere;
    }

    public Integer getOrganisation()
    {
        return organisation;
    }

    public void setOrganisation(Integer organisation)
    {
        this.organisation = organisation;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
