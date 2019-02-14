package gullideckel.seasonhunter.JobList;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.R;


public class FragJobList extends Fragment
{


    public FragJobList()
    {
        // Required empty public constructor
    }


    public static FragJobList newInstance()
    {
        FragJobList fragment = new FragJobList();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_job_list, container, false);
    }

}
