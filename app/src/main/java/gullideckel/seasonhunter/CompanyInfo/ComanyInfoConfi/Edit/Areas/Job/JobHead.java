package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Job;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class JobHead
{
    private static final String TAG = "JobHead";

    private LayoutInflater inflater;
    private LinearLayout linHead;

    private TextView txtTitle;
    private TextView txtDate;
    private TextView txtNotesHead;
    private TextView txtNotes;
    private Button btnEdit;
    private Button btnRemove;
    private CompanyJobs.CompanyJob job;
    private CompanyJobs jobs;

    private JobBottom jobBottom;

    public JobHead(LayoutInflater inflater, JobBottom jobBottom, LinearLayout linHead)
    {
        this.inflater = inflater;
        this.linHead = linHead;
        this.jobBottom = jobBottom;
        this.jobs = new CompanyJobs();
    }

    public View getView(CompanyJobs.CompanyJob job)
    {
        this.job = job;

        View v = inflater.inflate(R.layout.frag_edit_job_head, null);

        txtTitle = (TextView) v.findViewById(R.id.txtEditJobTitle);
        txtDate = (TextView) v.findViewById(R.id.txtEditDate);
        txtNotesHead = (TextView) v.findViewById(R.id.txtEditNotesHead);
        txtNotes = (TextView) v.findViewById(R.id.txtEditNotes);
        btnEdit = (Button) v.findViewById(R.id.btnEditJobEdit);
        btnRemove = (Button) v.findViewById(R.id.btnEditRemoveJob);

        txtTitle.setText(job.getJobTitle());
        txtDate.setText(job.getStartDate() + " - " + job.getEndDate());
        if(job.getNotes().isEmpty())
        {
            txtNotesHead.setVisibility(View.GONE);
            txtNotes.setVisibility(View.GONE);
        }
        else
        {
            txtNotesHead.setVisibility(View.VISIBLE);
            txtNotes.setVisibility(View.VISIBLE);
            txtNotes.setText(job.getNotes());
        }

        v.setTag(job);
        jobs.addCompanyJob(job);

        btnEdit.setOnClickListener(Edit);

        btnRemove.setOnClickListener(Remove);
        btnRemove.setTag(v);
        btnEdit.setTag(v);

        return v;
    }

    private View.OnClickListener Remove = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Remove(v.getTag());
        }
    };

    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            jobBottom.SetJobEdit(job);
            Remove(v.getTag());
        }
    };

    private void Remove(Object tag)
    {
        for(int i = 0; i < linHead.getChildCount(); i++)
            if(linHead.getChildAt(i).equals(tag))
            {
                linHead.removeView(linHead.getChildAt(i));
                CompanyJobs.CompanyJob job = (CompanyJobs.CompanyJob) ((View) tag).getTag();
                if(jobs.getCompanyJobs().contains(job))
                    jobs.getCompanyJobs().remove(job);
                else
                    Log.wtf(TAG, "Remove: The job has to be in the list. There is a logic mistake and has to be fixed");
            }
    }

    public CompanyJobs getJobs()
    {
        return jobs;
    }
}
