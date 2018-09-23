package gullideckel.seasonhunter.ActivityMap.GeoCoding;

public class CurrentAddress
{
    private String mAddressLine;
    private String mCountry;
    private double mLatitude;
    private double mLongitude;


    public void SetAddressLine(String address)
    {
        mAddressLine = address;
    }

    public void SetCountry(String country) {mCountry = country;}

    public void SetLatitude(double latitude) {mLatitude = latitude;}

    public  void SetLongitude (double longitude) {mLongitude = longitude;}

    public String GetAddressLine()
    {
        return mAddressLine;
    }

    public String GetCountry() {return  mCountry;}

    public double GetLatitude()
    {
        return mLatitude;
    }

    public  double GetLongitude()
    {
        return mLongitude;
    }
}
