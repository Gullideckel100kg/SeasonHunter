package gullideckel.seasonhunter.Objects.Review;

public class ReviewWorkTime
{
    private int startDay = -1;
    private int startMonth = -1;
    private int startYear = -1;

    private int endDay = -1;
    private int endMonth = -1;
    private int endYear = -1;

    private long duration;

    public int getStartDay()
    {
        return startDay;
    }

    public void setStartDay(int startDay)
    {
        this.startDay = startDay;
    }

    public int getStartMonth()
    {
        return startMonth;
    }

    public void setStartMonth(int startMonth)
    {
        this.startMonth = startMonth;
    }

    public int getStartYear()
    {
        return startYear;
    }

    public void setStartYear(int startYear)
    {
        this.startYear = startYear;
    }

    public int getEndDay()
    {
        return endDay;
    }

    public void setEndDay(int endDay)
    {
        this.endDay = endDay;
    }

    public int getEndMonth()
    {
        return endMonth;
    }

    public void setEndMonth(int endMonth)
    {
        this.endMonth = endMonth;
    }

    public int getEndYear()
    {
        return endYear;
    }

    public void setEndYear(int endYear)
    {
        this.endYear = endYear;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }
}
