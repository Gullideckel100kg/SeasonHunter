package gullideckel.seasonhunter.JobRecruitment.Fragments.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;

import gullideckel.seasonhunter.R;

public class AdapterCompanyType extends RecyclerView.Adapter<AdapterCompanyType.CostumeViewHolder>
{
    private List<String> mCompanyTypes;

    public AdapterCompanyType(List<String> companyTypes)
    {
        mCompanyTypes = companyTypes;
    }

    @NonNull
    @Override
    public CostumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_add_company_type, parent, false);

        CostumeViewHolder vh = new CostumeViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CostumeViewHolder holder, int position)
    {
        holder.mChk.setText(mCompanyTypes.get(position));

        holder.mChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {

                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mCompanyTypes.size();
    }


    public static class CostumeViewHolder extends RecyclerView.ViewHolder
    {
        protected CheckBox mChk;

        public CostumeViewHolder(View v)
        {
            super(v);
            mChk = (CheckBox) v.findViewById(R.id.chkAddCompanyType);
        }
    }
}
