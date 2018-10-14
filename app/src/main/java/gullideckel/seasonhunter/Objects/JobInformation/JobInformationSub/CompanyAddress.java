package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

public class CompanyAddress
{
    private String mAddress;
    private String mCountry;
    private double mLatitude;
    private double mLongitude;


    public void SetAddress(String address)
    {
        mAddress = address;
    }

    public void SetCountry(String country)
    {
        mCountry = country;
    }

    public void SetLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    public void SetLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    public String GetAddress()
    {
        return mAddress;
    }

    public String GetCountry()
    {
        return mCountry;
    }

    public double GetLatitude()
    {
        return mLatitude;
    }

    public double GetLongitude()
    {
        return  mLongitude;
    }
}
