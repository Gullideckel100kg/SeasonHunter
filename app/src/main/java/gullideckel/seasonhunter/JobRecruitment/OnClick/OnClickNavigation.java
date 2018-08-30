package gullideckel.seasonhunter.JobRecruitment.OnClick;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;

public class OnClickNavigation implements View.OnClickListener
{
    private Fragment mFragment;
    private @IntFrag int mIntFrag;
    private IFragmentHandler mHandler;

    public  OnClickNavigation(Fragment fragment, @IntFrag int intFrag, IFragmentHandler handler)
    {
        mFragment = fragment;
        mIntFrag = intFrag;
        mHandler = handler;
    }

    @Override
    public void onClick(View v)
    {
        mHandler.onReplaceFragment(mFragment, mIntFrag);
    }
}
