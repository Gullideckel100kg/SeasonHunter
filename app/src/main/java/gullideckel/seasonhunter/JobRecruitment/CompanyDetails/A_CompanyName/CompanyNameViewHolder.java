package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.A_CompanyName;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import gullideckel.seasonhunter.R;

public class CompanyNameViewHolder extends RecyclerView.ViewHolder
{
    private EditText mEdtCompanyName;
    private TextView mTxtCompanyName;
    private Button mSave;
    private ImageButton mEdit;

    private ConstraintLayout mCnstEdit;
    private ConstraintLayout mCnstSave;

    public CompanyNameViewHolder(View v)
    {
        super(v);

        mEdtCompanyName = (EditText) v.findViewById(R.id.edtAddCompanyName);
        mTxtCompanyName = (TextView) v.findViewById(R.id.txtAddCompanyName);
        mSave = (Button) v.findViewById(R.id.btnSaveCompanyName);
        mEdit = (ImageButton) v.findViewById(R.id.ibtnEditCompanyName);

        mCnstEdit = (ConstraintLayout) v.findViewById(R.id.cnstEditCompanyName);
        mCnstSave = (ConstraintLayout) v.findViewById(R.id.cnstSavedCompanyName);

    }


    public boolean IsSaved()
    {
        return  false;
    }

    public ConstraintLayout GetCnstEdit()
    {
        return mCnstEdit;
    }

    public ConstraintLayout GetCnstSave()
    {
        return mCnstSave;
    }

    public EditText GetEdtCompanyName()
    {
        return mEdtCompanyName;
    }

    public TextView GetTxtCompanyName()
    {
        return mTxtCompanyName;
    }

    public Button GetBtnSave()
    {
        return mSave;
    }

    public ImageButton GetIBtnEdit()
    {
        return mEdit;
    }
}
