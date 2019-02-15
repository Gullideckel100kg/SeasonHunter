package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetails;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.Job.CompanyContact;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class CompanyContactConfi extends CompanyDetailsBase
{
    private CompanyContactAdapter adapterPhone;
    private CompanyContactAdapter adapterEmail;

    private List<String> lstPhone;
    private List<String> lstEmail;

    private CompanyContact item;

    public CompanyContactConfi(RecyclerView.ViewHolder vh, ICompanyDetails listener, CompanyDetails detailsObject)
    {
        super(vh, listener, detailsObject);
        item = getObjectAtPosition(CompanyContact.class);
    }

    public void Confi()
    {
        if(getContact().getRelContact().getVisibility() == View.INVISIBLE || getContact().getRelContact().getVisibility() == View.GONE)
        {
            OpenContact();

            lstPhone = new ArrayList<>();
            lstPhone.add(new String());
            lstEmail = new ArrayList<>();
            lstEmail.add(new String());

            adapterPhone = new CompanyContactAdapter(lstPhone, getContext().getString(R.string.phone), InputType.TYPE_CLASS_PHONE);
            adapterEmail = new CompanyContactAdapter(lstEmail, getContext().getString(R.string.email), InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            getContact().getiBtnAddPhone().setOnClickListener(AddPhone);
            getContact().getiBtnAddEmail().setOnClickListener(AddEmail);

            getContact().GetBtnSave().setOnClickListener(Save);
            getContact().GetiBtnEdit().setOnClickListener(Edit);

            getContact().GetLstPhone().setLayoutManager(new LinearLayoutManager(getContext()));
            getContact().GetLstEmail().setLayoutManager(new LinearLayoutManager(getContext()));

            getContact().GetLstPhone().setAdapter(adapterPhone);
            getContact().GetLstEmail().setAdapter(adapterEmail);

            getContact().getRelContact().setVisibility(View.VISIBLE);
        }
    }



    private View.OnClickListener AddPhone = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            lstPhone.add(new String());
            adapterPhone.notifyItemInserted(lstPhone.size() - 1);
        }
    };

    private View.OnClickListener AddEmail = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            lstEmail.add(new String());
            adapterEmail.notifyItemInserted(lstEmail.size() - 1);
        }
    };

    @Override
    protected void OnKeyPadDisappearing()
    {
        super.OnKeyPadDisappearing();
        if(getContact().GetCnstContactEdit().getVisibility() == View.VISIBLE)
        {
            getContact().GetCnstContactEdit().setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ScrollToPositionDelayed(CompanyDetails.COMPANYCONTACT);
        }
    }

    private View.OnClickListener Save = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(CorrectInput())
            {
                StringPhone();
                StringEmail();
                StringWebsite();

                StaticMethod.RemoveKeyPad((Activity) getContext());

                getContact().GetCnstContactEdit().setVisibility(View.GONE);
                getContact().GetCnstContactSaved().setVisibility(View.VISIBLE);

                getListener().OnItemUpdate(CompanyDetails.COMPANYJOBS);
                getContact().GetTxtWrongInput().setVisibility(View.GONE);

                item.setWebsite(getContact().GetEdtWebsite().getText().toString());

                item.setOnlineRecruitment(getContact().GetChkOnlineRecruitment().isChecked());

                getLayoutManager().setScrollEnabled(true);
            }
            else
                getContact().GetTxtWrongInput().setVisibility(View.VISIBLE);
        }
    };

    private void StringPhone()
    {
        getContact().GetTxtPhone().setVisibility(View.GONE);

        StringBuilder sb = new StringBuilder();
        item.getPhoneNumber().clear();
        for(int i = 0; i < lstPhone.size(); i++)
        {
            if(!lstPhone.get(i).isEmpty())
            {
                if(i > 0)
                    sb.append("\n" + getContext().getString(R.string.phone) + " " + lstPhone.get(i));
                else
                    sb.append(getContext().getString(R.string.phone) + " " + lstPhone.get(i));

                item.getPhoneNumber().add(lstPhone.get(i));
            }
        }
        if(!sb.toString().isEmpty())
        {
            getContact().GetTxtPhone().setText(sb);
            getContact().GetTxtPhone().setVisibility(View.VISIBLE);
        }

    }

    private void StringEmail()
    {
        getContact().GetTxtEmail().setVisibility(View.GONE);

        StringBuilder sb = new StringBuilder();
        item.getEmail().clear();
        for(int i = 0; i < lstEmail.size(); i++)
        {
            if(!lstEmail.get(i).isEmpty())
            {
                if(i > 0)
                    sb.append("\n" + getContext().getString(R.string.email) + " " + lstEmail.get(i));
                else
                    sb.append(getContext().getString(R.string.email) + " " + lstEmail.get(i));

                item.getEmail().add(lstEmail.get(i));
            }
        }
        if(!sb.toString().isEmpty())
        {
            getContact().GetTxtEmail().setText(sb);
            getContact().GetTxtEmail().setVisibility(View.VISIBLE);
        }
    }

    private void StringWebsite()
    {
        getContact().GetTxtWebsite().setVisibility(View.GONE);

        if(!getContact().GetEdtWebsite().getText().toString().isEmpty())
        {
            item.setWebsite(getContact().GetEdtWebsite().getText().toString());
            if(getContact().GetChkOnlineRecruitment().isChecked())
            {
                getContact().GetTxtWebsite().setText(getContext().getString(R.string.website) + " " +
                        getContact().GetEdtWebsite().getText() + " (" + getContext().getString(R.string.online_recruitment) + ")");
                item.setOnlineRecruitment(true);
            }
            else
            {
                getContact().GetTxtWebsite().setText(getContext().getString(R.string.website) + " " +
                        getContact().GetEdtWebsite().getText());
            }
            getContact().GetTxtWebsite().setVisibility(View.VISIBLE);
        }
    }

    private View.OnClickListener Edit = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            OpenContact();
        }
    };

    private void OpenContact()
    {
        getContact().GetCnstContactEdit().setVisibility(View.VISIBLE);
        getContact().GetCnstContactSaved().setVisibility(View.GONE);

        ScrollToPositionDelayed(CompanyDetails.COMPANYCONTACT);
    }


    //TODO Check if phone number is correct, email input correct and website correct, Method can be looked up at google
    private boolean CorrectInput()
    {
        for(int i = 0; i < lstPhone.size(); i ++)
        {
            if(!lstPhone.get(i).isEmpty())
                return true;
        }

        for(int i = 0; i < lstEmail.size(); i ++)
        {
            if(!lstEmail.get(i).isEmpty())
                return true;
        }

        if(!getContact().GetEdtWebsite().getText().toString().isEmpty())
            return true;

        return false;
    }
}
