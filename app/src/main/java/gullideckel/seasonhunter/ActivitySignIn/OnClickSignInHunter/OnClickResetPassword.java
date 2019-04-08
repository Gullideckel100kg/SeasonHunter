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
import com.google.firebase.auth.FirebaseAuth;

public class OnClickResetPassword implements View.OnClickListener
{
    private static final String TAG = "OnClickResetPassword";

    private EditText mEdtEmail;
    private Activity mContext;

    public OnClickResetPassword(EditText edtEmail, Context context)
    {
        mEdtEmail = edtEmail;
        mContext = (Activity) context;
    }

    @Override
    public void onClick(View v)
    {
        FirebaseAuth.getInstance().sendPasswordResetEmail(mEdtEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG, "Email sent: successfull");
                            Toast.makeText(mContext, "E-mail sent", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.w(TAG, "Email sent: failure ");
                            Toast.makeText(mContext, "Couldn't send email. Check if email is correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
