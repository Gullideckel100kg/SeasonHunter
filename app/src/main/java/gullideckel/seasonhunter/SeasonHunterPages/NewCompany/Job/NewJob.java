package gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Job;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender.MonthInitializer;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class NewJob
{
    private static final String TAG = "NewJob";

    private LayoutInflater inflater;

    private EditText edtTitle;
    private TextView txtStartDay;
    private Spinner spinStartDay;
    private Spinner spinStartMonth;
    private TextView txtEndDay;
    private Spinner spinEndDay;
    private Spinner spinEndMonth;
    private CheckBox chkNotes;
    private EditText edtNotes;
    private ImageButton imbMinus;

    private Context context;
    private LinearLayout lin;

    ArrayAdapter<CharSequence> adapterDay30;
    ArrayAdapter<CharSequence> adapterDay31;
    ArrayAdapter<CharSequence> adapterDay28;

    public NewJob(LayoutInflater inflater, Context context, LinearLayout lin)
    {
        this.inflater = inflater;
        this.context = context;
        this.lin = lin;
    }

    public View createJobView()
    {
        View v = inflater.inflate(R.layout.frag_new_company_job, null);

        edtTitle = (EditText) v.findViewById(R.id.edtNewJobTitle);
        txtStartDay = (TextView) v.findViewById(R.id.txtNewStartJob);
        spinStartDay = (Spinner) v.findViewById(R.id.spinNewStartDay);
        spinStartMonth = (Spinner) v.findViewById(R.id.spinNewStartMonth);
        txtEndDay = (TextView) v.findViewById(R.id.txtNewEndJob);
        spinEndDay = (Spinner) v.findViewById(R.id.spinNewEndDay);
        spinEndMonth = (Spinner) v.findViewById(R.id.spinNewEndMonth);
        chkNotes = (CheckBox) v.findViewById(R.id.chkNewNotes);
        edtNotes = (EditText) v.findViewById(R.id.edtNewNotes);
        imbMinus = (ImageButton) v.findViewById(R.id.imbNewMinusJob);

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

        chkNotes.setOnCheckedChangeListener(Notes);

        if(lin.getChildCount() == 0)
            imbMinus.setVisibility(View.GONE);

        imbMinus.setTag(v);
        imbMinus.setOnClickListener(Minus);

        return v;
    }

    private View.OnClickListener Minus = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            for(int i = 0; i < lin.getChildCount(); i++)
                if(lin.getChildAt(i).equals(v.getTag()))
                    lin.removeView(lin.getChildAt(i));
        }
    };

    private CompoundButton.OnCheckedChangeListener Notes = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
                edtNotes.setVisibility(View.VISIBLE);
            else
                edtNotes.setVisibility(View.GONE);
        }
    };

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

    public CompanyJobs.CompanyJob getCompanyJob()
    {
        CompanyJobs.CompanyJob job = new CompanyJobs.CompanyJob();

        job.setJobTitle(edtTitle.getText().toString());
        job.setStartDate(DateConverter.ConvertToDate(spinStartDay.getSelectedItemPosition(), spinStartMonth.getSelectedItemPosition()));
        job.setEndDate(DateConverter.ConvertToDate(spinEndDay.getSelectedItemPosition(), spinEndMonth.getSelectedItemPosition()));
        job.setFullDate(context.getString(R.string.new_start) + " " + spinStartDay.getSelectedItem().toString() + " " +
                        context.getString(R.string.new_of) + " " + spinStartMonth.getSelectedItem().toString() + "\n" +
                        context.getString(R.string.new_end) + " " + spinEndDay.getSelectedItem().toString() +
                        context.getString(R.string.new_of) + spinEndMonth.getSelectedItem().toString());


        if(chkNotes.isChecked() && !edtNotes.getText().toString().isEmpty())
            job.setNotes(edtNotes.getText().toString());

        return job;
    }

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
            txtStartDay.setTextColor(Color.RED);
            is = false;
        }
        else
            txtStartDay.setTextColor(Color.BLACK);

        if(spinEndDay.getSelectedItemPosition() == 0)
        {
            txtEndDay.setTextColor(Color.RED);
            is = false;
        }
        else
            txtEndDay.setTextColor(Color.BLACK);

        return is;
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

        if(spinner.getAdapter().getCount() >= selectedItem)
            spinner.setSelection(selectedItem);
        }
}
