package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyData;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.Objects.Job.CompanyContact;
import gullideckel.seasonhunter.R;

public class InitCompanyContacts
{

    private LayoutInflater inflater;
    private Context context;

    public InitCompanyContacts(LayoutInflater inflater, Context context)
    {
        this.inflater = inflater;
        this.context = context;
    }

    public void InitContact(LinearLayout linContacts, CompanyContact contact)
    {
        if(contact.getPhoneNumber().size() > 0)
            for(String phone : contact.getPhoneNumber())
                linContacts.addView(getViewPhone(phone));

        if(contact.getEmail().size() > 0)
            for(String email : contact.getEmail())
                linContacts.addView(getViewEmail(email));

        if(!contact.getWebsite().isEmpty())
            linContacts.addView(getViewWebSite(contact.getWebsite(), contact.isOnlineRecruitment()));
    }


    private View getViewPhone(String contact)
    {
        View v = inflater.inflate(R.layout.frag_company_data_contact_view, null);

        ImageView imgPhone = (ImageView) v.findViewById(R.id.imgContact);
        TextView txtPhone = (TextView) v.findViewById(R.id.txtContact);

        imgPhone.setImageResource(R.drawable.phone);
        txtPhone.setText("+" + contact);
        Linkify.addLinks(txtPhone, Linkify.PHONE_NUMBERS);

        return v;
    }

    private View getViewEmail(String email)
    {
        View v = inflater.inflate(R.layout.frag_company_data_contact_view, null);

        ImageView imgEmail = (ImageView) v.findViewById(R.id.imgContact);
        TextView txtEmail = (TextView) v.findViewById(R.id.txtContact);

        imgEmail.setImageResource(R.drawable.email);
        txtEmail.setText(email);
        Linkify.addLinks(txtEmail, Linkify.EMAIL_ADDRESSES);

        return v;
    }

    private View getViewWebSite(String website, boolean isRecruitment)
    {
        View v = inflater.inflate(R.layout.frag_company_data_contact_view, null);

        ImageView imgWebsite = (ImageView) v.findViewById(R.id.imgContact);
        TextView txtWebsite = (TextView) v.findViewById(R.id.txtContact);
        TextView txtOnlineRecruitment = (TextView) v.findViewById(R.id.txtContactOnlineRecruitment);

        imgWebsite.setImageResource(R.drawable.website);
        txtWebsite.setText(website);
        Linkify.addLinks(txtWebsite, Linkify.WEB_URLS);

        if(isRecruitment)
            txtOnlineRecruitment.setText(context.getText(R.string.online_recruitment));

        return v;

    }









}
