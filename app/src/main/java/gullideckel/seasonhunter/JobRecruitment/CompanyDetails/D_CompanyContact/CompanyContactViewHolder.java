package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class CompanyContactViewHolder extends RecyclerView.ViewHolder
{
    private ConstraintLayout cnstContactEdit;
    private ConstraintLayout cnstContactSaved;

    private Button btnAddPhone;
    private Button btnAddEmail;

    private RecyclerView lstPhone;
    private RecyclerView lstEmail;

    private EditText edtWebsite;
    private CheckBox chkOnlineRecruitment;

    private Button btnSave;

    private TextView txtPhone;
    private TextView txtEmail;
    private TextView txtWebsite;

    private ImageButton iBtnEdit;

    public CompanyContactViewHolder(View vh)
    {
        super(vh);

        cnstContactEdit = (ConstraintLayout) vh.findViewById(R.id.cnstContactEdit);
        cnstContactSaved = (ConstraintLayout) vh.findViewById(R.id.cnstContactSaved);

        btnAddPhone = (Button) vh.findViewById(R.id.btnAddPhone);
        btnAddEmail = (Button) vh.findViewById(R.id.btnAddEmail);

        lstPhone = (RecyclerView) vh.findViewById(R.id.lstPhone);
        lstEmail = (RecyclerView) vh.findViewById(R.id.lstEmail);

        edtWebsite = (EditText) vh.findViewById(R.id.edtWebiste);
        chkOnlineRecruitment = (CheckBox) vh.findViewById(R.id.chkOnlineRecruitment);

        btnSave = (Button) vh.findViewById(R.id.btnSaveContact);

        txtPhone = (TextView) vh.findViewById(R.id.txtPhone);
        txtEmail = (TextView) vh.findViewById(R.id.txtEmail);
        txtWebsite = (TextView) vh.findViewById(R.id.txtWebsite);

        iBtnEdit = (ImageButton) vh.findViewById(R.id.ibtnEditContact);
    }

    public ConstraintLayout GetCnstContactEdit()
    {
        return cnstContactEdit;
    }

    public ConstraintLayout GetCnstContactSaved()
    {
        return cnstContactSaved;
    }

    public Button GetBtnAddEmail()
    {
        return btnAddEmail;
    }

    public Button GetBtnAddPhone()
    {
        return btnAddPhone;
    }

    public RecyclerView GetLstEmail()
    {
        return lstEmail;
    }

    public RecyclerView GetLstPhone()
    {
        return lstPhone;
    }

    public EditText GetEdtWebsite()
    {
        return edtWebsite;
    }

    public CheckBox GetChkOnlineRecruitment()
    {
        return chkOnlineRecruitment;
    }

    public Button GetBtnSave()
    {
        return btnSave;
    }

    public TextView GetTxtPhone()
    {
        return txtPhone;
    }

    public TextView GetTxtEmail()
    {
        return txtEmail;
    }

    public TextView GetTxtWebsite()
    {
        return txtWebsite;
    }

    public ImageButton GetiBtnEdit()
    {
        return iBtnEdit;
    }
}
