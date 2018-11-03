package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.CompanyDetailsBase;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.ComplexCompanyDetailsAdapter;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyJob;
import gullideckel.seasonhunter.R;

public class CompanyContactConfi extends CompanyDetailsBase
{
    private CompanyContactAdapter adapterPhone;
    private CompanyContactAdapter adapterEmail;

    private List<String> lstPhone;
    private List<String> lstEmail;

    private CompanyContact item;

    //TODO: Set String in String value folder
    public CompanyContactConfi(RecyclerView.ViewHolder vh, Context context, ICompanyDetails listener, CompanyContact item)
    {
        super(vh, context, listener);

        lstPhone = new ArrayList<>();
        lstEmail = new ArrayList<>();

        this.item = item;

        adapterPhone = new CompanyContactAdapter(lstPhone, "Phone");
        adapterEmail = new CompanyContactAdapter(lstEmail, "Email");
    }

    public void Confi()
    {
        getContact().GetBtnAddPhone().setOnClickListener(AddPhone);
        getContact().GetBtnAddEmail().setOnClickListener(AddEmail);

        getContact().GetBtnSave().setOnClickListener(Save);
        getContact().GetiBtnEdit().setOnClickListener(Edit);

        getContact().GetLstPhone().setLayoutManager(new LinearLayoutManager(getContext()));
        getContact().GetLstEmail().setLayoutManager(new LinearLayoutManager(getContext()));

        getContact().GetLstPhone().setAdapter(adapterPhone);
        getContact().GetLstEmail().setAdapter(adapterEmail);
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

    //TODO: Check for correct user input. Minimum one Contact
    private View.OnClickListener Save = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            item = new CompanyContact();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < lstPhone.size(); i++)
            {
                if(i > 0)
                    sb.append("\n" + getContext().getString(R.string.phone) + " " + lstPhone.get(i));
                else
                    sb.append(getContext().getString(R.string.phone) + " " + lstPhone.get(i));
                item.SetPhoneNumber(lstPhone.get(i));
            }
            if(!sb.toString().isEmpty())
                getContact().GetTxtPhone().setText(sb);

            sb = new StringBuilder();
            for(int i = 0; i < lstEmail.size(); i++)
            {
                if(i > 0)
                    sb.append("\n" + getContext().getString(R.string.email) + " " + lstEmail.get(i));
                else
                    sb.append(getContext().getString(R.string.email) + " " + lstEmail.get(i));
                item.SetEmail(lstEmail.get(i));
            }
            if(!sb.toString().isEmpty())
                getContact().GetTxtEmail().setText(sb);

            if(!getContact().GetEdtWebsite().getText().toString().isEmpty())
            {
                item.SetWebsite(getContact().GetEdtWebsite().getText().toString());
                if(getContact().GetChkOnlineRecruitment().isChecked())
                {
                    getContact().GetTxtWebsite().setText(getContext().getString(R.string.website) + " " +
                            getContact().GetEdtWebsite().getText() + " (" + getContext().getString(R.string.online_recruitment) + ")");
                    item.SetOnlineRecruitment(true  );
                }
                else
                {
                    getContact().GetTxtWebsite().setText(getContext().getString(R.string.website) + " " +
                            getContact().GetEdtWebsite().getText());
                }

            }

            getContact().GetCnstContactEdit().setVisibility(View.GONE);
            getContact().GetCnstContactSaved().setVisibility(View.VISIBLE);

            getListener().OnItemUpdate(ComplexCompanyDetailsAdapter.COMPANYCONTACT);
        }
    };

    private View.OnClickListener Edit = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            getContact().GetCnstContactEdit().setVisibility(View.VISIBLE);
            getContact().GetCnstContactSaved().setVisibility(View.GONE);
        }
    };
}
