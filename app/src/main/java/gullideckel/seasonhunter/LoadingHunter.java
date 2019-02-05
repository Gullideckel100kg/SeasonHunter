package gullideckel.seasonhunter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoadingHunter
{

    public boolean LoadUser()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            return true;
        }
        return false;
    }


//    public void OpenApp(Context context)
//    {
//        if(LoadUser())
//        {
//            Intent intent = new Intent(this, MapHunter.class);
//            startActivity(intent);
//        }
//        else
//        {
//            Intent intent = new Intent(this, SignInHunter.class);
//            startActivity(intent);
//        }
//        finish();
//    }
}
