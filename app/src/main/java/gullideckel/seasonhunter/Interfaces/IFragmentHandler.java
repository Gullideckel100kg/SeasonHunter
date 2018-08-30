package gullideckel.seasonhunter.Interfaces;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;


public interface IFragmentHandler
{
    void onReplaceFragment(Fragment fragment, @IntFrag int intFrag);
}

