package gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Job;

import android.util.Log;

public class DateConverter
{
    private static final String TAG = "DateConverter";

    //Important with every changes in the spinner springs this class has to be uptaded
    public static String ConvertToDate(int d, int m)
    {
        String month = ConvertedMonth(m);

        if(!month.isEmpty())
        {
            if(d == 1)
                return "1w " + month;
            else if (d == 2)
                return "2w " + month;
            else if (d == 3)
                return "3w " + month;
            else if (d == 4)
                return "4w " + month;
            else if (d == 5)
                return "1 " + month;
            else if (d == 6)
                return "2 " + month;
            else if (d == 7)
                return "3 " + month;
            else if (d == 8)
                return "4 " + month;
            else if (d == 9)
                return "5 " + month;
            else if (d == 10)
                return "6 " + month;
            else if (d == 11)
                return "7 " + month;
            else if (d == 12)
                return "8 " + month;
            else if (d == 13)
                return "9 " + month;
            else if (d == 14)
                return "10 " + month;
            else if (d == 15)
                return "11 " + month;
            else if (d == 16)
                return "12 " + month;
            else if (d == 17)
                return "13 " + month;
            else if (d == 18)
                return "14 " + month;
            else if (d == 19)
                return "15 " + month;
            else if (d == 20)
                return "16 " + month;
            else if (d == 21)
                return "17 " + month;
            else if (d == 22)
                return "18 " + month;
            else if (d == 23)
                return "19 " + month;
            else if (d == 24)
                return "20 " + month;
            else if (d == 25)
                return "21 " + month;
            else if (d == 26)
                return "22 " + month;
            else if (d == 27)
                return "23 " + month;
            else if (d == 28)
                return "24 " + month;
            else if (d == 29)
                return "25 " + month;
            else if (d == 30)
                return "26 " + month;
            else if (d == 31)
                return "27 " + month;
            else if (d == 32)
                return "28 " + month;
            else if (d == 33)
                return "29 " + month;
            else if (d == 34)
                return "30 " + month;
            else if (d == 35)
                return "31 " + month;
        }

        Log.wtf(TAG, "ConvertToDate: Could not convert date. Day: " + d + " Month: " + m   );
        return "";
    }

    private static String ConvertedMonth(int month)
    {
        if(month == 0)
            return "January";
        if(month == 1)
            return "February";
        if(month == 2)
            return "March";
        if(month == 3)
            return "April";
        if(month == 4)
            return "May";
        if(month == 5)
            return "June";
        if(month == 6)
            return "July";
        if(month ==7)
            return "August";
        if(month == 8)
            return "September";
        if(month == 9)
            return "October";
        if(month == 10)
            return "November";
        if(month == 11)
            return "December";

        return "";
    }
}
