package gullideckel.seasonhunter.CompanyInfo;

import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.firebase.firestore.Transaction;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Review.FragAddReview;
import gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyData.FragCompanyData;
import gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.FragCompanyDescription;
import gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.FragCompanyPhotos;
import gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.FragCompanyReview;
import gullideckel.seasonhunter.CostumLayouts.NonSwipeViewPager;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.SeasonHunterViewPagerAdapter;

public class FragCompanyInfo extends Fragment
{
    private NonSwipeViewPager vPCompanyInfo;
    private TabLayout tabCompanyInfo;

    private FragCompanyData fragData;
    private FragCompanyReview fragReview;
    private FragCompanyDescription fragDescription;
    private FragCompanyPhotos fragPhotos;

    private ImageButton imbAddReview;

    protected CompanyDocument doc;

    public static FragCompanyInfo NewInstance(CompanyDocument doc)
    {
        FragCompanyInfo frag = new FragCompanyInfo();
        frag.doc = doc;
        return frag;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.bringToFront();
        view.setBackgroundColor(Color.WHITE);

        imbAddReview = (ImageButton) view.findViewById(R.id.imbAddReview);
        imbAddReview.setOnClickListener(AddReview);

        vPCompanyInfo = (NonSwipeViewPager) view.findViewById(R.id.vPCompanyInfo);
        tabCompanyInfo = (TabLayout) view.findViewById(R.id.tabCompanyInfo);

        SeasonHunterViewPagerAdapter adapter = SetupAdapter();
        vPCompanyInfo.setAdapter(SetupAdapter());

        if(adapter.getCount() == 1)
            tabCompanyInfo.setVisibility(View.GONE);
        else
            tabCompanyInfo.setupWithViewPager(vPCompanyInfo);

        vPCompanyInfo.setOffscreenPageLimit(adapter.getCount());
    }

    private View.OnClickListener AddReview = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            imbAddReview.setEnabled(false);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.frmInfoFragHolder, FragAddReview.newInstance());
            transaction.addToBackStack(null);
            transaction.commit();
            imbAddReview.setEnabled(true);
        }
    };

    private SeasonHunterViewPagerAdapter SetupAdapter()
    {
        SeasonHunterViewPagerAdapter adapter =  new SeasonHunterViewPagerAdapter(getChildFragmentManager());

        fragData = FragCompanyData.newInstance(doc);
        adapter.addFrag(fragData, getContext().getString(R.string.info));

        if(doc.getReview() != null)
        {
            fragReview = FragCompanyReview.newInstance();
            adapter.addFrag(fragReview, getContext().getString(R.string.reviews));
        }
        else if(!doc.getExtras().getDescription().isEmpty())
        {
            fragDescription = FragCompanyDescription.newInstance(doc.getExtras().getDescription());
            adapter.addFrag(fragDescription, getContext().getString(R.string.description));
        }
        else if(doc.getPhoto() != null)
        {
            fragPhotos = FragCompanyPhotos.newInstance();
            adapter.addFrag(fragPhotos, getContext().getString(R.string.photos));
        }

        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_company_info, container, false);
    }


}
