package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.GeoCoding;

import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.R;

public class AddressTask extends AsyncTask<GeoMap, Void, CompanyAddress>
{
    private Context mContext;
    private ICompanyAddress mCallback;
    public  static boolean ISTASKFINISH = false;

    public AddressTask(ICompanyAddress updateAddress, Context context)
    {
        mCallback = updateAddress;
        mContext = context;
    }

    @Override
    protected CompanyAddress doInBackground(GeoMap... geoMaps)
    {
        Address address = new Address(Locale.getDefault());
        if(geoMaps != null)
        {
            try
            {
                List<Address> addresses = geoMaps[0].GetGeocoder().getFromLocation(geoMaps[0].GetLatLng().latitude, geoMaps[0].GetLatLng().longitude, 1);
                CompanyAddress currentAddress = new CompanyAddress();
                if(addresses.size() > 0)
                {
                    currentAddress.SetAddress(addresses.get(0).getAddressLine(0));
                    currentAddress.SetCountry(addresses.get(0).getCountryName());
                    currentAddress.SetLatitude(addresses.get(0).getLatitude());
                    currentAddress.SetLongitude(addresses.get(0).getLongitude());
                    return currentAddress;
                }
                else
                {
                    currentAddress.SetAddress(mContext.getString(R.string.no_address_found));
                    currentAddress.SetCountry("");
                    currentAddress.SetLatitude(geoMaps[0].GetLatLng().latitude);
                    currentAddress.SetLongitude(geoMaps[0].GetLatLng().longitude);
                    return currentAddress;
                }

            } catch (IOException e)
            {
                System.out.println("Class: AddressTask " + e.getMessage());
            }
        }

        return null;
    }



    @Override
    protected void onPostExecute(CompanyAddress address)
    {
        super.onPostExecute(address);
        mCallback.OnCompanyAddress(address);
        ISTASKFINISH = true;
    }
}


