package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobSaved;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.CompanyJobs;
import gullideckel.seasonhunter.R;

public class CompanyJobSavedAdapter extends RecyclerView.Adapter<CompanyJobSavedViewHolder>
{
    private List<CompanyJobs.CompanyJob> items;
    private Context context;

    public CompanyJobSavedAdapter(List<CompanyJobs.CompanyJob> items, Context context)
    {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CompanyJobSavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_company_job_saved_viewholder, parent, false);
        CompanyJobSavedViewHolder viewHolder = new CompanyJobSavedViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyJobSavedViewHolder holder, int position)
    {
        holder.getTxtJobTitle().setText(items.get(position).getJobTitle());
        holder.getTxtPayment().setText(GetPayment(items.get(position)));
        holder.getTxtDuration().setText(items.get(position).getStartDay() + " " + context.getString(R.string.of) + " " +
                                        items.get(position).getStartMonth() + " - " +
                                        items.get(position).getEndDay() + " " + context.getString(R.string.of) + " " +
                                        items.get(position).getEndMonth());
        holder.getTxtAdditionalInfo().setText(items.get(position).getAddtionalInfo());
    }



    private String GetPayment(CompanyJobs.CompanyJob companyJob)
    {
        if(companyJob.isHourlyPaid())
            return context.getString(R.string.hourly_paid);
        else if(companyJob.isPieceWork())
            return context.getString(R.string.piece_work);
        else if(companyJob.isVolunteering())
            return context.getString(R.string.volunteering);
        return "No payment method selected";
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
