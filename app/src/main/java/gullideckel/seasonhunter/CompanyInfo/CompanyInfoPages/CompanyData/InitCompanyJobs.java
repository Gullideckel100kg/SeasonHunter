package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyData;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class InitCompanyJobs
{
    private LayoutInflater inflater;

    public InitCompanyJobs(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }

    public void InitJobs(LinearLayout linJobs, CompanyJobs jobs)
    {
        for(CompanyJobs.CompanyJob job : jobs.getCompanyJobs())
        {
            View v = inflater.inflate(R.layout.frag_company_data_jobs_view, null);

            TextView title = v.findViewById(R.id.txtJobTitle);
            TextView date = v.findViewById(R.id.txtJobDate);
            LinearLayout linNotes = v.findViewById(R.id.linJobNotes);
            TextView txtNotes = v.findViewById(R.id.txtJobNotes);

            title.setText(job.getJobTitle());
            date.setText(job.getStartDate() + " - " + job.getEndDate());

            if(job.getNotes().isEmpty())
                linNotes.setVisibility(View.GONE);
            else
                txtNotes.setText(job.getNotes());

            linJobs.addView(v);
        }
    }
}
