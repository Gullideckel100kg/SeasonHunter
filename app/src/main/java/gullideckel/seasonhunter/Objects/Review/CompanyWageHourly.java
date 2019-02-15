package gullideckel.seasonhunter.Objects.Review;

public class CompanyWageHourly
{
    private String hourlyPay;
    private String hourlyPayOvertime;
    private String hourlyPayNightshift;
    private String bonus;

    public String getHourlyPay()
    {
        return hourlyPay;
    }

    public void setHourlyPay(String hourlyPay)
    {
        this.hourlyPay = hourlyPay;
    }

    public String getHourlyPayOvertime()
    {
        return hourlyPayOvertime;
    }

    public void setHourlyPayOvertime(String hourlyPayOvertime)
    {
        this.hourlyPayOvertime = hourlyPayOvertime;
    }

    public String getHourlyPayNightshift()
    {
        return hourlyPayNightshift;
    }

    public void setHourlyPayNightshift(String hourlyPayNightshift)
    {
        this.hourlyPayNightshift = hourlyPayNightshift;
    }

    public String getBonus()
    {
        return bonus;
    }

    public void setBonus(String bonus)
    {
        this.bonus = bonus;
    }
}
