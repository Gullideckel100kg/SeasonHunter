package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.D_CompanyContact;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class CompanyContactViewHolder extends RecyclerView.ViewHolder
{
    private RelativeLayout relContact;

    private ScrollView cnstContactEdit;
    private ConstraintLayout cnstContactSaved;

    private ImageButton iBtnAddPhone;
    private ImageButton iBtnAddEmail;

    private RecyclerView lstPhone;
    private RecyclerView lstEmail;

    private EditText edtWebsite;
    private CheckBox chkOnlineRecruitment;

    private Button btnSave;

    private TextView txtPhone;
    private TextView txtEmail;
    private TextView txtWebsite;

    private ImageButton iBtnEdit;

    private TextView txtWrongInput;

    public TextView GetTxtWrongInput()
    {
        return txtWrongInput;
    }

    public CompanyContactViewHolder(View vh)
    {
        super(vh);

        relContact = (RelativeLayout) vh.findViewById(R.id.relContact);

        cnstContactEdit = (ScrollView) vh.findViewById(R.id.cnstContactEdit);
        cnstContactSaved = (ConstraintLayout) vh.findViewById(R.id.cnstContactSaved);

        iBtnAddPhone = (ImageButton) vh.findViewById(R.id.iBtnAddPhone);
        iBtnAddEmail = (ImageButton) vh.findViewById(R.id.iBtnAddEmail);

        lstPhone = (RecyclerView) vh.findViewById(R.id.lstPhone);
        lstEmail = (RecyclerView) vh.findViewById(R.id.lstEmail);

        edtWebsite = (EditText) vh.findViewById(R.id.edtWebiste);
        chkOnlineRecruitment = (CheckBox) vh.findViewById(R.id.chkOnlineRecruitment);

        btnSave = (Button) vh.findViewById(R.id.btnSaveContact);

        txtPhone = (TextView) vh.findViewById(R.id.txtPhone);
        txtEmail = (TextView) vh.findViewById(R.id.txtEmail);
        txtWebsite = (TextView) vh.findViewById(R.id.txtWebsite);

        iBtnEdit = (ImageButton) vh.findViewById(R.id.ibtnEditContact);

        txtWrongInput = (TextView) vh.findViewById(R.id.txtContactWrongInput);

    }

    public RelativeLayout getRelContact()
    {
        return relContact;
    }

    public ScrollView GetCnstContactEdit()
    {
        return cnstContactEdit;
    }

    public ConstraintLayout GetCnstContactSaved()
    {
        return cnstContactSaved;
    }

    public ImageButton getiBtnAddPhone()
    {
        return iBtnAddPhone;
    }

    public ImageButton getiBtnAddEmail()
    {
        return iBtnAddEmail;
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
