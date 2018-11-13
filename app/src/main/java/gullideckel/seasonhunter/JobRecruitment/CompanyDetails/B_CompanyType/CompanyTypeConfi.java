package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsObject;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.ComplexCompanyDetailsAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyTypes;

public class CompanyTypeConfi extends CompanyDetailsBase
{
    private CompanyTypes items;
    private CompanyTypeAdapter mAdapter;

    public CompanyTypeConfi(CompanyTypeViewHolder vh, ICompanyDetails listener, CompanyDetailsObject companyDetails, List<CompanyTypes.CompanyType> companyTypes)
    {
        super(vh, listener, companyDetails);
        this.items = getObjectAtPosition(CompanyTypes.class);
        this.items.setCompanyTypes(companyTypes);
    }

    public void Confi()
    {
        mAdapter = new CompanyTypeAdapter(items);
        getType().GetRclyCompanyType().setLayoutManager(new LinearLayoutManager(getContext()));
        getType().GetRclyCompanyType().setAdapter(mAdapter);

        getType().GetBtnSave().setOnClickListener(SaveCompanyType);
        getType().GetIBtnEdit().setOnClickListener(EditCompanyType);
    }

    private View.OnClickListener SaveCompanyType = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(items.getSelectedCompanyType() > -1)
            {
                getType().GetCnstEdit().setVisibility(View.VISIBLE);
                getType().GetCnstSave().setVisibility(View.GONE);
                getType().GetImgCompanyLogo().setImageBitmap(items.getCompanyTypes().get(items.getSelectedCompanyType()).GetLogo());
                getType().GetTxtCompanyType().setText(items.getCompanyTypes().get(items.getSelectedCompanyType()).GetCompanyType());
                getListener().OnItemUpdate(ComplexCompanyDetailsAdapter.COMPANYTYPE);
            }
            else
                getType().GetSelectCompanyType().setTextColor(Color.RED);
        }
    };

    private View.OnClickListener EditCompanyType = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            getType().GetSelectCompanyType().setTextColor(Color.BLACK);
            getType().GetCnstEdit().setVisibility(View.GONE);
            getType().GetCnstSave().setVisibility(View.VISIBLE);
        }
    };
}
