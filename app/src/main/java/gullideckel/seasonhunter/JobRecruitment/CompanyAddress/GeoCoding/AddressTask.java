package gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding;

import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import gullideckel.seasonhunter.Interfaces.IUpdateAddress;
import gullideckel.seasonhunter.R;

public class AddressTask extends AsyncTask<GeoMap, Void, CurrentAddress>
{
    private Context mContext;
    private IUpdateAddress mCallback;
    public  static boolean ISTASKFINISH = false;

    public AddressTask(IUpdateAddress updateAddress, Context context)
    {
        mCallback = updateAddress;
        mContext = context;
    }

    @Override
    protected CurrentAddress doInBackground(GeoMap... geoMaps)
    {
        Address address = new Address(Locale.getDefault());
        if(geoMaps != null)
        {
            try
            {
                List<Address> addresses = geoMaps[0].GetGeocoder().getFromLocation(geoMaps[0].GetLatLng().latitude, geoMaps[0].GetLatLng().longitude, 1);
                CurrentAddress currentAddress = new CurrentAddress();
                if(addresses.size() > 0)
                {
                    currentAddress.SetAddressLine(addresses.get(0).getAddressLine(0));
                    currentAddress.SetCountry(addresses.get(0).getCountryName());
                    currentAddress.SetLatitude(addresses.get(0).getLatitude());
                    currentAddress.SetLongitude(addresses.get(0).getLongitude());
                    return currentAddress;
                }
                else
                {
                    currentAddress.SetAddressLine(mContext.getString(R.string.no_address_found));
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
    protected void onPostExecute(CurrentAddress address)
    {
        super.onPostExecute(address);
        mCallback.UpdatedAddress(address);
        ISTASKFINISH = true;
    }
}


