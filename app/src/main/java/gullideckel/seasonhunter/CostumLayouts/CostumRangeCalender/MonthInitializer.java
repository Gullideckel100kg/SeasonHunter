package gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender;

import android.util.Log;
import android.widget.ListAdapter;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MonthInitializer
{
    private static final String TAG = "MonthInitializer";
    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    public List<CalenderObject> InitMonth(int mm, int yy)
    {
        int trailingSpaces = 0;
        int daysInPrevMonth = 0;
        int prevMonth = 0;
        int prevYear = 0;
        int nextMonth = 0;
        int nextYear = 0;
        int daysInMonth = 0;

        List<CalenderObject> list = new ArrayList<>();

        int currentMonth = mm - 1;
//        String currentMonthName = getMonthAsString(currentMonth);
        daysInMonth = daysOfMonth[currentMonth];

        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
        GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
        Log.d(TAG, "Gregorian Calendar:= " + cal.getTime().toString());

        if (currentMonth == 11)
        {
            prevMonth = currentMonth - 1;
            daysInPrevMonth = daysOfMonth[prevMonth];
            nextMonth = 0;
            prevYear = yy;
            nextYear = yy + 1;
            Log.d(TAG, "*->PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
        } else if (currentMonth == 0)
        {
            prevMonth = 11;
            prevYear = yy - 1;
            nextYear = yy;
            daysInPrevMonth = daysOfMonth[prevMonth];
            nextMonth = 1;
            Log.d(TAG, "**--> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
        } else
        {
            prevMonth = currentMonth - 1;
            nextMonth = currentMonth + 1;
            nextYear = yy;
            prevYear = yy;
            daysInPrevMonth = daysOfMonth[prevMonth];
            Log.d(TAG, "***---> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
        }

        // Compute how much to leave before before the first day of the
        // month.
        // getDay() returns 0 for Sunday.
        int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        trailingSpaces = currentWeekDay;

        if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 2)
        {
            daysInMonth++;
        }

        // Trailing Month days
        for (int i = 0; i < trailingSpaces; i++)
        {
            list.add(new CalenderObject((daysInPrevMonth - trailingSpaces + 1) + i, prevMonth, prevYear, true));
        }

        // Current Month Days
        for (int i = 1; i <= daysInMonth; i++)
        {
            list.add(new CalenderObject(i, currentMonth, yy, false));
        }

        // Leading Month days
        for (int i = 0; i < list.size() % 7; i++)
        {
            list.add(new CalenderObject(i + 1, nextMonth, nextYear, true));
        }

        return  list;
    }
}
