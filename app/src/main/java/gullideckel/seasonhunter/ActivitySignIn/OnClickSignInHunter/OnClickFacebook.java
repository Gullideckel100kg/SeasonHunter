package gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import gullideckel.seasonhunter.ActSeasonHunter;

public class OnClickFacebook implements FacebookCallback<LoginResult>
{
    private final static String TAG = "OnClickFacebook";

    private Activity activity;
    private FirebaseAuth auth;

    public OnClickFacebook(Activity activity)
    {
        this.activity = activity;
        auth = FirebaseAuth.getInstance();
    }

    private void HandleFacebookAccessToken(AccessToken token)
    {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FinishLogIn();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(activity , "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void FinishLogIn()
    {
        Intent intent = new Intent(activity, ActSeasonHunter.class);
        activity.startActivity(intent);
    }

    @Override
    public void onSuccess(LoginResult loginResult)
    {
        Log.d(TAG, "facebook:onSuccess:" + loginResult);
        HandleFacebookAccessToken(loginResult.getAccessToken());
    }

    @Override
    public void onCancel()
    {
        Log.d(TAG, "facebook:onCancel");
    }

    @Override
    public void onError(FacebookException error)
    {
        Log.d(TAG, "facebook:onError", error);
    }
}
