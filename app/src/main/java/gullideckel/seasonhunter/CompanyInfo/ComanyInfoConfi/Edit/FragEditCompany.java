package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.R;

public class FragEditCompany extends Fragment
{

    public static FragEditCompany newInstance()
    {
        FragEditCompany fragment = new FragEditCompany();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_edit_company, container, false);
    }

}
