package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.F_CompanyBenefits;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class CompanyBenefitsAdapter extends RecyclerView.Adapter<CompanyBenefitsViewHolderEdit>
{
    @NonNull
    @Override
    public CompanyBenefitsViewHolderEdit onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyBenefitsViewHolderEdit holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }
//    private List<CompanyBenefits.CompanyBenefit> items;
//
//    public CompanyBenefitsAdapter(List<CompanyBenefits.CompanyBenefit> items)
//    {
//        this.items = items;
//    }
//
//    @NonNull
//    @Override
//    public CompanyBenefitsViewHolderEdit onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
//    {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_company_benefits_viewholder_edit, parent, false);
//        CompanyBenefitsViewHolderEdit viewHolder = new CompanyBenefitsViewHolderEdit(v);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final CompanyBenefitsViewHolderEdit holder, final int position)
//    {
//        holder.getImgBenefitLogo().setImageBitmap(items.get(position).getBenefitLogo());
//        holder.getTxtBenefit().setText(items.get(position).getBenefit());
//        holder.getV().setBackgroundColor(items.get(position).isSelected() ? Color.CYAN : Color.WHITE);
//        holder.getV().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                items.get(position).setSelected(!items.get(position).isSelected());
//                holder.getV().setBackgroundColor(items.get(position).isSelected() ? Color.CYAN : Color.WHITE);
//            }
//        });
//    }
//
//    public void SetViewSelectAble(boolean isSelectAble)
//    {
//
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return items.size();
//    }
}
