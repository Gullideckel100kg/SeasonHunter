package gullideckel.seasonhunter.ActivityMap.MapHunterClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.JobRecruitment.CompanyName.FragCompanyName;
import gullideckel.seasonhunter.R;

public class ButtonClicks
{
    private Activity mActivity;
    private Button mBtnAddCompany;
    private Button mBtnLogOut;

    public ButtonClicks(View view, Activity activity)
    {
        mBtnAddCompany = (Button)  view.findViewById(R.id.btnAddCompany);
        mBtnLogOut = (Button) view.findViewById(R.id.btnLogout);
        mActivity = activity;
    }

    public void AddNewCompanyClickEvent()
    {
        mBtnAddCompany.setOnClickListener(ClickNewCompany);
    }

    public void AddNewLogOutClickEvent()
    {
        mBtnLogOut.setOnClickListener(ClickLockOut);
    }


    private View.OnClickListener ClickNewCompany = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            ((IReplaceFragment) mActivity).onReplaceFragment(new FragCompanyName());
        }
    };

    private View.OnClickListener ClickLockOut = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(mActivity, SignInHunter.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }
    };
}
