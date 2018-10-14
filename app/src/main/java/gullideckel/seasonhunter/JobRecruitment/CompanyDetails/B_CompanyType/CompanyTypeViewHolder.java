package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import gullideckel.seasonhunter.R;

public class CompanyTypeViewHolder extends RecyclerView.ViewHolder
{

    private ConstraintLayout mCnstSave;
    private ConstraintLayout mCnstEdit;

    private ListView mLstCompanyType;
    private Button mBtnSave;

    private TextView mTxtCompanyType;
    private ImageButton mIBtnEdit;

    public CompanyTypeViewHolder(View v)
    {
        super(v);

        mCnstSave = (ConstraintLayout) v.findViewById(R.id.cnstSaveCompanyType);
        mCnstEdit = (ConstraintLayout) v.findViewById(R.id.cnstEditCompanyType);

        mLstCompanyType = (ListView) v.findViewById(R.id.lstAddCompanyType);
        mBtnSave = (Button) v.findViewById(R.id.btnSaveCompanyType);

        mTxtCompanyType = (TextView) v.findViewById(R.id.txtAddCompanyType);
        mIBtnEdit = (ImageButton) v.findViewById(R.id.ibtnEditCompanyType);
    }

    public ConstraintLayout GetCnstEdit()
    {
        return mCnstEdit;
    }

    public ConstraintLayout GetCnstSave()
    {
        return mCnstSave;
    }

    public ListView GetLstCompanyType()
    {
        return mLstCompanyType;
    }

    public Button GetBtnSave()
    {
        return mBtnSave;
    }

    public TextView GetTxtCompanyType()
    {
        return mTxtCompanyType;
    }

    public ImageButton GetIBtnEdit()
    {
        return mIBtnEdit;
    }
}
