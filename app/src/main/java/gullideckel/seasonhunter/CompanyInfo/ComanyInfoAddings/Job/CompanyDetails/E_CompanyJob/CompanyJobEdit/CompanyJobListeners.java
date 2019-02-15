package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.E_CompanyJob.CompanyJobEdit;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class CompanyJobListeners
{
    private List<CompanyJobs.CompanyJob> companyJobs;

    public CompanyJobListeners(List<CompanyJobs.CompanyJob> companyJobs)
    {
        this.companyJobs = companyJobs;
    }

    public void SetJobTitleTextWraper(final int position, EditText jobTitle)
    {
        jobTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                companyJobs.get(position).setJobTitle(s.toString());
            }
        });
    }

    public void SetPaymentListener(RadioGroup rdgPayments,final int position)
    {
        rdgPayments.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                companyJobs.get(position).setHourlyPaid(false);
                companyJobs.get(position).setPieceWork(false);
                companyJobs.get(position).setVolunteering(false);
                if(checkedId == R.id.rdbHourlyPaid)
                    companyJobs.get(position).setHourlyPaid(true);
                else if (checkedId == R.id.rdbPieceWork)
                    companyJobs.get(position).setPieceWork(true);
                else if (checkedId == R.id.rdbVolunteering)
                    companyJobs.get(position).setVolunteering(true);
            }
        });
    }

    public void SetSpinnerStartDay(Spinner spinner, final int pos, final ArrayAdapter<CharSequence> adapter)
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if(position > 0)
                    companyJobs.get(pos).setStartDay(adapter.getItem(position - 1).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                System.out.print("");
            }
        });
    }

    public void SetSpinnerEndDay(Spinner spinner, final int pos, final ArrayAdapter<CharSequence> adapter)
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position > 0)
                    companyJobs.get(pos).setEndDay(adapter.getItem(position - 1).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void SetSpinnerStartMonth(Spinner spinner, final int pos, final ArrayAdapter<CharSequence> adapter)
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position > 0)
                    companyJobs.get(pos).setStartMonth(adapter.getItem(position -1).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void SetSpinnerEndMonth(Spinner spinner, final int pos, final ArrayAdapter<CharSequence> adapter)
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position > 0)
                companyJobs.get(pos).setEndMonth(adapter.getItem(position -1).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void SetAdditionalNoteWrapper(EditText edtAdditionalText, final int position)
    {
        edtAdditionalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                companyJobs.get(position).setAddtionalInfo(s.toString());
            }
        });
    }
}
