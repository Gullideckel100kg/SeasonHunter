package gullideckel.seasonhunter.CompanyInfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.CompanyJobs;
import gullideckel.seasonhunter.R;

public class JobsAdapter extends RecyclerView.Adapter<JobsViewHolder>
{
    private List<CompanyJobs.CompanyJob> jobs;
    private Context context;

    public JobsAdapter(List<CompanyJobs.CompanyJob> jobs, Context context)
    {
        this.jobs = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.details_company_job_saved_viewholder, parent, false);

        return new JobsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder holder, int position)
    {
        holder.getTxtJobTitle().setText(jobs.get(position).getJobTitle());

        if(jobs.get(position).isHourlyPaid())
            holder.getTxtPayment().setText(context.getText(R.string.hourly_paid));
        else if(jobs.get(position).isPieceWork())
            holder.getTxtPayment().setText(context.getText(R.string.piece_work));
        else if(jobs.get(position).isVolunteering())
            holder.getTxtPayment().setText(context.getText(R.string.volunteering));

        holder.getTxtDuration().setText(jobs.get(position).getStartDay() + " of " + jobs.get(position).getStartMonth() + " - " +
                                        jobs.get(position).getEndDay() + " of " + jobs.get(position).getEndMonth());

        holder.getTxtAdditionalInfo().setText(jobs.get(position).getAddtionalInfo());
    }

    @Override
    public int getItemCount()
    {
        return jobs.size();
    }
}
