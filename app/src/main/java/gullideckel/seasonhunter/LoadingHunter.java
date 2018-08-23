package gullideckel.seasonhunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoadingHunter extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_start_loading);

        OpenApp(LoadUser());
    }

    private boolean LoadUser()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            return true;
        }
        return false;
    }

    private void OpenApp(boolean isCurrentUser)
    {
        if(isCurrentUser)
        {
            Intent intent = new Intent(this, MapHunter.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, SignInHunter.class);
            startActivity(intent);
        }
        finish();
    }
}
