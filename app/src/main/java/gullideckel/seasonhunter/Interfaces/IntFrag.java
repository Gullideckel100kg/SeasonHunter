package gullideckel.seasonhunter.Interfaces;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({IntFrag.ADD, IntFrag.REPLACE, IntFrag.POPSTACK, IntFrag.POPSTACKCOMPLETLY, IntFrag.REMOVE})
@Retention(RetentionPolicy.SOURCE)
public @interface IntFrag
{
    public static final int ADD = 0;
    public static final int REPLACE = 1;
    public static final int POPSTACK = 2;
    public static final int POPSTACKCOMPLETLY = 3;
    public static final int REMOVE = 4;
}
