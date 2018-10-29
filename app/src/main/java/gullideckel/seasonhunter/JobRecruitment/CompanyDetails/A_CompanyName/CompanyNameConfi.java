package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyName;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class CompanyNameConfi
{
    private CompanyNameViewHolder mHolder;
    private ICompanyName mListener;
    private Context mContext;

    public CompanyNameConfi(CompanyNameViewHolder holder, ICompanyName listener, Context context)
    {
        mHolder = holder;
        mListener = listener;
        mContext = context;
    }

    public void Confi()
    {
        mHolder.GetBtnSave().setOnClickListener(SaveCompanyName);
        mHolder.GetIBtnEdit().setOnClickListener(EditCompanyName);
        mHolder.GetEdtCompanyName().setOnFocusChangeListener(OnFocus);
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
                StaticMethod.HideKeypadFrom(mContext, mHolder.GetEdtCompanyName());
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

    private View.OnFocusChangeListener OnFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            if(hasFocus)
                StaticMethod.ShowKeypadFrom(mContext, v);
        }
    };
}
