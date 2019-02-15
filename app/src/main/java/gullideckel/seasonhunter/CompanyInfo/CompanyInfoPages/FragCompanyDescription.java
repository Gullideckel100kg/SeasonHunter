package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gullideckel.seasonhunter.R;


public class FragCompanyDescription extends Fragment
{
    protected String description;

    public static FragCompanyDescription newInstance(String description)
    {
        FragCompanyDescription fragment = new FragCompanyDescription();
        fragment.description = description;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_company_description, container, false);

        TextView txtDescription = (TextView) v.findViewById(R.id.txtJobDescription);
        txtDescription.setText(description);

        return v;
    }


}
