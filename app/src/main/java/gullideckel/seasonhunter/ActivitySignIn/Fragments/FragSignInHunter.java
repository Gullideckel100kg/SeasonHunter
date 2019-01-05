package gullideckel.seasonhunter.ActivitySignIn.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickSignIn;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.R;


//TODO: UI underlap behind the Icon
//TODO; No line breaks at Editboxes
public class FragSignInHunter extends Fragment
{
    private IFragmentHandler mListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


    }


    //TODO: Enable LogIn with the Google Account
    private void OpenWithGoogleAccount()
    {
        //Get UserInfo from Google Account
        //Check is Account already exist else save data on Server
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_sign_in_hunter, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IFragmentHandler)
        {
            mListener = (IFragmentHandler) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentHandler");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
}
