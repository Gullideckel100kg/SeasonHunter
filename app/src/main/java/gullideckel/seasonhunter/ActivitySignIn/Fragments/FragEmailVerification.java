package gullideckel.seasonhunter.ActivitySignIn.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.R;

public class FragEmailVerification extends Fragment
{
    private final static String TAG = "FragEmailVerification";

    private FirebaseAuth mAuth;

    private TextView mTxtVerifyEmail;
    private Button mBtnVerified;

    private IReplaceFragment mListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        mTxtVerifyEmail = (TextView) view.findViewById(R.id.txtVerifyEmail);
        mBtnVerified = (Button) view.findViewById(R.id.btnVerified);

        Button btnBack = (Button) view.findViewById(R.id.btnVerifyBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getFragmentManager().popBackStackImmediate();
            }
        });

        VerifiyEmail();
    }


    public void VerifiyEmail()
    {
        final FirebaseUser user = mAuth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG,"Sending email: successful");
                            mTxtVerifyEmail.setText("Email sent");
                            mBtnVerified.setText("Done");
                            mBtnVerified.setOnClickListener(VerifySuccessed);
                        }
                        else
                        {
                            Log.w(TAG, "Sending email: failure");
                            mTxtVerifyEmail.setText("Couldn't sent email!");
                            mBtnVerified.setText("TRY AGAIN");
                            mBtnVerified.setOnClickListener(VerifyAgain);
                        }

                        mBtnVerified.setVisibility(View.VISIBLE);
                        mBtnVerified.setEnabled(true);
                    }
                });
    }

    private View.OnClickListener VerifyAgain = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mBtnVerified.setEnabled(false);
            VerifiyEmail();
        }
    };

    private View.OnClickListener VerifySuccessed = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            mListener.onReplaceFragment(new FragSignInHunter());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_email_verification, container, false);
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IReplaceFragment)
        {
            mListener = (IReplaceFragment) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

}
