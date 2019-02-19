package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyReviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gullideckel.seasonhunter.Objects.Review.Review;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticVariabels;


public class FragCompanyReview extends Fragment
{
    protected Review review;

    private TextView txtWorkPosition;
    private TextView txtWorkTime;
    private TextView txtWage;
    private TextView txtWageHead;
    private TextView txtSalary;
    private TextView txtComment;
    private TextView txtCommentHead;

    public static FragCompanyReview newInstance(Review review)
    {
        FragCompanyReview fragment = new FragCompanyReview();
        fragment.review = review;
        return fragment;
    }

    //TODO Convert date to ex. 2nd of Mai. Just looks nicer. Write there an static class
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_company_review, container, false);

        txtWorkPosition = v.findViewById(R.id.txtWorkPosition);
        txtWorkTime = v.findViewById(R.id.txtWorkTime);
        txtWage = v.findViewById(R.id.txtWage);
        txtWageHead = v.findViewById(R.id.txtWageHead);
        txtSalary = v.findViewById(R.id.txtPayment);
        txtComment = v.findViewById(R.id.txtReviewComment);
        txtCommentHead = v.findViewById(R.id.txtCommentHead);

        txtWorkPosition.setText(review.getWorkPosition());
        txtWorkTime.setText(review.getWorkTime().getStartDay() + "/" +
                            review.getWorkTime().getStartMonth() + "/" +
                            review.getWorkTime().getStartYear() + " - " +
                            review.getWorkTime().getEndDay() + "/" +
                            review.getWorkTime().getEndMonth() + "/" +
                            review.getWorkTime().getEndYear());

        if(review.getSalary().isEmpty() && review.getOtherPayment().isEmpty())
        {
            txtWageHead.setVisibility(View.GONE);
            txtWage.setVisibility(View.GONE);
        }
        else
        {
            switch (review.getWage())
            {
                case StaticVariabels.HOURLY:
                    txtWage.setText(getContext().getString(R.string.hourly_paid));
                    break;
                case StaticVariabels.PIECE_RATE:
                    txtWage.setText(getContext().getString(R.string.piece_work));
                case StaticVariabels.OTHER_PAYMENT:
                    txtWage.setText(review.getOtherPayment());
            }
        }

        txtSalary.setText(review.getSalary());

        StarRating starRating = new StarRating(v);
        starRating.SetStars(review.getStarRating());

        if(review.getComment().isEmpty())
            txtCommentHead.setVisibility(View.GONE);
        else
            txtComment.setText(review.getComment());

        return v;
    }


}
