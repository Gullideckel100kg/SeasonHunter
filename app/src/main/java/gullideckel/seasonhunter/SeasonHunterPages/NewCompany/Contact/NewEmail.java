package gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Contact;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class NewEmail extends NewContact
{
    public NewEmail(LayoutInflater inflater, LinearLayout lin)
    {
        super(inflater, lin);
    }

    public View createEmailView()
    {
        View v = createView();

        getEdtContact().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        return v;
    }

    public String getContactText()
    {
        return getEdtContact().getText().toString();
    }
}
