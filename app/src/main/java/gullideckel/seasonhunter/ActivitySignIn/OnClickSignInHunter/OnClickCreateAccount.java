package gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gullideckel.seasonhunter.Authentification.Validation;

public class OnClickCreateAccount implements View.OnClickListener
{
    private static final String TAG = "OnClickCreateAccount";

    private FirebaseAuth mAuth;

    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private EditText mEdtRepeatPassword;
    private Activity mContext;

    public  OnClickCreateAccount(EditText edtEmail, EditText edtPassword, EditText edtRepeatPassword, Context context)
    {
        mAuth = FirebaseAuth.getInstance();

        mEdtEmail = edtEmail;
        mEdtPassword = edtPassword;
        mEdtRepeatPassword = edtRepeatPassword;
        mContext = (Activity) context;
    }

    @Override
    public void onClick(View v)
    {
        CreateAccount();
    }

    private void CreateAccount()
    {
        Log.d(TAG, "createAccount:" + mEdtEmail.getText().toString());
        if (!Validation.ValidateForm(mEdtEmail, mEdtPassword)) {
            return;
        }


        mAuth.createUserWithEmailAndPassword(mEdtEmail.getText().toString(), mEdtPassword.getText().toString())
                .addOnCompleteListener(mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Log.d(TAG, "createUserWithEmail:success");
                        }
                        else
                        {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
}
