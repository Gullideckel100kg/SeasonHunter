package gullideckel.seasonhunter;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import gullideckel.seasonhunter.Firestore.LoadAllDocuments;
import gullideckel.seasonhunter.Firestore.SetReview;
import gullideckel.seasonhunter.Interfaces.IDocument;
import gullideckel.seasonhunter.Interfaces.IDocumentList;
import gullideckel.seasonhunter.Interfaces.IReview;
import gullideckel.seasonhunter.Interfaces.IReviewList;
import gullideckel.seasonhunter.JobFilter.FragJobFilter;
import gullideckel.seasonhunter.JobList.FragJobList;
import gullideckel.seasonhunter.JobMap.FragJobMap;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.FragCompanyDetails;
import gullideckel.seasonhunter.JobSettings.FragJobSettings;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Review.Review;


public class ActSeasonHunter extends FragmentActivity implements IDocumentList, IDocument
{
    private ViewPager vpSeasonHunter;
    private TabLayout tabSeasonHunter;

    private FrameLayout frmLoading;
    private LoadingScreen loadingScreen;

    private FragJobMap fragMap;
    private FragJobList fragList;
    private FragJobFilter fragFilter;
    private FragCompanyDetails fragNew;
    private FragJobSettings fragSettings;

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

        frmLoading = (FrameLayout) findViewById(R.id.frmLoading);

        vpSeasonHunter = (ViewPager) findViewById(R.id.vpSeasonHunter);
        tabSeasonHunter = (TabLayout) findViewById(R.id.tabSeasonHunter);

        loadingScreen = new LoadingScreen();
        frmLoading.addView(loadingScreen.onCreateLoadingView(getLayoutInflater(),this));

        db = FirebaseFirestore.getInstance();

        LoadAllDocuments loadDocuments = new LoadAllDocuments(this, this,db);
        loadDocuments.InitDocuments(getCountryType());

        fragMap = FragJobMap.newInstance();
        fragList = FragJobList.newInstance();
        fragFilter = FragJobFilter.newInstance();
        fragNew = FragCompanyDetails.NewInstance();
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
        frmLoading.removeAllViews();
        fragMap.InitDocuments(docs, getCountryType());
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
    public void RecieveDocument(CompanyDocument doc)
    {
        SetReview setReview = new SetReview(this , db);
        setReview.Set(doc);
    }
}
