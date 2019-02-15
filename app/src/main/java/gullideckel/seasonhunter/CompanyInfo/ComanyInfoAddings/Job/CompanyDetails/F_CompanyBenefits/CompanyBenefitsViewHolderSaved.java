package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.F_CompanyBenefits;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import gullideckel.seasonhunter.R;

public class CompanyBenefitsViewHolderSaved extends RecyclerView.ViewHolder
{
    private ImageView imgBenefit;

    public ImageView getImgBenefit()
    {
        return imgBenefit;
    }

    public CompanyBenefitsViewHolderSaved(View v)
    {
        super(v);

        imgBenefit = (ImageView) v.findViewById(R.id.imgBenefitSaved);

    }
}
