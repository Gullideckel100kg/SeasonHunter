package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import gullideckel.seasonhunter.Objects.JobInformation.CompanyTypes;
import gullideckel.seasonhunter.R;

public class CompanyTypeAdapter extends RecyclerView.Adapter<CompanyTypeViewHolderList>
{
    private CompanyTypes items;

    private int mLastCheckedPosition = -1;

    private CompanyTypes.CompanyType mCompanyType;

    public CompanyTypeAdapter(CompanyTypes items)
    {
        this.items = items;
    }

    @NonNull
    @Override
    public CompanyTypeViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_company_type_viewholder_list, parent, false);
        CompanyTypeViewHolderList viewHolder = new CompanyTypeViewHolderList(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CompanyTypeViewHolderList holder, final int position)
    {
        holder.GetImgCompanyType().setImageBitmap(items.getCompanyTypes().get(position).getLogo());
        holder.GetTxtCompanyType().setText(items.getCompanyTypes().get(position).getCompanyType());


        holder.getV().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mLastCheckedPosition = position;
                notifyDataSetChanged();
                if(holder.GetRdbCompanyType().isChecked())
                    items.setSelectedCompanyType(position);
            }
        });

        holder.GetRdbCompanyType().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                    items.setSelectedCompanyType(position);
            }
        });

        holder.GetRdbCompanyType().setChecked(position == mLastCheckedPosition);
    }

    @Override
    public int getItemCount()
    {
        return items.getCompanyTypes().size();
    }
}
