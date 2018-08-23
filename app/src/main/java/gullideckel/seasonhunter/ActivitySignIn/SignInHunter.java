package gullideckel.seasonhunter.ActivitySignIn;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragSignInHunter;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.R;

public class SignInHunter extends FragmentActivity implements IReplaceFragment
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in_hunter);

        ReplaceFragment(new FragSignInHunter());
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragCreateAccount, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onReplaceFragment(Fragment fragment)
    {
        ReplaceFragment(fragment);
    }
}
