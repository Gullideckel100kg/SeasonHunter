package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;

public class CompanyTypeConfi extends CompanyDetailsBase
{
//    private CompanyTypes items;
    private CompanyTypeAdapter adapter;

    public CompanyTypeConfi(CompanyTypeViewHolder vh, ICompanyDetails listener, CompanyDetails companyDetails)
    {
        super(vh, listener, companyDetails);
//        this.items = getObjectAtPosition(CompanyTypes.class);
    }

    public void Confi()
    {
        if(getType().getRelType().getVisibility() == View.INVISIBLE || getType().getRelType().getVisibility() == View.GONE)
        {
//            adapter = new CompanyTypeAdapter(items);
            getType().GetRclyCompanyType().setLayoutManager(new LinearLayoutManager(getContext()));
            getType().GetRclyCompanyType().setAdapter(adapter);

            getType().GetBtnSave().setOnClickListener(SaveCompanyType);
            getType().GetIBtnEdit().setOnClickListener(EditCompanyType);

            getType().getRelType().setVisibility(View.VISIBLE);
            OpenType();
        }
    }

    private View.OnClickListener SaveCompanyType = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
//            if(items.getSelectedCompanyType() > -1)
//            {
//                getType().GetCnstEdit().setVisibility(View.VISIBLE);
//                getType().GetCnstSave().setVisibility(View.GONE);
//                getType().GetImgCompanyLogo().setImageBitmap(items.getCompanyTypes().get(items.getSelectedCompanyType()).getLogo());
//                getType().GetTxtCompanyType().setText(items.getCompanyTypes().get(items.getSelectedCompanyType()).getCompanyType());
//
//                getListener().OnItemUpdate(CompanyDetails.COMPANYADDRESS);
//            }
//            else
//                getType().GetSelectCompanyType().setTextColor(Color.RED);
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

    private void OpenType()
    {
        ScrollToPosition(CompanyDetails.COMPANYTYPE);
        getBtnPost().setVisibility(View.GONE);
        getType().GetSelectCompanyType().setTextColor(Color.BLACK);
        getType().GetCnstEdit().setVisibility(View.GONE);
        getType().GetCnstSave().setVisibility(View.VISIBLE);
    }
}
