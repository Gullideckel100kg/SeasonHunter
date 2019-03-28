package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import gullideckel.seasonhunter.CostumLayouts.RecyclerViewKeyPadSolution;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.A_CompanyName.CompanyNameViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.B_CompanyType.CompanyTypeViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.C_CompanyAddress.CompanyAddressViewHolder;
import gullideckel.seasonhunter.CostumLayouts.CostumLayoutManager;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.D_CompanyContact.CompanyContactViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.E_CompanyJob.CompanyJobViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.E_CompanyJob.FragCompanyJobs;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.F_CompanyBenefits.CompanyBenefitsViewHolder;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class CompanyDetailsBase extends RecyclerViewKeyPadSolution
{
    private final static String TAG = "CompanyDetailsBase";

    private RecyclerView.ViewHolder vh;
    private ICompanyDetails listener;
    private CompanyDetails detailObject;
    private Fragment fragment;

    public CompanyDetailsBase(RecyclerView.ViewHolder vh, ICompanyDetails listener, CompanyDetails detailObject)
    {
        super(detailObject.getRcylView());
        this.vh  = vh;
        this.listener = listener;
        this.detailObject = detailObject;
    }

    public CompanyDetailsBase(Fragment fragment, ICompanyDetails listener, CompanyDetails detailObject)
    {
        super(detailObject.getRcylView());
        this.fragment = fragment;
        this.listener = listener;
        this.detailObject = detailObject;
    }

    public CostumLayoutManager getLayoutManager()
    {
        return detailObject.getLayoutManager();
    }

    public Context getContext()
    {
        return detailObject.getContext();
    }

    public Button getBtnPost()
    {
        return detailObject.getBtnPost();
    }

    public List<Object> getItems()
    {
        return detailObject.getItems();
    }

    public <T> T getObjectAtPosition(Class<T> c)
    {
        if(StaticMethod.CastClass(c, detailObject.getItems()) != null)
            return StaticMethod.CastClass(c, detailObject.getItems());

        Log.d(TAG,"getObjectAtPosition: Wrong Instance");
        return null;
    }

    public CompanyDetails getDetailObject()
    {
        return detailObject;
    }

    public CompanyNameViewHolder getName()
    {
        return (CompanyNameViewHolder) vh;
    }

    public CompanyTypeViewHolder getType()
    {
        return (CompanyTypeViewHolder) vh;
    }

    public CompanyAddressViewHolder getAddress()
    {
        return (CompanyAddressViewHolder) vh;
    }

    public CompanyContactViewHolder getContact()
    {
        return (CompanyContactViewHolder) vh;
    }

    public CompanyJobViewHolder getJob() { return (CompanyJobViewHolder) vh;}

    public FragCompanyJobs getJobFrag()
    {
        return  (FragCompanyJobs) fragment;
    }

    public CompanyBenefitsViewHolder getBenefit(){ return  (CompanyBenefitsViewHolder) vh;}

    public ICompanyDetails getListener()
    {
        return listener;
    }

    public GoogleApiClient getGoogleApiClient()
    {
        return detailObject.getGoogleApiClient();
    }

    //TODO: Find an other solution for scrolltoPosition without delay
    public void ScrollToPositionDelayed(final int position)
    {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                detailObject.getLayoutManager().scrollToPosition(position);
            }
        }, 100);
    }

    public void ScrollToPosition(final int position)
    {
        new android.os.Handler().post(new Runnable() {
            @Override
            public void run()
            {
                detailObject.getLayoutManager().scrollToPosition(position);
            }
        });
    }
}
