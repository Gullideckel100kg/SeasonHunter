package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Job;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Job.DateConverter;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class JobBottom
{
    private static final String TAG = "JobBottom";

    private LayoutInflater inflater;
    private Context context;

    private EditText edtTitle;
    private Spinner spinStartDay;
    private Spinner spinStartMonth;
    private Spinner spinEndDay;
    private Spinner spinEndMonth;
    private EditText edtNotes;

    private TextView txtStartDate;
    private TextView txtEndDate;

    ArrayAdapter<CharSequence> adapterDay30;
    ArrayAdapter<CharSequence> adapterDay31;
    ArrayAdapter<CharSequence> adapterDay28;

    public JobBottom(LayoutInflater inflater, Context context)
    {
        this.inflater = inflater;
        this.context = context;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_job_bottom, null);

        edtTitle = (EditText) v.findViewById(R.id.edtEditJobTitle);
        spinStartDay = (Spinner) v.findViewById(R.id.spinEditStartDay);
        spinStartMonth = (Spinner) v.findViewById(R.id.spinEditStartMonth);
        spinEndDay = (Spinner) v.findViewById(R.id.spinEditEndDay);
        spinEndMonth = (Spinner) v.findViewById(R.id.spinEditEndMonth);

        txtStartDate = (TextView) v.findViewById(R.id.txtEditStartDateBottom);
        txtEndDate = (TextView) v.findViewById(R.id.txtEditEndDateBottom);

        edtNotes = (EditText) v.findViewById(R.id.edtEditNotes);

        adapterDay30 = ArrayAdapter.createFromResource(context, R.array.days30, android.R.layout.simple_spinner_item);
        adapterDay30.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterDay28 = ArrayAdapter.createFromResource(context, R.array.days28, android.R.layout.simple_spinner_item);
        adapterDay28.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterDay31 = ArrayAdapter.createFromResource(context, R.array.days31, android.R.layout.simple_spinner_item);
        adapterDay31.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinStartDay.setAdapter(adapterDay31);
        spinEndDay.setAdapter(adapterDay31);

        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(context, R.array.months, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinStartMonth.setAdapter(adapterMonth);
        spinEndMonth.setAdapter(adapterMonth);
        spinStartMonth.setOnItemSelectedListener(MonthStart);
        spinEndMonth.setOnItemSelectedListener(MonthEnd);

        return v;
    }



    private AdapterView.OnItemSelectedListener MonthStart = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            MonthDayCorrection(spinStartDay, position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }
    };

    private AdapterView.OnItemSelectedListener MonthEnd = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            MonthDayCorrection(spinEndDay, position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }
    };

    public void SetJobEdit(CompanyJobs.CompanyJob job)
    {
        edtTitle.setText(job.getJobTitle());
        try
        {
            spinStartDay.setSelection(GetDay(job.getStartDate()));
            spinStartMonth.setSelection(GetMonth(job.getStartDate()));
            spinEndDay.setSelection(GetDay(job.getEndDate()));
            spinEndMonth.setSelection(GetMonth(job.getEndDate()));
        }catch (ArrayIndexOutOfBoundsException e)
        {
            Log.e(TAG, "SetJobEdit: ", e);
        }

        edtNotes.setText(job.getNotes());
    }

    private int GetDay(String day)
    {
        if(day.contains("1w"))
            return 1;
        else if(day.contains("2w"))
            return 2;
        else if(day.contains("3w"))
            return 3;
        else if(day.contains("4w"))
            return 4;

        day = day.replace(" ","");

        if(Character.isDigit(day.charAt(0)))
        {
            int d = Character.getNumericValue(day.charAt(0));

            return (d + 4);
        }

        return 0;
    }

    private int GetMonth(String month)
    {
        String[] months = context.getResources().getStringArray(R.array.months);
        for(int i = 0; i < months.length; i++)
            if(month.contains(months[i]))
                return i;

        return 0;
    }


    private void MonthDayCorrection(Spinner spinner, int position)
    {
        int selectedItem = spinner.getSelectedItemPosition();

        if(position == 0)
            spinner.setAdapter(adapterDay31);
        else if(position == 1)
            spinner.setAdapter(adapterDay28);
        else if(position == 2)
            spinner.setAdapter(adapterDay31);
        else if(position == 3)
            spinner.setAdapter(adapterDay30);
        else if(position == 4)
            spinner.setAdapter(adapterDay31);
        else if(position == 5)
            spinner.setAdapter(adapterDay30);
        else if(position == 6)
            spinner.setAdapter(adapterDay31);
        else if(position == 7)
            spinner.setAdapter(adapterDay31);
        else if(position == 8)
            spinner.setAdapter(adapterDay30);
        else if(position == 9)
            spinner.setAdapter(adapterDay31);
        else if(position == 10)
            spinner.setAdapter(adapterDay30);
        else if(position == 11)
            spinner.setAdapter(adapterDay31);

        if(spinner.getAdapter().getCount() > selectedItem)
            spinner.setSelection(selectedItem);
    }

    public CompanyJobs.CompanyJob getJob()
    {
        CompanyJobs.CompanyJob job = new CompanyJobs.CompanyJob();

        job.setJobTitle(edtTitle.getText().toString());
        job.setStartDate(DateConverter.ConvertToDate(spinStartDay.getSelectedItemPosition(), spinStartMonth.getSelectedItemPosition()));
        job.setEndDate(DateConverter.ConvertToDate(spinEndDay.getSelectedItemPosition(), spinEndMonth.getSelectedItemPosition()));
        job.setFullDate(context.getString(R.string.new_start) + " " + spinStartDay.getSelectedItem().toString() + " " +
                context.getString(R.string.new_of) + " " + spinStartMonth.getSelectedItem().toString() + "\n" +
                context.getString(R.string.new_end) + " " + spinEndDay.getSelectedItem().toString() +
                context.getString(R.string.new_of) + spinEndMonth.getSelectedItem().toString());


        if(!edtNotes.getText().toString().isEmpty())
            job.setNotes(edtNotes.getText().toString());
        return job;
    }

    //TODO:The check of Job is double. The date converter is double. In future make it better
    public boolean isCorrect()
    {
        boolean is = true;

        if(edtTitle.getText().toString().isEmpty())
        {
            edtTitle.setHint(context.getString(R.string.new_job_title));
            edtTitle.setHintTextColor(Color.RED);
            is = false;
        }

        if(spinStartDay.getSelectedItemPosition() == 0)
        {
            txtStartDate.setTextColor(Color.RED);
            is = false;
        }
        else
            txtStartDate.setTextColor(Color.BLACK);

        if(spinEndDay.getSelectedItemPosition() == 0)
        {
            txtEndDate.setTextColor(Color.RED);
            is = false;
        }
        else
            txtEndDate.setTextColor(Color.BLACK);

        return is;
    }
}
