package gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyInfo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Interfaces.IEditCompanyType;
import gullideckel.seasonhunter.Interfaces.IFragmentHandlerCompany;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.Adapters.AdapterAddedCompanyType;
import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.Adapters.AdapterCompanyType;
import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyContact.FragCompanyContact;
import gullideckel.seasonhunter.Objects.CompanyTypeObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyInfoObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;


public class FragCompanyInfo extends Fragment implements IEditCompanyType
{

    private List<CompanyTypeObject> mCompanyTypes;
    private List<CompanyTypeObject> mAddedCompanyType;

    private IFragmentHandlerCompany mListener;

    private EditText mEdtAddOtherCompanyType;
    private CheckBox mChkOrganic;
    private EditText mEdtCompanyName;

    private TextView mTxtNoCompanyName;

    private AdapterAddedCompanyType mAdapterAddedCompanyType;
    private AdapterCompanyType mAdapterCompanyType;

    private FillCompanyInfo mFillInfo;
    private CompanyTypePositions mCompanyPosition;

    public JobInfoObject mJobInfo;


    //TODO:Save CompanyTypes On Server
    private void FillCompanyTypes()
    {
        mCompanyTypes.add(new CompanyTypeObject( "Farm",false));
        mCompanyTypes.add(new CompanyTypeObject("Packhouse",false));
        mCompanyTypes.add(new CompanyTypeObject("Hostel",false));
        mCompanyTypes.add(new CompanyTypeObject("Restaurant",false));
    }

    public static FragCompanyInfo newInstance(JobInfoObject jobInfo)
    {
        FragCompanyInfo fragment = new FragCompanyInfo();
        fragment.mJobInfo = jobInfo;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();
        RecyclerView recAddCompanyType = (RecyclerView) view.findViewById(R.id.recAddCompanyType);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recAddCompanyType.setLayoutManager(layoutManager);

        mCompanyTypes = new ArrayList<>();
        mAddedCompanyType = new ArrayList<>();

        FillCompanyTypes();

        mAdapterCompanyType = new AdapterCompanyType(mCompanyTypes, this);
        recAddCompanyType.setAdapter(mAdapterCompanyType);

        RecyclerView recAddedCompanyType = (RecyclerView) view.findViewById(R.id.recAddedCompanyType);

        StaggeredGridLayoutManager staggeredLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        mAdapterAddedCompanyType = new AdapterAddedCompanyType(mAddedCompanyType, this);

        recAddedCompanyType.setLayoutManager(staggeredLayoutManager);
        recAddedCompanyType.setAdapter(mAdapterAddedCompanyType);

        mEdtAddOtherCompanyType = (EditText) view.findViewById(R.id.edtAddOtherCompanyType);
        mChkOrganic = (CheckBox) view.findViewById(R.id.chkAddOrganic);
        mTxtNoCompanyName = (TextView) view.findViewById(R.id.txtNoCompanyType);
        mEdtCompanyName = (EditText) view.findViewById(R.id.edtAddCompanyName);

        ((Button) view.findViewById(R.id.btnBackAddCompanyName)).setOnClickListener(BackToTheBeginning);
        ((Button)view.findViewById(R.id.btnNextAddCompanyName)).setOnClickListener(NextToCompanyContact);
        ((Button)view.findViewById(R.id.btnAddOtherCompanyType)).setOnClickListener(OnAddOtherCompanyType);

        mCompanyPosition = new CompanyTypePositions();
        mFillInfo = new FillCompanyInfo();

        mFillInfo.FillCompanyInfo(mJobInfo, mEdtCompanyName, mChkOrganic);
        mFillInfo.FillCompanyTypes(mJobInfo,mCompanyTypes, mAdapterCompanyType, mAddedCompanyType, mAdapterAddedCompanyType);
    }

    private View.OnClickListener NextToCompanyContact = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            mTxtNoCompanyName.setVisibility(View.GONE);

            if(mFillInfo.IsCorrectFilled(mEdtCompanyName, mAddedCompanyType, mTxtNoCompanyName))
            {
                List<String> companyTypes = new ArrayList<>();
                for (CompanyTypeObject obj: mAddedCompanyType)
                    companyTypes.add(obj.GetCompanyType());

                mJobInfo.SetCompanyInfo(new CompanyInfoObject(mEdtCompanyName.getText().toString(), companyTypes, mChkOrganic.isChecked()));
                mListener.onReplaceFragment(FragCompanyContact.newInstance(mJobInfo), IntFrag.REPLACE, mJobInfo);
            }
        }
    };

    private View.OnClickListener BackToTheBeginning = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mListener.onReplaceFragment(null, IntFrag.POPSTACK, new JobInfoObject());
        }
    };

    //TODO: For better List check set text uncapitalized, dont allow special charaktors and no numbers may as well
    private View.OnClickListener OnAddOtherCompanyType =  new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            for (CompanyTypeObject obj: mAddedCompanyType)
                if(obj.GetCompanyType().contains(mEdtAddOtherCompanyType.getText().toString()))
                {
                    Toast.makeText(getContext(), "This company type is already in the list", Toast.LENGTH_LONG).show();
                    return;
                }

            mAddedCompanyType.add(new CompanyTypeObject(mEdtAddOtherCompanyType.getText().toString(), false));
            mAdapterAddedCompanyType.notifyDataSetChanged();
        }
    };


    @Override
    public void onCompanyType(CompanyTypeObject companyType, int position)
    {
        mCompanyPosition.SetPositionCompanyType(companyType, mAdapterAddedCompanyType, mAdapterCompanyType, mCompanyTypes, mAddedCompanyType, position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.old_frag_company_info, container, false);
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
