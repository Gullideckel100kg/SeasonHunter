package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;

import gullideckel.seasonhunter.Objects.Job.CompanyContact;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class Job
{
    private LayoutInflater inflater;
    private Context context;

    private LinearLayout linJobHead;
    private LinearLayout linJob;
    private Button btnNewJob;
    private Button btnDone;

    private JobBottom jobBottom;
    private JobHead jobHead;
    private CompanyJobs jobs;

    public Job(LayoutInflater inflater, Context context, CompanyJobs jobs)
    {
        this.inflater = inflater;
        this.context = context;
        this.jobs = jobs;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_job, null);

        linJobHead = (LinearLayout) v.findViewById(R.id.linEditJobHead);
        linJob = (LinearLayout) v.findViewById(R.id.linEditJob);
        btnNewJob = (Button) v.findViewById(R.id.btnEditNewJob);
        btnDone= (Button) v.findViewById(R.id.btnEditJobDone);

        jobBottom = new JobBottom(inflater, context);
        jobHead = new JobHead(inflater, jobBottom, linJobHead);

        for(CompanyJobs.CompanyJob job : jobs.getCompanyJobs())
            linJobHead.addView(jobHead.getView(job));

        linJob.addView(jobBottom.getView());
        btnDone.setOnClickListener(Done);
        btnNewJob.setOnClickListener(NewJob);


        return v;
    }

    private View.OnClickListener Done = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(jobBottom.isCorrect())
            {
                linJobHead.addView(jobHead.getView(jobBottom.getJob()));
            }
        }
    };

    private View.OnClickListener NewJob = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            linJob.removeAllViews();
            linJob.addView(jobBottom.getView());
        }
    };

    public CompanyJobs getJobs()
    {
        if(jobs.equals(jobHead.getJobs().equals(jobs)))
            return null;
        else
            return jobHead.getJobs();
    }
}
