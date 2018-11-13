package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.F_CompanyBenefits;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CostumLayoutManager;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsObject;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.ComplexCompanyDetailsAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyBenefits;
import gullideckel.seasonhunter.R;

public class CompanyBenefitsConfi extends CompanyDetailsBase
{
    private CompanyBenefitsAdapter adapter;
    private CompanyBenefits benefits;

    public CompanyBenefitsConfi(RecyclerView.ViewHolder vh, ICompanyDetails listener, CompanyDetailsObject detailsObject)
    {
        super(vh, listener, detailsObject);
        benefits = getObjectAtPosition(CompanyBenefits.class);
    }


    public void Confi()
    {
        FillBenefitsExample();

        adapter = new CompanyBenefitsAdapter(benefits.getCompanyBenefits());

        getBenefit().getRcylBenefitsEdit().setLayoutManager(new GridLayoutManager(getContext(), 1));
        getBenefit().getRcylBenefitsEdit().setAdapter(adapter);

        ScrollToPositionDelayed(ComplexCompanyDetailsAdapter.COMPANYBENEFITS);
        getLayoutManager().setScrollEnabled(false);

        getBenefit().getBtnSave().setOnClickListener(Save);
        getBenefit().getiBtnEdit().setOnClickListener(Edit);
    }

    private View.OnClickListener Save = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            SetBenefitList();

            getBenefit().getRcylBenefitsSaved().SetAutFitGridLayout(getContext(), null);
            getBenefit().getRcylBenefitsSaved().setAdapter(new CompanyBenefitsAdapterSaved(benefits.getCompanyBenefitsSaved() ,getContext()));

            getLayoutManager().setScrollEnabled(true);

            ViewVisibility(View.GONE, View.VISIBLE);

            getListener().OnItemUpdate(ComplexCompanyDetailsAdapter.POST);
        }
    };

    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            getBenefit().getRcylBenefitsEdit().setLayoutManager(new GridLayoutManager(getContext(),1));
            getBenefit().getRcylBenefitsEdit().setAdapter(adapter);

            ViewVisibility(View.VISIBLE, View.GONE);

            getBtnPost().setVisibility(View.GONE);

            ScrollToPositionDelayed(ComplexCompanyDetailsAdapter.COMPANYBENEFITS);
            getLayoutManager().setScrollEnabled(false);
        }
    };

    private void SetBenefitList()
    {
        benefits.setCompanyBenefitsSaved(new ArrayList<CompanyBenefits.CompanyBenefit>());
        for(CompanyBenefits.CompanyBenefit benefit : benefits.getCompanyBenefits())
        {
            if(benefit.isSelected())
                benefits.getCompanyBenefitsSaved().add(benefit);
        }
    }

    private void ViewVisibility(int visEdit, int visSaved)
    {
        getBenefit().getCnstBenefitsEdit().setVisibility(visEdit);
        getBenefit().getCnstBenefitsSaved().setVisibility(visSaved);
    }

    //TODO:Think about server or local save and delete example
    private void FillBenefitsExample()
    {
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.offersfood, R.string.offer_food));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.offersdrinks, R.string.offer_dring));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.sleepingplace, R.string.sleepingplace));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.campgroung, R.string.campgroung));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.shower, R.string.shower));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.toilet, R.string.toilet));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.dips, R.string.dips));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.languagecourse, R.string.language_course));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.argicourse, R.string.agriculter_course));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.cookingcourse, R.string.cooking_course));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.kitchen, R.string.kitchen));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.fridge, R.string.fridge));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.storage, R.string.storage_room));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.communityroom, R.string.community_area));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.wifi, R.string.wifi));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.animalallowd, R.string.animals_allowed));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.barbecue, R.string.barbecue));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.laundry, R.string.laundry));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.power, R.string.power));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.nicescenery, R.string.scenery));
        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.orginc, R.string.organic));
    }

    private CompanyBenefits.CompanyBenefit GetBenefit(int drawable, int text)
    {
        CompanyBenefits.CompanyBenefit benefit = new CompanyBenefits.CompanyBenefit();
        benefit.setBenefitLogo(BitmapFactory.decodeResource(getContext().getResources(), drawable));
        benefit.setBenefit(getContext().getText(text).toString());

        return  benefit;
    }
}
