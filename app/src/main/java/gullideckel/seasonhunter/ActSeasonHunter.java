package gullideckel.seasonhunter;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender.FragCostumRangeCalender;
import gullideckel.seasonhunter.Firestore.LoadAllDocuments;
import gullideckel.seasonhunter.Firestore.SetReview;
import gullideckel.seasonhunter.Interfaces.IDocumentReview;
import gullideckel.seasonhunter.Interfaces.IDocumentList;
import gullideckel.seasonhunter.Interfaces.IFilteredDocuments;
import gullideckel.seasonhunter.Objects.Review.Review;
import gullideckel.seasonhunter.SeasonHunterPages.JobFilter.FragJobFilter;
import gullideckel.seasonhunter.SeasonHunterPages.JobList.FragJobListView;
import gullideckel.seasonhunter.SeasonHunterPages.JobMap.FragJobMap;
import gullideckel.seasonhunter.SeasonHunterPages.JobSettings.FragJobSettings;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.SeasonHunterPages.NewCompany.FragNewCompany;


public class ActSeasonHunter extends FragmentActivity implements IDocumentList, IDocumentReview, IFilteredDocuments, GoogleApiClient.OnConnectionFailedListener
{
    private static final String TAG = "ActSeasonHunter";

    private ViewPager vpSeasonHunter;
    private TabLayout tabSeasonHunter;

    private FrameLayout frmLoading;
    private LoadingScreen loadingScreen;

    private FragJobMap fragMap;
    private FragJobListView fragList;
    private FragJobFilter fragFilter;
    private FragNewCompany fragNew;
    private FragJobSettings fragSettings;

    private AdView banner;
    private InterstitialAd bigAd;
    FirebaseFirestore db;

    //TODO VERY IMPORTANT!!!!!!!!!!
    //TODO if I have more than one country or many Jobs in one Country
    //TODO let user click first on a pic of the country/state zoom in till the final area is selected and then load the documents
    //TODO Will help the performance that are not to much docs are loaded in one moment
    //TODO After area is selected open the view with the viewpager

    //private void LoadCountryPage
    //private void LoadStatePage
    //private void OpenViewpager


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_season_hunter);

        MobileAds.initialize(this, getString(R.string.admob_id));

        bigAd = new InterstitialAd(this);
        bigAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        bigAd.loadAd(new AdRequest.Builder().build());

        bigAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded()
            {
                super.onAdLoaded();
                bigAd.show();
            }
        });

        banner = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);

        frmLoading = (FrameLayout) findViewById(R.id.frmLoading);

        vpSeasonHunter = (ViewPager) findViewById(R.id.vpSeasonHunter);
        tabSeasonHunter = (TabLayout) findViewById(R.id.tabSeasonHunter);

        loadingScreen = new LoadingScreen();
        frmLoading.addView(loadingScreen.onCreateLoadingView(getLayoutInflater(),this));

        db = FirebaseFirestore.getInstance();

        LoadAllDocuments loadDocuments = new LoadAllDocuments(this, this,db);
        loadDocuments.InitDocuments(getCountryType());

        GoogleApiClient client = getGoogleApiClient();

        fragMap = FragJobMap.newInstance(client);
        fragList = FragJobListView.newInstance(client);
        fragFilter = FragJobFilter.newInstance(this);
        fragNew = FragNewCompany.newInstance(client);
        fragSettings = FragJobSettings.newInstance();

        vpSeasonHunter.setAdapter(SetupAdapter());
        tabSeasonHunter.setupWithViewPager(vpSeasonHunter);
        vpSeasonHunter.setOffscreenPageLimit(5);
    }



    private SeasonHunterViewPagerAdapter SetupAdapter()
    {
        SeasonHunterViewPagerAdapter adapter = new SeasonHunterViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(fragMap, getString(R.string.map));
        adapter.addFrag(fragList, getString(R.string.list));
        adapter.addFrag(fragFilter, getString(R.string.filter));
        adapter.addFrag(fragNew, getString(R.string.add_company));
        adapter.addFrag(fragSettings, getString(R.string.settings));

        return adapter;
    }

    @Override
    public void recievedDocuments(List<CompanyDocument> docs)
    {
        if(frmLoading != null)
            frmLoading.removeAllViews();

        fragMap.InitDocuments(docs, getCountryType());
        fragList.InitDocuments(docs);
        fragFilter.SetDocs(docs);
    }


    //TODO Now it returns default value because there is no countrypart selection
    private int getCountryType()
    {
        return getIntent().getIntExtra(getString(R.string.country_part_key), -1);
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getLastFragmentManagerWithBack(getSupportFragmentManager());

        if (fm.getBackStackEntryCount() > 0)
        {
            fm.popBackStack();
            int count = fm.getBackStackEntryCount();
            return;
        }
        super.onBackPressed();
    }

    private FragmentManager getLastFragmentManagerWithBack(FragmentManager fm)
    {
        FragmentManager fmLast = fm;
        List<Fragment> fragments = fm.getFragments();

        for (Fragment f : fragments)
        {
            if ((f.getChildFragmentManager() != null) && (f.getChildFragmentManager().getBackStackEntryCount() > 0))
            {
                fmLast = f.getFragmentManager();
                FragmentManager fmChild = getLastFragmentManagerWithBack(f.getChildFragmentManager());

                if (fmChild != fmLast)
                    fmLast = fmChild;
            }
        }
        return fmLast;
    }


    @Override
    public void RecieveFilterdDocuments(List<CompanyDocument> docs)
    {
        fragList.InitDocuments(docs);
        fragMap.InitDocuments(docs, getCountryType());
        vpSeasonHunter.setCurrentItem(0);
    }

    private GoogleApiClient getGoogleApiClient()
    {
        return new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.e(TAG, "onConnectionFailed: " +  connectionResult.getErrorMessage());
    }

    @Override
    public void onRecieveReview(Review review)
    {
        SetReview setReview = new SetReview(this , db);
        setReview.Add(review);
    }
}
