package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsObject;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.ComplexCompanyDetailsAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyName;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyName;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class CompanyNameConfi extends CompanyDetailsBase
{

    public CompanyNameConfi(CompanyNameViewHolder vh, ICompanyDetails listener, CompanyDetailsObject companyDetails)
    {
        super(vh, listener, companyDetails);
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
                getListener().OnItemUpdate(ComplexCompanyDetailsAdapter.COMPANYNAME);
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
