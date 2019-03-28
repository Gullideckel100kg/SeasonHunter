package gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import gullideckel.seasonhunter.ActSeasonHunter;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticVariabels;

public class OnClickGoogle implements View.OnClickListener
{
    private static final String TAG = "OnClickGoogle";

    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth auth;
    private Activity activity;

    public OnClickGoogle(Activity activity)
    {
        this.activity = activity;
        googleSignInClient = GoogleSignIn.getClient(activity, ConfigureSignIn());
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v)
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, StaticVariabels.Google_SIGN_IN);
    }

    private GoogleSignInOptions ConfigureSignIn(){
        // Configure sign-in to request the user's basic profile like name and email
        return  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }


    public void FirebaseAuthWithGoogle(final GoogleSignInAccount account)
    {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Log.d(TAG, "signInWithCredential:success");
                    FinishLogIn();
                }
                else
                {
                    // Now display a message to the user.
                    //TODO: May built in a google logIn in the app
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Snackbar.make(activity.findViewById(R.id.cnstLogIn), "Authentication Failed. You can Log In in you google account in the play store app", Snackbar.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void FinishLogIn()
    {
        Intent intent = new Intent(activity, ActSeasonHunter.class);
        activity.startActivity(intent);
        activity.finish();
    }

}
