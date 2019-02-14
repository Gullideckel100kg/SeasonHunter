package gullideckel.seasonhunter.CompanyInfo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.FragCompanyData;
import gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.FragCompanyReview;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.JobFilter.FragJobFilter;
import gullideckel.seasonhunter.JobList.FragJobList;
import gullideckel.seasonhunter.JobMap.FragJobMap;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.FragCompanyDetails;
import gullideckel.seasonhunter.JobSettings.FragJobSettings;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.SeasonHunterViewPagerAdapter;

public class FragCompanyInfo extends Fragment
{
    private ViewPager vPCompanyInfo;
    private TabLayout tabCompanyInfo;

    private FragCompanyData fragData;
    private FragCompanyReview fragReview;

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

        int pageCount = 1;

        vPCompanyInfo = (ViewPager) view.findViewById(R.id.vPCompanyInfo);
        tabCompanyInfo = (TabLayout) view.findViewById(R.id.tabCompanyInfo);

        fragData = FragCompanyData.newInstance();
        fragReview = FragCompanyReview.newInstance();

        SeasonHunterViewPagerAdapter adapter =  new SeasonHunterViewPagerAdapter(getChildFragmentManager());

        adapter.addFrag(fragData, "Data");

        vPCompanyInfo.setAdapter(SetupAdapter());
        tabCompanyInfo.setupWithViewPager(vPCompanyInfo);
        vPCompanyInfo.setOffscreenPageLimit(2);

//        InitCompanyInfo compInit = new InitCompanyInfo(view, getContext());
//
//        compInit.Init(doc);

    }

    private SeasonHunterViewPagerAdapter SetupAdapter()
    {
        SeasonHunterViewPagerAdapter adapter =  new SeasonHunterViewPagerAdapter(getChildFragmentManager());

        adapter.addFrag();
        adapter.addFrag(fragReview, "Review");

        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_company_info, container, false);
    }


}
