package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Review;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import gullideckel.seasonhunter.Interfaces.IWorkTime;
import gullideckel.seasonhunter.Objects.Review.ReviewWorkTime;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;


public class FragWorkTimeCalender extends Fragment
{
    private LinearLayout linStartDate;
    private LinearLayout linEndDate;
    private TextView txtStartDate;
    private TextView txtEndDate;
    private TextView txtDuration;
    private DatePicker datePicker;
    private Button btnCancel;
    private Button btnDone;

    private boolean isStartDate = true;
    private boolean isEndDate = false;

    private ReviewWorkTime workTime;
    private IWorkTime listener;


    public static FragWorkTimeCalender newInstance(IWorkTime listener)
    {
        FragWorkTimeCalender fragment = new FragWorkTimeCalender();
        fragment.listener = listener;
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_work_time_calender, container, false);

        v.bringToFront();
        v.setBackgroundColor(Color.GRAY);

        linStartDate = (LinearLayout) v.findViewById(R.id.linCalStartDate);
        linEndDate = (LinearLayout) v.findViewById(R.id.linCalEndDate);
        txtStartDate = (TextView) v.findViewById(R.id.txtCalStartDate);
        txtEndDate = (TextView) v.findViewById(R.id.txtCalEndDate);
        txtDuration = (TextView) v.findViewById(R.id.txtCalDuration);
        datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        btnCancel = (Button) v.findViewById(R.id.btnCalCancel);
        btnDone = (Button) v.findViewById(R.id.btnCalDone);

        workTime = new ReviewWorkTime();

        linStartDate.bringToFront();
        linEndDate.bringToFront();
        txtDuration.setText(getContext().getText(R.string.cal_duration));

        InitDatePicker();

        linStartDate.setOnClickListener(OnStartDate);
        linEndDate.setOnClickListener(OnEndDate);

        btnCancel.setOnClickListener(Cancel);
        btnDone.setOnClickListener(Done);

        return v;
    }

    private void InitDatePicker()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                if(isStartDate)
                {
                    txtStartDate.setText(dayOfMonth + " / " + (month + 1) + " / " + year);
                    workTime.setStartDay(dayOfMonth);
                    workTime.setStartMonth(month + 1);
                    workTime.setStartYear(year);
                    if(workTime.getEndDay() != -1 && workTime.getEndMonth() != -1 && workTime.getEndYear() != -1)
                    {
                        workTime.setDuration(StaticMethod.getCountOfDays(dayOfMonth, month + 1, year, workTime.getEndDay(), workTime.getEndMonth(), workTime.getEndYear()));
                        txtDuration.setText(getContext().getText(R.string.cal_duration) + " " + workTime.getDuration() + " " + getContext().getText(R.string.cal_duration_days));
                    }
                }

                if(isEndDate)
                {
                    txtEndDate.setText(dayOfMonth + " / " + (month + 1) + " / " + year);
                    workTime.setEndDay(dayOfMonth);
                    workTime.setEndMonth(month + 1);
                    workTime.setEndYear(year);
                    if (workTime.getStartDay() != -1 && workTime.getStartMonth() != -1 && workTime.getStartYear() != -1)
                    {
                        workTime.setDuration(StaticMethod.getCountOfDays(workTime.getStartDay(), workTime.getStartMonth(), workTime.getStartYear(), dayOfMonth, month + 1, year));
                        txtDuration.setText(getContext().getText(R.string.cal_duration) + " " + workTime.getDuration() + " " + getContext().getText(R.string.cal_duration_days));
                    }
                }
            }
        });

        txtStartDate.setText(datePicker.getDayOfMonth() + " / " + (datePicker.getMonth() + 1) + " / " + datePicker.getYear());
        workTime.setStartDay(datePicker.getDayOfMonth());
        workTime.setStartMonth(datePicker.getMonth() + 1);
        workTime.setStartYear(datePicker.getYear());
    }

    private View.OnClickListener OnStartDate = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            SetStartDateView();
        }
    };

    private View.OnClickListener OnEndDate = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            SetEndDateView();
        }
    };

    private void SetStartDateView()
    {
        isStartDate = true;
        isEndDate = false;
        linStartDate.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.costum_border_calender, null));
        linEndDate.setBackground(null);
    }

    private void SetEndDateView()
    {
        isStartDate = false;
        isEndDate = true;
        linEndDate.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.costum_border_calender, null));
        linStartDate.setBackground(null);
    }

    private View.OnClickListener Cancel = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            getActivity().onBackPressed();
        }
    };

    private View.OnClickListener Done = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(isStartDate)
            {
                if(workTime.getEndDay() == -1 && workTime.getEndMonth() == -1 && workTime.getEndYear() == -1)
                    SetEndDateView();
                else
                    SendWorkTime();
            }
            else
                SendWorkTime();
        }
    };

    private void SendWorkTime()
    {
        if(workTime.getDuration() > 0)
        {
            listener.RevieveWorkTime(workTime);
            getActivity().onBackPressed();
        }
        else
            StaticMethod.Toast(getContext().getString(R.string.cal_duration_warning), getContext());

    }


}
