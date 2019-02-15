package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;


public class FragCompanyData extends Fragment
{
    protected CompanyDocument doc;

    public static FragCompanyData newInstance(CompanyDocument doc)
    {
        FragCompanyData fragment = new FragCompanyData();
        fragment.doc = doc;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_company_data, container, false);

        InitCompanyData initData = new InitCompanyData(view, getActivity());
        initData.Init(doc);

        return view;
    }


}
