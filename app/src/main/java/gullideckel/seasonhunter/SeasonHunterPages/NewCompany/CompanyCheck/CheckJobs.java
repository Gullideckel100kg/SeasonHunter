package gullideckel.seasonhunter.SeasonHunterPages.NewCompany.CompanyCheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class CheckJobs
{
    private LayoutInflater inflater;

    private TextView txtTitle;
    private TextView txtDate;
    private TextView txtNotesHead;
    private TextView txtNotes;

    public CheckJobs(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }

    public View FillContact(CompanyJobs.CompanyJob job)
    {
        View v = inflater.inflate(R.layout.frag_new_company_review_job, null);

        txtTitle = (TextView) v.findViewById(R.id.txtCheckJobTitle);
        txtDate = (TextView) v.findViewById(R.id.txtCheckDate);
        txtNotesHead = (TextView) v.findViewById(R.id.txtCheckNotesHead);
        txtNotes = (TextView) v.findViewById(R.id.txtCheckNotes);

        txtTitle.setText(job.getJobTitle());
        txtDate.setText(job.getFullDate());

        if(!job.getNotes().isEmpty())
        {
            txtNotesHead.setVisibility(View.VISIBLE);
            txtNotes.setVisibility(View.VISIBLE);
            txtNotes.setText(job.getNotes());
        }

        return v;
    }
}
