package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName.CompanyNameConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName.CompanyNameViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CostumLayoutManager;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyName;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyType;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class ComplexCompanyDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICompanyName, ICompanyType, ICompanyAddress
{
    private List<Object> mItems;
    private List<CompanyType> mLstCompanyTypes;

    private Context mContext;
    private CostumLayoutManager mLayoutManager;

    private Bitmap mLogo;
    private CompanyAddressConfi mCompanyAddressConfi;

    public static final int COMPANYNAME = 0, COMPANYTYPE = 1, COMPANYADDRESS = 2, COMPANYCONTACT = 3, COMPANYJOBS = 4, COMPANYBENEFITS = 5;

    public ComplexCompanyDetailsAdapter(List<Object> items, List<CompanyType> lstCompanyTypes, Context context, CostumLayoutManager layoutManager)
    {
        mItems =  items;
        mLstCompanyTypes = lstCompanyTypes;
        mContext = context;
        mLayoutManager = layoutManager;
    }


    //TODO: Add the other Companydetails
    @Override
    public int getItemViewType(int position)
    {
        if(mItems.get(position) instanceof CompanyName)
            return COMPANYNAME;
        else if (mItems.get(position) instanceof CompanyType)
            return  COMPANYTYPE;
        else if (mItems.get(position) instanceof CompanyAddress)
            return COMPANYADDRESS;

        return -1;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType)
        {
            case COMPANYNAME:
                View v1 = inflater.inflate(R.layout.details_company_name_viewholder, parent, false);
                viewHolder = new CompanyNameViewHolder(v1);
                break;
            case COMPANYTYPE:
                View v2 = inflater.inflate(R.layout.details_company_type_viewholder, parent, false);
                viewHolder = new CompanyTypeViewHolder(v2);
                break;
            case COMPANYADDRESS:
                View v3 = inflater.inflate(R.layout.details_company_address_viewholder, parent, false);
                viewHolder = new CompanyAddressViewHolder(v3);
                break;
            default:
                return null;
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        switch (holder.getItemViewType())
        {
            case COMPANYNAME:
                CompanyNameConfi confiNme = new CompanyNameConfi((CompanyNameViewHolder) holder, this, mContext);
                confiNme.Confi();
                break;
            case COMPANYTYPE:
                CompanyTypeConfi confiTpe = new CompanyTypeConfi((CompanyTypeViewHolder) holder, this, mLstCompanyTypes, mContext);
                confiTpe.Confi();
                break;
            case COMPANYADDRESS:
                mCompanyAddressConfi = new CompanyAddressConfi((CompanyAddressViewHolder) holder, this, mLogo, mContext);
                mCompanyAddressConfi.Confi();
                break;
        }
    }



    @Override
    public int getItemCount()
    {
        return mItems.size();
    }


    @Override
    public void OnCompanyName(String companyName)
    {
        if(StaticMethod.ContainsInstance(CompanyType.class, mItems))
        {
            mItems.add(new CompanyType());
            notifyItemInserted(mItems.size() - 1);
        }

        if(StaticMethod.CastClass(CompanyName.class, mItems) != null)
            StaticMethod.CastClass(CompanyName.class, mItems).SetCompanyName(companyName);
    }


    @Override
    public void OnCompanyType(CompanyType companyType)
    {
        mLogo = companyType.GetLogo();

        if (StaticMethod.ContainsInstance(CompanyAddress.class, mItems))
        {
            mItems.add(new CompanyAddress());
            notifyItemInserted(mItems.size() - 1);
        }

        if(mCompanyAddressConfi != null)
            mCompanyAddressConfi.SetLogo(companyType.GetLogo());

        CompanyType companyTypeClass = StaticMethod.CastClass(CompanyType.class, mItems);

        if(companyTypeClass != null)
        {
            companyTypeClass.SetCompanyType(companyType.GetCompanyType());
            companyTypeClass.SetCompanyTypeLogo(companyType.GetLogo());
        }
    }

    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {

    }
}
