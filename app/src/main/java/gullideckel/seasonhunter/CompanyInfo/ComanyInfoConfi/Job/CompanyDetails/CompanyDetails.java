package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.firestore.Exclude;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gullideckel.seasonhunter.CostumLayouts.CostumLayoutManager;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyBenefits;
import gullideckel.seasonhunter.Objects.Job.CompanyContact;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.Objects.Job.CompanyName;

public final class CompanyDetails
{

    //TODO:Make an inheritance for CompanyDocument
    public static final String TAG = "CompanyDetails";

    public static final int COMPANYNAME = 0;
    public static final int COMPANYTYPE = 1;
    public static final int COMPANYADDRESS = 2;
    public static final int COMPANYCONTACT = 3;
    public static final int COMPANYJOBS = 4;
    public static final int COMPANYBENEFITS = 5;
    public static final int POST = 6;

    @IntDef({COMPANYNAME, COMPANYTYPE, COMPANYADDRESS, COMPANYJOBS, COMPANYCONTACT, COMPANYBENEFITS, POST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CompanyDetail {}

    private List<Object> items;
    private CostumLayoutManager layoutManager;
    private RecyclerView rcylView;
    private Context context;
    private Button btnPost;
    private GoogleApiClient googleApiClient;

    private CompanyName name;
    private List<String> types;
    private CompanyAddress address;
    private CompanyContact contact;
    private CompanyJobs jobs;
    private CompanyBenefits benefits;

    //Empty Constructor needed for Firebase Firestore!!!
    public CompanyDetails() {}

    public CompanyDetails(Context context, Button btnPost, RecyclerView rcylView, GoogleApiClient googleApiClient)
    {
        this.items = items;
        this.rcylView = rcylView;

        this.layoutManager = (CostumLayoutManager) rcylView.getLayoutManager();
        this.context = context;
        this.btnPost = btnPost;
        this.googleApiClient = googleApiClient;

        name = new CompanyName();
        address = new CompanyAddress();
        contact = new CompanyContact();
        jobs = new CompanyJobs();
        benefits = new CompanyBenefits();

        items = new ArrayList<Object>(Arrays.asList(name, types, address, contact, jobs, benefits));
    }

    @Exclude
    public List<Object> getItems()
    {
        return items;
    }

    @Exclude
    public RecyclerView getRcylView()
    {
        return rcylView;
    }

    @Exclude
    public GoogleApiClient getGoogleApiClient()
    {
        return googleApiClient;
    }

    @Exclude
    public CostumLayoutManager getLayoutManager()
    {
        return layoutManager;
    }

    @Exclude
    public Context getContext()
    {
        return context;
    }

    @Exclude
    public Button getBtnPost()
    {
        return btnPost;
    }

    public String getName()
    {
        return name.getCompanyName();
    }

    public String getType()
    {
//        if(types.getSelectedCompanyType() > -1 && types.getCompanyTypes().size() > types.getSelectedCompanyType())
//            return types.getCompanyTypes().get(types.getSelectedCompanyType()).getCompanyType();
//        else
//        {
//            Log.d(TAG, "getType: SelectedCompanyType is Out of Index ");
//            return null;
//        }
        return  null;
    }

    public CompanyAddress getAddress()
    {
        return address;
    }

    public CompanyContact getContact()
    {
        return contact;
    }

    public CompanyJobs getJobs()
    {
        return jobs;
    }

//    public List<CompanyBenefits.CompanyBenefit> getBenefits() { return benefits.getCompanyBenefitsSaved(); }

    public String getBenifits()
    {
        return  benefits.getFeatures();
    }
}
