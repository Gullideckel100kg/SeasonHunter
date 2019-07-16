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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import gullideckel.seasonhunter.ActSeasonHunter;
import gullideckel.seasonhunter.BuildConfig;
import gullideckel.seasonhunter.Objects.User.UserInfo;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticVariabels;

public class OnClickGoogle implements View.OnClickListener
{
    private static final String TAG = "OnClickGoogle";

    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth auth;
    FirebaseFirestore db;
    private Activity activity;

    public OnClickGoogle(Activity activity)
    {
        this.activity = activity;
        googleSignInClient = GoogleSignIn.getClient(activity, ConfigureSignIn());
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v)
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, StaticVariabels.Google_SIGN_IN);
    }

    private GoogleSignInOptions ConfigureSignIn()
    {
        String webClientId = "";

        //Checking if application is in release or debub
        if(BuildConfig.DEBUG)
            webClientId = activity.getString(R.string.web_client_id_debug);
        else
            webClientId = activity.getString(R.string.web_client_id);

        // Configure sign-in to request the user's basic profile like name and email
        return  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webClientId)
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
                    final String uId = task.getResult().getUser().getUid();
                    final String email = task.getResult().getUser().getEmail();

                    db.collection(activity.getString(R.string.db_user_info)).document(uId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                DocumentSnapshot document = task.getResult();
                                if(!document.exists())
                                {
                                    UserInfo info = new UserInfo(email, uId);
                                    db.collection(activity.getString(R.string.db_user_info)).document(uId)
                                            .set(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if(task.isSuccessful())
                                            {
                                                Log.i(TAG, "onComplete: New UserInfo document in Database");
                                                FinishLogIn();
                                            }
                                            else
                                                Log.e(TAG, "onComplete: ", task.getException());
                                        }
                                    });
                                }
                                else
                                {
                                    Log.d(TAG, "signInWithCredential:success");
                                    FinishLogIn();
                                }
                            }
                            else
                            {
                                Log.e(TAG, "onComplete: ", task.getException());
                            }
                        }
                    });

                }
                else
                {
                    // Now display a message to the user.
                    //TODO: Handle the Exception for solving the problem. For example if the user is blocked
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Snackbar.make(activity.findViewById(R.id.cnstLogIn), "Authentication Failed. ", Snackbar.LENGTH_LONG).show();

                }
            }
        });
    }

    private void FinishLogIn()
    {
        Intent intent = new Intent(activity, ActSeasonHunter.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

}
