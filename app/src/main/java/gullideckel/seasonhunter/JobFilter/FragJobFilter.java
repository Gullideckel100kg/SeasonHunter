package gullideckel.seasonhunter.JobFilter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.R;


public class FragJobFilter extends Fragment
{



    public static FragJobFilter newInstance()
    {
        FragJobFilter fragment = new FragJobFilter();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_job_filter, container, false);
    }


}
