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
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.R;


// {@link IReplaceFragment} interface

public class FragSignInHunter extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IReplaceFragment mListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        EditText edtSignEmail = (EditText) view.findViewById(R.id.edtSignEmail);
        EditText edtSignPassword = (EditText) view.findViewById(R.id.edtSignPassword);

        TextView txtForgotPassword = (TextView) view.findViewById(R.id.txtForgotPassword);
        TextView txtSignInWithoutPassword = (TextView) view.findViewById(R.id.txtSignInWithoutPassword);

        Button btnCreateAccount = (Button) view.findViewById(R.id.btnCreateAccount);
        Button btnSignIn = (Button) view.findViewById(R.id.btnSignIn);

        final Fragment createAccount = new FragCreateAccountHunter();

        btnCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mListener.onReplaceFragment(new FragCreateAccountHunter(), null);
            }
        });

        //TODO: Check if possible declare Editbox in OnCreate!!!
        btnSignIn.setOnClickListener(new OnClickSignIn(edtSignEmail, edtSignPassword, getActivity()));
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
        if (context instanceof IReplaceFragment)
        {
            mListener = (IReplaceFragment) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement IReplaceFragment");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
}
