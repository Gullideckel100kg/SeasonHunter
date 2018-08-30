package gullideckel.seasonhunter.ActivitySignIn.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickCreateAccount;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.R;


public class FragCreateAccountHunter extends Fragment
{
    //TODO: Checkbox show password
    //TODO: Password typed in hidden
    //TODO: No line breaks at Editboxes

    private IFragmentHandler mListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();

        EditText edtCreateEmail = (EditText) view.findViewById(R.id.edtCreateEmail);
        EditText edtCreatePassword = (EditText) view.findViewById(R.id.edtCreatePassword);

        Button btnCreateAccount = (Button) view.findViewById(R.id.btnCreateNewAccount);

        btnCreateAccount.setOnClickListener(new OnClickCreateAccount(edtCreateEmail,edtCreatePassword, getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_create_account_hunter, container, false);
    }

//    @Override
//    public void onAttach(Context context)
//    {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener)
//        {
//            mListener = (OnFragmentInteractionListener) context;
//        } else
//        {
//            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach()
//    {
//        super.onDetach();
//        mListener = null;
//    }


}
