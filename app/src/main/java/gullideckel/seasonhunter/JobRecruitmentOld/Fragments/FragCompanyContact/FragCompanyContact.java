package gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyContact;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.Interfaces.IFragmentHandlerCompany;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyJobOffers;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;

public class FragCompanyContact extends Fragment
{
    private static final String TAG = "FragCompanyContact";

    protected JobInfoObject mJobInfo;
    private CompanyContact mContactInfo;

    private IFragmentHandlerCompany mListener;
    private AddressManager mAddressManager;

    private EditText mEdtCountry;
    private EditText mEdtPhoneNumber;
    private EditText mEdtBusinessNumber;
    private EditText mEdtEmail;
    private EditText mEdtWebsite;
    private CheckBox mChkOnlineRecruitment;
    private TextView mTxtAtLeastOneContact;

    //TODO: Add pick location from Map
    //TODO: Check Address and add automatically Country
    //TODO: Check if Website Address is real
    //TODO: Open a Map to Select Address and use manuell picking and google placeautocomplete search
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

        mContactInfo = new CompanyContact();
        mAddressManager = new AddressManager();
        mAddressManager.AutoCompleteListener(getActivity(), mContactInfo);

        mEdtBusinessNumber = (EditText) view.findViewById(R.id.edtAddBusinessNumber);
        mEdtCountry = (EditText) view.findViewById(R.id.edtAddPhoneNumber);
        mEdtEmail = (EditText) view.findViewById(R.id.edtAddEmail);
        mEdtWebsite = (EditText) view.findViewById(R.id.edtAddWebsite);
        mChkOnlineRecruitment = (CheckBox) view.findViewById(R.id.chkAddOnlineRecruitment);
        mTxtAtLeastOneContact = (TextView) view.findViewById(R.id.txtAtLeastOneContact);

    }

    private View.OnClickListener BackToCompanyInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mListener.onReplaceFragment(null, IntFrag.POPSTACK, mJobInfo);
        }
    };

    //TODO: Create better options for Contacts like a ListView so User can add by himself
    private View.OnClickListener NextToJobOffers = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(CheckIsFilledCorrectly())
            {
//                mJobInfo.GetCompanyContact().SetAddress(mContactInfo.GetAddress());
//                mJobInfo.GetCompanyContact().SetBuisnessNumber(mEdtBusinessNumber.getText().toString());
//                mJobInfo.GetCompanyContact().SetCountry(mEdtCountry.getText().toString());
                mJobInfo.GetCompanyContact().SetEmail(mEdtEmail.getText().toString());
                mJobInfo.GetCompanyContact().SetOnlineRecruitment(mChkOnlineRecruitment.isChecked());
                mJobInfo.GetCompanyContact().SetPhoneNumber(mEdtPhoneNumber.getText().toString());
                mJobInfo.GetCompanyContact().SetWebsite(mEdtWebsite.getText().toString());

                mListener.onReplaceFragment(FragCompanyJobOffers.newInstance(mJobInfo), IntFrag.REPLACE, mJobInfo);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.old_frag_company_contact, container, false);
        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();
        return view;
    }


    private boolean CheckIsFilledCorrectly()
    {
        if  (mEdtWebsite.getText().toString().isEmpty()
            && mEdtEmail.getText().toString().isEmpty()
            && mEdtBusinessNumber.getText().toString().isEmpty()
            && mEdtPhoneNumber.getText().toString().isEmpty())
        {
            mTxtAtLeastOneContact.setTextColor(Color.RED);
            return false;
        }

//        if(mContactInfo.GetAddress().isEmpty())
//        {
//            return false;
//        }


        if(mChkOnlineRecruitment.isChecked() && mEdtWebsite.getText().toString().isEmpty())
            return false;

        return true;
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
}
