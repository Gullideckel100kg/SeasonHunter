package gullideckel.seasonhunter.Interfaces;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({IntFrag.REPLACE, IntFrag.POPSTACK, IntFrag.POPSTACKCOMPLETLY})
@Retention(RetentionPolicy.SOURCE)
public @interface IntFrag
{
    public static final int REPLACE = 0;
    public static final int POPSTACK = 1;
    public static final int POPSTACKCOMPLETLY = 2;
}
