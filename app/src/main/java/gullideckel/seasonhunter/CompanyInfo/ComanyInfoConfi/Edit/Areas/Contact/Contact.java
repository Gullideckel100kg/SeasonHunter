package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Contact;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Objects.Job.CompanyContact;
import gullideckel.seasonhunter.R;

public class Contact
{
    private LayoutInflater inflater;
    private CompanyContact contact;

    private LinearLayout linPhone;
    private EditText edtPhone;
    private ImageButton imbPhone;

    private LinearLayout linEmail;
    private EditText edtEmail;
    private ImageButton imbEmail;

    private TextView txtWebsite;
    private EditText edtWebsite;
    private CheckBox chkOnlineRecruitment;

    ContactRow rowPhone;
    ContactRow rowEmail;

    public Contact(LayoutInflater inflater, CompanyContact contact)
    {
        this.inflater = inflater;
        this.contact = contact;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_contact, null);

        linPhone = v.findViewById(R.id.linEditPhone);
        edtPhone = v.findViewById(R.id.edtEditNewPhone);
        imbPhone = v.findViewById(R.id.imbEditPhonePlus);

        linEmail = v.findViewById(R.id.linEditEmail);
        edtEmail = v.findViewById(R.id.edtEditNewEmail);
        imbEmail = v.findViewById(R.id.imbEditEmailPlus);

        txtWebsite = (TextView) v.findViewById(R.id.txtEditWebsite);
        edtWebsite = (EditText) v.findViewById(R.id.edtEditWebsite);
        chkOnlineRecruitment = (CheckBox) v.findViewById(R.id.chkEditOnlineRecruitment);

        imbPhone.setOnClickListener(PlusPhone);
        imbEmail.setOnClickListener(PlusEmail);


        rowPhone = new ContactRow(inflater, linPhone);
        for(String phone : contact.getPhoneNumber())
            linPhone.addView(rowPhone.getView(phone));

        rowEmail = new ContactRow(inflater, linEmail);
        for(String email : contact.getEmail())
            linEmail.addView(rowEmail.getView(email));

        txtWebsite.setText(contact.getWebsite());
        chkOnlineRecruitment.setChecked(contact.isOnlineRecruitment());

        return v;
    }

    private View.OnClickListener PlusPhone = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(!edtPhone.getText().toString().isEmpty())
                linPhone.addView(rowPhone.getView(edtPhone.getText().toString()));
        }
    };

    private View.OnClickListener PlusEmail = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(!edtEmail.getText().toString().isEmpty())
                linEmail.addView(rowEmail.getView(edtEmail.getText().toString()));
        }
    };

    public CompanyContact getContact()
    {
        CompanyContact contact = new CompanyContact();

        List<String> phones = new ArrayList<>();
        for(int i = 0; i < linPhone.getChildCount(); i++)
            phones.add(((TextView)linPhone.getChildAt(i).findViewById(R.id.txtEditContact)).getText().toString());

        contact.setPhoneNumber(phones);

        List<String> emails = new ArrayList<>();
        for(int i = 0; i < linEmail.getChildCount(); i++)
            emails.add(((TextView)linEmail.getChildAt(i).findViewById(R.id.txtEditContact)).getText().toString());

        contact.setEmail(emails);

        if(!edtWebsite.getText().toString().isEmpty())
            contact.setWebsite(edtWebsite.getText().toString());

        contact.setOnlineRecruitment(chkOnlineRecruitment.isChecked());

        if(phones.size() == 0 && emails.size() == 0 && edtWebsite.getText().toString().isEmpty() && chkOnlineRecruitment.isChecked() == contact.isOnlineRecruitment())
            return null;

        return contact;
    }

}
