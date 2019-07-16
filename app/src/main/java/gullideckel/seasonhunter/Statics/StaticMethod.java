package gullideckel.seasonhunter.Statics;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gullideckel.seasonhunter.R;

public class StaticMethod
{
    private static final String TAG = "StaticMethod";

    private boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void RemoveKeyPad(Activity activity)
    {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void HideKeypadFrom(Context context, View view)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void ShowKeypadFrom(Context context, View view)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static String GPSConvert(double latitude, double longitude)
    {
        StringBuilder builder = new StringBuilder();

        if (latitude < 0) {
            builder.append("S ");
        } else {
            builder.append("N ");
        }

        String latitudeDegrees = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS);
        String[] latitudeSplit = latitudeDegrees.split(":");
        builder.append(latitudeSplit[0]);
        builder.append("°");
        builder.append(latitudeSplit[1]);
        builder.append("'");
        builder.append(latitudeSplit[2]);
        builder.append("\"");

        builder.append(" ");

        if (longitude < 0) {
            builder.append("W ");
        } else {
            builder.append("E ");
        }

        String longitudeDegrees = Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS);
        String[] longitudeSplit = longitudeDegrees.split(":");
        builder.append(longitudeSplit[0]);
        builder.append("°");
        builder.append(longitudeSplit[1]);
        builder.append("'");
        builder.append(longitudeSplit[2]);
        builder.append("\"");

        return builder.toString();
    }

    public static <T> T CastClass(Class<T> c, List<Object> items)
    {
        for (Object o: items)
        {
            if (c.isInstance(o))
            {
                T t = c.cast(o);
                return  t;
            }
        }
        return null;
    }


    public static <T> boolean ContainsInstance(Class<T> cl, List<Object> items)
    {
        for (Object o : items)
        {
            if (cl.isInstance(o)) {
                return true;
            }
        }
        return false;
    }


    public static void Toast(String message, Context context)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static BitmapDescriptor ResizeBitmap(Bitmap bmp, int width, int height)
    {
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bmp, width, height, false));
    }

    public static String StringBuilder(List<String> strings, String seperater)
    {
        StringBuilder b = new StringBuilder();
        b.append("");

        if(strings.size() > 0)
            b.append(strings.get(0));

        if(strings.size() > 1)
        {
            for(int i = 1; i < strings.size(); i++)
            {
                b.append(seperater);
                b.append(strings.get(i));
            }
        }

        return b.toString();
    }

    public static long getCountOfDays(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)
    {

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = startDay + " " + startMonth + " " + startYear;
        String inputString2 = endDay + " " + endMonth + " " + endYear;

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "getCountOfDays: ", e);
        }
        return 0;
    }

    public static String getDate(Date date, String pattern)
    {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }


    public static Date getMonthFromStringDate(String date, Context context)
    {
        String[] months = context.getResources().getStringArray(R.array.months);

        SimpleDateFormat format = new SimpleDateFormat("MMMM");

        for(String month : months)
        {
            if(date.contains(month))
            {
                try
                {
                    return format.parse(month.toLowerCase());
                } catch (ParseException e)
                {
                    Log.e(TAG, "getMonthFromStringDate: Could not parse the Month", e);;
                }
            }
        }
        return null;
    }

    public static Date getDateFromString(String dateStr)
    {
        StringBuilder b = new StringBuilder();

        String[] wN = new String[]{"1", "7", "14" , "21"};

        dateStr = dateStr.replace(" ", "").toLowerCase();

        if(dateStr.length() > 1)
        {
            if(Character.isDigit(dateStr.charAt(0)) && dateStr.charAt(1) == 'w')
            {
                if(dateStr.charAt(0) == '1')
                    dateStr = dateStr.replace(String.valueOf(dateStr.charAt(0)) + String.valueOf(dateStr.charAt(1)), wN[0]);
                else if(dateStr.charAt(0) == '2')
                    dateStr = dateStr.replace(String.valueOf(dateStr.charAt(0)) + String.valueOf(dateStr.charAt(1)), wN[1]);
                else if(dateStr.charAt(0) == '3')
                    dateStr = dateStr.replace(String.valueOf(dateStr.charAt(0)) + String.valueOf(dateStr.charAt(1)), wN[2]);
                else if(dateStr.charAt(0) == '4')
                    dateStr = dateStr.replace(String.valueOf(dateStr.charAt(0)) + String.valueOf(dateStr.charAt(1)), wN[3]);
            }
        }


        SimpleDateFormat format = new SimpleDateFormat("ddMMMM");

        try
        {
            return format.parse(dateStr);
        } catch (ParseException e)
        {
            SimpleDateFormat format1 = new SimpleDateFormat("MMMM");
            try
            {
                return format1.parse(dateStr);
            }catch (ParseException e1)
            {
                Log.e(TAG, "getDateFromString: Could not parse Date", e);
            }
        }

        return null;
    }

    public static Bitmap getMapResizedSnapshot(Bitmap bitmap)
    {
        if(bitmap != null)
        {
            Bitmap resizedBmp = Bitmap.createBitmap(bitmap,0, (int)(bitmap.getHeight()/2 - bitmap.getHeight() * 0.4),
                                                    bitmap.getWidth(), (int)(bitmap.getHeight() * 0.4));

            if(bitmap.getWidth() >= bitmap.getHeight())
                return Bitmap.createBitmap(bitmap,0, bitmap.getWidth()/4 - bitmap.getHeight()/4,bitmap.getHeight(), bitmap.getHeight());
            else
                return Bitmap.createBitmap(bitmap,0, bitmap.getHeight()/2 - bitmap.getWidth()/4,bitmap.getWidth(),bitmap.getWidth()/2);
        }
        else
            throw new NullPointerException("class:MySnapShop; First take a SnapShot then call getMapSnapshot");
    }

}
