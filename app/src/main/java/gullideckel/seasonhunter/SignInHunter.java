package gullideckel.seasonhunter;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gullideckel.seasonhunter.Authentification.Validation;

public class SignInHunter extends FragmentActivity implements CreateAccountHunter.OnFragmentInteractionListener
{

    private static final String TAG = "SignInHunter";
    private FirebaseAuth mAuth;
    private EditText mEdtSignEmail;
    private EditText mEdtSignPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in_hunter);


        mAuth = FirebaseAuth.getInstance();

        mEdtSignEmail = (EditText) findViewById(R.id.edtSignEmail);
        mEdtSignPassword = (EditText) findViewById(R.id.edtSignPassword);

        TextView txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        TextView txtSignInWithoutPassword = (TextView) findViewById(R.id.txtSignInWithoutPassword);

        Button btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        final Fragment createAccount = new CreateAccountHunter();

        btnCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddFragment(createAccount, null);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SignIn(mEdtSignEmail.getText().toString(), mEdtSignPassword.getText().toString());
            }
        });

    }

    private void AddFragment(Fragment fragment, Bundle bundle)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragCreateAccount, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void OpenWithGoogleAccount()
    {
        //Get UserInfo from Google Account
        //Check is Account already exist else save data on Server
        OpenMapHunter("","");
    }

    private void OpenWithFacebookAccount()
    {
        //Get UserInfo from Facebook Account
        //Check is Account already exist else save data on Server
        OpenMapHunter("","");
    }

    private void OpenMapHunter(String userName, String password)
    {
        //Open MapHunter
        onDestroy();
    }

    private void CreateAccount()
    {

    }

    private void SignIn(String email, String password) {

        Log.d(TAG , "signIn:" + email);
        Validation validation = new Validation();
        if (!validation.ValidateForm(mEdtSignEmail, mEdtSignPassword)) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
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
                            Toast.makeText(SignInHunter.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void OpenMapHunter()
    {
        Intent intent = new Intent(this, MapHunter.class);
        startActivity(intent);
        finish();
    }



    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
