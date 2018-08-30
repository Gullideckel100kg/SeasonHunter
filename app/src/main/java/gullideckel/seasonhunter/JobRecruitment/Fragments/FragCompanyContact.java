package gullideckel.seasonhunter.JobRecruitment.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IFragmentHandlerCompany;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragCompanyContact.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragCompanyContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragCompanyContact extends Fragment
{
    protected JobInfoObject mJobInfo;

    private IFragmentHandlerCompany mListener;

    // TODO: Rename and change types and number of parameters
    public static FragCompanyContact newInstance(JobInfoObject jobInfo)
    {
        FragCompanyContact fragment = new FragCompanyContact();
        fragment.mJobInfo = jobInfo;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

//        ((Button) view.findViewById(R.id.btnNextAddCompanyContact)).setOnClickListener(new OnClickNavigation(new FragCompanyJobOffers(), IntFrag.REPLACE, mListener));
        ((Button) view.findViewById(R.id.btnBackAddCompanyContact)).setOnClickListener(BackToCompanyInfo);
    }

    private View.OnClickListener BackToCompanyInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mListener.onReplaceFragment(null, IntFrag.POPSTACK, null);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_company_contact, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IFragmentHandlerCompany)
            mListener = (IFragmentHandlerCompany) context;
        else
            throw new RuntimeException(context.toString() + " must implement IFragmentHandlerCompany");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
