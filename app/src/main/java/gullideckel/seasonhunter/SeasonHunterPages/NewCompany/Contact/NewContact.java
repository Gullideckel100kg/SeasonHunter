package gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Contact;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.R;

public class NewContact
{
    private LayoutInflater inflater;
    private ImageButton imbMinus;
    private EditText edtContact;
    private TextView txtPlusPhone;
    private LinearLayout linContact;

    public NewContact(LayoutInflater inflater, LinearLayout linContact)
    {
        this.inflater = inflater;
        this.linContact = linContact;
    }

    protected View createView()
    {
        View v = inflater.inflate(R.layout.frag_new_company_contact, null);

        imbMinus = (ImageButton) v.findViewById(R.id.imbNewMinusContact);
        edtContact = (EditText) v.findViewById(R.id.edtNewContact);
        txtPlusPhone = (TextView) v.findViewById(R.id.txtNewContactPhonePlus);

        imbMinus.setOnClickListener(Minus);

        imbMinus.setTag(v);

        if(linContact.getChildCount() == 0)
            imbMinus.setVisibility(View.GONE);

        return v;
    }

    private View.OnClickListener Minus = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            for(int i = 0; i < linContact.getChildCount(); i++)
                if(linContact.getChildAt(i).equals(v.getTag()))
                    linContact.removeView(linContact.getChildAt(i));
        }
    };

    public List<String> getContacts()
    {
        List<String> contacts = new ArrayList<>();
        for(int i = 0; i < linContact.getChildCount(); i++)
        {
            EditText text = (EditText)linContact.getChildAt(i).findViewById(R.id.edtNewContact);
            if(!text.getText().toString().isEmpty())
                contacts.add(text.getText().toString());
        }

        return contacts;
    }

    protected EditText getEdtContact()
    {
        return edtContact;
    }

    protected TextView getTxtPlusPhone()
    {
        return txtPlusPhone;
    }

}
