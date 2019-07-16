package gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gullideckel.seasonhunter.ActSeasonHunter;
import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragEmailVerification;
import gullideckel.seasonhunter.ActivitySignIn.Authentification.Validation;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;

public class OnClickSignIn implements View.OnClickListener
{
    private EditText mSignEmail;
    private EditText mSignPassword;

    private Activity activity;

    private static final String TAG = "OnClickSignIn";
    private FirebaseAuth mAuth;

    public OnClickSignIn(EditText signEmail, EditText signPassword, Activity activity)
    {
        mSignEmail = signEmail;
        mSignPassword = signPassword;
        this.activity =  activity;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v)
    {
        SignIn(mSignEmail.getText().toString(), mSignPassword.getText().toString());
    }

    private void SignIn(String email, String password) {


        Log.d(TAG , "signIn:" + email);

        if (!Validation.ValidateForm(mSignEmail,mSignPassword)) {
            return;
        }


        //TODO: Enable Button so map activity pops up only one time!!!
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Log.d(TAG, "signInWithEmail:success");
                    if(mAuth.getCurrentUser().isEmailVerified())
                    {
                        Intent intent = new Intent(activity, ActSeasonHunter.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                    else
                         AlertDialogVerification();
                }
                else
                {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(activity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AlertDialogVerification()
    {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setTitle("Email not verified")
                .setMessage("Send new verification email?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ((IFragmentHandler)activity).onReplaceFragment(new FragEmailVerification(), IntFrag.REPLACE);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
