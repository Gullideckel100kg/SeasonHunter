//package gullideckel.seasonhunter.JobRecruitmentOld.Fragments.Adapters;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.List;
//
//import gullideckel.seasonhunter.Interfaces.IEditCompanyType;
//import gullideckel.seasonhunter.R;
//
//public class AdapterAddedCompanyType extends RecyclerView.Adapter<AdapterAddedCompanyType.AddedCompanyTypeViewHolder>
//{
////    private List<CompanyTypeObject> mAddedCompanyTypes;
//    private IEditCompanyType mContext;
//
////    public AdapterAddedCompanyType(List<CompanyTypeObject> addedCompanyTypes, IEditCompanyType context)
////    {
////        mAddedCompanyTypes = addedCompanyTypes;
////        mContext = context;
////    }
//
//    @NonNull
//    @Override
//    public AddedCompanyTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
//    {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_added_company_type,parent,false);
//
//        AddedCompanyTypeViewHolder vh = new AddedCompanyTypeViewHolder(v);
//
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final AddedCompanyTypeViewHolder holder, final int position)
////    {
////        holder.TxtAddedCompanyType.setText(mAddedCompanyTypes.get(position).GetCompanyType());
////
////        holder.BtnRemoveAddedCompanyType.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v)
////            {
////                mContext.onCompanyType(mAddedCompanyTypes.get(position), position);
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return mAddedCompanyTypes.size();
//    }
//
//    public static class AddedCompanyTypeViewHolder extends RecyclerView.ViewHolder
//    {
//        protected Button BtnRemoveAddedCompanyType;
//        protected TextView TxtAddedCompanyType;
//
//        public AddedCompanyTypeViewHolder(View v)
//        {
//            super(v);
//            BtnRemoveAddedCompanyType = (Button) v.findViewById(R.id.btnRemoveCompanyType);
//            TxtAddedCompanyType = (TextView) v.findViewById(R.id.txtAddedCompanyType);
//        }
//    }
//}
