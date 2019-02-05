package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.F_CompanyBenefits;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyBenefits;

public class CompanyBenefitsConfi extends CompanyDetailsBase
{
    private CompanyBenefitsAdapter adapter;
    private CompanyBenefits benefits;

    public CompanyBenefitsConfi(RecyclerView.ViewHolder vh, ICompanyDetails listener, CompanyDetails detailsObject)
    {
        super(vh, listener, detailsObject);
        benefits = getObjectAtPosition(CompanyBenefits.class);
    }


    public void Confi()
    {
        getBenefit().getCnstFeatures().setVisibility(View.VISIBLE);

        getBenefit().getEdtFeatures().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                benefits.setFeatures(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

//        if(getBenefit().getRelBenefits().getVisibility() == View.INVISIBLE || getBenefit().getRelBenefits().getVisibility() == View.GONE)
//        {
//            FillBenefitsExample();
//
//            adapter = new CompanyBenefitsAdapter(benefits.getCompanyBenefits());
//            StaticMethod.RemoveKeyPad((Activity) getContext());
//
//            getBenefit().getRcylBenefitsEdit().setLayoutManager(new GridLayoutManager(getContext(), 1));
//            getBenefit().getRcylBenefitsEdit().setAdapter(adapter);
//
//            OpenBenefits();
//
//            getBenefit().getBtnSave().setOnClickListener(Save);
//            getBenefit().getiBtnEdit().setOnClickListener(Edit);
//
//            getBenefit().getRelBenefits().setVisibility(View.VISIBLE);
//        }


//    @Override
//    protected void OnKeyPadDisappearing()
//    {
//        super.OnKeyPadDisappearing();
//        if(getBenefit().getCnstBenefitsEdit().getVisibility() == View.VISIBLE)
//        {
//            getBenefit().getCnstBenefitsEdit().setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            ScrollToPositionDelayed(CompanyDetails.COMPANYBENEFITS);
//        }
//    }
//
//    private View.OnClickListener Save = new View.OnClickListener() {
//        @Override
//        public void onClick(View v)
//        {
//            SetBenefitList();
//
//            getBenefit().getRcylBenefitsSaved().SetAutFitGridLayout(getContext(), null);
//            getBenefit().getRcylBenefitsSaved().setAdapter(new CompanyBenefitsAdapterSaved(benefits.getCompanyBenefitsSaved() ,getContext()));
//
//            getLayoutManager().setScrollEnabled(true);
//
//            ViewVisibility(View.GONE, View.VISIBLE);
//
//            getListener().OnItemUpdate(CompanyDetails.POST);
//        }
//    };
//
//    private View.OnClickListener Edit = new View.OnClickListener() {
//        @Override
//        public void onClick(View v)
//        {
//
//            getBenefit().getRcylBenefitsEdit().setLayoutManager(new GridLayoutManager(getContext(),1));
//            getBenefit().getRcylBenefitsEdit().setAdapter(adapter);
//
//            OpenBenefits();
//        }
//    };
//
//    private void OpenBenefits()
//    {
//        ViewVisibility(View.VISIBLE, View.GONE);
//
//        getBtnPost().setVisibility(View.GONE);
//
//        ScrollToPositionDelayed(CompanyDetails.COMPANYBENEFITS);
//        getLayoutManager().setScrollEnabled(false);
//    }
//
//    private void SetBenefitList()
//    {
//        benefits.setCompanyBenefitsSaved(new ArrayList<CompanyBenefits.CompanyBenefit>());
//        for(CompanyBenefits.CompanyBenefit benefit : benefits.getCompanyBenefits())
//        {
//            if(benefit.isSelected())
//                benefits.getCompanyBenefitsSaved().add(benefit);
//        }
//    }
//
//    private void ViewVisibility(int visEdit, int visSaved)
//    {
//        getBenefit().getCnstBenefitsEdit().setVisibility(visEdit);
//        getBenefit().getCnstBenefitsSaved().setVisibility(visSaved);
//    }
//
//    //TODO:Think about server or local save and delete example
//    private void FillBenefitsExample()
//    {
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.offersfood, R.string.offer_food));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.offersdrinks, R.string.offer_dring));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.sleepingplace, R.string.sleepingplace));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.campgroung, R.string.campgroung));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.shower, R.string.shower));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.toilet, R.string.toilet));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.languagecourse, R.string.language_course));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.argicourse, R.string.agriculter_course));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.cookingcourse, R.string.cooking_course));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.kitchen, R.string.kitchen));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.fridge, R.string.fridge));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.storage, R.string.storage_room));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.communityroom, R.string.community_area));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.wifi, R.string.wifi));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.animalallowd, R.string.animals_allowed));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.barbecue, R.string.barbecue));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.laundry, R.string.laundry));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.power, R.string.power));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.nicescenery, R.string.scenery));
//        benefits.getCompanyBenefits().add(GetBenefit(R.drawable.orginc, R.string.organic));
//    }
//
//    private CompanyBenefits.CompanyBenefit GetBenefit(int drawable, int text)
//    {
//        CompanyBenefits.CompanyBenefit benefit = new CompanyBenefits.CompanyBenefit();
//        benefit.setBenefitLogo(BitmapFactory.decodeResource(getContext().getResources(), drawable));
//        benefit.setBenefit(getContext().getText(text).toString());
//
//        return  benefit;
//    }
}
