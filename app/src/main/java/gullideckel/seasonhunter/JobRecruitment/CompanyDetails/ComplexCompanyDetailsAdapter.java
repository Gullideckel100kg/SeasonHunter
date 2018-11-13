package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.graphics.Bitmap;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName.CompanyNameConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName.CompanyNameViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CompanyAddressViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact.CompanyContactConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact.CompanyContactViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob.CompanyJobViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.F_CompanyBenefits.CompanyBenefitsConfi;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.F_CompanyBenefits.CompanyBenefitsViewHolder;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyName;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyType;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyAddress;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyBenefits;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyJobs;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyTypes;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class ComplexCompanyDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICompanyDetails
{
    private static final String TAG="ComplexCompanyDetails";

    private List<CompanyTypes.CompanyType> mLstCompanyTypes;

    private Bitmap mLogo;

    private CompanyNameConfi NameConfi;
    private CompanyTypeConfi TypeConfi;
    private CompanyAddressConfi AddressConfi;
    private CompanyContactConfi ContactConfi;
    private CompanyJobConfi JobConfi;
    private CompanyBenefitsConfi BenefitsConfi;
    private CompanyDetailsObject companyDetails;

    public static final int COMPANYNAME = 0, COMPANYTYPE = 1, COMPANYADDRESS = 2, COMPANYCONTACT = 3, COMPANYJOBS = 4, COMPANYBENEFITS = 5, POST = 6;

    public ComplexCompanyDetailsAdapter(CompanyDetailsObject detailsObject, List<CompanyTypes.CompanyType> lstCompanyTypes)
    {
        this.companyDetails = detailsObject;
        mLstCompanyTypes = lstCompanyTypes;
    }


    //TODO: Add the other Companydetails
    @Override
    public int getItemViewType(int position)
    {
        if(companyDetails.getItems().get(position) instanceof CompanyName)
            return COMPANYNAME;
        else if (companyDetails.getItems().get(position) instanceof CompanyTypes)
            return  COMPANYTYPE;
        else if (companyDetails.getItems().get(position) instanceof CompanyAddress)
            return COMPANYADDRESS;
        else if (companyDetails.getItems().get(position) instanceof CompanyContact)
            return  COMPANYCONTACT;
        else if (companyDetails.getItems().get(position) instanceof  CompanyJobs)
            return  COMPANYJOBS;
        else if (companyDetails.getItems().get(position) instanceof CompanyBenefits)
            return COMPANYBENEFITS;

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
            case COMPANYBENEFITS:
                View v6 = inflater.inflate(R.layout.details_company_benefits_viewholder, parent, false);
                viewHolder = new CompanyBenefitsViewHolder(v6);
                break;
            default:
                return null;
        }

        return viewHolder;
    }

    //TODO: Check that inizilation is not double happen
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        switch (holder.getItemViewType())
        {
            case COMPANYNAME:
                if (NameConfi == null)
                {
                    NameConfi = new CompanyNameConfi((CompanyNameViewHolder) holder, this, companyDetails);
                    NameConfi.Confi();
                }
                break;
            case COMPANYTYPE:
                if(TypeConfi == null)
                {
                    TypeConfi= new CompanyTypeConfi((CompanyTypeViewHolder) holder, this, companyDetails, mLstCompanyTypes);
                    TypeConfi.Confi();
                }
                break;
            case COMPANYADDRESS:
                if(AddressConfi == null)
                {
                    AddressConfi = new CompanyAddressConfi((CompanyAddressViewHolder) holder, this, mLogo, companyDetails);
                    AddressConfi.Confi();
                }
                break;
            case COMPANYCONTACT:
                if(ContactConfi == null)
                {
                    ContactConfi = new CompanyContactConfi((CompanyContactViewHolder) holder,  this, companyDetails);
                    ContactConfi.Confi();
                }
                break;
            case COMPANYJOBS:
                if(JobConfi == null)
                {
                    JobConfi = new CompanyJobConfi((CompanyJobViewHolder) holder, this, companyDetails);
                    JobConfi.Confi();
                }
                break;
            case COMPANYBENEFITS:
                if(BenefitsConfi == null)
                {
                    BenefitsConfi= new CompanyBenefitsConfi((CompanyBenefitsViewHolder) holder, this, companyDetails);
                    BenefitsConfi.Confi();
                }
                break;
        }
    }



    @Override
    public int getItemCount()
    {
        return companyDetails.getItems().size();
    }



//    @Override
//    public void OnCompanyType(CompanyTypes companyType)
//    {
//        mLogo = companyType.GetLogo();
//
//        if (!StaticMethod.ContainsInstance(CompanyAddress.class, mItems))
//        {
//            mItems.add(new CompanyAddress());
//            notifyItemInserted(mItems.size() - 1);
//        }
//
//        if(AddressConfi != null)
//            AddressConfi.SetLogo(companyType.GetLogo());
//
//        CompanyTypes companyTypeClass = StaticMethod.CastClass(CompanyTypes.class, mItems);
//
//        if(companyTypeClass != null)
//        {
//            companyTypeClass.SetCompanyType(companyType.GetCompanyType());
//            companyTypeClass.SetCompanyTypeLogo(companyType.GetLogo());
//        }
//    }
//
//    @Override
//    public void OnCompanyAddress(CompanyAddress companyAddress)
//    {
//        if(!StaticMethod.ContainsInstance(CompanyContact.class, mItems))
//        {
//            mItems.add(new CompanyContact());
//            notifyItemInserted(mItems.size() - 1);
//        }
//
//        CompanyAddress companyAddressClass = StaticMethod.CastClass(CompanyAddress.class, mItems);
//
//        if(companyAddressClass != null)
//        {
//            companyAddressClass.SetAddress(companyAddress.GetAddress());
//            companyAddressClass.SetCountry(companyAddress.GetCountry());
//            companyAddressClass.SetLatitude(companyAddress.GetLatitude());
//            companyAddress.SetLongitude(companyAddress.GetLongitude());
//        }
//
//    }

//    mLogo = companyType.GetLogo();
//
//        if (!StaticMethod.ContainsInstance(CompanyAddress.class, mItems))
//    {
//        mItems.add(new CompanyAddress());
//        notifyItemInserted(mItems.size() - 1);
//    }
//
//        if(AddressConfi != null)
//        AddressConfi.SetLogo(companyType.GetLogo());
//
//    CompanyTypes companyTypeClass = StaticMethod.CastClass(CompanyTypes.class, mItems);
//
//        if(companyTypeClass != null)
//    {
//        companyTypeClass.SetCompanyType(companyType.GetCompanyType());
//        companyTypeClass.SetCompanyTypeLogo(companyType.GetLogo());
//    }

//     if(!StaticMethod.ContainsInstance(CompanyContact.class, mItems))
//    {
//        mItems.add(new CompanyContact());
//        notifyItemInserted(mItems.size() - 1);
//    }
//
//    CompanyAddress companyAddressClass = StaticMethod.CastClass(CompanyAddress.class, mItems);
//
//        if(companyAddressClass != null)
//    {
//        companyAddressClass.SetAddress(companyAddress.GetAddress());
//        companyAddressClass.SetCountry(companyAddress.GetCountry());
//        companyAddressClass.SetLatitude(companyAddress.GetLatitude());
//        companyAddress.SetLongitude(companyAddress.GetLongitude());
//    }

    @Override
    public void OnItemUpdate(int position)
    {
        int  count = getItemCount();

        if(position == COMPANYTYPE)
        {

            CompanyTypes companyTypes = (CompanyTypes)companyDetails.getItems().get(position);
            mLogo = companyTypes.getCompanyTypes().get(companyTypes.getSelectedCompanyType()).GetLogo();
            if(AddressConfi != null)
                AddressConfi.SetLogo(mLogo);
        }

        if(companyDetails.getItems().size() == 6)
        {
            companyDetails.getBtnPost().setVisibility(View.VISIBLE);
        }
        else if(position >= count - 1 && position > -1)
        {
            companyDetails.getItems().add(getNewClassInstance(position + 1));
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
                return new CompanyTypes();
            case COMPANYADDRESS:
                return new CompanyAddress();
            case COMPANYCONTACT:
                return new CompanyContact();
            case COMPANYJOBS:
                return new CompanyJobs();
            case COMPANYBENEFITS:
                return new CompanyBenefits();
            default:
                Log.e(TAG, "getNewClassInstance: classType out of Range");

            return null;
        }
    }


}
