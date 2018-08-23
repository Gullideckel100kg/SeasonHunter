package gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

import gullideckel.seasonhunter.ActivityMap.MapHunter;
import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragEmailVerification;
import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.Authentification.Validation;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;

public class OnClickSignIn implements View.OnClickListener
{
    private EditText mSignEmail;
    private EditText mSignPassword;

    private Activity mContext;

    private static final String TAG = "OnClickSignIn";
    private FirebaseAuth mAuth;

    public OnClickSignIn(EditText signEmail, EditText signPassword, Context context)
    {
        mSignEmail = signEmail;
        mSignPassword = signPassword;
        mContext = (Activity) context;
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
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mContext, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Log.d(TAG, "signInWithEmail:success");
                    if(mAuth.getCurrentUser().isEmailVerified())
                        OpenMapHunter();
                    else
                    {
                         AlertDialogVerification();
                    }
                }
                else
                {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(mContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void OpenMapHunter()
    {
        Intent intent = new Intent(mContext, MapHunter.class);
        mContext.startActivity(intent);
        mContext.finish();
    }

    private void AlertDialogVerification()
    {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle("Email not verified")
                .setMessage("Send new verification email?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ((IReplaceFragment)mContext).onReplaceFragment(new FragEmailVerification());
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
