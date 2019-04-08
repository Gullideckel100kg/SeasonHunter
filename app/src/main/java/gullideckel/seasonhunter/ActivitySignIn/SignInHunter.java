package gullideckel.seasonhunter.ActivitySignIn;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gullideckel.seasonhunter.ActSeasonHunter;
import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragCreateAccountHunter;
import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragForgotPassword;
import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickGoogle;
import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickSignIn;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticVariabels;

public class SignInHunter extends FragmentActivity implements IFragmentHandler
{
    private ImageButton imbHidePassword;
    private EditText edtSignPassword;
    private boolean isHidden;

    private OnClickGoogle clickGoogle;
//    private CallbackManager callbackManager;

    private boolean LoadUser()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null && user.isEmailVerified())
            return true;
        return false;
    }

    //TODO: Can be rewritten as a fragment and open the other windows as Fragments in a fragment(But check if availible in the lower versions)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(LoadUser())
        {
            Intent intent = new Intent(this, ActSeasonHunter.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.act_sign_in_hunter);

        EditText edtSignEmail = (EditText) findViewById(R.id.edtSignEmail);
        edtSignPassword = (EditText) findViewById(R.id.edtSignPassword);

        edtSignPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        TextView txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);

        TextView txtCreateAccount = (TextView) findViewById(R.id.txtCreateAccount);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        txtCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReplaceFragment(new FragCreateAccountHunter());
            }
        });

        btnSignIn.setOnClickListener(new OnClickSignIn(edtSignEmail, edtSignPassword, this));

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ReplaceFragment(new FragForgotPassword());
            }
        });


        clickGoogle = new OnClickGoogle(this);
        findViewById(R.id.btnLogGoogle).setOnClickListener(clickGoogle);

//        callbackManager = CallbackManager.Factory.create();
//        LoginButton btnFacebook = findViewById(R.id.btnLogInFacebook);
//        btnFacebook.setReadPermissions("email", "public_profile");
//        btnFacebook.registerCallback(callbackManager, new OnClickFacebook(this));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == StaticVariabels.Google_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                clickGoogle.FirebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragCreateAccount, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onReplaceFragment(Fragment fragment, int intFrag)
    {
        onBackPressed();
        ReplaceFragment(fragment);
    }


    private View.OnClickListener PasswordHide = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(isHidden)
            {
                edtSignPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                isHidden = false;
            }
            else
            {
                edtSignPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                isHidden = true;
            }

        }
    };
}
