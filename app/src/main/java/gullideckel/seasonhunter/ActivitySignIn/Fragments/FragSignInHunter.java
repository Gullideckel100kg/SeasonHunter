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
//TODO: UI underlap behind the Icon
//TODO; No line breaks at Editboxes
public class FragSignInHunter extends Fragment
{
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
                mListener.onReplaceFragment(new FragCreateAccountHunter());
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
