package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Contact;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class ContactRow
{
    private LayoutInflater inflater;
    private LinearLayout linContact;

    private TextView txtContact;
    private ImageButton imbMinus;

    public ContactRow(LayoutInflater inflater, LinearLayout linConatct)
    {
        this.inflater = inflater;
        this.linContact = linConatct;
    }

    public View getView(String contact)
    {

        View v = inflater.inflate(R.layout.frag_edit_contact_row, null);

        txtContact = (TextView) v.findViewById(R.id.txtEditContact);
        imbMinus = (ImageButton) v.findViewById(R.id.imbEditMinusContact);

        txtContact.setText(contact);
        imbMinus.setTag(v);
        imbMinus.setOnClickListener(Minus);

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
}
