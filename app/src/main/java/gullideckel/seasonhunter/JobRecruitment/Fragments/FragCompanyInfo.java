package gullideckel.seasonhunter.JobRecruitment.Fragments;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragSignInHunter;
import gullideckel.seasonhunter.Interfaces.IEditCompanyType;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitment.Fragments.Adapters.AdapterAddedCompanyType;
import gullideckel.seasonhunter.JobRecruitment.Fragments.Adapters.AdapterCompanyType;
import gullideckel.seasonhunter.JobRecruitment.OnClick.OnClickNavigation;
import gullideckel.seasonhunter.Objects.CompanyTypeObject;
import gullideckel.seasonhunter.R;


public class FragCompanyInfo extends Fragment implements IEditCompanyType
{
    //TODO List of Company types add properly on Server
    private List<CompanyTypeObject> mCompanyTypes = new ArrayList<>();
    private List<CompanyTypeObject> mAddedCompanyType = new ArrayList<>();

    private IFragmentHandler mListener;

    private EditText mEdtAddOtherCompanyType;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    private AdapterAddedCompanyType mAdapterAddedCompanyType;
    private AdapterCompanyType mAdapterCompanyType;

    private boolean mIsCompanyChanged = false;

    public FragCompanyInfo()
    {
        mCompanyTypes.add(new CompanyTypeObject( "Farm",false));
        mCompanyTypes.add(new CompanyTypeObject("Packhouse",false));
        mCompanyTypes.add(new CompanyTypeObject("Hostel",false));
        mCompanyTypes.add(new CompanyTypeObject("Restaurant",false));
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragCompanyInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragCompanyInfo newInstance(String param1, String param2)
    {
        FragCompanyInfo fragment = new FragCompanyInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recAddCompanyType = (RecyclerView) view.findViewById(R.id.recAddCompanyType);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recAddCompanyType.setLayoutManager(layoutManager);

        mAdapterCompanyType = new AdapterCompanyType(mCompanyTypes, this);
        recAddCompanyType.setAdapter(mAdapterCompanyType);

        RecyclerView recAddedCompanyType = (RecyclerView) view.findViewById(R.id.recAddedCompanyType);

        StaggeredGridLayoutManager staggeredLayoutManager = new StaggeredGridLayoutManager(10, LinearLayoutManager.HORIZONTAL);

        mAdapterAddedCompanyType = new AdapterAddedCompanyType(mAddedCompanyType, this);

        recAddedCompanyType.setLayoutManager(staggeredLayoutManager);
        recAddedCompanyType.setAdapter(mAdapterAddedCompanyType);

        mEdtAddOtherCompanyType = (EditText) view.findViewById(R.id.edtAddOtherCompanyType);

        ((Button) view.findViewById(R.id.btnBackAddCompanyName)).setOnClickListener(new OnClickNavigation(null, IntFrag.POPSTACKCOMPLETLY, mListener));
        ((Button)view.findViewById(R.id.btnNextAddCompanyName)).setOnClickListener(new OnClickNavigation(new FragCompanyContact(), IntFrag.REPLACE, mListener));

        ((Button)view.findViewById(R.id.btnAddOtherCompanyType)).setOnClickListener(OnAddOtherCompanyType);
    }

    private Fragment StartNewInstance()
    {
        return FragCompanyContact.newInstance("", "");
    }

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

    //TODO: if I wanna have an animation I have to figure out how its gonna work without notifyDataSetChanged
    @Override
    public void onCompanyType(CompanyTypeObject companyType, int position)
    {
        if(mAddedCompanyType.contains(companyType) && !mIsCompanyChanged)
        {
            mAddedCompanyType.remove(companyType);
            mAdapterAddedCompanyType.notifyDataSetChanged();

            if(mCompanyTypes.contains(companyType) && mCompanyTypes.get(mCompanyTypes.indexOf(companyType)).GetChecked())
            {
                mCompanyTypes.get(mCompanyTypes.indexOf(companyType)).SetCompany(companyType.GetCompanyType(), false);
                mIsCompanyChanged = true;
                mAdapterCompanyType.notifyDataSetChanged();
            }
        }
        else if(position > mAddedCompanyType.size() - 1 && !mIsCompanyChanged)
        {
            mAddedCompanyType.add(companyType);
            mAdapterAddedCompanyType.notifyDataSetChanged();
        }
        else if (!mIsCompanyChanged)
        {
                mAddedCompanyType.add(position, companyType);
                mAdapterAddedCompanyType.notifyDataSetChanged();
        }
        else
            mIsCompanyChanged = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_company_info, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IFragmentHandler)
            mListener = (IFragmentHandler) context;
        else
            throw new RuntimeException(context.toString() + " must implement IFragmentHandler");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
}
