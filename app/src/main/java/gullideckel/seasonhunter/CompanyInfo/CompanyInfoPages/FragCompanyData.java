package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.R;


public class FragCompanyData extends Fragment
{

    public static FragCompanyData newInstance()
    {
        FragCompanyData fragment = new FragCompanyData();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_company_data, container, false);
    }


}
