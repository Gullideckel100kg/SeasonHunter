package gullideckel.seasonhunter.SeasonHunterPages.JobSettings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.R;


public class FragJobSettings extends Fragment
{


    public static FragJobSettings newInstance()
    {
        FragJobSettings fragment = new FragJobSettings();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_job_settings, container, false);
    }


}
