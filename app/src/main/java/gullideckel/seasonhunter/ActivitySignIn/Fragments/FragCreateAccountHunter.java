package gullideckel.seasonhunter.ActivitySignIn.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.R;


public class FragCreateAccountHunter extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.frag_create_account_hunter, container, false);

        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();

        final EditText edtCreateEmail = (EditText) view.findViewById(R.id.edtCreateEmail);
        final EditText edtCreatePassword = (EditText) view.findViewById(R.id.edtCreatePassword);
        final EditText edtRepeatPassword = (EditText) view.findViewById(R.id.edtRepeatPassword);

        TextView txtPasswordLength = (TextView) view.findViewById(R.id.txtPasswordLength);

        Button btnCreateAccount = (Button) view.findViewById(R.id.btnCreateNewAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(CheckPasswordLength(edtCreatePassword.getText().toString())
                        && CheckPasswordSync(edtCreatePassword.getText().toString(), edtRepeatPassword.getText().toString()))
                {
                    if(CheckEmailOnServer(edtCreateEmail.getText().toString()))
                    {
                        //TODO: Send Data to Server and LogIn
                    }
                }
            }
        });

        return view;
    }

    private boolean CheckPasswordLength(String password)
    {
        if(password.length() < 6)
            return false;
        else
            return true;
    }

    private boolean CheckPasswordSync(String password, String repeatedPassword)
    {
        if(password == repeatedPassword)
            return true;
        else
            return false;
    }

    private boolean CheckEmailOnServer(String eMail)
    {
        // Todo: Check on Firebase
        return false;
    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri)
//    {
//        if (mListener != null)
//        {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context)
//    {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener)
//        {
//            mListener = (OnFragmentInteractionListener) context;
//        } else
//        {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach()
//    {
//        super.onDetach();
//        mListener = null;
//    }


}
