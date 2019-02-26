package gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import gullideckel.seasonhunter.R;

public class GridCellWeekDaysAdapter extends BaseAdapter
{
    private String[] weekDays;
    private Context context;

    public GridCellWeekDaysAdapter(Context context)
    {
        weekDays = context.getResources().getStringArray(R.array.weekdays_shortcuts);
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return weekDays.length;
    }

    @Override
    public Object getItem(int position)
    {
        return weekDays[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View cell = convertView;
        if (cell == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.frag_costum_range_calender_weekday_cell, parent, false);
        }

        TextView txtWeekDay = (TextView) cell.findViewById(R.id.txtCellWeekday);

        txtWeekDay.setText(weekDays[position]);

        return cell;
    }
}
