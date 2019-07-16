package gullideckel.seasonhunter.CostumLayouts.LocationPicker;

import android.location.Address;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.Objects.Map.GeoMap;

public class PickerTask extends AsyncTask<GeoMap, Void, CompanyAddress>
{
    private static final String TAG = "PickerTask";

    private ICompanyAddress callback;

    public PickerTask(ICompanyAddress callback)
    {
        this.callback = callback;
    }

    @Override
    protected CompanyAddress doInBackground(GeoMap... geoMaps)
    {
        CompanyAddress address = new CompanyAddress();
        if (geoMaps != null)
        {
            try
            {
                List<Address> addresses = geoMaps[0].GetGeocoder().getFromLocation(geoMaps[0].GetLatLng().latitude, geoMaps[0].GetLatLng().longitude, 1);

                if (addresses.size() > 0)
                {
                    address.setAddress(addresses.get(0).getAddressLine(0));
                    address.setCountry(addresses.get(0).getCountryName());
                    address.setLatitude(addresses.get(0).getLatitude());
                    address.setLongitude(addresses.get(0).getLongitude());
                }
            } catch (IOException e)
            {
                Log.e(TAG, "doInBackground: ", e);
            }

        }
        return address;
    }

    @Override
    protected void onPostExecute(CompanyAddress address)
    {
        callback.OnCompanyAddress(address);
    }
}

