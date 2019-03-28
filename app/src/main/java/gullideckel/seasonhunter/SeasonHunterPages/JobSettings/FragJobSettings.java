package gullideckel.seasonhunter.SeasonHunterPages.JobSettings;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.SeasonHunterPages.JobSettings.Message.FragJobSettingsMessage;


public class FragJobSettings extends Fragment
{

    private Button btnMessage;
    private Button btnShare;
    private Button btnRate;
    private Button btnLogout;

    public static FragJobSettings newInstance()
    {
        FragJobSettings fragment = new FragJobSettings();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_job_settings, container, false);

        btnMessage = (Button) v.findViewById(R.id.btnSettingsMessage);
        btnShare = (Button) v.findViewById(R.id.btnSettingsShare);
        btnRate = (Button) v.findViewById(R.id.btnSettingsRate);
        btnLogout = (Button) v.findViewById(R.id.btnSettingsLogOut);

        btnLogout.setOnClickListener(Logout);
        btnMessage.setOnClickListener(Message);

        return v;
    }

    private View.OnClickListener Logout = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), SignInHunter.class);
            getActivity().startActivity(intent);
            getActivity().finish();

        }
    };

    private View.OnClickListener Message = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.frmSettings    , FragJobSettingsMessage.newInstance());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    private View.OnClickListener Share = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

        }
    };

    private View.OnClickListener Rate = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getContext(), " unable to find market app", Toast.LENGTH_LONG).show();
            }
        }
    };


}
