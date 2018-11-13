package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobEdit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyJobs;
import gullideckel.seasonhunter.R;

public class CompanyJobEditAdapter extends RecyclerView.Adapter<CompanyJobEditViewHolder>
{
    private CompanyJobs items;
    private Context context;
    private ArrayAdapter<CharSequence> adapterDay;
    private ArrayAdapter<CharSequence> adapterMonth;

    public CompanyJobEditAdapter(CompanyJobs items, Context context)
    {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CompanyJobEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_company_job_edit_viewholder, parent, false);
        CompanyJobEditViewHolder viewHolder = new CompanyJobEditViewHolder(v);
        return viewHolder;
    }

    //TODO: Month selection with the right days
    @Override
    public void onBindViewHolder(@NonNull CompanyJobEditViewHolder holder, final int position)
    {
        final int thisPosition = position;

        adapterDay = ArrayAdapter.createFromResource(context, R.array.days, android.R.layout.simple_spinner_item);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.getSpinEndDay().setAdapter(new CompanyJobSpinnerAdapter(adapterDay, R.layout.nothing_selected_layout, context, context.getText(R.string.day).toString()));
        holder.getSpinStartDay().setAdapter(new CompanyJobSpinnerAdapter(adapterDay, R.layout.nothing_selected_layout, context, context.getText(R.string.day).toString()));

        adapterMonth = ArrayAdapter.createFromResource(context, R.array.months, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.getSpinEndMonth().setAdapter(new CompanyJobSpinnerAdapter(adapterMonth, R.layout.nothing_selected_layout, context, context.getText(R.string.month).toString()));
        holder.getSpinStartMonth().setAdapter(new CompanyJobSpinnerAdapter(adapterMonth,R.layout.nothing_selected_layout,context,context.getText(R.string.month).toString()));

        CompanyJobListeners layoutListeners = new CompanyJobListeners(items.getCompanyJobs());

        layoutListeners.SetJobTitleTextWraper( position, holder.getEdtJobTitle());
        layoutListeners.SetPaymentListener(holder.getRdgPayment(), position);
        layoutListeners.SetSpinnerStartDay(holder.getSpinStartDay(), position, adapterDay);
        layoutListeners.SetSpinnerEndDay(holder.getSpinEndDay(), position, adapterDay);
        layoutListeners.SetSpinnerStartMonth(holder.getSpinStartMonth(), position, adapterMonth);
        layoutListeners.SetSpinnerEndMonth(holder.getSpinEndMonth(), position, adapterMonth);
        layoutListeners.SetAdditionalNoteWrapper(holder.getEdtAdditionalInfo(), position);

        if(position == 0)
            holder.getiBtnRemoveJob().setVisibility(View.GONE);

        holder.getiBtnRemoveJob().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(position != 0)
                {
                    items.getCompanyJobs().remove(position);
                    notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return items.getCompanyJobs().size();
    }
}
