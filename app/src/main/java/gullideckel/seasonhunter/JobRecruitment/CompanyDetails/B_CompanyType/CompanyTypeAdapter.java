package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyType;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;
import gullideckel.seasonhunter.R;

public class CompanyTypeAdapter extends RecyclerView.Adapter<CompanyTypeViewHolderList>
{
    private List<CompanyType> mItems;

    private int mLastCheckedPosition = -1;

    private CompanyType mCompanyType;

    public CompanyTypeAdapter(List<CompanyType> items)
    {
        mItems = items;
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
        holder.GetImgCompanyType().setImageBitmap(mItems.get(position).GetLogo());
        holder.GetTxtCompanyType().setText(mItems.get(position).GetCompanyType());

        holder.GetRdbCompanyType().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLastCheckedPosition = position;
                notifyDataSetChanged();
                if(holder.GetRdbCompanyType().isChecked())
                    mCompanyType = mItems.get(position);
            }
        });
        holder.GetRdbCompanyType().setChecked(position == mLastCheckedPosition);
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    public CompanyType GetCompanyType()
    {
        return mCompanyType;
    }
}
