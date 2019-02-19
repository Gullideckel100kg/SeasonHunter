package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyReviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import gullideckel.seasonhunter.Objects.Review.Review;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.SeasonHunterViewPagerAdapter;


public class FragReviewHolder extends Fragment
{
    private static final String TAG = "FragReviewHolder";

    protected List<Review> reviews;

    private TextView txtPage;
    private TextView txtPageTotal;
    private TextView txtSwipe;
    private LinearLayout linPageInfo;
    private ViewPager vpReview;

    public static FragReviewHolder newInstance(List<Review> reviews)
    {
        FragReviewHolder fragment = new FragReviewHolder();
        fragment.reviews = reviews;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_review_holder, container, false);

        txtPage = (TextView) v.findViewById(R.id.txtReviewPage);
        txtPageTotal = (TextView) v.findViewById(R.id.txtReviewTotal);
        txtSwipe = (TextView) v.findViewById(R.id.txtReviewSwipeAdvise);

        linPageInfo = (LinearLayout) v.findViewById(R.id.linReviewPageInfo);

        vpReview = (ViewPager) v.findViewById(R.id.vpReviews);

        SeasonHunterViewPagerAdapter adapter = new SeasonHunterViewPagerAdapter(getChildFragmentManager());

        if(reviews != null && reviews.size() > 0)
            for(Review review : reviews)
                adapter.addFrag(FragCompanyReview.newInstance(review), review.getId());
        else
            Log.wtf(TAG, "onCreateView: No reviews availibe. This fragment shouldn't have been built");

        vpReview.setAdapter(adapter);
        vpReview.addOnPageChangeListener(pageChange);

        InitPageInfo();

        return v;
    }

    private ViewPager.OnPageChangeListener pageChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(int position)
        {
            txtPage.setText(String.valueOf(position + 1));
            if(position == reviews.size() - 1)
                txtSwipe.setText(getContext().getString(R.string.swipe_review) + " >>>");
            else if(position == reviews.size() - 1)
                txtSwipe.setText("<<< " + getContext().getString(R.string.swipe_review));
            else if(position == 1)
                txtSwipe.setText("<<< " + getContext().getString(R.string.swipe_review) + " >>>");
        }

        @Override
        public void onPageScrollStateChanged(int state) { }
    };

    private void InitPageInfo()
    {
        if(reviews.size() == 1)
            linPageInfo.setVisibility(View.GONE);
        else
        {
            linPageInfo.setVisibility(View.VISIBLE);
            txtPageTotal.setText(String.valueOf(reviews.size()));
            txtPage.setText("1");
            txtSwipe.setText(getContext().getString(R.string.swipe_review) + " >>>");
        }
    }


}
