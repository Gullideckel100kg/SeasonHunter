package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName;

import android.graphics.Color;
import android.view.View;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyName;

public class CompanyNameConfi
{
    private CompanyNameViewHolder mHolder;
    private ICompanyName mListener;

    public CompanyNameConfi(CompanyNameViewHolder holder, ICompanyName listener)
    {
        mHolder = holder;
        mListener = listener;
    }

    public void Confi()
    {
        mHolder.GetBtnSave().setOnClickListener(SaveCompanyName);
        mHolder.GetIBtnEdit().setOnClickListener(EditCompanyName);
    }

    private View.OnClickListener SaveCompanyName = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            if(mHolder.GetEdtCompanyName().getText().toString().isEmpty())
            {
                mHolder.GetEdtCompanyName().setHintTextColor(Color.RED);
            }
            else
            {
                mHolder.GetCnstEdit().setVisibility(View.GONE);
                mHolder.GetCnstSave().setVisibility(View.VISIBLE);
                mHolder.GetTxtCompanyName().setText(mHolder.GetEdtCompanyName().getText());
                mListener.OnCompanyName(mHolder.GetTxtCompanyName().getText().toString());
            }

        }
    };

    private View.OnClickListener EditCompanyName = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mHolder.GetCnstEdit().setVisibility(View.VISIBLE);
            mHolder.GetCnstSave().setVisibility(View.GONE);
        }
    };
}
