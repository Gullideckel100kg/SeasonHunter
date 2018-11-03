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
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact.CompanyContactConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact.CompanyContactViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyName;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyType;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyJob;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class ComplexCompanyDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICompanyName, ICompanyType, ICompanyAddress, ICompanyDetails
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
        else if (mItems.get(position) instanceof CompanyContact)
            return  COMPANYCONTACT;
        else if (mItems.get(position) instanceof  CompanyJob)
            return  COMPANYJOBS;

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
            case COMPANYCONTACT:
                View v4 = inflater.inflate(R.layout.details_company_contact_viewholder, parent, false);
                viewHolder = new CompanyContactViewHolder(v4);
                break;
            case COMPANYJOBS:
                View v5 = inflater.inflate(R.layout.details_company_job_viewholder, parent, false);
                viewHolder = new CompanyJobViewHolder(v5);
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
                mCompanyAddressConfi = new CompanyAddressConfi((CompanyAddressViewHolder) holder, this, mLogo, mContext, mLayoutManager, (CompanyAddress) mItems.get(position));
                mCompanyAddressConfi.Confi();
                break;
            case COMPANYCONTACT:
                CompanyContactConfi confiCntct = new CompanyContactConfi((CompanyContactViewHolder) holder, mContext, this, (CompanyContact)mItems.get(position));
                confiCntct.Confi();
                break;
            case COMPANYJOBS:
                CompanyJobConfi confiJob = new CompanyJobConfi((CompanyJobViewHolder) holder, mContext, this, mLayoutManager);
                confiJob.Confi();
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
        if(!StaticMethod.ContainsInstance(CompanyType.class, mItems))
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

        if (!StaticMethod.ContainsInstance(CompanyAddress.class, mItems))
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
        if(!StaticMethod.ContainsInstance(CompanyContact.class, mItems))
        {
            mItems.add(new CompanyContact());
            notifyItemInserted(mItems.size() - 1);
        }

        CompanyAddress companyAddressClass = StaticMethod.CastClass(CompanyAddress.class, mItems);

        if(companyAddressClass != null)
        {
            companyAddressClass.SetAddress(companyAddress.GetAddress());
            companyAddressClass.SetCountry(companyAddress.GetCountry());
            companyAddressClass.SetLatitude(companyAddress.GetLatitude());
            companyAddress.SetLongitude(companyAddress.GetLongitude());
        }

    }

    @Override
    public void OnItemUpdate(int position)
    {
        int  count = getItemCount();

        if(position >= count - 1)
        {
            mItems.add(getNewClassInstance(position + 1));
            notifyItemInserted(position + 1);
        }

    }

    private Object getNewClassInstance(int classType)
    {
        switch (classType)
        {
            case COMPANYNAME:
                return new CompanyName();
            case COMPANYTYPE:
                return new CompanyType();
            case COMPANYADDRESS:
                return new CompanyAddress();
            case COMPANYCONTACT:
                return new CompanyContact();
            case COMPANYJOBS:
                return new CompanyJob();
            default:
                return null;
        }
    }


}
