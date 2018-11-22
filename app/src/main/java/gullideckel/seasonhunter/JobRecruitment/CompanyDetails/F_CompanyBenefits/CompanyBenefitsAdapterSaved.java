package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.F_CompanyBenefits;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.CompanyBenefits;
import gullideckel.seasonhunter.R;

public class CompanyBenefitsAdapterSaved extends RecyclerView.Adapter<CompanyBenefitsViewHolderSaved>
{
    private List<CompanyBenefits.CompanyBenefit> items;
    private Context context;

    public CompanyBenefitsAdapterSaved(List<CompanyBenefits.CompanyBenefit> items, Context context)
    {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CompanyBenefitsViewHolderSaved onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_company_benefits_viewholder_saved, parent, false);
        CompanyBenefitsViewHolderSaved viewHolder = new CompanyBenefitsViewHolderSaved(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CompanyBenefitsViewHolderSaved holder, final int position)
    {
        holder.getImgBenefit().setImageBitmap(items.get(position).getBenefitLogo());
        holder.getImgBenefit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, items.get(position).getBenefit(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}