package gullideckel.seasonhunter.JobRecruitmentOld.Fragments.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;

import gullideckel.seasonhunter.Interfaces.IEditCompanyType;
import gullideckel.seasonhunter.Objects.CompanyTypeObject;
import gullideckel.seasonhunter.R;

public class AdapterCompanyType extends RecyclerView.Adapter<AdapterCompanyType.CompanyTypeViewHolder>
{
    private List<CompanyTypeObject> mCompanyTypes;
    private IEditCompanyType mContext;

    public AdapterCompanyType(List<CompanyTypeObject> companyTypes, IEditCompanyType context)
    {
        mCompanyTypes = companyTypes;
        mContext = context;
    }

    @NonNull
    @Override
    public CompanyTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_add_company_type, parent, false);

        CompanyTypeViewHolder vh = new CompanyTypeViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyTypeViewHolder holder, final int position)
    {
        holder.mChk.setText(mCompanyTypes.get(position).GetCompanyType());
        holder.mChk.setChecked(mCompanyTypes.get(position).GetChecked());

        holder.mChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                mCompanyTypes.get(position).SetChecked(isChecked);
                mContext.onCompanyType(mCompanyTypes.get(position), position);
            }
        });
    }



    public void CompanyTypeSelection(boolean isSelected, int position)
    {
        getItemViewType(position);
    }

    @Override
    public int getItemCount()
    {
        return mCompanyTypes.size();
    }


    public static class CompanyTypeViewHolder extends RecyclerView.ViewHolder
    {
        protected CheckBox mChk;

        public CompanyTypeViewHolder(View v)
        {
            super(v);
            mChk = (CheckBox) v.findViewById(R.id.chkAddCompanyType);
        }
    }
}
