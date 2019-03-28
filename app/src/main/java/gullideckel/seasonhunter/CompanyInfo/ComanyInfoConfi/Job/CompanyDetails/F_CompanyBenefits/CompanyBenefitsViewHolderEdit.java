package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.F_CompanyBenefits;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class CompanyBenefitsViewHolderEdit extends RecyclerView.ViewHolder
{
    private ImageView imgBenefitLogo;
    private TextView txtBenefit;

    private View v;

    public ImageView getImgBenefitLogo()
    {
        return imgBenefitLogo;
    }

    public TextView getTxtBenefit()
    {
        return txtBenefit;
    }

    public View getV()
    {
        return v;
    }

    public CompanyBenefitsViewHolderEdit(View v)
    {
        super(v);
        this.v = v;
        imgBenefitLogo = (ImageView) v.findViewById(R.id.imgBenefitLogo);
        txtBenefit = (TextView) v.findViewById(R.id.txtBenefit);


    }
}
