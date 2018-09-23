package gullideckel.seasonhunter.JobRecruitment.CompanyName;


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
import android.widget.EditText;

import gullideckel.seasonhunter.Interfaces.IAddressHandler;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IFragmentHandlerCompany;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragCompanyName extends Fragment
{
    protected JobInfoObject JobInfo;

    private IAddressHandler mListener;

    private Button mBtnSaveCompanyName;
    private EditText mEdtCompanyName;

    public static FragCompanyName newInstance(JobInfoObject jobInfo)
    {
        FragCompanyName fragment = new FragCompanyName();
        fragment.JobInfo = jobInfo;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mBtnSaveCompanyName = (Button) view.findViewById(R.id.btnSaveCompanyName);
        mEdtCompanyName = (EditText) view.findViewById(R.id.edtCompanyName);

        mBtnSaveCompanyName.setOnClickListener(SaveCompanyName);
    }

    private View.OnClickListener SaveCompanyName = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(mEdtCompanyName.getText().toString().isEmpty())
                mEdtCompanyName.setHintTextColor(Color.RED);
            else
                mListener.SetAddress(mEdtCompanyName.getText().toString());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.frag_add_company_name, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IAddressHandler)
            mListener = (IAddressHandler) context;
        else
            throw new RuntimeException(context.toString() + " must implement IAddressHandler");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

}
