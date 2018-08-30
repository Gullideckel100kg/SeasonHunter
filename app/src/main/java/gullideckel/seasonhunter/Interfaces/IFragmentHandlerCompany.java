package gullideckel.seasonhunter.Interfaces;

import android.support.v4.app.Fragment;

import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;

public interface IFragmentHandlerCompany
{
    void onReplaceFragment(Fragment fragment, @IntFrag int intFrag, JobInfoObject jobInfo);
}
