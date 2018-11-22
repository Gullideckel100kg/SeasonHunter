package gullideckel.seasonhunter.ActivitySignIn;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import gullideckel.seasonhunter.ActivitySignIn.Fragments.FragSignInHunter;
import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickSignIn;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.R;

public class SignInHunter extends FragmentActivity implements IFragmentHandler
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in_hunter);

        ReplaceFragment(new FragSignInHunter());
    }

    @Override
    public void onReplaceFragment(Fragment fragment, int intFrag)
    {
        switch (intFrag)
        {
            case IntFrag.REPLACE:
                ReplaceFragment(fragment);
            case IntFrag.POPSTACK:
                PopStack();
        }
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragCreateAccount, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private  void PopStack()
    {
        getSupportFragmentManager().popBackStackImmediate();
    }

    private void PopStackCompeletly()
    {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
