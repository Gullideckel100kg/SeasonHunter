package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.A_CompanyName.CompanyNameConfi;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.A_CompanyName.CompanyNameViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.B_CompanyType.CompanyTypeConfi;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.B_CompanyType.CompanyTypeViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.C_CompanyAddress.CompanyAddressConfi;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.C_CompanyAddress.CompanyAddressViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.D_CompanyContact.CompanyContactConfi;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.D_CompanyContact.CompanyContactViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.E_CompanyJob.CompanyJobConfi;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.E_CompanyJob.CompanyJobViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.F_CompanyBenefits.CompanyBenefitsConfi;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.F_CompanyBenefits.CompanyBenefitsViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyBenefits;
import gullideckel.seasonhunter.Objects.Job.CompanyContact;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.Objects.Job.CompanyName;
import gullideckel.seasonhunter.R;

//ICompanyDetails: This listener is for looking up the next Item not the previous one
public class ComplexCompanyDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICompanyDetails
{
    private static final String TAG = "ComplexCompanyDetails";

    private CompanyNameConfi nameConfi;
    private CompanyTypeConfi typeConfi;
    private CompanyAddressConfi addressConfi;
    private CompanyContactConfi contactConfi;
    private CompanyJobConfi jobConfi;
    private CompanyBenefitsConfi benefitsConfi;
    private CompanyDetails companyDetails;

    private boolean isPost = false;

    public ComplexCompanyDetailsAdapter(CompanyDetails detailsObject)
    {
        this.companyDetails = detailsObject;
    }


    @Override
    public int getItemViewType(int position)
    {
        if (companyDetails.getItems().get(position) instanceof CompanyName)
            return CompanyDetails.COMPANYNAME;
//        else if (companyDetails.getItems().get(position) instanceof nul)
//            return CompanyDetails.COMPANYTYPE;
        else if (companyDetails.getItems().get(position) instanceof CompanyAddress)
            return CompanyDetails.COMPANYADDRESS;
        else if (companyDetails.getItems().get(position) instanceof CompanyContact)
            return CompanyDetails.COMPANYCONTACT;
        else if (companyDetails.getItems().get(position) instanceof CompanyJobs)
            return CompanyDetails.COMPANYJOBS;
        else if (companyDetails.getItems().get(position) instanceof CompanyBenefits)
            return CompanyDetails.COMPANYBENEFITS;

        return -1;
    }

    int heightPreviousItems = 0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType)
        {
            case CompanyDetails.COMPANYNAME:
                View v1 = inflater.inflate(R.layout.details_company_name_viewholder, parent, false);
                viewHolder = new CompanyNameViewHolder(v1);
                heightPreviousItems += v1.getHeight();
                nameConfi = new CompanyNameConfi((CompanyNameViewHolder) viewHolder, this, companyDetails);
                nameConfi.Confi();
                break;
            case CompanyDetails.COMPANYTYPE:
                View v2 = inflater.inflate(R.layout.details_company_type_viewholder, parent, false);
                viewHolder = new CompanyTypeViewHolder(v2);
                heightPreviousItems += v2.getHeight();
                typeConfi = new CompanyTypeConfi((CompanyTypeViewHolder) viewHolder, this, companyDetails);
                break;
            case CompanyDetails.COMPANYADDRESS:
                View v3 = inflater.inflate(R.layout.details_company_address_viewholder, parent, false);
                viewHolder = new CompanyAddressViewHolder(v3);
                addressConfi = new CompanyAddressConfi((CompanyAddressViewHolder) viewHolder, this, companyDetails);
                break;
            case CompanyDetails.COMPANYCONTACT:
                View v4 = inflater.inflate(R.layout.details_company_contact_viewholder, parent, false);
                viewHolder = new CompanyContactViewHolder(v4);
                contactConfi = new CompanyContactConfi((CompanyContactViewHolder) viewHolder, this, companyDetails);
                break;
            case CompanyDetails.COMPANYJOBS:
                View v5 = inflater.inflate(R.layout.details_company_job_viewholder, parent, false);
                viewHolder = new CompanyJobViewHolder(v5);
                jobConfi = new CompanyJobConfi(viewHolder,this, companyDetails);
                break;
            case CompanyDetails.COMPANYBENEFITS:
                View v6 = inflater.inflate(R.layout.details_company_benefits_viewholder, parent, false);
                viewHolder = new CompanyBenefitsViewHolder(v6);
                benefitsConfi = new CompanyBenefitsConfi((CompanyBenefitsViewHolder) viewHolder, this, companyDetails);
                break;
            default:
                return null;
        }

        return viewHolder;
    }

    //This method is replaced with the Costum Method OnItemUpdate
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount()
    {
        return companyDetails.getItems().size();
    }

    @Override
    public void OnItemUpdate(@CompanyDetails.CompanyDetail int position)
    {

        switch (position)
        {
            case CompanyDetails.COMPANYTYPE:
                typeConfi.Confi();
                break;
            case CompanyDetails.COMPANYADDRESS:
//                CompanyTypes companyTypes = (CompanyTypes) companyDetails.getItems().get(CompanyDetails.COMPANYTYPE);
//                addressConfi.Confi(companyTypes.getCompanyTypes().get(companyTypes.getSelectedCompanyType()).getLogo());
                break;
            case CompanyDetails.COMPANYCONTACT:
                contactConfi.Confi();
                break;
            case CompanyDetails.COMPANYJOBS:
                jobConfi.Confi();
                break;
            case CompanyDetails.COMPANYBENEFITS:
                benefitsConfi.Confi();
                isPost = true;
                break;
        }

        if(isPost)
            companyDetails.getBtnPost().setVisibility(View.VISIBLE);
    }

}
