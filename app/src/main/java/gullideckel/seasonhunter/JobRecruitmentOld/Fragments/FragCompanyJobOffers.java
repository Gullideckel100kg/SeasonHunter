package gullideckel.seasonhunter.JobRecruitmentOld.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.Interfaces.IFragmentHandlerCompany;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragCompanyJobOffers.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragCompanyJobOffers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragCompanyJobOffers extends Fragment
{
    private IFragmentHandlerCompany mListener;

    protected JobInfoObject mJobInfo;

    public static FragCompanyJobOffers newInstance(JobInfoObject jobInfo)
    {
        FragCompanyJobOffers fragment = new FragCompanyJobOffers();
        fragment.mJobInfo = jobInfo;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.old_frag_company_job_offers, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IFragmentHandlerCompany)
        {
            mListener = (IFragmentHandlerCompany) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentHandlerCompany");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
}
