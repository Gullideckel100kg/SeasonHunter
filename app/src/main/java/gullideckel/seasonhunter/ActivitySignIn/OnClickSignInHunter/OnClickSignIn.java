package gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.Authentification.Validation;

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

        if (!ValidateForm()) {
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
                    OpenMapHunter();
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

    //TODO:Hide typed in Password!!!
    //This method checks the correctness of the typed in Email and Password!!!
    public boolean ValidateForm() {
        boolean valid = true;

        String email = mSignEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mSignEmail.setError("Required.");
            valid = false;
        } else {
            mSignEmail.setError(null);
        }

        String password = mSignPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mSignPassword.setError("Required.");
            valid = false;
        } else {
            mSignPassword.setError(null);
        }

        return valid;
    }
}
