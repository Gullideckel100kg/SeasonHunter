package gullideckel.seasonhunter.ActivitySignIn;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragCreateAccountHunter;
import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragForgotPassword;
import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragSignInHunter;
import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickSignIn;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.R;

public class SignInHunter extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in_hunter);

        EditText edtSignEmail = (EditText) findViewById(R.id.edtSignEmail);
        EditText edtSignPassword = (EditText) findViewById(R.id.edtSignPassword);

        TextView txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
//        TextView txtSignInWithoutPassword = (TextView) findViewById(R.id.txtSignInWithoutPassword);

        Button btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnCreateAccount.setOnClickListener(new View.OnClickListener()
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
    }


    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragCreateAccount, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
