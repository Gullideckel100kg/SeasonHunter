package gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Contact;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import gullideckel.seasonhunter.R;

public class NewPhone extends NewContact
{
    private Context context;

    public NewPhone(LayoutInflater inflater, Context context, LinearLayout lin)
    {
        super(inflater, lin);
        this.context = context;
    }

    public View createPhoneView()
    {
        View v = createView();

        getEdtContact().setInputType(InputType.TYPE_CLASS_PHONE);
        getEdtContact().setHint(context.getString(R.string.new_phone_hint));
        getTxtPlusPhone().setVisibility(View.VISIBLE);

        return v;
    }
}
