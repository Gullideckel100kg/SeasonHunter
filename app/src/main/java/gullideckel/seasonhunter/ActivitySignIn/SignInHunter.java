package gullideckel.seasonhunter.ActivitySignIn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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

import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickSignIn;
import gullideckel.seasonhunter.Authentification.Validation;
import gullideckel.seasonhunter.ActivitySignIn.Fragments.CreateAccountHunter;
import gullideckel.seasonhunter.ActivityMap.MapHunter;
import gullideckel.seasonhunter.R;

public class SignInHunter extends FragmentActivity implements CreateAccountHunter.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in_hunter);

        EditText edtSignEmail = (EditText) findViewById(R.id.edtSignEmail);
        EditText edtSignPassword = (EditText) findViewById(R.id.edtSignPassword);

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

        //TODO: Check if possible declare Editbox in OnCreate!!!
        btnSignIn.setOnClickListener(new OnClickSignIn(edtSignEmail, edtSignPassword, this));
    }

    private void AddFragment(Fragment fragment, Bundle bundle)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragCreateAccount, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    //TODO: Enable LogIn with the Google Account
    private void OpenWithGoogleAccount()
    {
        //Get UserInfo from Google Account
        //Check is Account already exist else save data on Server
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
