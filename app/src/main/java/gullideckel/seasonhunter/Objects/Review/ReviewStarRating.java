package gullideckel.seasonhunter.Objects.Review;

public class ReviewStarRating
{
    private int Earnings = - 1;
    private int Atmosphere = -1;
    private int Organisation = -1;


    public int getEarnings()
    {
        return Earnings;
    }

    public void setEarnings(int earnings)
    {
        Earnings = earnings;
    }

    public int getAtmosphere()
    {
        return Atmosphere;
    }

    public void setAtmosphere(int atmosphere)
    {
        Atmosphere = atmosphere;
    }

    public int getOrganisation()
    {
        return Organisation;
    }

    public void setOrganisation(int organisation)
    {
        Organisation = organisation;
    }
}
