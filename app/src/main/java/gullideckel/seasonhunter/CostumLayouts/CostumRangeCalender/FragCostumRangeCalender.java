//TODO: Calender in ListView with scroll loading up and down and maximum loaded is 5 years
//TODO: Take the calculation for the calender out of

package gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import gullideckel.seasonhunter.Interfaces.ISelectedDate;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;


public class FragCostumRangeCalender extends Fragment implements View.OnClickListener, IClickedDate
{
    private static final String TAG = "FragCostumRangeCalender";

    private TextView txtslctDate;
    private TextView currentMonth;
    private ImageButton prevMonth;
    private ImageButton nextMonth;
    private GridView gridWeekDays;
    private GridView calendarView;
    private GridCellAdapter adapter;
    private Calendar calendar;
    private int month, year;
    private final DateFormat dateFormatter = new DateFormat();
    private static final String dateTemplate = "MMMM yyyy";

    private Button btnCancel;
    private Button btnDone;

    private CalenderObject firstClicked;
    private CalenderObject lastClicked;

    protected ISelectedDate listener;

    public static FragCostumRangeCalender newInstance(ISelectedDate listener)
    {
        FragCostumRangeCalender fragment = new FragCostumRangeCalender();
        fragment.listener = listener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_costum_range_calender, container, false);

        v.bringToFront();
        v.setBackgroundColor(Color.WHITE);
        calendar = Calendar.getInstance(Locale.getDefault());
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
        Log.d(TAG, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);

        txtslctDate = (TextView) v.findViewById(R.id.txtSlctDate);
        txtslctDate.setText(getContext().getString(R.string.cal_range_select_date_range));

        prevMonth = (ImageButton) v.findViewById(R.id.imbPrevMonth);
        prevMonth.setOnClickListener(this);

        currentMonth = (TextView) v.findViewById(R.id.txtCurrentMonth);
        currentMonth.setText(dateFormatter.format(dateTemplate, calendar.getTime()));

        nextMonth = (ImageButton) v.findViewById(R.id.imbNextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) v.findViewById(R.id.calendarRange);

        btnCancel = (Button) v.findViewById(R.id.btnCalenderCancel);
        btnDone = (Button) v.findViewById(R.id.btnCalenderDone);

        btnDone.setOnClickListener(Done);
        btnCancel.setOnClickListener(Cancel);

        // Initialised
        adapter = new GridCellAdapter(getContext(), month, year, txtslctDate, false,"", this);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);

        gridWeekDays = (GridView) v.findViewById(R.id.gridWeekdays);
        GridCellWeekDaysAdapter weekDaysAdapter = new GridCellWeekDaysAdapter(getContext());
        weekDaysAdapter.notifyDataSetChanged();
        gridWeekDays.setAdapter(weekDaysAdapter);

        return v;
    }


    private View.OnClickListener Done = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(firstClicked != null)
            {
                if(lastClicked != null)
                    listener.RecieveDates(firstClicked.getDate(), lastClicked.getDate());
                else
                    listener.RecieveDates(firstClicked.getDate(), null);
            }


            getActivity().onBackPressed();
        }
    };

    private View.OnClickListener Cancel = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            getActivity().onBackPressed();
        }
    };

    private void setGridCellAdapterToDate(int month, int year, boolean isPrevSlct, String prevClicked)
    {
        adapter = new GridCellAdapter(getContext(), month, year, txtslctDate, isPrevSlct, prevClicked, this);
        calendar.set(year, month - 1, calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(dateFormatter.format(dateTemplate, calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        if (v == prevMonth)
        {
            if (month <= 1)
            {
                month = 12;
                year--;
            }
            else
            {
                month--;
            }
            Log.d(TAG, "Setting Prev Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);


            setGridCellAdapterToDate(month, year , false, "");

            if(firstClicked != null && lastClicked != null)
            {
                final CalenderObject prevCal = firstClicked;
                final CalenderObject lastCal = lastClicked;
                calendarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout()
                    {
                        adapter.SetPreviousMonth(lastCal,  prevCal, month -1, year);
                        calendarView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        }
        if (v == nextMonth)
        {
            if (month > 11)
            {
                month = 1;
                year++;
            }
            else
            {
                month++;
            }
            Log.d(TAG, "Setting Next Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
            if(lastClicked == null && firstClicked != null)
                setGridCellAdapterToDate(month, year, true, StaticMethod.getDate(firstClicked.getDate(), "dd/MM/yyyy"));
            else
                setGridCellAdapterToDate(month, year, false, "");
                if(lastClicked != null && firstClicked != null)
                {
                    final CalenderObject prevCal = firstClicked;
                    final CalenderObject lastCal = lastClicked;
                    calendarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout()
                        {
                            adapter.SetNextMonth(lastCal, prevCal, month -1, year);
                            calendarView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }
        }

    }

    @Override
    public void RecieveDate(CalenderObject cal, boolean isLast)
    {
        if(isLast)
            lastClicked = cal;
        else
            firstClicked = cal;
    }
}
