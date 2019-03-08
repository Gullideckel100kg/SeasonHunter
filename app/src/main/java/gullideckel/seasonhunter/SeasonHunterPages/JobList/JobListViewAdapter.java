package gullideckel.seasonhunter.SeasonHunterPages.JobList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import gullideckel.seasonhunter.CompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.Interfaces.IDocument;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticTypes;

public class JobListViewAdapter extends RecyclerView.Adapter<JobListViewHolder>
{
    private static final String TAG = "JobListViewAdapter";

    private List<CompanyDocument> docs;
    private Context context;
    private LayoutInflater inflater;
    private IDocument listener;
    private FragmentManager manager;
    private GoogleApiClient client;

    public JobListViewAdapter(List<CompanyDocument> docs, IDocument listener, FragmentManager manager, GoogleApiClient client)
    {
        this.docs = docs;
        this.listener = listener;
        this.manager = manager;
        this.client = client;
    }

    @NonNull
    @Override
    public JobListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.view_list_company_info, parent, false);
        JobListViewHolder viewHolder = new JobListViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobListViewHolder holder, final int position)
    {
        if(docs.get(position).getTypes() != null && docs.get(position).getTypes().size() > 0)
            holder.getImgType().setImageBitmap(StaticTypes.getLogo(docs.get(position).getTypes().get(0), context));
        else
            Log.wtf(TAG, "onBindViewHolder: Doc has no Type. DocId: " + docs.get(position).getId() );

        holder.getTxtListName().setText(docs.get(position).getName());

        if(docs.get(position).getJobs() != null && docs.get(position).getJobs().getCompanyJobs().size() > 0)
        {
            holder.getLinJobs().removeAllViews();
            for(CompanyJobs.CompanyJob job : docs.get(position).getJobs().getCompanyJobs())
            {
                View v = inflater.inflate(R.layout.view_list_company_job, null);

                TextView txtTitle = v.findViewById(R.id.txtListTitle);
                TextView txtDate = v.findViewById(R.id.txtListDate);

                txtTitle.setText(job.getJobTitle());
                txtDate.setText(job.getStartDate() + " - " + job.getEndDate());

                holder.getLinJobs().addView(v);
            }
        }
        else
            Log.wtf(TAG, "onBindViewHolder: Company has no Job offer. Has to be updated or take out\nDocId " + docs.get(position).getId() );

        if(docs.get(position).getReviews() != null && docs.get(position).getReviews().size() > 0)
            holder.getTxtSowMore().setText( context.getString(R.string.list_show_more) + " (" + docs.get(position).getReviews().size() + " " +
                                            context.getString(R.string.list_review_count) + ")");
        else
            holder.getTxtSowMore().setText(context.getString(R.string.list_show_more));

        holder.getTxtSowMore().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.cnstMapHunter, FragCompanyInfo.NewInstance(docs.get(position), listener, client));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }



    @Override
    public int getItemCount()
    {
        return docs.size();
    }
}
