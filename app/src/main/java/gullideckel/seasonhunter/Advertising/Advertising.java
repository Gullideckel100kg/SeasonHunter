package gullideckel.seasonhunter.Advertising;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import gullideckel.seasonhunter.ActSeasonHunter;
import gullideckel.seasonhunter.Objects.User.UserInfo;
import gullideckel.seasonhunter.R;

public class Advertising
{
    private static final String TAG = "Advertising";
    private InterstitialAd bigAd;
    private Context context;

    private Runnable runnable;
    private Handler handler;
    private static final int delay = 1000 * 60 * 10;

    private FirebaseFirestore db;

    public Advertising(Context context)
    {
        this.context = context;
    }

    //TODO: Try to put the admob thing in an async task
    public void OpenAd(final AdView banner, boolean isAd)
    {
        if(isAd)
        {
            MobileAds.initialize(context, context.getString(R.string.admob_id));

            bigAd = new InterstitialAd(context);
            bigAd.setAdUnitId(context.getString(R.string.admob_intersitial));
            bigAd.loadAd(new AdRequest.Builder().build());
            bigAd.setAdListener(adListener);

            AdRequest adRequest = new AdRequest.Builder().build();
            banner.loadAd(adRequest);

            StartHandler();
        }
    }

    public void OnPause()
    {
        if(handler != null)
            handler.removeCallbacks(runnable);
    }

    public void OnResume()
    {
        StartHandler();
    }

    private void StartHandler()
    {
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run()
            {
                bigAd.loadAd(new AdRequest.Builder().build());
            }
        }, delay);
    }

    private AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded()
        {
            super.onAdLoaded();
            bigAd.show();
        }



        @Override
        public void onAdFailedToLoad(int i)
        {
            super.onAdFailedToLoad(i);
        }
    };

}
