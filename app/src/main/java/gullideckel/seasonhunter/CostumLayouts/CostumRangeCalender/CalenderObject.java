package gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender;

import android.widget.Button;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalenderObject
{
    private Date date;
    private boolean isTrailing;
    private Button btnCell;

    public CalenderObject(int day, int month, int year, boolean isTrailing)
    {
        this.date = new GregorianCalendar(year, month, day).getTime();
        this.isTrailing = isTrailing;
    }

    public Date getDate()
    {
        return date;
    }

    public boolean isTrailing()
    {
        return isTrailing;
    }

    public String getDay()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    public int getYear()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public int getMonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public Button getBtnCell()
    {
        return btnCell;
    }

    public void setBtnCell(Button btnCell)
    {
        this.btnCell = btnCell;
    }
}
