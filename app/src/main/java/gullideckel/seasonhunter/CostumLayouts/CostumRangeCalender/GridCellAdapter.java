package gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gullideckel.seasonhunter.R;

public class GridCellAdapter extends BaseAdapter
{
    private static final String TAG = "GridCellAdapter";

    private final Context _context;

    private final List<CalenderObject> list;
    private Button gridcell;
    private CalenderCellMarking clickHandler;

    // Days in Current Month
    public GridCellAdapter(Context context, int month, int year, TextView txtSlctDate, boolean isPrevCal, String prevClicked, IClickedDate listener)
    {
        super();
        this._context = context;

        MonthInitializer monthInitializer = new MonthInitializer();
        list = monthInitializer.InitMonth(month, year);
        clickHandler = new CalenderCellMarking(list, txtSlctDate, context, isPrevCal, prevClicked, listener);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View cell = convertView;
        if (cell == null)
        {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.frag_costum_range_calender_cell, parent, false);
        }

        gridcell = (Button) cell.findViewById(R.id.btnCalenderDay);

        // Get a reference to the Day gridcell
        list.get(position).setBtnCell(gridcell);

        gridcell.setOnClickListener(clickHandler);

        // Set the Day GridCell
        gridcell.setText(list.get(position).getDay());
        gridcell.setTag(list.get(position));
        if (list.get(position).isTrailing())
        {
            gridcell.setEnabled(false);
            gridcell.setTextColor(Color.GRAY);
        }
        else
        {
            gridcell.setTextColor(Color.BLACK);
        }

        return cell;
    }

    public CalenderObject getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }


    public void SetPreviousMonth(CalenderObject lastSelection, CalenderObject firstSelection, int month, int year)
    {
        try
        {
            SimpleDateFormat firstDateFormat = new SimpleDateFormat("yyyyMM");

            Date firstDate = firstDateFormat.parse(String.valueOf(firstSelection.getYear() + String.valueOf(firstSelection.getMonth())));
            SimpleDateFormat actDateFormat = new SimpleDateFormat("yyyyMM");
            Date actDate = actDateFormat.parse(String.valueOf(year + String.valueOf(month)));

            if(firstSelection.getMonth() == month && firstSelection.getYear() == year)
            {
                for(CalenderObject calOb : list)
                {
                    if(calOb.getDate().equals(firstSelection.getDate()))
                    {
                        clickHandler.AddTextColor(list.indexOf(calOb), list.get(list.size() - 1));
                        clickHandler.SetTextSelection(firstSelection, lastSelection);
                    }
                }
            }
            else if(firstDate.before(actDate))
            {
                clickHandler.AddTextColor(0, list.get(list.size() - 1));
                clickHandler.SetTextSelection(firstSelection, lastSelection);
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
            Log.e(TAG, "SetPreviousMonth: Could not parse the Date. Calender will not work probably ", e);
        }
    }

    public void SetNextMonth(CalenderObject lastSelection, CalenderObject firstSelection, int month, int year)
    {
        try
        {

            SimpleDateFormat firstDateFormat = new SimpleDateFormat("yyyyMM");

            Date firstDate = firstDateFormat.parse(String.valueOf(lastSelection.getYear() + String.valueOf(lastSelection.getMonth())));
            SimpleDateFormat actDateFormat = new SimpleDateFormat("yyyyMM");
            Date actDate = actDateFormat.parse(String.valueOf(year + String.valueOf(month)));

            if(lastSelection.getMonth() == month && lastSelection.getYear() == year)
            {
                for(CalenderObject calOb : list)
                {
                    if(calOb.getDate().equals(lastSelection.getDate()))
                    {
                        clickHandler.AddTextColor(0, calOb);
                        clickHandler.SetTextSelection(firstSelection, lastSelection);
                    }
                }
            }
            else if(lastSelection.getMonth() > month)
            {
                clickHandler.AddTextColor(0, list.get(list.size() - 1));
                clickHandler.SetTextSelection(firstSelection, lastSelection);
            }

        } catch (ParseException e)
        {
            e.printStackTrace();
            Log.e(TAG, "SetPreviousMonth: Could not parse the Date. Calender will not work probably ", e);
        }
    }

}
