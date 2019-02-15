package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.A_CompanyName;

import android.graphics.Color;
import android.view.View;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.Job.CompanyName;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class CompanyNameConfi extends CompanyDetailsBase
{
    private CompanyName item;

    public CompanyNameConfi(CompanyNameViewHolder vh, ICompanyDetails listener, CompanyDetails companyDetails)
    {
        super(vh, listener, companyDetails);
        item = getObjectAtPosition(CompanyName.class);
    }

    public void Confi()
    {
        getName().GetBtnSave().setOnClickListener(SaveCompanyName);
        getName().GetIBtnEdit().setOnClickListener(EditCompanyName);
        getName().GetEdtCompanyName().setOnFocusChangeListener(OnFocus);
    }

    private View.OnClickListener SaveCompanyName = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            if(getName().GetEdtCompanyName().getText().toString().isEmpty())
            {
                getName().GetEdtCompanyName().setHintTextColor(Color.RED);
            }
            else
            {
                getName().GetCnstEdit().setVisibility(View.GONE);
                getName().GetCnstSave().setVisibility(View.VISIBLE);
                getName().GetTxtCompanyName().setText(getName().GetEdtCompanyName().getText());
                item.setCompanyName(getName().GetTxtCompanyName().getText().toString());
                getListener().OnItemUpdate(CompanyDetails.COMPANYTYPE);
                StaticMethod.HideKeypadFrom(getContext(), getName().GetEdtCompanyName());
            }

        }
    };

    private View.OnClickListener EditCompanyName = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            getName().GetCnstEdit().setVisibility(View.VISIBLE);
            getName().GetCnstSave().setVisibility(View.GONE);
            getBtnPost().setVisibility(View.GONE);
        }
    };

    private View.OnFocusChangeListener OnFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            if(hasFocus)
                StaticMethod.ShowKeypadFrom(getContext(), v);
        }
    };
}
