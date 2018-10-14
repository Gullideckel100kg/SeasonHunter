package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyAddress.GeoCoding.CameraMove;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName.CompanyNameConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName.CompanyNameViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyName;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyType;
import gullideckel.seasonhunter.Objects.CompanyType.CompanyTypeObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class ComplexCompanyDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICompanyName, ICompanyType
{
    private List<Object> mItems;
    private List<CompanyTypeObject> mCompanyTypes;

    private final int COMPANYNAME = 0, COMPANYTYPE = 1, COMPANYADDRESS = 2, COMPANYCONTACT = 3, COMPANYJOBS = 4, COMPANYBENEFITS = 5;

    public ComplexCompanyDetailsAdapter(List<Object> items, List<CompanyTypeObject> companyTypes)
    {
        mItems = items;
        mCompanyTypes = companyTypes;
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
                CompanyNameConfi confiNme = new CompanyNameConfi((CompanyNameViewHolder) holder, this);
                confiNme.Confi();
                break;
            case COMPANYTYPE:
                CompanyTypeConfi confiTpe = new CompanyTypeConfi((CompanyTypeViewHolder) holder, this, mCompanyTypes);
                confiTpe.Confi();
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
        if(!StaticMethod.ContainsInstance(CompanyType.class, mItems));
        {
            mItems.add(new CompanyType(mCompanyTypes));
            notifyItemInserted(mItems.size() - 1);
        }
        if(StaticMethod.CastClass(CompanyName.class, mItems) != null)
            StaticMethod.CastClass(CompanyName.class, mItems).SetCompanyName(companyName);
    }

    @Override
    public void OnCompanyType(CompanyTypeObject companyType)
    {

    }






}
