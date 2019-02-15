package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.B_CompanyType;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class CompanyTypeViewHolder extends RecyclerView.ViewHolder
{
    private RelativeLayout relType;



    private ConstraintLayout mCnstSave;
    private ConstraintLayout mCnstEdit;

    private TextView mSelectCompanyType;

    private RecyclerView mRclyCompanyType;
    private Button mBtnSave;

    private ImageView mImgCompanyLogo;
    private TextView mTxtCompanyType;
    private ImageButton mIBtnEdit;

    public CompanyTypeViewHolder(View v)
    {
        super(v);

        relType = (RelativeLayout) v.findViewById(R.id.relType);

        mCnstSave = (ConstraintLayout) v.findViewById(R.id.cnstSaveCompanyType);
        mCnstEdit = (ConstraintLayout) v.findViewById(R.id.cnstEditCompanyType);

        mRclyCompanyType = (RecyclerView) v.findViewById(R.id.rclyAddCompanyType);
        mBtnSave = (Button) v.findViewById(R.id.btnSaveCompanyType);

        mTxtCompanyType = (TextView) v.findViewById(R.id.txtAddCompanyType);
        mIBtnEdit = (ImageButton) v.findViewById(R.id.ibtnEditCompanyType);
        mImgCompanyLogo = (ImageView) v.findViewById(R.id.imgSaveCompanyTypeLogo);

        mSelectCompanyType = (TextView) v.findViewById(R.id.txtSelectCompanyType);

    }

    public RelativeLayout getRelType()
    {
        return relType;
    }

    public ConstraintLayout GetCnstEdit()
    {
        return mCnstEdit;
    }

    public ConstraintLayout GetCnstSave()
    {
        return mCnstSave;
    }

    public RecyclerView GetRclyCompanyType()
    {
        return mRclyCompanyType;
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

    public ImageView GetImgCompanyLogo()
    {
        return  mImgCompanyLogo;
    }

    public TextView GetSelectCompanyType()
    {
        return mSelectCompanyType;
    }
}
