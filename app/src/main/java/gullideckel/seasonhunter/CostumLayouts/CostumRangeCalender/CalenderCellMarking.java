package gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class CalenderCellMarking implements View.OnClickListener
{
    private List<CalenderObject> list;
    private int firstMarked = -1;
    private int lastMarked = -1;
    private boolean isPrevClicked;
    private TextView slctDate;
    private Context context;
    private String firstSelection = "";
    private IClickedDate listener;

    public CalenderCellMarking(List<CalenderObject> list, TextView slctDate, Context context, boolean isPrevClicked, String prevClicked, IClickedDate listener)
    {
        this.list = list;
        this.slctDate = slctDate;
        this.context = context;
        this.listener = listener;
        this.isPrevClicked = isPrevClicked;
        if(isPrevClicked)
            firstSelection = prevClicked;
    }

    @Override
    public void onClick(View v)
    {
        CalenderObject cal = (CalenderObject) v.getTag();

        if(list.contains(cal))
        {
            if(firstMarked == -1 && !isPrevClicked || firstMarked > list.indexOf(cal) && !isPrevClicked)
            {
                RemoveTextColor();
                SetFirstMarked(cal);
            }
            else if(firstMarked < list.indexOf(cal) && !isPrevClicked)
            {
                RemoveTextColor();
                if(lastMarked == -1)
                {
                    AddTextColor(firstMarked, cal);
                    listener.RecieveDate(cal, true);
                }
                else
                    SetFirstMarked(cal);
            }
            else if(isPrevClicked)
            {
                isPrevClicked = false;
                AddTextColor(0, cal);
                listener.RecieveDate(cal, true);
            }

        }
    }

    private void RemoveTextColor()
    {
        for(CalenderObject cal : list)
        {
            if(cal != null)
                cal.getBtnCell().setBackgroundColor(Color.TRANSPARENT);
        }
        listener.RecieveDate(null, true);
        listener.RecieveDate(null, false);
        isPrevClicked = false;
    }

    public void AddTextColor(int indexOne, CalenderObject cal)
    {
        int indexTwo = list.indexOf(cal);
        if(list.size() > indexOne && list.size() > indexTwo && indexOne < indexTwo && indexOne > -1 && indexTwo > -1)
            for(int i = indexOne; i <= indexTwo; i++)
                list.get(i).getBtnCell().setBackground(ContextCompat.getDrawable(context, R.drawable.blue_round_background));
        lastMarked = indexTwo;
        SetTextLastSelected(cal);
    }

    private void SetFirstMarked(CalenderObject cal)
    {
        firstMarked = list.indexOf(cal);
        lastMarked = -1;
        listener.RecieveDate(cal, false);
        cal.getBtnCell().setBackground(ContextCompat.getDrawable(context, R.drawable.blue_round_background));
        SetTextFirstSelected(cal);
    }

    private void SetTextFirstSelected(CalenderObject cal)
    {
        firstSelection = StaticMethod.getDate(cal.getDate(), "dd/MM/yyyy");
        slctDate.setText(context.getResources().getString(R.string.cal_range_start_date) + " " + firstSelection +  "\n" +
                context.getResources().getString(R.string.cal_range_select_end_date));
    }

    private void SetTextLastSelected(CalenderObject cal)
    {
        slctDate.setText(context.getString(R.string.cal_range_start_date) + " " + firstSelection +  "\n" +
                context.getString(R.string.cal_range_end_date) + " " + StaticMethod.getDate(cal.getDate(), "dd/MM/yyyy"));
    }

    public void SetTextSelection(CalenderObject firstCal, CalenderObject lastCal)
    {
        slctDate.setText(context.getString(R.string.cal_range_start_date) + " " +
                StaticMethod.getDate(firstCal.getDate(), "dd/MM/yyyy") +  "\n" +
                context.getString(R.string.cal_range_end_date) + " " +
                StaticMethod.getDate(lastCal.getDate(), "dd/MM/yyyy"));
    }
}
