package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class CompanyContactViewHolderList extends RecyclerView.ViewHolder
{
    private EditText edtContact;
    private TextView txtContact;
    private CheckBox chkOnlineRecruitment;

    public CompanyContactViewHolderList(View v)
    {
        super(v);

        edtContact = (EditText) v.findViewById(R.id.edtContact);
        txtContact = (TextView) v.findViewById(R.id.txtContact);
        chkOnlineRecruitment = (CheckBox) v.findViewById(R.id.chkOnlineRecruitment);
    }

    public EditText getEdtContact()
    {
        return edtContact;
    }

    public TextView getTxtContact()
    {
        return txtContact;
    }

    public CheckBox getChkOnlineRecruitment()
    {
        return chkOnlineRecruitment;
    }
}
