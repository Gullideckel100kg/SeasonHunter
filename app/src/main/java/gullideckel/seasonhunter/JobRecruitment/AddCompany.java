package gullideckel.seasonhunter.JobRecruitment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitment.Fragments.FragCompanyInfo;
import gullideckel.seasonhunter.R;

public class AddCompany extends FragmentActivity implements IFragmentHandler
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_company);

        ReplaceFragment(new FragCompanyInfo());

    }

    @Override
    public void onReplaceFragment(Fragment fragment,@IntFrag int intFrag)
    {
        switch (intFrag)
        {
            case IntFrag.REPLACE:
                ReplaceFragment(fragment);
                break;
            case IntFrag.POPSTACK:
                PopStack();
                break;
            case IntFrag.POPSTACKCOMPLETLY:
                 PopStackCompletly();
                 break;
        }
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmJobRecruitment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private  void PopStack()
    {
        getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onBackPressed()
    {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frmJobRecruitment);
        if(fragment != null && fragment instanceof FragCompanyInfo && fragment.isVisible())
            finish();
        else
            super.onBackPressed();
    }



    private void PopStackCompletly()
    {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
